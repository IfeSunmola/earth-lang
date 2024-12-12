package parser.stmts;

import parser.exprs.Expr;
import parser.exprs.UntypedIdentExpr;

public record ReassignStmt(UntypedIdentExpr name, Expr newValue,
                           int line) implements Stmt {
}
