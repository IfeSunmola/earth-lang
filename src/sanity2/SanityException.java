package sanity2;

import static earth.EarthUtils.formatError;

final class SanityException extends RuntimeException {
	final String msg;

	SanityException(String msg, int line) {
		msg = formatError("Sanity Error", msg, line);
		this.msg = msg;
		super(msg);
	}
}
