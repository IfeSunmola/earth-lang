package parser.ast_helpers;

import parser.stmts.Stmt;

import java.util.ArrayList;
import java.util.List;

public final class StmtList extends ArrayList<Stmt> {
	public StmtList(List<Stmt> stmts) {
		super(stmts);
	}

	public StmtList() {
		super();
	}
}
