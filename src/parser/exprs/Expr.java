package parser.exprs;

import sanity.EarthType;

public sealed interface Expr permits BinaryExpr, FnCallExpr, GroupedExpr,
	IdentExpr, LitExpr, NegExpr, NotExpr {
	int line();

	EarthType dataType();
}
