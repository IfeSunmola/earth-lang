package codegen;

import antlr.MoneyParser.*;
import antlr.MoneyParserBaseVisitor;
import money.MoneyUtils;
import sanity.MoneyType;

import java.io.IOException;
import java.lang.classfile.ClassBuilder;
import java.lang.classfile.ClassFile;
import java.lang.classfile.attribute.SourceFileAttribute;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static java.lang.classfile.ClassFile.*;
import static java.lang.classfile.TypeKind.*;
import static java.lang.constant.ConstantDescs.*;


@SuppressWarnings("preview")
public class StmtCodeGen extends MoneyParserBaseVisitor<Void> {
	static final ClassDesc OUTPUT_DESC = ClassDesc.of("Output");
	static final String OUTPUT_STR = OUTPUT_DESC.displayName();
	static final Map<String, MethodTypeDesc> methodSignatures = new HashMap<>();

	// should probably change to Deque since Stack is synchronized
	private final Stack<Method> prevMethods = new Stack<>();
	private Method currentMethod; // current method being built
	private ClassBuilder classBuilder;

	public StmtCodeGen(ProgramContext program) {
		try {
			MethodTypeDesc mainDesc = MethodTypeDesc.of(CD_void,
				CD_String.arrayType());

			ClassFile.of().buildTo(Path.of(OUTPUT_STR + ".class"), OUTPUT_DESC,
				classBuilder -> {
					this.classBuilder = classBuilder;
					classBuilder
						.with(SourceFileAttribute.of(OUTPUT_STR + ".java"))
						.withFlags(ACC_PUBLIC | ACC_FINAL)
						.withMethodBody(INIT_NAME, MTD_void, ACC_PUBLIC, builder -> builder
							.aload(0)
							.invokespecial(CD_Object, INIT_NAME, MTD_void)
							.return_()
						)
						.withMethodBody("main",
							mainDesc,
							ACC_PUBLIC | ACC_STATIC, mainBuilder -> {
								currentMethod = new Method(
									mainBuilder, mainDesc.parameterCount(), mainDesc
								);
								visit(program);
								currentMethod.builder.return_();
							}
						)
					;
				});
		}
		catch (IOException e) {
			System.err.println("Could not write class file: " + e.getMessage());
		}
	}

	@Override
	public Void visitDeclStmt(DeclStmtContext ctx) {
		String name = ctx.typedIdentExpr().name.getText();
		var moneyType = MoneyType.fromString(ctx.typedIdentExpr().type.getText());
		var typeKind = moneyType.toTypeKind();

		// load the expression to store on the stack
		currentMethod.exprCodegen.visit(ctx.expr());

		int slot = currentMethod.slot++;
		currentMethod.builder.storeLocal(typeKind, slot);

		currentMethod.exprCodegen.variables.add(
			new Variable(name, moneyType, typeKind, slot)
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
		MoneyUtils.ensure(methodDesc.parameterCount() == params.size());
		methodSignatures.put(ctx.name.getText(), methodDesc);

		classBuilder.withMethodBody(
			ctx.name.getText(),
			methodDesc,
			ACC_STATIC | ACC_PRIVATE,
			builder -> {
				prevMethods.push(currentMethod);

				currentMethod = new Method(
					builder, methodDesc.parameterCount(), methodDesc
				);
				// add the method parameters to the local variables
				for (int i = 0; i < params.size(); i++) {
					TypedIdentExprContext param = params.get(i);
					String name = param.name.getText();
					var moneyType = MoneyType.fromString(param.type.getText());
					var typeKind = moneyType.toTypeKind();
					currentMethod.exprCodegen.variables.add(
						new Variable(name, moneyType, typeKind, i)
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

				currentMethod = prevMethods.pop();
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
}
