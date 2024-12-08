package codegen;

import antlr.EarthParser.*;
import antlr.EarthParserBaseVisitor;
import earth.EarthUtils;
import sanity.EarthType;

import java.lang.classfile.ClassBuilder;
import java.lang.classfile.attribute.SourceFileAttribute;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.nio.file.Path;
import java.util.*;

import static codegen.CodegenUtils.CD_PrintStream;
import static codegen.CodegenUtils.CD_System;
import static java.lang.classfile.ClassFile.*;
import static java.lang.classfile.TypeKind.*;
import static java.lang.constant.ConstantDescs.*;


@SuppressWarnings("preview")
public class StmtCodeGen extends EarthParserBaseVisitor<Void> {
	static final Map<String, MethodTypeDesc> methodSignatures = new HashMap<>();

	// Stack class is synchronized!!
	// All operations are Deque as a stack are on the last element
	private final Deque<Method> prevMethods = new ArrayDeque<>();
	private final Path filePath;
	private Method currentMethod; // current method being built
	private ClassBuilder classBuilder;
	private final byte[] classBytes;

	public StmtCodeGen(ProgramContext program, Path fPathNoExt) {
		// remove any extensions
		filePath = fPathNoExt;

		var mainDesc = MethodTypeDesc.of(CD_void, CD_String.arrayType());
		String fileName = filePath.getFileName().toString();

		classBytes = of().build(ClassDesc.of(fileName),
			classBuilder -> {
				this.classBuilder = classBuilder;
				generateBuiltins();

				classBuilder
					.with(SourceFileAttribute.of(filePath + ".earth"))
					.withFlags(ACC_PUBLIC | ACC_FINAL)
					.withMethodBody(INIT_NAME, MTD_void, ACC_PUBLIC, builder -> builder
						.aload(0)
						.invokespecial(CD_Object, INIT_NAME, MTD_void)
						.return_()
					)
					.withMethodBody("main",
						mainDesc,
						ACC_PUBLIC | ACC_STATIC, builder -> {
							currentMethod = new Method(
								builder, mainDesc.parameterCount(), mainDesc, fileName
							);
							visit(program);
							currentMethod.builder.return_();
						}
					);
			});
	}

	public byte[] getClassFile() {
		return classBytes;
	}

	@Override
	public Void visitDeclStmt(DeclStmtContext ctx) {
		String name = ctx.typedIdentExpr().name.getText();
		var earthType = EarthType.fromString(ctx.typedIdentExpr().type.getText());
		var typeKind = earthType.toTypeKind();

		// load the expression to store on the stack
		currentMethod.exprCodegen.visit(ctx.expr());

		int slot = currentMethod.slot++;
		currentMethod.builder.storeLocal(typeKind, slot);

		currentMethod.exprCodegen.variables.add(
			new Variable(name, earthType, typeKind, slot)
		);
		return null;
	}

	@Override
	public Void visitReassignStmt(ReassignStmtContext ctx) {
		String name = ctx.ident.getText();
		currentMethod.exprCodegen.visit(ctx.expr());

		Variable variable = currentMethod.exprCodegen
			.variables.stream()
			.filter(v -> v.name().equals(name))
			.findFirst()
			.orElseThrow();

		int slot = variable.slot();
		switch (variable.typeKind()) {
			case IntType -> currentMethod.builder
				.storeLocal(IntType, slot);
			case BooleanType -> currentMethod.builder
				.storeLocal(BooleanType, slot);
			case FloatType -> currentMethod.builder
				.storeLocal(FloatType, slot);
			case ReferenceType -> currentMethod.builder
				.storeLocal(ReferenceType, slot);
			default -> throw new RuntimeException();
		}
		return null;
	}

	@Override
	public Void visitWhenElseStmt(WhenElseStmtContext ctx) {
		throw new RuntimeException("visitWhenElseStmt has not been implemented");
	}

	@Override
	public Void visitYeetStmt(YeetStmtContext ctx) {
		// simply load the expression to yeet (return) onto the stack
		currentMethod.exprCodegen.visit(ctx.expr());
		return null;
	}

