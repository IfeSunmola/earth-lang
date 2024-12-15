package sanity2;

import lexer.TokenType;
import parser.ast_helpers.ExprList;
import parser.exprs.*;
import sanity2.NEarthType.Base;

import static earth.EarthUtils.ordinal;
import static lexer.TokenType.PLus;
import static sanity2.NEarthType.Base.*;

/// The purpose of this class is to find the type of an expression. If the
/// type of the expression is not what's expected, then a `SanityException` is
/// thrown
interface ExprResolver {
	SymbolTable table = SymbolTable.instance;

	static NEarthType exprType(Expr expr) {
		return switch (expr) {
			case FnCallExpr e -> fnCallExprType(e);
			case GroupedExpr e -> exprType(e.expr());
			case IdentExpr e -> identExprType(e);
			case LitExpr e -> litExprType(e);
			case AdditiveExpr e -> additiveExprType(e);
			case EqualityExpr e -> equalityExprType(e);
			case LogicalExpr e -> logicalExprType(e);
			case NegExpr e -> negExprType(e);
			case NotExpr e -> notExprType(e);
			case ProductExpr e -> productExprType(e);
			case RelationalExpr e -> relationalExprType(e);
		};
	}

	/// The type of an identifier is the type of the variable it refers to
	/// in the symbol table
	private static NEarthType identExprType(IdentExpr e) {
		String name = e.name();

		return table.findInAllScopes(name)
			.map(Symbol::type)
			.orElseThrow(() -> new SanityException(
				"Could not resolve type of `%s`. Has it been declared?"
					.formatted(name),
				e.line()
			));
	}

