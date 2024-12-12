package earth;

/// After migrating away from antlr, this class should be made to have only
/// one constructor that takes a message, and calls the super constructor.
public class EarthException extends RuntimeException {
	public EarthException(String msg, int line) {
		super("Error on line %d - %s".formatted(line, msg));
	}

	public EarthException(String msg) {
		super(msg);
	}
}
