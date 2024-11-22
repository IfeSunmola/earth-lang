package codegen;

import antlr.MoneyParser.LiteralExprContext;
import antlr.MoneyParserBaseVisitor;

import java.lang.classfile.CodeBuilder;

@SuppressWarnings("preview")
public class ExprCodegen extends MoneyParserBaseVisitor<Void> {
	private final CodeBuilder methodBuilder;

	public ExprCodegen(CodeBuilder builder) {
		methodBuilder = builder;
	}

	/// Loads the value of the literal onto the stack
	@Override
	public Void visitLiteralExpr(LiteralExprContext ctx) {
		if (ctx.FloatLit() != null)
			methodBuilder.ldc(Float.parseFloat(ctx.FloatLit().getText()));

		else if (ctx.IntLit() != null)
			methodBuilder.ldc(Integer.parseInt(ctx.IntLit().getText()));

		else if (ctx.StrLit() != null)
			methodBuilder.ldc(ctx.StrLit().getText()
				.substring(1, ctx.StrLit().getText().length() - 1)); // remove quotes

		else if (ctx.BoolLit() != null)
			methodBuilder.ldc(ctx.BoolLit().getText().equals("true") ? 1 : 0);

		throw new RuntimeException("Should not reach here");
	}
}
