package codegen;

import earth.EarthResult;
import earth.EarthUtils;
import parser.ast_helpers.StmtList;
import parser.ast_helpers.TypedIdent;
import parser.ast_helpers.TypedIdentList;
import parser.exprs.Expr;
import parser.stmts.*;
import sanity.EarthType;

import java.lang.classfile.ClassBuilder;
import java.lang.classfile.ClassFile;
import java.lang.classfile.Label;
import java.lang.classfile.TypeKind;
import java.lang.classfile.attribute.SourceFileAttribute;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static codegen.CodegenUtils.*;
import static java.lang.classfile.ClassFile.*;
import static java.lang.constant.ConstantDescs.*;
import static sanity.EarthType.Base.*;
import static sanity.EarthType.FuncType;

@SuppressWarnings("preview")
public class StmtCodegen {
	private final Path srcPath;
	private final ClassDesc thisClass;
	private ClassBuilder classBuilder;
	private Method currentMethod;
	static final Map<String, Method> methods = new HashMap<>();

	public StmtCodegen(String src) {
		srcPath = EarthUtils.removeExt(Path.of(src));
		thisClass = ClassDesc.of(srcPath.getFileName().toString());
	}

	public EarthResult<byte[]> generate(StmtList stmts) {
		byte[] result = ClassFile.of().build(thisClass, classBuilder -> {
			this.classBuilder = classBuilder;
			generateBuiltins();

			classBuilder
				.with(SourceFileAttribute.of(srcPath + ".earth"))
				.withFlags(ACC_PUBLIC | ACC_FINAL)
				.withMethodBody(INIT_NAME, MTD_void, ACC_PUBLIC, builder -> builder
					.aload(0)
					.invokespecial(CD_Object, INIT_NAME, MTD_void)
					.return_()
				)
				// static constructor
				.withMethodBody(CLASS_INIT_NAME, MTD_void, ACC_STATIC,
					builder -> {
						currentMethod = new Method(builder, MTD_void, thisClass);
						methods.put(CLASS_INIT_NAME, currentMethod);
						generateStmts(stmts);
						builder.return_();
					}
				);

		});
		return EarthResult.ok(result);
	}

	private void generateStmts(StmtList stmts) {
		stmts.forEach(this::generateStmt);
	}

	private void generateStmt(Stmt stmt) {
		switch (stmt) {
			case DeclStmt s -> generateDeclStmt(s);
			case FnDefStmt s -> generateFnDefStmt(s);
			case LoopStmt s -> generateLoopStmt(s);
			case ReassignStmt s -> generateReassignStmt(s);
			case UnnamedStmt s -> generateUnnamedStmt(s);
			case WhenStmt s -> generateWhenStmt(s);
			case YeetStmt s -> generateYeetStmt(s);
		}
	}

	private void generateDeclStmt(DeclStmt s) {
		String name = s.nameAndType().name().name();
		Expr toDeclare = s.value();
		ClassDesc desc = earthTypeToDesc(toDeclare.dataType());
		boolean isStatic = methods.get(CLASS_INIT_NAME).equals(currentMethod);

		if (toDeclare.dataType() == NadaType) {
			currentMethod.exprCodegen().loadExpr(toDeclare);
			return;
		}

		if (isStatic) {
			classBuilder.withField(name, desc, ACC_STATIC | ACC_PRIVATE);
			currentMethod.exprCodegen().loadExpr(toDeclare);
			currentMethod.builder().putstatic(thisClass, name, desc);

			ExprCodegen.classVariables
				.put(name, new ClassVariable(name, desc, thisClass));
			return;
		}

		currentMethod.exprCodegen().loadExpr(toDeclare);
		currentMethod = currentMethod.incrementSlot();
		int slot = currentMethod.slot();
		TypeKind typeKind = earthTypeToTypeKind(toDeclare.dataType());
		currentMethod.builder().storeLocal(typeKind, slot);

		currentMethod.exprCodegen().methodVariables
			.put(name,
				new MethodVariable(name, toDeclare.dataType(), typeKind, slot));
	}

