package parser.exprs;

import lexer.TokenType;

/// expr (`<` OR `<=` OR `>` OR `>=`) expr
public record RelationalExpr(Expr left, TokenType op, Expr right,
                             int line) implements Expr {

}
