package sanity;

import antlr.MoneyParser.*;
import antlr.MoneyParserBaseVisitor;
import money.MoneyException;

import static money.MoneyUtils.ordinal;
import static sanity.MoneyType.Base;

/// Resolves expressions by validating them, and returning the type if it's
/// valid, or throwing an exception if it's not.
class ExprResolver extends MoneyParserBaseVisitor<MoneyType> {
	private final SymbolTable table = SymbolTable.instance;

	@Override
	public MoneyType visitNegExpr(NegExprContext ctx) {
		// -23, -23.0, -[ident], where ident is an int or float
		MoneyType exprType = visit(ctx.expr());
		if (exprType == Base.INT || exprType == Base.FLOAT)
			return exprType;

		throw new MoneyException(
			"`-` is not a valid operator on %s".formatted(exprType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public MoneyType visitNotExpr(NotExprContext ctx) {
		// !true, !false, ![expr], where expr is a boolean
		MoneyType exprType = visit(ctx.expr());
		if (exprType == Base.BOOL) return Base.BOOL;

		throw new MoneyException(
			"`!` is not a valid operator on %s".formatted(exprType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public MoneyType visitMultiplicationExpr(MultiplicationExprContext ctx) {
		// *, /, %, where both operands are int or float
		// If one of the operands is a float, the result is a float
		// if one of the operands is not a float or int, throw an exception
		MoneyType leftType = visit(ctx.expr(0));
		MoneyType rightType = visit(ctx.expr(1));

		if (leftType == Base.INT && rightType == Base.INT)
			return Base.INT;

		if (leftType == Base.FLOAT || rightType == Base.FLOAT)
			return Base.FLOAT;

		throw new MoneyException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(ctx.op.getText(), leftType, rightType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public MoneyType visitAdditiveExpr(AdditiveExprContext ctx) {
		// + and -
		// plus supports string concatenation
		// Aside from string concatenation, the operands must be either an int or
		// a float.

		MoneyType leftType = visit(ctx.expr(0));
		String op = ctx.op.getText();
		MoneyType rightType = visit(ctx.expr(1));

		// string concatenation
		if (op.equals("+") && leftType == Base.STRING && rightType == Base.STRING)
			return Base.STRING;

		// checking that the operands are either int or float
		if ((leftType == Base.INT || leftType == Base.FLOAT) &&
		    (rightType == Base.INT || rightType == Base.FLOAT)) {
			// if one of the operands is a float, the result is a float
			if (leftType == Base.FLOAT || rightType == Base.FLOAT) return Base.FLOAT;
			// otherwise, the result is an int
			return Base.INT;
		}

		throw new MoneyException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(op, leftType, rightType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public MoneyType visitRelationalExpr(RelationalExprContext ctx) {
		// <, <=, >, >=
		// The operands must be either an int or a float. Result is a boolean
		MoneyType leftType = visit(ctx.expr(0));
		String op = ctx.op.getText();
		MoneyType rightType = visit(ctx.expr(1));

		// checking that the operands are either int or float
		if ((leftType == Base.INT || leftType == Base.FLOAT) &&
		    (rightType == Base.INT || rightType == Base.FLOAT)) {
			return Base.BOOL;
		}

		throw new MoneyException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(op, leftType, rightType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public MoneyType visitEqualityExpr(EqualityExprContext ctx) {
		// ==, !=
		// The operands must be of the same type.
		// The operands must be a base type (int, float, string, or boolean).
		// Result is a boolean
		MoneyType leftType = visit(ctx.expr(0));
		String op = ctx.op.getText();
		MoneyType rightType = visit(ctx.expr(1));

		if (leftType == rightType && leftType.isBase()) return Base.BOOL;

		throw new MoneyException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(op, leftType, rightType),
			ctx.getStart().getLine()
		);


	}

	@Override
	public MoneyType visitAndExpr(AndExprContext ctx) {
		// &&
		// The operands must be boolean. Result is a boolean
		MoneyType leftType = visit(ctx.expr(0));
		MoneyType rightType = visit(ctx.expr(1));

		if (leftType == Base.BOOL && rightType == Base.BOOL) return Base.BOOL;

		throw new MoneyException(
			"`&&` is not a valid operator on %s and %s"
				.formatted(leftType, rightType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public MoneyType visitOrExpr(OrExprContext ctx) {
		// ||
		// The operands must be boolean. Result is a boolean
		MoneyType leftType = visit(ctx.expr(0));
		MoneyType rightType = visit(ctx.expr(1));

		if (leftType == Base.BOOL && rightType == Base.BOOL) return Base.BOOL;

		throw new MoneyException(
			"`||` is not a valid operator on %s and %s"
				.formatted(leftType, rightType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public MoneyType visitGroupedExpr(GroupedExprContext ctx) {
		// (expr)
		return visit(ctx.expr());
	}

	@Override
	public MoneyType visitLiteralExpr(LiteralExprContext ctx) {
		if (ctx.FloatLit() != null) return Base.FLOAT;
		if (ctx.IntLit() != null) return Base.INT;
		if (ctx.StrLit() != null) return Base.STRING;
		if (ctx.BoolLit() != null) return Base.BOOL;

		throw new RuntimeException("Should not reach here");
	}

	@Override
	public MoneyType visitUntypedIdentExpr(UntypedIdentExprContext ctx) {
		String name = ctx.UntypedIdent().getText();

		return table.findInAllScopes(name)
			.map(Symbol::type)
			.orElseThrow(() -> new MoneyException(
				"Could not resolve type of `%s`. Has it been declared?".formatted(name),
				ctx.getStart().getLine()
			));
	}

	@Override
	public MoneyType visitFnCallExpr(FnCallExprContext ctx) {
		// first, check that the function is a valid function
		String fnName = ctx.fnName.getText();
		Symbol symbol = table.findInAllScopes(fnName)
			.orElseThrow(() -> new MoneyException(
				"`%s` is not a known identifier".formatted(fnName),
				ctx.getStart().getLine()
			));

		// Second, It's an identifier, check if it's a function
		if (symbol.kind() != Kind.Function ||
		    !(symbol.type() instanceof MoneyType.Func fnType)) {
			throw new MoneyException(
				"`%s` is not a function".formatted(fnName),
				ctx.getStart().getLine()
			);
		}

		// Third, check that the number of arguments is correct
		ExprListContext params = ctx.params;
		// params is what the user passed
		// fnType is what the function expects from the symbol table
		if (params.expr().size() != fnType.params().size()) {
			throw new MoneyException(
				"Expected %d arguments but got %d. Required signature is %s".formatted(
					fnType.params().size(), params.expr().size(), fnType
				),
				ctx.getStart().getLine()
			);
		}

		// Fourth, check that the types of the arguments match the types of the
		// parameters of the function signature
		for (int i = 0; i < params.expr().size(); i++) {
			MoneyType argType = visit(params.expr(i)); // type of argument
			Base paramType = fnType.params().get(i);
			if (argType != paramType) {
				throw new MoneyException(
					"""
						Expected %s argument to be of type `%s` but got `%s`. \
						Required signature is %s
						""".formatted(ordinal(i + 1), paramType, argType, fnType),
					ctx.getStart().getLine()
				);
			}
		}
		return fnType.returnType();
	}
}