	private void generateFnDefStmt(FnDefStmt s) {
		String fnName = s.name().name();
		if (fnName.equals("main")) { // special boy
			var mainDesc = MethodTypeDesc.of(CD_void, CD_String.arrayType());
			classBuilder.withMethodBody("main", mainDesc, ACC_STATIC | ACC_PUBLIC,
				builder -> {
					Method prevMethod = currentMethod;
					currentMethod = new Method(builder, mainDesc, thisClass);
					methods.put("main", currentMethod);

					// main has no parameters, so skip
					generateStmts(s.body());

					currentMethod = prevMethod;
				});
		}
		else {
			MethodTypeDesc methodDesc = createSignature(
				s.params(), s.returnType().dataType()
			);

			classBuilder.withMethodBody(fnName, methodDesc,
				ACC_STATIC | ACC_PRIVATE, builder -> {
					Method prevMethod = currentMethod;

					currentMethod = new Method(builder, methodDesc, thisClass);
					methods.put(fnName, currentMethod);

					// add the method parameters to the local methodVariables.
					// No need to add it on the jvm level because function parameters are
					// there by default.
					TypedIdentList params = s.params();
					for (int i = 0; i < params.size(); i++) {
						TypedIdent param = params.get(i);
						String name = param.name().name();
						EarthType paramType = param.type().dataType();

						TypeKind typeKind = earthTypeToTypeKind(paramType);

						currentMethod.exprCodegen().methodVariables
							.put(name, new MethodVariable(name, paramType, typeKind, i));
					}

					generateStmts(s.body());

					currentMethod = prevMethod;
				});
		}
	}

	private void generateLoopStmt(LoopStmt s) {
		Label loopStart = currentMethod.builder().newLabel();
		Label loopEnd = currentMethod.builder().newLabel();

		generateDeclStmt(s.initializer()); // create the loop variable
		currentMethod.builder().labelBinding(loopStart);
		currentMethod.exprCodegen().loadExpr(s.condition());
		// Here, the stack contains 1 if the loop should keep going, 0 if not.
		// if condition is false, jump to loopEnd
		currentMethod.builder().ifeq(loopEnd);

		generateStmts(s.body());
		generateReassignStmt(s.update());
		currentMethod.builder().goto_(loopStart);

		currentMethod.builder().labelBinding(loopEnd);
	}

	private void generateReassignStmt(ReassignStmt s) {
		String name = s.name().name();

		// First check if the variable is in the current method
		MethodVariable methodVar =
			currentMethod.exprCodegen().methodVariables.get(name);
		if (methodVar != null) {
			currentMethod.exprCodegen().loadExpr(s.newValue());
			currentMethod.builder().storeLocal(methodVar.typeKind(),
				methodVar.slot());
		}
		else {
			// not in method, check if it's a class variable
			ClassVariable classVar = ExprCodegen.classVariables.get(name);
			if (classVar != null) {
				currentMethod.exprCodegen().loadExpr(s.newValue());
				currentMethod.builder().putstatic(classVar.owner(), classVar.name(),
					classVar.type());
				return;
			}
			throw new AssertionError("Malformed ReassignStmt: " + s);
		}
	}

	private void generateUnnamedStmt(UnnamedStmt s) {
		currentMethod.exprCodegen().loadExpr(s.expr());
	}

	private void generateWhenStmt(WhenStmt s) {
		Label end = currentMethod.builder().newLabel();
		Label elseLabel = currentMethod.builder().newLabel();
		var elseWhenLabels = new ArrayList<Label>();
		for (int i = 0; i < s.elseWhen().size(); i++) {
			elseWhenLabels.add(currentMethod.builder().newLabel());
		}

		// First, handle the when.
		currentMethod.exprCodegen().loadExpr(s.when().condition());
		// Now, the stack contains 1 if the condition is true, 0 if false
		// read the below as if equal to 0, jump to elseLabel
		// Also, empty elseWhenLabels means that there are no elseWhen blocks,
		// so jump to the else label
		if (elseWhenLabels.isEmpty()) currentMethod.builder().ifeq(elseLabel);
		else currentMethod.builder().ifeq(elseWhenLabels.getFirst());
		// condition is true
		generateStmts(s.when().body());
		currentMethod.builder().goto_(end);

		// handle the elseWhen blocks. Surely, there are better ways to do this
		s.elseWhen().forEach(elseWhen -> {
			// (for the first iteration): remember we jumped to the first label in
			// the elseWhenLabels list? Now, we're defining what goes there
			// (for subsequent iterations): same as what we did above, but jumping
			// is now done in the else below
			currentMethod.builder().labelBinding(elseWhenLabels.removeFirst());
			currentMethod.exprCodegen().loadExpr(elseWhen.condition());

			if (elseWhenLabels.isEmpty()) currentMethod.builder().ifeq(elseLabel);
			else currentMethod.builder().ifeq(elseWhenLabels.getFirst());

			generateStmts(elseWhen.body());
			currentMethod.builder().goto_(end);
		});

		// Finally, handle the else block
		currentMethod.builder().labelBinding(elseLabel);
		generateStmts(s.elseBody());

		currentMethod.builder().labelBinding(end);
	}

