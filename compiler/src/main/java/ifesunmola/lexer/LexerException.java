package ifesunmola.lexer;


import static ifesunmola.earth.EarthUtils.formatError;

final class LexerException extends RuntimeException {
	final String msg;

	LexerException(String msg, int line) {
		super(formatError("Lexical Error", msg, line));
		this.msg = super.getMessage();
	}
}
