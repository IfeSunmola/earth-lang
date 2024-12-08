package earth;

public class EarthException extends RuntimeException {
	public EarthException(String msg, int line) {
		super("Error on line %d - %s".formatted(line, msg));
	}
}
