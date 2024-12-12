package earth;

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
}
