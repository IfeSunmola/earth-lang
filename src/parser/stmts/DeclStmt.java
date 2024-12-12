package parser.stmts;

import parser.exprs.Expr;
import parser.ast_helpers.TypedIdentExpr;

public record DeclStmt(TypedIdentExpr nameAndType, Expr value,
                       int line) implements Stmt {
}
