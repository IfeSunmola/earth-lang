package parser.exprs;

import sanity2.NEarthType;

/// !expr
public record NotExpr(Expr expr, int line) implements Expr {
	@Override
	public NEarthType dataType() {
		return NEarthType.Base.BoolType;
	}
}
