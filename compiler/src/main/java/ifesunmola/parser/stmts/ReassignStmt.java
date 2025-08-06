package ifesunmola.parser.stmts;


import ifesunmola.parser.exprs.Expr;
import ifesunmola.parser.exprs.IdentExpr;

public record ReassignStmt(IdentExpr name, Expr newValue,
                           int line) implements Stmt {
}
