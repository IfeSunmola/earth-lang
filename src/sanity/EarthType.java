package sanity;

import java.lang.classfile.TypeKind;
import java.lang.constant.ClassDesc;
import java.util.List;

@SuppressWarnings("preview")
public sealed interface EarthType {
	static EarthType fromString(String type) {
		return switch (type) {
			case "int" -> Base.INT;
			case "float" -> Base.FLOAT;
			case "str" -> Base.STRING;
			case "bool" -> Base.BOOL;
			case "void" -> Base.VOID;
			default -> throw new IllegalArgumentException("Unknown type: " + type);
		};
	}

	static EarthType fromClassDesc(ClassDesc classDesc) {
		return switch (classDesc.displayName()) {
			case "int" -> Base.INT;
			case "float" -> Base.FLOAT;
			case "String" -> Base.STRING;
			case "boolean" -> Base.BOOL;
			case "void" -> Base.VOID;
			default ->
				throw new IllegalStateException("Unexpected value: " + classDesc.displayName());
		};
	}

	default boolean stringEquals(String strType) {
		return toString().equals(strType);
	}

	default TypeKind toTypeKind() {
		return switch (this) {
			case Base.INT -> TypeKind.IntType;
			case Base.FLOAT -> TypeKind.FloatType;
			case Base.STRING -> TypeKind.ReferenceType;
			case Base.BOOL -> TypeKind.BooleanType;
			case Base.VOID -> throw new RuntimeException();
			case Func _ -> throw new RuntimeException();
		};
	}

	default boolean isBase() {
		return this instanceof Base;
	}

	default boolean is(EarthType type) {
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

	enum Base implements EarthType {
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

	record Func(List<Base> params, Base returnType) implements EarthType {
		@Override
		public String toString() {
			return "fn(%s)%s".formatted(
				params.stream()
					.map(EarthType::toString)
					.reduce((a, b) -> a + ", " + b)
					.orElse(""),
				returnType
			);
		}
	}
}

