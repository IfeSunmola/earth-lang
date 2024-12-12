package parser.stmts;

import parser.ast_helpers.StmtList;
import parser.ast_helpers.TypedIdentExprList;
import parser.exprs.UntypedIdentExpr;

public record FnDefStmt(UntypedIdentExpr name,
                        TypedIdentExprList params,
                        UntypedIdentExpr returnType,
                        StmtList body,
                        int line
) implements Stmt {}
