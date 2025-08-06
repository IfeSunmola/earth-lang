package ifesunmola.earth;

public class EarthException extends RuntimeException {
	public EarthException(String msg) {
		super("Error: - %s".formatted(msg));
	}
}
