package parser.exprs;

import sanity2.NEarthType;

public record GroupedExpr(Expr expr, int line,
                          NEarthType dataType) implements Expr {
	public GroupedExpr(Expr expr, int line) {
		this(expr, line, null);
	}

	@Override
	public NEarthType dataType() {
		return expr.dataType();
	}
}
