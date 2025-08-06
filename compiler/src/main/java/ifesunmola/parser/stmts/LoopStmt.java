package ifesunmola.parser.stmts;


import ifesunmola.parser.ast_helpers.StmtList;
import ifesunmola.parser.exprs.Expr;

public record LoopStmt(DeclStmt initializer, Expr condition,
                       ReassignStmt update, StmtList body,
                       int line) implements Stmt {}
