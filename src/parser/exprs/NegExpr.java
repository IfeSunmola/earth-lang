package parser.exprs;

/// -expr
public record NegExpr(Expr expr, int line) implements Expr {
}
