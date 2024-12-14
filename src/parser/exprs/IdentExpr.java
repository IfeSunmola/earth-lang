package parser.exprs;

import parser.sanity2.BuiltInTypes;

public record IdentExpr(String name, int line) implements Expr {
	public static IdentExpr nada(int line) {
		return new IdentExpr(BuiltInTypes.NADA, line);
	}
}
