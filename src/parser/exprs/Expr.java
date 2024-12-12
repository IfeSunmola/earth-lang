package parser.exprs;

public sealed interface Expr permits BinaryExpr, FnCallExpr,
	GroupedExpr,
	LitExpr, UnaryExpr, UntypedIdentExpr {
	int line();
}
