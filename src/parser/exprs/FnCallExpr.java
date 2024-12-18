package parser.exprs;

import parser.ast_helpers.ExprList;
import sanity.EarthType;

public record FnCallExpr(IdentExpr name,
                         ExprList params, int line, EarthType dataType) implements Expr {
	public FnCallExpr(IdentExpr name, ExprList params, int line) {
		this(name, params, line, null);
	}
}
