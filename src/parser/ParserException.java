package parser;

import static earth.EarthUtils.formatError;

final class ParserException extends RuntimeException {
	final String msg;

	ParserException(String msg, int line) {
		msg = formatError("Parser Error", msg, line);
		this.msg = msg;
		super(msg);
	}
}
