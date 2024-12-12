package parser.exprs;

import earth.EarthUtils;
import lexer.TokenType;

import static lexer.TokenType.MINUS;

public record UnaryExpr(TokenType op, Expr expr, int line) implements Expr {
	public UnaryExpr {
		EarthUtils.ensure(op == MINUS || op == TokenType.BANG,
			"Invalid unary operator");
	}
}
