package ifesunmola.earth;

import java.util.List;

@SuppressWarnings("unused")
public sealed interface EarthResult <V> {
	record Ok <T>(T value) implements EarthResult<T> {}

	record Err <T>(List<String> errors) implements EarthResult<T> {}

	static <T> EarthResult<T> ok(T value) {
		return new Ok<>(value);
	}

	static <T> Err<T> err(List<String> errors) {
		return new Err<>(errors);
	}

	default boolean isErr() {
		return this instanceof Err;
	}

	@SuppressWarnings("unchecked")
	default <T> T value() {
		if (this instanceof Ok<?> ok) return (T) ok.value;
		throw new AssertionError("Nope, not allowed.");
	}

	default List<String> errors() {
		if (this instanceof Err<?> err) return err.errors;
		throw new AssertionError("Nope, not allowed.");
	}

	default void quitOnError() {
		if (isErr()) {
			errors().forEach(System.err::println);
			System.exit(1);
		}
	}
}
