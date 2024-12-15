package sanity2;

import java.util.List;

public sealed interface NEarthType {
	static NEarthType fromString(String s, int line) {
		return switch (s) {
			case "int" -> Base.IntType;
			case "float" -> Base.FloatType;
			case "str" -> Base.StrType;
			case "bool" -> Base.BoolType;
			case "nada" -> Base.NadaType;
			// TODO: Implement function type
			default -> throw new SanityException(
				"Invalid type: `%s`".formatted(s),
				line);
		};
	}

	default boolean isOneOf(NEarthType... types) {
		for (NEarthType t : types) {
			if (this == t) {
				return true;
			}
		}
		return false;
	}

	default String string() {
		return switch (this) {
			case Base b -> b.type;
			case FuncType funcType -> funcType.toString();
		};
	}

	enum Base implements NEarthType {
		IntType("int"),
		FloatType("float"),
		BoolType("bool"),
		StrType("str"),
		NadaType("nada");

		public final String type;

		Base(String s) {
			this.type = s;
		}
	}

	record FuncType(List<Base> params, Base returnType) implements NEarthType {
		@Override
		public String toString() {
			return "fn(%s)%s".formatted(
				params.stream()
					.map(base -> base.type)
					.reduce((a, b) -> a + ", " + b)
					.orElse(""),
				returnType.type
			);
		}
	}
}
