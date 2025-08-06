package ifesunmola.parser.stmts;


import ifesunmola.parser.ast_helpers.TypedIdent;
import ifesunmola.parser.exprs.Expr;

public record DeclStmt(TypedIdent nameAndType, Expr value,
                       int line) implements Stmt {
}
