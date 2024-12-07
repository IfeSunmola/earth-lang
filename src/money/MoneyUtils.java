package money;

import java.lang.constant.ClassDesc;

public class MoneyUtils {
	public static final ClassDesc CD_StringBuilder =
		ClassDesc.ofInternalName("java/lang/StringBuilder");

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

	public static void ensure(boolean condition) {
		if (!condition)
			throw new AssertionError("Assertion Failed");
	}

	public static void ensure(boolean condition, String msg) {
		if (!condition)
			throw new AssertionError("Assertion Failed: " + msg);
	}
}
