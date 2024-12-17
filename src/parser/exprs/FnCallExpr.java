package parser.exprs;

import parser.ast_helpers.ExprList;
import sanity2.NEarthType;

public record FnCallExpr(IdentExpr name,
                         ExprList params, int line, NEarthType dataType) implements Expr {
	public FnCallExpr(IdentExpr name, ExprList params, int line) {
		this(name, params, line, null);
	}
}