	/// Both operands must be booleans, and the result is a boolean
	private static NEarthType logicalExprType(LogicalExpr e) {
		NEarthType leftType = exprType(e.left());
		TokenType op = e.op();
		NEarthType rightType = exprType(e.right());

		if (leftType == BoolType && rightType == BoolType) {
			return BoolType;
		}

		throw new SanityException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(op.desc, leftType.string(), rightType.string()),
			e.line()
		);
	}

	/// `*`, `/`, `%` are only valid on integers and floats, and results in an
	/// integer or a float, depending on the combination.\
	/// Any operation involving a float results in a float.\
	private static NEarthType productExprType(ProductExpr e) {
		NEarthType leftType = exprType(e.left());
		TokenType op = e.op();
		NEarthType rightType = exprType(e.right());

		if (!leftType.isOneOf(IntType, FloatType) ||
		    !rightType.isOneOf(IntType, FloatType)) {
			throw new SanityException(
				"`%s` is not a valid operator on %s and %s"
					.formatted(op.desc, leftType.string(), rightType.string()),
				e.line()
			);
		}

		// Safe to assume only IntType and FloatType from here on
		return switch (op) {
			case Star -> {
				// int * int = int
				if (leftType == IntType && rightType == IntType) yield IntType;

				// int * float = float
				if (leftType == FloatType || rightType == FloatType) yield FloatType;

				throw new SanityException(
					"`%s` is not a valid operator on %s and %s"
						.formatted(op.desc, leftType.string(), rightType.string()),
					e.line()
				);
			}
			case Slash -> FloatType;
			case Mod -> {
				// int % int = int
				if (leftType == IntType && rightType == IntType) yield IntType;

				// int % float = float
				if (leftType == FloatType || rightType == FloatType) yield FloatType;

				throw new SanityException(
					"`%s` is not a valid operator on %s and %s"
						.formatted(op.desc, leftType.string(), rightType.string()),
					e.line()
				);
			}
			default -> throw new AssertionError("Should not happen: " + op);
		};
	}

	/// `<`, `>`, `<=`, `>=` are only valid on integers and floats, and results
	/// in a boolean.
	private static NEarthType relationalExprType(RelationalExpr e) {
		NEarthType leftType = exprType(e.left());
		TokenType op = e.op();
		NEarthType rightType = exprType(e.right());

		if (leftType.isOneOf(IntType, FloatType) &&
		    rightType.isOneOf(IntType, FloatType)) {
			return BoolType;
		}

		throw new SanityException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(op.desc, leftType.string(), rightType.string()),
			e.line()
		);
	}

	/// The type a of function call is the return type of the function
	private static NEarthType fnCallExprType(FnCallExpr e) {
		String fnName = e.name().name();
		int line = e.line();
		ExprList params = e.params();

		// Find the symbol
		Symbol symbol = table.findInAllScopes(fnName)
			.orElseThrow(() -> new SanityException(
				"Unknown identifier: %s".formatted(fnName),
				line
			));

		// Now, we know the symbol exists, check if it's a function
		if (symbol.kind() != Kind.FnDecl) {
			throw new SanityException(
				"%s is not a function".formatted(fnName),
				line
			);
		}
		FuncType fnType = (FuncType) symbol.type();

		// Then, check that the number of arguments matches what was stored in
		// the symbol table
		if (params.size() != fnType.params().size()) {
			throw new SanityException(
				"Expected %d arguments but got %d. Required signature is %s".formatted(
					fnType.params().size(), params.size(), fnType
				),
				line
			);
		}

		// Then, check that the types of the parameters matches the types that
		// was stored in the symbol table
		for (int i = 0; i < params.size(); i++) {
			NEarthType argType = exprType(params.get(i)); // from source code
			NEarthType paramType = fnType.params().get(i); // from symbol table

			if (argType != paramType) {
				throw new SanityException(
					"""
						Expected %s argument to be of type `%s` but got `%s`. \
						Required signature is %s
						"""
						.formatted(ordinal(i + 1), paramType.string(), argType.string(),
							fnType.string()),
					line
				);
			}
		}

		return fnType.returnType();
	}

	/// `==` and `!=` are valid on the same types. E.g. int == int, float ==
	/// float. An int can also be compared to a float. The result is a boolean.
	/// or an exception is thrown.
	private static NEarthType equalityExprType(EqualityExpr e) {
		NEarthType leftType = exprType(e.left());
		NEarthType rightType = exprType(e.right());


		if ((leftType == rightType) || (leftType.isOneOf(IntType, FloatType) &&
		                                rightType.isOneOf(IntType, FloatType))) {
			return BoolType;
		}

		throw new SanityException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(e.op().desc, leftType.string(), rightType.string()),
			e.line()
		);
	}

	/// Everything except the following are invalid:\
	/// int (+ | -) int = int\
	/// int (+ | -) float = float\
	/// str + str = str
	private static NEarthType additiveExprType(AdditiveExpr e) {
		NEarthType leftType = exprType(e.left());
		TokenType op = e.op();
		NEarthType rightType = exprType(e.right());

		if (op == PLus && leftType == StrType && rightType == StrType)
			return StrType;

		if (leftType == IntType && rightType == IntType)
			return IntType;

		if (leftType == FloatType || rightType == FloatType)
			return FloatType;

		throw new SanityException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(op.desc, leftType.string(), rightType.string()),
			e.line()
		);
	}

	/// Not is only possible on booleans, so throw an error if the type is not
	/// a boolean
	private static NEarthType notExprType(NotExpr e) {
		NEarthType exprType = exprType(e.expr());
		if (exprType == BoolType) {
			return BoolType;
		}

		throw new SanityException(
			"`!` is not a valid operator on %s".formatted(exprType),
			e.line()
		);
	}

	/// Negation is only possible on integers and floats, so throw an error if
	/// the type is neither
	private static NEarthType negExprType(NegExpr expr) {
		NEarthType exprType = exprType(expr.expr());
		if (exprType == IntType || exprType == FloatType) {
			return exprType;
		}

		throw new SanityException(
			"`-` is not a valid operator on %s".formatted(exprType),
			expr.line()
		);
	}

	private static NEarthType litExprType(LitExpr e) {
		return switch (e) {
			case LitExpr.Int _ -> IntType;
			case LitExpr.Str _ -> StrType;
			case LitExpr.Bool _ -> Base.BoolType;
			case LitExpr.Float _ -> FloatType;
			case LitExpr.Nada _ -> Base.NadaType;
		};
	}
}
