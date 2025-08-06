package ifesunmola.parser.exprs;


import ifesunmola.sanity.EarthType;

/// -expr
public record NegExpr(Expr expr, int line,
                      EarthType dataType) implements Expr {
	public NegExpr(Expr expr, int line) {
		this(expr, line, null);
	}
}
