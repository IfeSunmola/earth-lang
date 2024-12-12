package parser.ast_helpers;

import java.util.ArrayList;
import java.util.List;

public final class TypedIdentExprList extends ArrayList<TypedIdentExpr> {
	public TypedIdentExprList(List<TypedIdentExpr> exprs) {
		super(exprs);
	}
}
