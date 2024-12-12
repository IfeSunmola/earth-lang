package parser.exprs;

public record GroupedExpr(Expr expr, int line) implements Expr {
}
