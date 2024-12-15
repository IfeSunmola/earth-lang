package parser.exprs;

public sealed interface Expr permits AdditiveExpr, EqualityExpr, FnCallExpr,
	GroupedExpr, IdentExpr, LitExpr, LogicalExpr, NegExpr, NotExpr, ProductExpr,
	RelationalExpr {
	int line();
}
