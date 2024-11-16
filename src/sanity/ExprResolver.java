package sanity;

import antlr.MoneyParser;
import antlr.MoneyParser.LiteralExprContext;
import antlr.MoneyParser.NegExprContext;
import antlr.MoneyParserBaseVisitor;
import money.MoneyException;

/// Resolves expressions by validating them, and returning the type if it's
/// valid, or throwing an exception if it's not.
class ExprResolver extends MoneyParserBaseVisitor<MoneyType> {
	@Override
	public MoneyType visitLiteralExpr(LiteralExprContext ctx) {
		if (ctx.FloatLit() != null) return MoneyType.Base.FLOAT;
		if (ctx.IntLit() != null) return MoneyType.Base.INT;
		if (ctx.StrLit() != null) return MoneyType.Base.STRING;
		if (ctx.BoolLit() != null) return MoneyType.Base.BOOL;

		throw new RuntimeException("Should not reach here");
	}

	@Override
	public MoneyType visitNegExpr(NegExprContext ctx) {
		// -23, -23.0, -[ident], where ident is an int or float
		MoneyType exprType = visit(ctx.expr());
		if (exprType == MoneyType.Base.INT || exprType == MoneyType.Base.FLOAT)
			return exprType;

		throw new MoneyException(
			"Cannot apply `-` to a non-numeric type",
			ctx.getStart().getLine()
		);
	}

	@Override
	public MoneyType visitNotExpr(MoneyParser.NotExprContext ctx) {
		// !true, !false, ![expr], where expr is a boolean
		MoneyType exprType = visit(ctx.expr());
		if (exprType == MoneyType.Base.BOOL) return MoneyType.Base.BOOL;

		throw new MoneyException(
			"Cannot negate a non-boolean type",
			ctx.getStart().getLine()
		);
	}
}
