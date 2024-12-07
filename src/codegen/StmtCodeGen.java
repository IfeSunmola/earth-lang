package codegen;

import antlr.MoneyParser;
import antlr.MoneyParser.FnDefStmtContext;
import antlr.MoneyParser.ReassignStmtContext;
import antlr.MoneyParser.TypedIdentExprContext;
import antlr.MoneyParser.YeetStmtContext;
import antlr.MoneyParserBaseVisitor;
import sanity.MoneyType;

import java.io.IOException;
import java.lang.classfile.ClassBuilder;
import java.lang.classfile.ClassFile;
import java.lang.classfile.attribute.SourceFileAttribute;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.nio.file.Path;
import java.util.List;
import java.util.Stack;

import static java.lang.classfile.ClassFile.*;
import static java.lang.constant.ConstantDescs.*;
import static sanity.MoneyType.Base.*;


@SuppressWarnings("preview")
public class StmtCodeGen extends MoneyParserBaseVisitor<Void> {
	private final ClassDesc outputDesc = ClassDesc.of("Output");
	private final String outputStr = outputDesc.displayName();

	// should probably change to Deque since Stack is synchronized
	private final Stack<Method> prevMethods = new Stack<>();
	private Method currentMethod; // current method being built
	private ClassBuilder classBuilder;

	public StmtCodeGen(MoneyParser.ProgramContext program) {
		try {
			ClassFile.of().buildTo(Path.of(outputStr + ".class"), outputDesc,
				(ClassBuilder classBuilder) -> {
					this.classBuilder = classBuilder;
					classBuilder
						.with(SourceFileAttribute.of(outputStr + ".java"))
						.withFlags(ACC_PUBLIC | ACC_FINAL)
						.withMethodBody(INIT_NAME, MTD_void, ACC_PUBLIC, builder -> builder
							.aload(0)
							.invokespecial(CD_Object, INIT_NAME, MTD_void)
							.return_()
						)
						.withMethodBody("main",
							MethodTypeDesc.of(CD_void, CD_String.arrayType()),
							ACC_PUBLIC | ACC_STATIC, mainBuilder -> {
								currentMethod = new Method(mainBuilder, 1);
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
	public Void visitDeclStmt(MoneyParser.DeclStmtContext ctx) {
		String name = ctx.typedIdentExpr().name.getText();
		MoneyType type =
			MoneyType.fromString(ctx.typedIdentExpr().type.getText());
		currentMethod.exprCodegen.visit(ctx.expr());

		switch (type) {
			case INT, BOOL -> currentMethod.builder
				.istore(currentMethod.slot++);
			case FLOAT -> currentMethod.builder
				.fstore(currentMethod.slot++);
			case STRING -> currentMethod.builder
				.astore(currentMethod.slot++);

			case VOID -> throw new RuntimeException();
			case MoneyType.Func func -> throw new RuntimeException();
		}

		currentMethod.variables.add(new Variable(name, type,
			currentMethod.slot - 1));
		return null;
	}

	@Override
	public Void visitReassignStmt(ReassignStmtContext ctx) {
		String name = ctx.ident.getText();
		currentMethod.exprCodegen.visit(ctx.expr());

		Variable variable = currentMethod.variables.stream()
			.filter(v -> v.name().equals(name))
			.findFirst()
			.orElseThrow();

		switch (variable.type()) {
			case INT, BOOL -> currentMethod.builder
				.istore(variable.slot());
			case FLOAT -> currentMethod.builder
				.fstore(variable.slot());
			case STRING -> currentMethod.builder
				.astore(variable.slot());
			case VOID -> throw new RuntimeException();
			case MoneyType.Func func -> throw new RuntimeException();
		}

		return null;
	}

	@Override
	public Void visitFnDefStmt(FnDefStmtContext ctx) {
		List<TypedIdentExprContext> params = ctx.params.typedIdentExpr();
		MethodTypeDesc methodDesc = createDesc(params,
			ctx.retType == null ? "" : ctx.retType.getText()
		);
		classBuilder.withMethodBody(
			ctx.name.getText(),
			methodDesc,
			ACC_STATIC | ACC_PRIVATE,
			builder -> {
				prevMethods.push(currentMethod);

				currentMethod = new Method(builder, methodDesc.parameterCount());
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
	public Void visitYeetStmt(YeetStmtContext ctx) {
		// simply load the expression to yeet (return) onto the stack
		currentMethod.exprCodegen.visit(ctx.expr());
		return null;
	}

	private MethodTypeDesc createDesc(List<TypedIdentExprContext> params,
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
