package parser.exprs;

import parser.ast_helpers.ExprList;

public record FnCallExpr(IdentExpr name,
                         ExprList params, int line) implements Expr {
}
