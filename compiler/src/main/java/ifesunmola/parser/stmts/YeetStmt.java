package ifesunmola.parser.stmts;


import ifesunmola.parser.exprs.Expr;

public record YeetStmt(Expr yeetValue, int line) implements Stmt {}
