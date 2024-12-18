package parser.exprs;

import sanity.EarthType;

/// !expr
public record NotExpr(Expr expr, int line) implements Expr {
	@Override
	public EarthType dataType() {
		return EarthType.Base.BoolType;
	}
}
