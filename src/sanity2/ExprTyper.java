package sanity2;

import lexer.TokenType;
import parser.ast_helpers.ExprList;
import parser.ast_helpers.TypedIdent;
import parser.exprs.*;

import static earth.EarthUtils.ordinal;
import static lexer.TokenType.PLus;
import static parser.exprs.BinaryExpr.*;
import static sanity2.NEarthType.Base.*;

class ExprTyper {
	/// Returns a new expression with the type set, or throws an exception if
	/// there's an error. We could probably just mutate the expression in
	/// place, but I like to keep my sanity.
	static Expr typeExpr(Expr expr) {
		return switch (expr) {
			case AdditiveExpr e -> typeAdditiveExpr(e);
			case EqualityExpr e -> typeEqualityExpr(e);
			case FnCallExpr e -> typeFnCallExpr(e);
			case GroupedExpr e -> typeExpr(e.expr());
			case IdentExpr e -> typeIdentExpr(e);
			case LitExpr e -> e; // literals are already typed
			case LogicalExpr e -> typeLogicalExpr(e);
			case NegExpr e -> typeNegExpr(e);
			case NotExpr e -> typeNotExpr(e);
			case ProductExpr e -> typeProductExpr(e);
			case RelationalExpr e -> typeRelationalExpr(e);
		};
	}

	static IdentExpr typeIdentExpr(IdentExpr e) {
		String name = e.name();

		return SymbolTable.instance.findInAllScopes(name)
			.map(symbol -> new IdentExpr(name, e.line(), symbol.type()))
			.orElseThrow(() -> new SanityException(
				"Could not resolve type of `%s`. Has it been declared?"
					.formatted(name),
				e.line()
			));
	}

	/// The type a of function call is the return type of the function
	static FnCallExpr typeFnCallExpr(FnCallExpr e) {
		String fnName = e.name().name();
		int line = e.line();
		ExprList params = e.params();

		// Find the symbol
		Symbol symbol = SymbolTable.instance.findInAllScopes(fnName)
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
		var typedParams = new ExprList();
		for (int i = 0; i < params.size(); i++) {
			Expr typedExpr = typeExpr(params.get(i));
			typedParams.add(typedExpr);

			NEarthType argType = typedExpr.dataType(); // from source code
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

		return new FnCallExpr(
			typeIdentExpr(e.name()), typedParams,
			line, fnType.returnType()
		);
	}

	/// Everything except the following are invalid:\
	/// int (+ | -) int = int\
	/// int (+ | -) float = float\
	/// str + str = str
	static AdditiveExpr typeAdditiveExpr(AdditiveExpr e) {
		Expr left = typeExpr(e.left());
		Expr right = typeExpr(e.right());
		int line = e.line();

		NEarthType leftType = left.dataType();
		TokenType op = e.op();
		NEarthType rightType = right.dataType();


		if (op == PLus && leftType == StrType && rightType == StrType)
			return new AdditiveExpr(left, op, right, line, StrType);

		if (leftType == IntType && rightType == IntType)
			return new AdditiveExpr(left, op, right, line, IntType);

		if (leftType == FloatType || rightType == FloatType)
			return new AdditiveExpr(left, op, right, line, FloatType);

		throw new SanityException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(op.desc, leftType.string(), rightType.string()),
			line
		);
	}

