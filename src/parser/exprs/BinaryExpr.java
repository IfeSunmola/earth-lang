package parser.exprs;

import earth.EarthUtils;
import lexer.TokenType;

import static lexer.TokenType.*;

public record BinaryExpr(Expr left, TokenType op, Expr right, int line) implements Expr  {
	public BinaryExpr {
		EarthUtils.ensure(
			op == Minus || op == PLus || op == TokenType.Slash ||
			op == Star || op == Gt || op == Gte || op == Lt || op == Lte ||
			op == BangEq || op == EqEq || op == And || op == Or,
			"Invalid binary operator"
		);
	}
}
