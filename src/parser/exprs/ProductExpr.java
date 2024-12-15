package parser.exprs;

import lexer.TokenType;

/// expr (`*` OR `/` OR `%`) expr
public record ProductExpr(Expr left, TokenType op, Expr right,
                          int line) implements Expr {

}