	/// `==` and `!=` are valid on the same types. E.g. int == int, float ==
	/// float. An int can also be compared to a float. The result is a boolean.
	/// or an exception is thrown.
	static EqualityExpr typeEqualityExpr(EqualityExpr e) {
		Expr left = typeExpr(e.left());
		Expr right = typeExpr(e.right());

		NEarthType leftType = left.dataType();
		NEarthType rightType = right.dataType();


		if ((leftType == rightType) || (leftType.isOneOf(IntType, FloatType) &&
		                                rightType.isOneOf(IntType, FloatType))) {
			return new EqualityExpr(left, e.op(), right, e.line());
		}

		throw new SanityException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(e.op().desc, leftType.string(), rightType.string()),
			e.line()
		);
	}

	/// `<`, `>`, `<=`, `>=` are only valid on integers and floats, and results
	/// in a boolean.
	static RelationalExpr typeRelationalExpr(RelationalExpr e) {
		Expr left = typeExpr(e.left());
		Expr right = typeExpr(e.right());

		NEarthType leftType = left.dataType();
		TokenType op = e.op();
		NEarthType rightType = right.dataType();

		if (leftType.isOneOf(IntType, FloatType) &&
		    rightType.isOneOf(IntType, FloatType)) {
			return new RelationalExpr(left, op, right, e.line());
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
	static ProductExpr typeProductExpr(ProductExpr e) {
		Expr left = typeExpr(e.left());
		Expr right = typeExpr(e.right());
		int line = e.line();

		NEarthType leftType = left.dataType();
		TokenType op = e.op();
		NEarthType rightType = right.dataType();

		if (!leftType.isOneOf(IntType, FloatType) ||
		    !rightType.isOneOf(IntType, FloatType)) {
			throw new SanityException(
				"`%s` is not a valid operator on %s and %s"
					.formatted(op.desc, leftType.string(), rightType.string()),
				line
			);
		}

		// Safe to assume only IntType and FloatType from here on
		return switch (op) {
			case Star -> {
				// int * int = int
				if (leftType == IntType && rightType == IntType)
					yield new ProductExpr(left, op, right, line, IntType);

				// int * float = float
				if (leftType == FloatType || rightType == FloatType)
					yield new ProductExpr(left, op, right, line, FloatType);

				throw new SanityException(
					"`%s` is not a valid operator on %s and %s"
						.formatted(op.desc, leftType.string(), rightType.string()),
					line
				);
			}
			case Slash -> new ProductExpr(left, op, right, line, FloatType);
			case Mod -> {
				// int % int = int
				if (leftType == IntType && rightType == IntType)
					yield new ProductExpr(left, op, right, line, IntType);

				// int % float = float
				if (leftType == FloatType || rightType == FloatType)
					yield new ProductExpr(left, op, right, line, FloatType);

				throw new SanityException(
					"`%s` is not a valid operator on %s and %s"
						.formatted(op.desc, leftType.string(), rightType.string()),
					line
				);
			}
			default -> throw new AssertionError("Should not happen: " + op);
		};
	}

	/// Not is only possible on booleans, so throw an error if the type is not
	/// a boolean
	static NotExpr typeNotExpr(NotExpr e) {
		Expr expr = typeExpr(e.expr());
		NEarthType exprType = expr.dataType();

		if (exprType == BoolType) {
			return new NotExpr(expr, e.line());
		}

		throw new SanityException(
			"`!` is not a valid operator on %s".formatted(exprType.string()),
			e.line()
		);
	}

	/// Negation is only possible on integers and floats, so throw an error if
	/// the type is neither
	static NegExpr typeNegExpr(NegExpr e) {
		Expr expr = typeExpr(e.expr());
		NEarthType exprType = expr.dataType();

		if (exprType == IntType || exprType == FloatType) {
			return new NegExpr(expr, e.line(), exprType);
		}

		throw new SanityException(
			"`-` is not a valid operator on %s".formatted(exprType),
			e.line()
		);
	}

	/// Both operands must be booleans, and the result is a boolean.
	static LogicalExpr typeLogicalExpr(LogicalExpr e) {
		Expr left = typeExpr(e.left());
		Expr right = typeExpr(e.right());

		NEarthType leftType = left.dataType();
		TokenType op = e.op();
		NEarthType rightType = right.dataType();

		if (leftType == BoolType && rightType == BoolType) {
			return new LogicalExpr(left, op, right, e.line());
		}

		throw new SanityException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(op.desc, leftType.string(), rightType.string()),
			e.line()
		);
	}

	// AST Helpers
	static TypedIdent typedIdentExpr(TypedIdent param) {
		return new TypedIdent(
			typeIdentExpr(param.name()),
			typeIdentExpr(param.type())
		);
	}
}
