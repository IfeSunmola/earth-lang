package ifesunmola.parser.stmts;


import ifesunmola.parser.ast_helpers.StmtList;
import ifesunmola.parser.exprs.Expr;

import java.util.List;

public record WhenStmt(When when, List<When> elseWhen, StmtList elseBody,
                       int line) implements Stmt {
	public record When(Expr condition, StmtList body) {
	}
}
