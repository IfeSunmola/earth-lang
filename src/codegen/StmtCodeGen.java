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
public class StmtCodeGen extends MoneyParserBaseVisitor<Void> {
	private ExprCodegen exprCodegen;
	private final ClassDesc outputDesc = ClassDesc.of("Output");
	private final String outputStr = outputDesc.displayName();

	private Method methodBuilder; // current method being built
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
								methodBuilder = new Method(mainBuilder, 1);
								exprCodegen = new ExprCodegen(methodBuilder.builder);
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
		exprCodegen.visit(ctx.expr());

		switch (type) {
			case INT, BOOL -> methodBuilder.builder
				.istore(methodBuilder.slot++);
			case FLOAT -> methodBuilder.builder
				.fstore(methodBuilder.slot++);
			case STRING -> methodBuilder.builder
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
		exprCodegen.visit(ctx.expr());

		Variable variable = methodBuilder.variables.stream()
			.filter(v -> v.name().equals(name))
			.findFirst()
			.orElseThrow();

		switch (variable.type()) {
			case INT, BOOL -> methodBuilder.builder
				.istore(variable.slot());
			case FLOAT -> methodBuilder.builder
				.fstore(variable.slot());
			case STRING -> methodBuilder.builder
				.astore(variable.slot());
			case VOID -> throw new RuntimeException();
			case MoneyType.Func func -> throw new RuntimeException();
		}

		return null;
	}
}
