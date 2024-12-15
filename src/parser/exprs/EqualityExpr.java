package parser.exprs;

import lexer.TokenType;

/// expr (`==` OR `!=`) expr
public record EqualityExpr(Expr left, TokenType op, Expr right,
                           int line) implements Expr {

}
