package money;

public class MoneyException extends RuntimeException {
	public MoneyException(String msg, int line) {
		super("Error on line %d - %s".formatted(line, msg));
	}
}
