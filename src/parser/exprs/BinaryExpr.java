package parser.exprs;

import earth.EarthUtils;
import lexer.TokenType;

import static lexer.TokenType.*;

public record BinaryExpr(Expr left, TokenType op, Expr right, int line) implements Expr  {
	public BinaryExpr {
		EarthUtils.ensure(
			op == MINUS || op == PLUS || op == TokenType.SLASH ||
			op == STAR || op == GT || op == GTE || op == LT || op == LTE ||
			op == BangEq || op == EqEq || op == AND || op == OR,
			"Invalid binary operator"
		);
	}
}
