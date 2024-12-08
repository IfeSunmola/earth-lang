package money;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MoneyUtils {
	private static final String JAVA_PATH = "java-runtime/bin/java";

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

	/// Writes the given bytes to a file with the same name as the given path,
	/// but with a .class extension
	///
	/// @param bytes The bytes to write to the file
	/// @param path  The path to the file to write to; will be appended with
	///
	///                                              .class
	/// @return The path to the written file
	public static Path writeToFile(byte[] bytes, Path path) {
		try {
			path = path.resolveSibling(path.getFileName() + ".class");
			return Files.write(path, bytes);
		}
		catch (IOException e) {
			System.err.println("Failed to write class file: " + e.getMessage());
			System.exit(1);
			return null;
		}
	}

	/// Runs a class file at the given path using the custom Java runtime
	///
	/// @param path The path to the class file to run
	public static void runClassFile(Path path) {
		Path parent = path.getParent();
		String cp = parent != null ? parent.toString() : "";

		try {
			var process = new ProcessBuilder(
				JAVA_PATH,
				"-cp", cp,
				path.getFileName().toString().replace(".class", "")
			)
				.inheritIO()
				.start();
			process.waitFor();
		}
		catch (IOException | InterruptedException e) {
			System.err.println("Failed to run class file: " + e.getMessage());
			System.exit(1);
		}
	}
}
