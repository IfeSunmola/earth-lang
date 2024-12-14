package parser.stmts;

import parser.exprs.Expr;
import parser.exprs.IdentExpr;

public record ReassignStmt(IdentExpr name, Expr newValue,
                           int line) implements Stmt {
}
