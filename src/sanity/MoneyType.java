package sanity;

public sealed interface MoneyType {
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

