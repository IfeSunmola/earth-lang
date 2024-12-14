package parser.stmts;

import parser.exprs.Expr;
import parser.ast_helpers.TypedIdent;

public record DeclStmt(TypedIdent nameAndType, Expr value,
                       int line) implements Stmt {
}
