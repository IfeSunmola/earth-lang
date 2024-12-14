package parser.exprs;

import parser.sanity2.BuiltInTypes;

public sealed interface LitExpr extends Expr {
	default String value() {
		return switch (this) {
			case Int i -> String.valueOf(i.num());
			case Str s -> s.str();
			case Bool b -> String.valueOf(b.bool());
			case Float f -> String.valueOf(f.num());
			case Nada _ -> BuiltInTypes.NADA;
		};
	}

	default String type() {
		return switch (this) {
			case Int _ -> BuiltInTypes.INT;
			case Str _ -> BuiltInTypes.STRING;
			case Bool _ -> BuiltInTypes.BOOL;
			case Float _ -> BuiltInTypes.FLOAT;
			case Nada _ -> BuiltInTypes.NADA;
		};
	}

	record Int(int num, int line) implements LitExpr {}

	record Str(String str, int line) implements LitExpr {}

	record Bool(boolean bool, int line) implements LitExpr {}

	record Float(double num, int line) implements LitExpr {}

	record Nada(int line) implements LitExpr {}
}
