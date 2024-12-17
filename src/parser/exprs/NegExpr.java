package parser.exprs;

import sanity2.NEarthType;

/// -expr
public record NegExpr(Expr expr, int line,
                      NEarthType dataType) implements Expr {
	public NegExpr(Expr expr, int line) {
		this(expr, line, null);
	}
}
