package sanity2;

import java.util.List;
import java.util.Map;

public sealed interface NEarthType {
	Map<String, NEarthType> strToType = Map.of(
		"int", Base.IntType,
		"float", Base.FloatType,
		"str", Base.StrType,
		"bool", Base.BoolType,
		"nada", Base.NadaType
	);

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
					.map(NEarthType::toString)
					.reduce((a, b) -> a + ", " + b)
					.orElse(""),
				returnType
			);
		}
	}
}
