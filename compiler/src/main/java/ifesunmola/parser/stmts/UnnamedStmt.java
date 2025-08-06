package ifesunmola.parser.stmts;


import ifesunmola.parser.exprs.Expr;

public record UnnamedStmt(Expr expr, int line) implements Stmt {}
