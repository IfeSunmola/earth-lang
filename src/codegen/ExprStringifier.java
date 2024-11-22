package codegen;

import antlr.MoneyParser;
import antlr.MoneyParserBaseVisitor;

public class ExprStringifier extends MoneyParserBaseVisitor<String> {
	@Override
	public String visitLiteralExpr(MoneyParser.LiteralExprContext ctx) {
		if (ctx.FloatLit() != null) return ctx.FloatLit().getText();
		if (ctx.IntLit() != null) return ctx.IntLit().getText();
		if (ctx.StrLit() != null) return ctx.StrLit().getText()
			.substring(1, ctx.StrLit().getText().length() - 1); // remove quotes

		if (ctx.BoolLit() != null)
			return ctx.BoolLit().getText().equals("true") ? "1" : "0";

		throw new RuntimeException("Should not reach here");
	}
}
