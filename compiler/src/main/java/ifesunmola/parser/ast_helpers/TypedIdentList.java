package ifesunmola.parser.ast_helpers;


import java.util.ArrayList;
import java.util.List;

public final class TypedIdentList extends ArrayList<TypedIdent> {
	public TypedIdentList(List<TypedIdent> exprs) {
		super(exprs);
	}

	public TypedIdentList() {
		super();
	}
}
