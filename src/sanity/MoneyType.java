package sanity;

public sealed interface MoneyType {
	static MoneyType fromString(String type) {
		return switch (type) {
			case "int" -> Base.INT;
			case "float" -> Base.FLOAT;
			case "str" -> Base.STRING;
			case "bool" -> Base.BOOL;
			default -> throw new IllegalArgumentException("Unknown type: " + type);
		};
	}

	default boolean stringEquals(String strType) {
		return toString().equals(strType);
	}

	static boolean isKnown(String strType) {
		return switch (strType) {
			case "int", "float", "str", "bool" -> true;
			default -> false;
		};
	}

	enum Base implements MoneyType {
		INT("int"),
		FLOAT("float"),
		STRING("str"),
		BOOL("bool");

		private final String type; // access with toString() for consistency

		Base(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return type;
		}
	}
}

