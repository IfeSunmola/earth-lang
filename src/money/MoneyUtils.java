package money;

public class MoneyUtils {
	/// Returns the ordinal suffix for a number. E.g. 1 -> "1st", 2 -> "2nd"
	///
	/// @param n The number to get the suffix for
	/// @return The ordinal suffix for the number
	public static String ordinal(int n) {
		if (n >= 10 && n <= 20) return n + "th";
		switch (n % 10) {
			case 1 -> {
				return n + "st";
			}
			case 2 -> {
				return n + "nd";
			}
			case 3 -> {
				return n + "rd";
			}
			default -> {
				return n + "th";
			}
		}
	}

	/// Ensures that a condition is true, otherwise throws an AssertionError
	/// with the message "Assertion Failed"
	///
	/// @param condition The condition to check
	/// @throws AssertionError If the condition is false
	/// @see #ensure(boolean, String)
	public static void ensure(boolean condition) {
		if (!condition)
			throw new AssertionError("Assertion Failed");
	}

	/// Ensures that a condition is true, otherwise throws an AssertionError
	/// with the given message
	///
	/// @param condition The condition to check
	/// @param msg       The message to include in the AssertionError
	/// @throws AssertionError If the condition is false
	/// @see #ensure(boolean)
	public static void ensure(boolean condition, String msg) {
		if (!condition)
			throw new AssertionError("Assertion Failed: " + msg);
	}
}
