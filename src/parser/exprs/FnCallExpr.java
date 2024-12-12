package parser.exprs;

import parser.ast_helpers.ExprList;

public record FnCallExpr(UntypedIdentExpr name,
                         ExprList params, int line) implements Expr {
}
