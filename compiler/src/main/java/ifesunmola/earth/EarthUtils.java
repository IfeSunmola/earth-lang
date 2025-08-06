package ifesunmola.earth;

import java.io.IOException;
import java.lang.classfile.ClassFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

import static ifesunmola.earth.EarthMain.DEBUG;
import static java.util.logging.Level.SEVERE;

@SuppressWarnings("preview")
public class EarthUtils {
	private static final String JAVA_PATH = "earth_jre/bin/java";
	public static final String COMPILER_NAME_VERSION = "Earth Compiler V0.0.1";
	public static final String COMPILER_NAME = "earth";
	public static final Logger LOGGER = Logger.getGlobal();

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

	/// Ensures that a condition is true, otherwise throws an AssertionError with
	/// the given message
	///
	/// @param condition The condition to check
	/// @param msg The message to include in the AssertionError
	/// @throws AssertionError If the condition is false
	public static void ensure(boolean condition, String msg) {
		if (!condition)
			throw new AssertionError("Assertion Failed: " + msg);
	}

	public static String formatError(String type, String msg, int line) {
		return "%s on line %d: %s".formatted(type, line, msg);
	}

	/// Writes the given bytes to a file with the same name as the given path,
	///  but
	/// with a .class extension
	///
	/// @param bytes The bytes to write to the file
	/// @param fpath The path str to write to; will be appended with .class
	/// @return The path to the written file
	public static Path writeToFile(byte[] bytes, String fpath,
	                               boolean printMsg) {
		try {
			Path path = Path.of(fpath);
			path = removeExt(path);
			path = path.resolveSibling(path.getFileName() + ".class");

			Files.write(path, bytes);
			if (printMsg) System.out.printf("""
				Compiled to: %s
				Run with: earth run %s
				""", path, path);
			return path;
		}
		catch (IOException e) {
			if (DEBUG) LOGGER.log(SEVERE, e.getMessage(), e);
			else System.err.println("Failed to write class file: " + e.getMessage());

			System.exit(1);
			return null;
		}
	}

	/// Runs a class file at the given path using the custom Java runtime
	///
	/// @param path The path to the class file to run
	public static void runClassFile(Path path) {
		if (!Files.exists(path)) {
			System.err.println("File not found: " + path);
			System.exit(1);
		}

		try {
			List<VerifyError> verify = ClassFile.of().verify(path);
			if (!verify.isEmpty()) {
				System.err.println("Invalid class file for the following reasons: ");
				verify.forEach(e -> System.err.println("  " + e.getMessage()));
				System.exit(1);
			}
		}
		catch (IOException e) {
			if (DEBUG) LOGGER.log(SEVERE, e.getMessage(), e);
			else System.err.println("Could not verify class file: " + e.getMessage());

			System.exit(1);
		}

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
			if (DEBUG) LOGGER.log(SEVERE, e.getMessage(), e);
			else System.err.println("Failed to run class file: " + e.getMessage());

			System.exit(1);
		}
	}

	/// Validates that the custom Java runtime is available Exits the program
	/// with
	/// an error message if it's not found Otherwise, does nothing
	public static void validateJavaRuntime() {
		var processBuilder = new ProcessBuilder(JAVA_PATH, "--version");
		processBuilder.redirectErrorStream(true);

		try {
			var process = processBuilder.start();
			process.waitFor();
		}
		catch (IOException | InterruptedException e) {
			if (DEBUG) LOGGER.log(SEVERE, e.getMessage(), e);
			else {
				System.err.println("""
					Earth's Java Runtime Environment not found.
					Make sure it's in the same directory as the compiler binary, and is
					named 'earth_jre'
					""".strip()
				);
			}
			System.exit(1);
		}
	}

	/// Removes the extension from a path
	public static Path removeExt(Path path) {
		String temp = path.getFileName().toString();
		temp = temp.substring(0, temp.lastIndexOf('.'));
		return path.resolveSibling(temp);
	}
}
