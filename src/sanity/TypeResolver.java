package sanity;

import antlr.MoneyParser.LiteralExprContext;
import antlr.MoneyParserBaseVisitor;

class TypeResolver extends MoneyParserBaseVisitor<MoneyType> {
	@Override
	public MoneyType visitLiteralExpr(LiteralExprContext ctx) {
		if (ctx.FloatLit() != null) return MoneyType.Base.FLOAT;
		if (ctx.IntLit() != null) return MoneyType.Base.INT;
		if (ctx.StrLit() != null) return MoneyType.Base.STRING;
		if (ctx.BoolLit() != null) return MoneyType.Base.BOOL;

		throw new RuntimeException("Should not reach here");
	}
}
