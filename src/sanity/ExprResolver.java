package sanity;

import antlr.EarthParser.*;
import antlr.EarthParserBaseVisitor;
import earth.EarthException;
import earth.EarthUtils;
import org.antlr.v4.runtime.tree.ParseTree;

import static earth.EarthUtils.LOGGER;
import static earth.EarthUtils.ordinal;
import static sanity.EarthType.Base;

/// Resolves expressions by validating them, and returning the type if it's
/// valid, or throwing an exception if it's not.
public class ExprResolver extends EarthParserBaseVisitor<EarthType> {
	private final SymbolTable table = SymbolTable.instance;

	@Override
	public EarthType visit(ParseTree tree) {
		try {
			return super.visit(tree);
		}
		catch (EarthException e) {
			if (EarthUtils.DEBUG) LOGGER.severe(e.getMessage());
			else System.err.println(e.getMessage());
			System.exit(1);
			return null;
		}
	}

	@Override
	public EarthType visitNegExpr(NegExprContext ctx) {
		// -23, -23.0, -[ident], where ident is an int or float
		EarthType exprType = visit(ctx.expr());
		if (exprType == Base.INT || exprType == Base.FLOAT)
			return exprType;

		throw new EarthException(
			"`-` is not a valid operator on %s".formatted(exprType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public EarthType visitNotExpr(NotExprContext ctx) {
		// !true, !false, ![expr], where expr is a boolean
		EarthType exprType = visit(ctx.expr());
		if (exprType == Base.BOOL) return Base.BOOL;

		throw new EarthException(
			"`!` is not a valid operator on %s".formatted(exprType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public EarthType visitMultiplicationExpr(MultiplicationExprContext ctx) {
		// *, /, %, where both operands are int or float
		// If one of the operands is a float, the result is a float
		// if one of the operands is not a float or int, throw an exception
		EarthType leftType = visit(ctx.left);
		EarthType rightType = visit(ctx.right);

		if (leftType == Base.INT && rightType == Base.INT)
			return Base.INT;

		if (leftType == Base.FLOAT || rightType == Base.FLOAT)
			return Base.FLOAT;

		throw new EarthException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(ctx.op.getText(), leftType, rightType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public EarthType visitAdditiveExpr(AdditiveExprContext ctx) {
		// + and -
		// plus supports string concatenation
		// Aside from string concatenation, the operands must be either an int or
		// a float.

		EarthType leftType = visit(ctx.left);
		String op = ctx.op.getText();
		EarthType rightType = visit(ctx.right);

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

		throw new EarthException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(op, leftType, rightType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public EarthType visitRelationalExpr(RelationalExprContext ctx) {
		// <, <=, >, >=
		// The operands must be either an int or a float. Result is a boolean
		EarthType leftType = visit(ctx.left);
		String op = ctx.op.getText();
		EarthType rightType = visit(ctx.right);

		// checking that the operands are either int or float
		if ((leftType == Base.INT || leftType == Base.FLOAT) &&
		    (rightType == Base.INT || rightType == Base.FLOAT)) {
			return Base.BOOL;
		}

		throw new EarthException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(op, leftType, rightType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public EarthType visitEqualityExpr(EqualityExprContext ctx) {
		// ==, !=
		// The operands must be of the same type.
		// The operands must be a base type (int, float, string, or boolean).
		// Result is a boolean
		EarthType leftType = visit(ctx.left);
		String op = ctx.op.getText();
		EarthType rightType = visit(ctx.right);

		if (leftType == rightType && leftType.isBase()) return Base.BOOL;

		throw new EarthException(
			"`%s` is not a valid operator on %s and %s"
				.formatted(op, leftType, rightType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public EarthType visitAndExpr(AndExprContext ctx) {
		// &&
		// The operands must be boolean. Result is a boolean
		EarthType leftType = visit(ctx.left);
		EarthType rightType = visit(ctx.right);

		if (leftType == Base.BOOL && rightType == Base.BOOL) return Base.BOOL;

		throw new EarthException(
			"`&&` is not a valid operator on %s and %s"
				.formatted(leftType, rightType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public EarthType visitOrExpr(OrExprContext ctx) {
		// ||
		// The operands must be boolean. Result is a boolean
		EarthType leftType = visit(ctx.left);
		EarthType rightType = visit(ctx.right);

		if (leftType == Base.BOOL && rightType == Base.BOOL) return Base.BOOL;

		throw new EarthException(
			"`||` is not a valid operator on %s and %s"
				.formatted(leftType, rightType),
			ctx.getStart().getLine()
		);
	}

	@Override
	public EarthType visitGroupedExpr(GroupedExprContext ctx) {
		// (expr)
		return visit(ctx.expr());
	}

	@Override
	public EarthType visitLiteralExpr(LiteralExprContext ctx) {
		if (ctx.FloatLit() != null) return Base.FLOAT;
		if (ctx.IntLit() != null) return Base.INT;
		if (ctx.StrLit() != null) return Base.STRING;
		if (ctx.BoolLit() != null) return Base.BOOL;

		throw new RuntimeException("Should not reach here");
	}

	@Override
	public EarthType visitUntypedIdentExpr(UntypedIdentExprContext ctx) {
		String name = ctx.UntypedIdent().getText();

		return table.findInAllScopes(name)
			.map(Symbol::type)
			.orElseThrow(() -> new EarthException(
				"Could not resolve type of `%s`. Has it been declared?".formatted(name),
				ctx.getStart().getLine()
			));
	}

	@Override
	public EarthType visitFnCallExpr(FnCallExprContext ctx) {
		// first, check that the function is a valid function
		String fnName = ctx.fnName.getText();
		Symbol symbol = table.findInAllScopes(fnName)
			.orElseThrow(() -> new EarthException(
				"`%s` is not a known identifier".formatted(fnName),
				ctx.getStart().getLine()
			));

		// Second, It's an identifier, check if it's a function
		if (symbol.kind() != Kind.FnDecl ||
		    !(symbol.type() instanceof EarthType.Func fnType)) {
			throw new EarthException(
				"`%s` is not a function".formatted(fnName),
				ctx.getStart().getLine()
			);
		}

		// Third, check that the number of arguments is correct
		ExprListContext params = ctx.params;
		// params is what the user passed
		// fnType is what the function expects from the symbol table
		if (params.expr().size() != fnType.params().size()) {
			throw new EarthException(
				"Expected %d arguments but got %d. Required signature is %s".formatted(
					fnType.params().size(), params.expr().size(), fnType
				),
				ctx.getStart().getLine()
			);
		}

		// Fourth, check that the types of the arguments match the types of the
		// parameters of the function signature
		for (int i = 0; i < params.expr().size(); i++) {
			EarthType argType = visit(params.expr(i)); // type of argument
			Base paramType = fnType.params().get(i);
			if (argType != paramType) {
				throw new EarthException(
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
