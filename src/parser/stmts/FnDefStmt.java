package parser.stmts;

import parser.ast_helpers.StmtList;
import parser.ast_helpers.TypedIdentExprList;
import parser.exprs.IdentExpr;

public record FnDefStmt(IdentExpr name,
                        TypedIdentExprList params,
                        IdentExpr returnType,
                        StmtList body,
                        int line
) implements Stmt {}
