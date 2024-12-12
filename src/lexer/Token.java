package lexer;

public record Token(TokenType type, String literal, int line) {
	@Override
	public String toString() {
		return "Token[type='%s', literal='%s', line=%d]"
			.formatted(type, literal, line);
	}
}
