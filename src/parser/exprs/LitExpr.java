package parser.exprs;

import lexer.TokenType;

public sealed interface LitExpr extends Expr {
	default String value() {
		return switch (this) {
			case Int i -> String.valueOf(i.num());
			case Str s -> s.str();
			case Bool b -> String.valueOf(b.bool());
			case Float f -> String.valueOf(f.num());
			case Nada _ -> TokenType.NadaType.desc;
		};
	}

	record Int(int num, int line) implements LitExpr {}

	record Str(String str, int line) implements LitExpr {}

	record Bool(boolean bool, int line) implements LitExpr {}

	record Float(double num, int line) implements LitExpr {}

	record Nada(int line) implements LitExpr {}
}
