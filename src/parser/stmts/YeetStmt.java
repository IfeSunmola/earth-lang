package parser.stmts;

import parser.exprs.Expr;

public record YeetStmt(Expr yeetValue, int line) implements Stmt {}
