package codegen;

import antlr.MoneyParser;
import antlr.MoneyParser.ReassignStmtContext;
import antlr.MoneyParserBaseVisitor;
import sanity.MoneyType;

import java.io.IOException;
import java.lang.classfile.ClassBuilder;
import java.lang.classfile.ClassFile;
import java.lang.classfile.attribute.SourceFileAttribute;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.nio.file.Path;

import static java.lang.classfile.ClassFile.*;
import static java.lang.constant.ConstantDescs.*;
import static sanity.MoneyType.Base.*;


@SuppressWarnings("preview")
public class JVMCodeGen extends MoneyParserBaseVisitor<Void> {
	private final ExprStringifier exprStringifier = new ExprStringifier();
	private final ClassDesc outputDesc = ClassDesc.of("Output");
	private final String outputStr = outputDesc.displayName();

	private Method methodBuilder; // current method being built
	private ClassBuilder classBuilder;

	public JVMCodeGen(MoneyParser.ProgramContext program) {
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
								methodBuilder = new Method(mainBuilder, 1);
								visit(program);
								mainBuilder.return_();
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
		String strExpr = exprStringifier.visit(ctx.expr());

		switch (type) {
			case INT, BOOL -> methodBuilder.builder
				.ldc(Integer.parseInt(strExpr))
				.istore(methodBuilder.slot++);
			case FLOAT -> methodBuilder.builder
				.ldc(Float.parseFloat(strExpr))
				.fstore(methodBuilder.slot++);
			case STRING -> methodBuilder.builder
				.ldc(strExpr)
				.astore(methodBuilder.slot++);

			case VOID -> throw new RuntimeException();
			case MoneyType.Func func -> throw new RuntimeException();
		}

		methodBuilder.variables.add(new Variable(name, type,
			methodBuilder.slot - 1));
		return null;
	}

	@Override
	public Void visitReassignStmt(ReassignStmtContext ctx) {
		String name = ctx.ident.getText();
		String strExpr = exprStringifier.visit(ctx.expr());

		Variable variable = methodBuilder.variables.stream()
			.filter(v -> v.name().equals(name))
			.findFirst()
			.orElseThrow();

		switch (variable.type()) {
			case INT, BOOL -> methodBuilder.builder
				.ldc(Integer.parseInt(strExpr))
				.istore(variable.slot());
			case FLOAT -> methodBuilder.builder
				.ldc(Float.parseFloat(strExpr))
				.fstore(variable.slot());
			case STRING -> methodBuilder.builder
				.ldc(strExpr)
				.astore(variable.slot());
			case VOID -> throw new RuntimeException();
			case MoneyType.Func func -> throw new RuntimeException();
		}

		return null;
	}
}
