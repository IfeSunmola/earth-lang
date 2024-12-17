package codegen2;

import static earth.EarthUtils.formatError;

final class CodegenException extends RuntimeException {
	final String msg;

	CodegenException(String msg, int line) {
		msg = formatError("Codegen Error", msg, line);
		this.msg = msg;
		super(msg);
	}
}
