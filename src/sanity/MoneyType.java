package sanity;

import java.util.List;

public sealed interface MoneyType {
	static MoneyType fromString(String type) {
		return switch (type) {
			case "int" -> Base.INT;
			case "float" -> Base.FLOAT;
			case "str" -> Base.STRING;
			case "bool" -> Base.BOOL;
			case "void" -> Base.VOID;
			default -> throw new IllegalArgumentException("Unknown type: " + type);
		};
	}

	default boolean stringEquals(String strType) {
		return toString().equals(strType);
	}

	default boolean isBase() {
		return this instanceof Base;
	}

	default boolean is(MoneyType type) {
		return switch (type) {
			case Base base -> base == this;
			case Func func -> func.equals(this);
		};
	}

	static boolean isKnown(String strType) {
		return switch (strType) {
			case "int", "float", "str", "bool", "void" -> true;
			default -> false;
		};
	}

	enum Base implements MoneyType {
		INT("int"),
		FLOAT("float"),
		STRING("str"),
		BOOL("bool"),
		VOID("void");

		private final String type; // access with toString() for consistency

		Base(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return type;
		}
	}

	record Func(List<Base> params, Base returnType) implements MoneyType {
		@Override
		public String toString() {
			return "fn(%s)%s".formatted(
				params.stream()
					.map(MoneyType::toString)
					.reduce((a, b) -> a + ", " + b)
					.orElse(""),
				returnType
			);
		}
	}
}

