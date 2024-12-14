package parser.stmts;

import parser.ast_helpers.StmtList;
import parser.exprs.Expr;

import java.util.List;


public record WhenStmt(When when, List<When> elseWhen, StmtList elseBody,
                       int line) implements Stmt {
	public record When(Expr condition, StmtList body) {
	}
}