	private void generateYeetStmt(YeetStmt s) {
		Expr expr = s.yeetValue();
		switch (expr.dataType()) {
			case IntType, BoolType -> {
				currentMethod.exprCodegen().loadExpr(expr);
				currentMethod.builder().ireturn();
			}
			case FloatType -> {
				currentMethod.exprCodegen().loadExpr(expr);
				currentMethod.builder().freturn();
			}
			case StrType -> {
				currentMethod.exprCodegen().loadExpr(expr);
				currentMethod.builder().areturn();
			}
			case NadaType -> currentMethod.builder().return_();
			case FuncType _ -> throw new AssertionError();
		}
	}

	private MethodTypeDesc createSignature(TypedIdentList params,
	                                       EarthType retType) {
		ClassDesc retTypeDesc = earthTypeToDesc(retType);

		// params are in form: name1:type1,name2:type2,...name9:type9
		// extract the types
		var paramTypes = new ClassDesc[params.size()];
		for (int i = 0; i < params.size(); i++) {
			paramTypes[i] = earthTypeToDesc(params.get(i).type().dataType());
		}
		return MethodTypeDesc.of(retTypeDesc, paramTypes);
	}

	private void generateBuiltins() {
		var intToStringDesc = MethodTypeDesc.of(CD_String, CD_int);
		var floatToStringDesc = MethodTypeDesc.of(CD_String, CD_float);
		var boolToStringDesc = MethodTypeDesc.of(CD_String, CD_boolean);
		var printDesc = MethodTypeDesc.of(CD_void, CD_String);
		var printlnDesc = MethodTypeDesc.of(CD_void, CD_String);

		// Hmm, what could go wrong with using nulls like this? I might find
		// out soon. Builder is null because it will be built immediately after
		methods.put("intToStr", new Method(null, intToStringDesc, thisClass));
		methods.put("floatToStr", new Method(null, floatToStringDesc, thisClass));
		methods.put("boolToStr", new Method(null, boolToStringDesc, thisClass));
		methods.put("yap", new Method(null, printDesc, thisClass));
		methods.put("yapln", new Method(null, printlnDesc, thisClass));

		classBuilder.withMethodBody("intToStr", intToStringDesc,
			ACC_STATIC | ACC_PRIVATE, builder -> builder
				.iload(0)
				.invokestatic(CD_Integer, "toString", intToStringDesc)
				.areturn());

		classBuilder.withMethodBody("floatToStr", floatToStringDesc,
			ACC_STATIC | ACC_PRIVATE, builder -> builder
				.fload(0)
				.invokestatic(CD_Float, "toString", floatToStringDesc)
				.areturn());

		classBuilder.withMethodBody("boolToStr", boolToStringDesc,
			ACC_STATIC | ACC_PRIVATE, builder -> builder
				.iload(0)
				.invokestatic(CD_Boolean, "toString", boolToStringDesc)
				.areturn());

		classBuilder.withMethodBody("yap", printDesc,
			ACC_STATIC | ACC_PRIVATE, builder -> builder
				.getstatic(CD_System, "out", CD_PrintStream)
				.aload(0)
				.invokevirtual(CD_PrintStream, "print", printDesc)
				.return_());

		classBuilder.withMethodBody("yapln", printlnDesc,
			ACC_STATIC | ACC_PRIVATE, builder -> builder
				.getstatic(CD_System, "out", CD_PrintStream)
				.aload(0)
				.invokevirtual(CD_PrintStream, "println", printlnDesc)
				.return_());
	}
}
