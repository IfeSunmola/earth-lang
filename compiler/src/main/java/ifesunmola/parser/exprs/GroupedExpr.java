package ifesunmola.parser.exprs;

import ifesunmola.sanity.EarthType;

public record GroupedExpr(Expr expr, int line,
                          EarthType dataType) implements Expr {
	public GroupedExpr(Expr expr, int line) {
		this(expr, line, null);
	}

	@Override
	public EarthType dataType() {
		return expr.dataType();
	}
}
