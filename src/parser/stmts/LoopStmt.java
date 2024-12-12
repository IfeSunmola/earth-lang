package parser.stmts;

import parser.ast_helpers.StmtList;
import parser.exprs.Expr;

public record LoopStmt(DeclStmt initializer, Expr condition,
                       ReassignStmt update, StmtList body,
                       int line) implements Stmt {}
