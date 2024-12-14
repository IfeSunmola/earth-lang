package parser.stmts;

import parser.ast_helpers.StmtList;
import parser.ast_helpers.TypedIdentList;
import parser.exprs.IdentExpr;

public record FnDefStmt(IdentExpr name,
                        TypedIdentList params,
                        IdentExpr returnType,
                        StmtList body,
                        int line
) implements Stmt {}
