package ifesunmola.parser.exprs;


import ifesunmola.sanity.EarthType;

import static ifesunmola.sanity.EarthType.Base.NadaType;

public record IdentExpr(String name, int line,
                        EarthType dataType) implements Expr {
	public IdentExpr(String name, int line) {
		this(name, line, null);
	}

	public static IdentExpr nada(int line) {
		return new IdentExpr(NadaType.type, line, NadaType);
	}
}
