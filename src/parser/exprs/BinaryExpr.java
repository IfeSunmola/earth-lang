package parser.exprs;

import lexer.TokenType;
import sanity.EarthType;

public sealed interface BinaryExpr extends Expr {
	Expr left();

	TokenType op();

	Expr right();

	/// expr (`+` OR `-`) expr
	record AdditiveExpr(Expr left, TokenType op, Expr right,
	                    int line, EarthType dataType) implements BinaryExpr {

		public AdditiveExpr(Expr left, TokenType op, Expr right, int line) {
			this(left, op, right, line, null);
		}
	}

	/// expr (`==` OR `!=`) expr
	record EqualityExpr(Expr left, TokenType op, Expr right,
	                    int line) implements BinaryExpr {
		@Override
		public EarthType dataType() {
			return EarthType.Base.BoolType;
		}
	}

	/// expr (`&&` OR `||`) expr
	record LogicalExpr(Expr left, TokenType op, Expr right,
	                   int line) implements BinaryExpr {
		@Override
		public EarthType dataType() {
			return EarthType.Base.BoolType;
		}
	}

	/// expr (`*` OR `/` OR `%`) expr
	record ProductExpr(Expr left, TokenType op, Expr right,
	                   int line, EarthType dataType) implements BinaryExpr {

		public ProductExpr(Expr left, TokenType op, Expr right, int line) {
			this(left, op, right, line, null);
		}
	}

	/// expr (`<` OR `<=` OR `>` OR `>=`) expr
	record RelationalExpr(Expr left, TokenType op, Expr right,
	                      int line) implements BinaryExpr {
		@Override
		public EarthType dataType() {
			return EarthType.Base.BoolType;
		}
	}
}
