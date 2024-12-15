package parser.exprs;

/// !expr
public record NotExpr(Expr expr, int line) implements Expr {
}
