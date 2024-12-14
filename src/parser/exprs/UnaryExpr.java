package parser.exprs;

import lexer.TokenType;

public record UnaryExpr(TokenType op, Expr expr, int line) implements Expr {}
