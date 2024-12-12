package parser.stmts;

import parser.exprs.Expr;

public record UnnamedStmt(Expr expr, int line) implements Stmt {}
