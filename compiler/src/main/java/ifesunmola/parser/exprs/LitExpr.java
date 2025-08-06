package ifesunmola.parser.exprs;


import ifesunmola.sanity.EarthType;

import static ifesunmola.sanity.EarthType.Base.*;

public sealed interface LitExpr extends Expr {
	default String value() {
		return switch (this) {
			case Int i -> String.valueOf(i.num());
			case Str s -> s.str();
			case Bool b -> String.valueOf(b.bool());
			case Float f -> String.valueOf(f.num());
			case Nada _ -> NadaType.type;
		};
	}

	default String type() {
		return switch (this) {
			case Int _ -> IntType.type;
			case Str _ -> StrType.type;
			case Bool _ -> BoolType.type;
			case Float _ -> FloatType.type;
			case Nada _ -> NadaType.type;
		};
	}

	default EarthType dataType() {
		return switch (this) {
			case Int _ -> IntType;
			case Str _ -> StrType;
			case Bool _ -> BoolType;
			case Float _ -> FloatType;
			case Nada _ -> NadaType;
		};
	}

	record Int(int num, int line) implements LitExpr {}

	record Str(String str, int line) implements LitExpr {}

	record Bool(boolean bool, int line) implements LitExpr {}

	record Float(double num, int line) implements LitExpr {}

	record Nada(int line) implements LitExpr {}
}
