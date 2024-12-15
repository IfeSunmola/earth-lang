package sanity2;

import java.util.Map;

public sealed interface NEarthType {
	Map<String, NEarthType> strToType = Map.of(
		"int", Base.IntType,
		"float", Base.FloatType,
		"str", Base.StrType,
		"bool", Base.BoolType,
		"nada", Base.NadaType
	);

	default String string() {
		return switch (this) {
			case Base b -> b.type;
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
}
