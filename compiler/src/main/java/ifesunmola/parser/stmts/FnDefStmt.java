package ifesunmola.parser.stmts;

import ifesunmola.parser.ast_helpers.StmtList;
import ifesunmola.parser.ast_helpers.TypedIdentList;
import ifesunmola.parser.exprs.IdentExpr;

public record FnDefStmt(IdentExpr name,
                        TypedIdentList params,
                        IdentExpr returnType,
                        StmtList body,
                        int line
) implements Stmt {}
