package parser.exprs;

import sanity2.NEarthType;

import static sanity2.NEarthType.Base.NadaType;

public record IdentExpr(String name, int line,
                        NEarthType dataType) implements Expr {
	public IdentExpr(String name, int line) {
		this(name, line, null);
	}

	public static IdentExpr nada(int line) {
		return new IdentExpr(NadaType.type, line, NadaType);
	}
}
