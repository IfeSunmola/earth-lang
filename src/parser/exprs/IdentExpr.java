package parser.exprs;

import static sanity2.NEarthType.Base.NadaType;

public record IdentExpr(String name, int line) implements Expr {
	public static IdentExpr nada(int line) {
		return new IdentExpr(NadaType.type, line);
	}
}
