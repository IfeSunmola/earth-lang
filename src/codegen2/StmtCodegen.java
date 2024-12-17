package codegen2;

import codegen2.CodegenUtils.ClassVariable;
import codegen2.CodegenUtils.Method;
import codegen2.CodegenUtils.MethodVariable;
import earth.EarthResult;
import earth.EarthUtils;
import parser.ast_helpers.StmtList;
import parser.ast_helpers.TypedIdentList;
import parser.exprs.Expr;
import parser.stmts.*;
import sanity2.NEarthType;

import java.lang.classfile.ClassBuilder;
import java.lang.classfile.ClassFile;
import java.lang.classfile.TypeKind;
import java.lang.classfile.attribute.SourceFileAttribute;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static codegen2.CodegenUtils.earthTypeToDesc;
import static codegen2.CodegenUtils.earthTypeToTypeKind;
import static java.lang.classfile.ClassFile.*;
import static java.lang.constant.ConstantDescs.*;
import static sanity2.NEarthType.Base;
import static sanity2.NEarthType.FuncType;

@SuppressWarnings("preview")
public class StmtCodegen {
	private final Path srcPath;
	private final ClassDesc thisClass;
	private boolean inMethod = false;
	private ClassBuilder classBuilder;
	private Method currentMethod;
	static final Map<String, Method> methods = new HashMap<>();

	public StmtCodegen(Path path) {
		srcPath = EarthUtils.removeExt(path);
		thisClass = ClassDesc.of(srcPath.getFileName().toString());
	}

	public EarthResult<byte[]> generate(StmtList stmts) {
		var errors = new ArrayList<String>();
		System.out.println(srcPath);
		byte[] result = ClassFile.of().build(thisClass, classBuilder -> {
			this.classBuilder = classBuilder;
			var mainDesc = MethodTypeDesc.of(CD_void, CD_String.arrayType());

			classBuilder
				.with(SourceFileAttribute.of(srcPath + ".earth"))
				.withFlags(ACC_PUBLIC | ACC_FINAL)
				.withMethodBody(INIT_NAME, MTD_void, ACC_PUBLIC, builder -> builder
					.aload(0)
					.invokespecial(CD_Object, INIT_NAME, MTD_void)
					.return_()
				)
				.withMethodBody("main", mainDesc,
					ACC_PUBLIC | ACC_STATIC, builder -> {
						this.currentMethod = new Method(builder, mainDesc, thisClass);
						methods.put("main", currentMethod);
						try {
							generateStmts(stmts);
						}
						catch (CodegenException e) {
							errors.add(e.getMessage());
						}
						builder.return_();
					}
				);
		});

		if (!errors.isEmpty()) return EarthResult.err(errors);
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

		if (!inMethod) {
			classBuilder.withField(name, desc, ACC_STATIC);
			currentMethod.exprCodegen.loadExpr(toDeclare);
			currentMethod.builder.putstatic(thisClass, name, desc);

			ExprCodegen.classVariables
				.put(name, new ClassVariable(name, desc, thisClass));

			return;
		}

		currentMethod.exprCodegen.loadExpr(toDeclare);
		int slot = currentMethod.slot++;
		TypeKind typeKind = earthTypeToTypeKind(toDeclare.dataType());
		currentMethod.builder.storeLocal(typeKind, slot);

		currentMethod.exprCodegen.methodVariables
			.put(name,
				new MethodVariable(name, toDeclare.dataType(), typeKind, slot));
	}

	private void generateFnDefStmt(FnDefStmt s) {
		inMethod = true;
		String fnName = s.name().name();
		if (fnName.equals("main")) { // main already defined so no need to redefine
			currentMethod = methods.get("main");
			generateStmts(s.body());
			inMethod = false;
			return;
		}

		MethodTypeDesc methodDesc = createSignature(
			s.params(), s.returnType().dataType()
		);

		classBuilder.withMethodBody(fnName, methodDesc,
			ACC_STATIC | ACC_PRIVATE, builder -> {
				Method prevMethod = currentMethod;

				currentMethod = new Method(builder, methodDesc, thisClass);
				methods.put(fnName, currentMethod);

				// add the method parameters to the local methodVariables
				s.params().forEach(param -> {
					String name = param.name().name();
					NEarthType paramType = param.type().dataType();
					int slot = currentMethod.slot++;

					TypeKind typeKind = earthTypeToTypeKind(paramType);
					currentMethod.builder.storeLocal(typeKind, slot);

					currentMethod.exprCodegen.methodVariables
						.put(name, new MethodVariable(name, paramType, typeKind, slot));
				});

				generateStmts(s.body());

				currentMethod = prevMethod;
			});

		inMethod = false;
	}

	private MethodTypeDesc createSignature(TypedIdentList params,
	                                       NEarthType retType) {
		ClassDesc retTypeDesc = earthTypeToDesc(retType);

		// params are in form: name1:type1,name2:type2,...name9:type9
		// extract the types
		var paramTypes = new ClassDesc[params.size()];
		for (int i = 0; i < params.size(); i++) {
			paramTypes[i] = earthTypeToDesc(params.get(i).type().dataType());
		}
		return MethodTypeDesc.of(retTypeDesc, paramTypes);
	}

	private void generateLoopStmt(LoopStmt s) {
		throw new AssertionError("generateLoopStmt has not been implemented");
	}

	private void generateReassignStmt(ReassignStmt s) {
		throw new AssertionError("generateReassignStmt has not been implemented");
	}

	private void generateUnnamedStmt(UnnamedStmt s) {
		currentMethod.exprCodegen.loadExpr(s.expr());

	}

	private void generateWhenStmt(WhenStmt s) {
		throw new AssertionError("generateWhenStmt has not been implemented");
	}

	private void generateYeetStmt(YeetStmt s) {
		Expr expr = s.yeetValue();
		switch (expr.dataType()) {
			case Base.IntType, Base.BoolType -> {
				currentMethod.exprCodegen.loadExpr(expr);
				currentMethod.builder.ireturn();
			}
			case Base.FloatType -> {
				currentMethod.exprCodegen.loadExpr(expr);
				currentMethod.builder.freturn();
			}
			case Base.StrType -> {
				currentMethod.exprCodegen.loadExpr(expr);
				currentMethod.builder.areturn();
			}
			case Base.NadaType -> currentMethod.builder.return_();
			case FuncType _ -> throw new AssertionError();
		}
	}
}
