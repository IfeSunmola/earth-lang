package ifesunmola.parser.ast_helpers;


import ifesunmola.parser.exprs.Expr;

import java.util.ArrayList;
import java.util.List;

public final class ExprList extends ArrayList<Expr> {
	public ExprList(List<Expr> exprs) {
		super(exprs);
	}

	public ExprList() {
		super();
	}
}
