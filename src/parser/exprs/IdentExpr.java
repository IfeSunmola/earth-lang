package parser.exprs;

import sanity.EarthType;

import static sanity.EarthType.Base.NadaType;

public record IdentExpr(String name, int line,
                        EarthType dataType) implements Expr {
	public IdentExpr(String name, int line) {
		this(name, line, null);
	}

	public static IdentExpr nada(int line) {
		return new IdentExpr(NadaType.type, line, NadaType);
	}
}
