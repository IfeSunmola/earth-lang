package parser.exprs;

import earth.EarthUtils;
import lexer.TokenType;

import static lexer.TokenType.Minus;

public record UnaryExpr(TokenType op, Expr expr, int line) implements Expr {
	public UnaryExpr {
		EarthUtils.ensure(op == Minus || op == TokenType.Bang,
			"Invalid unary operator");
	}
}
