package parser.exprs;

import sanity2.NEarthType;

public sealed interface Expr permits BinaryExpr, FnCallExpr, GroupedExpr,
	IdentExpr, LitExpr, NegExpr, NotExpr {
	int line();

	NEarthType dataType();
}
