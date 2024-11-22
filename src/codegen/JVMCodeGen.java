package codegen;

import antlr.MoneyParser;
import antlr.MoneyParser.ExprContext;
import antlr.MoneyParserBaseVisitor;

import java.io.IOException;
import java.lang.classfile.ClassBuilder;
import java.lang.classfile.ClassFile;
import java.lang.classfile.attribute.SourceFileAttribute;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.nio.file.Path;

import static java.lang.classfile.ClassFile.*;
import static java.lang.constant.ConstantDescs.*;

@SuppressWarnings("preview")
public class JVMCodeGen extends MoneyParserBaseVisitor<Void> {
	private final ExprStringifier exprStringifier = new ExprStringifier();
	private ClassDesc outputDesc = ClassDesc.of("Output");
	private String outputStr = outputDesc.displayName();

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
		String type = ctx.typedIdentExpr().type.getText();
		ExprContext expr = ctx.expr();
		String strExpr = exprStringifier.visit(expr);

		switch (type) {
			case "int" -> methodBuilder.builder
				.ldc(Integer.parseInt(strExpr))
				.istore(methodBuilder.slot++);

			case "bool" -> methodBuilder.builder
				.ldc(strExpr.equals("true") ? 1 : 0)
				.istore(methodBuilder.slot++);

			case "float" -> methodBuilder.builder
				.ldc(Float.parseFloat(strExpr))
				.fstore(methodBuilder.slot++);

			case "str" -> methodBuilder.builder
				.ldc(strExpr.translateEscapes())
				.astore(methodBuilder.slot++);
		}

		return null;
	}
}