	@Override
	public Void visitFnDefStmt(FnDefStmtContext ctx) {
		List<TypedIdentExprContext> params = ctx.params.typedIdentExpr();
		MethodTypeDesc methodDesc = createSignature(params,
			ctx.retType == null ? "" : ctx.retType.getText()
		);
		EarthUtils.ensure(methodDesc.parameterCount() == params.size());
		methodSignatures.put(ctx.name.getText(), methodDesc);

		classBuilder.withMethodBody(
			ctx.name.getText(),
			methodDesc,
			ACC_STATIC | ACC_PRIVATE,
			builder -> {
				prevMethods.addLast(currentMethod);

				currentMethod = new Method(
					builder, methodDesc.parameterCount(), methodDesc,
					filePath.getFileName().toString()
				);
				// add the method parameters to the local variables
				for (int i = 0; i < params.size(); i++) {
					TypedIdentExprContext param = params.get(i);
					String name = param.name.getText();
					var earthType = EarthType.fromString(param.type.getText());
					var typeKind = earthType.toTypeKind();
					currentMethod.exprCodegen.variables.add(
						new Variable(name, earthType, typeKind, i)
					);
				}

				visit(ctx.body);

				ClassDesc retDesc = methodDesc.returnType();
				if (retDesc.equals(CD_void)) builder.return_();
				else if (retDesc.equals(CD_int) || retDesc.equals(CD_boolean))
					builder.ireturn();
				else if (retDesc.equals(CD_String)) builder.areturn();
				else if (retDesc.equals(CD_float)) builder.freturn();
				else throw new RuntimeException("Unknown return type: " + retDesc);

				currentMethod = prevMethods.removeLast();
			});
		return null;
	}

	@Override
	public Void visitUnnamedStmt(UnnamedStmtContext ctx) {
		currentMethod.exprCodegen.visit(ctx.expr());
		return null;
	}

	@Override
	public Void visitLoopStmt(LoopStmtContext ctx) {
		throw new RuntimeException("visitLoopStmt has not been implemented");
	}

	private MethodTypeDesc createSignature(List<TypedIdentExprContext> params,
	                                       String retType) {
		ClassDesc retTypeDesc = switch (retType) {
			case "" -> CD_void;
			case "int" -> CD_int;
			case "float" -> CD_float;
			case "str" -> CD_String;
			case "bool" -> CD_boolean;
			default -> throw new RuntimeException("Unknown type: " + retType);
		};

		// params are in form: name1:type1,name2:type2,...name9:type9
		// extract the types
		List<ClassDesc> paramTypes = params.stream()
			.map(context -> {
				String type = context.type.getText();
				return switch (type) {
					case "int" -> CD_int;
					case "float" -> CD_float;
					case "str" -> CD_String;
					case "bool" -> CD_boolean;
					default -> throw new RuntimeException("Unknown type: " + type);
				};
			})
			.toList();
		return MethodTypeDesc.of(retTypeDesc, paramTypes);
	}

	private void generateBuiltins() {
		var intToStringDesc = MethodTypeDesc.of(CD_String, CD_int);
		var floatToStringDesc = MethodTypeDesc.of(CD_String, CD_float);
		var boolToStringDesc = MethodTypeDesc.of(CD_String, CD_boolean);
		var printDesc = MethodTypeDesc.of(CD_void, CD_String);
		var printlnDesc = MethodTypeDesc.of(CD_void, CD_String);

		methodSignatures.put("intToStr", intToStringDesc);
		methodSignatures.put("floatToStr", floatToStringDesc);
		methodSignatures.put("boolToStr", boolToStringDesc);
		methodSignatures.put("print", printDesc);
		methodSignatures.put("println", printlnDesc);

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

		classBuilder.withMethodBody("print", printDesc,
			ACC_STATIC | ACC_PRIVATE, builder -> builder
				.getstatic(CD_System, "out", CD_PrintStream)
				.aload(0)
				.invokevirtual(CD_PrintStream, "print", printDesc)
				.return_());

		classBuilder.withMethodBody("println", printlnDesc,
			ACC_STATIC | ACC_PRIVATE, builder -> builder
				.getstatic(CD_System, "out", CD_PrintStream)
				.aload(0)
				.invokevirtual(CD_PrintStream, "println", printlnDesc)
				.return_());
	}
}
