package sanity2;

import parser.exprs.*;
import sanity2.NEarthType.Base;

/// The purpose of this class is to find the type of an expression. If the
/// type of the expression is not what's expected, then a `SanityException` is
/// thrown
interface ExprResolver {
	SymbolTable table = SymbolTable.instance;

	static NEarthType exprType(Expr expr) {
		return switch (expr) {
			case FnCallExpr e -> null;
			case GroupedExpr e -> exprType(e.expr());
			case IdentExpr e -> null;
			case LitExpr e -> litExprType(e);
			case AdditiveExpr additiveExpr -> null;
			case EqualityExpr equalityExpr -> null;
			case LogicalExpr logicalExpr -> null;
			case NegExpr negExpr -> negExprType(negExpr);
			case NotExpr notExpr -> null;
			case ProductExpr productExpr -> null;
			case RelationalExpr relationalExpr -> null;
		};
	}

	/// Negation is only possible on integers and floats, so throw an error if
	/// the type is neither
	static NEarthType negExprType(NegExpr expr) {
		NEarthType exprType = exprType(expr.expr());
		if (exprType == Base.IntType || exprType == Base.FloatType) {
			return exprType;
		}

		throw new SanityException(
			"`-` is not a valid operator on %s".formatted(exprType),
			expr.line()
		);
	}

	static NEarthType litExprType(LitExpr e) {
		return switch (e) {
			case LitExpr.Int _ -> Base.IntType;
			case LitExpr.Str _ -> Base.StrType;
			case LitExpr.Bool _ -> Base.BoolType;
			case LitExpr.Float _ -> Base.FloatType;
			case LitExpr.Nada _ -> Base.NadaType;
		};
	}
}
