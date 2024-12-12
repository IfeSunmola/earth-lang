package lexer;

import static earth.EarthUtils.formatError;

final class LexerException extends RuntimeException {
	final String msg;

	LexerException(String msg, int line) {
		msg = formatError("Lexical Error", msg, line);
		this.msg = msg;
		super(msg);
	}
}
