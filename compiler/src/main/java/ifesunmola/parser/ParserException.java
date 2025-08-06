package ifesunmola.parser;


import static ifesunmola.earth.EarthUtils.formatError;

final class ParserException extends RuntimeException {
	final String msg;

	ParserException(String msg, int line) {
		super(formatError("Parser Error", msg, line));
		this.msg = super.getMessage();
	}
}
