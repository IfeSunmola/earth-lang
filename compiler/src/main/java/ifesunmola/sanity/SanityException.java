package ifesunmola.sanity;


import static ifesunmola.earth.EarthUtils.formatError;

final class SanityException extends RuntimeException {
	final String msg;

	SanityException(String msg, int line) {
		super(formatError("Sanity Error", msg, line));
		this.msg = super.getMessage();
	}
}
