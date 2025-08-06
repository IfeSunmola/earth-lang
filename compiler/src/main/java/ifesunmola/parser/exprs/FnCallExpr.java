package ifesunmola.parser.exprs;


import ifesunmola.parser.ast_helpers.ExprList;
import ifesunmola.sanity.EarthType;

public record FnCallExpr(IdentExpr name,
                         ExprList params, int line, EarthType dataType) implements Expr {
	public FnCallExpr(IdentExpr name, ExprList params, int line) {
		this(name, params, line, null);
	}
}
