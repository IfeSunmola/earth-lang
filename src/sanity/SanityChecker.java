package sanity;

import antlr.MoneyParserBaseVisitor;
import money.MoneyException;

import static antlr.MoneyParser.*;
import static sanity.MoneyType.Base.BOOL;

public class SanityChecker extends MoneyParserBaseVisitor<Void> {
	private final SymbolTable table = SymbolTable.instance;
	private final ExprResolver exprResolver = new ExprResolver();

	@Override
	public Void visitStmtList(StmtListContext ctx) {
		ctx.stmt().forEach(this::visit);
		return null;
	}

	@Override
	public Void visitDeclStmt(DeclStmtContext ctx) {
		String name = ctx.typedIdentExpr().UntypedIdent(0).getText();
		String type = ctx.typedIdentExpr().UntypedIdent(1).getText();
		int line = ctx.getStart().getLine();

		MoneyType exprType = checkDeclValidity(name, type, line, ctx.expr());
		Kind kind = ctx.Let() != null ? Kind.ImmutDecl : Kind.MutDecl;
		table.addSymbol(name, line, kind, exprType);
		return null;
	}

	@Override
	public Void visitReassignStmt(ReassignStmtContext ctx) {
		String name = ctx.UntypedIdent().getText();
		int line = ctx.getStart().getLine();

		// first, check that the name is declared
		Symbol variable = table.findInAllScopes(name)
			.orElseThrow(() -> new MoneyException(("`%s`s not a known identifier")
				.formatted(name), line));

		// second, resolve the type of the new expression
		MoneyType newType = exprResolver.visit(ctx.expr());

		// third, check that the type of the new expression matches the type of the
		// variable being reassigned
		if (!newType.equals(variable.type())) {
			var msg = "`%s` has type `%s` but the expression has type `%s`"
				.formatted(name, variable.type(), newType);
			throw new MoneyException(msg, line);
		}
		return null;
	}

	@Override
	public Void visitYeetStmt(YeetStmtContext ctx) {
		// yeet stmt -> return stmt -> yeet 23
		// simply validate that the expression is a valid expression
		exprResolver.visit(ctx.expr());
		return null;
	}

	@Override
	public Void visitUnnamedStmt(UnnamedStmtContext ctx) {
		// simply validate that the expression is a valid expression
		exprResolver.visit(ctx.expr());
		return null;
	}

	@Override
	public Void visitWhenElseStmt(WhenElseStmtContext ctx) {
		// First, check that the condition in the when stmt is a boolean expression
		ExprContext whenCondition = ctx.expr().getFirst();
		assertType(exprResolver.visit(whenCondition),
			BOOL, "when condition",
			whenCondition.getStart().getLine()
		);

		// Second, validate the body of the when stmt
		table.enterScope();
		visit(ctx.stmtList().getFirst());
		table.exitScope();

		// Third, handle elseWhen condition (if it exists), and the body
		// skip 1 because the first expr is the when condition
		ctx.expr().stream().skip(1).forEach(exprCtx -> {
			MoneyType elseWhenType = exprResolver.visit(exprCtx);
			assertType(elseWhenType, BOOL, "elseWhen condition",
				exprCtx.getStart().getLine()
			);

			// the index of the exprCtx in the expr list is the same as the index
			// of the stmtList in the stmtList list
			StmtListContext elseWhenBody =
				ctx.stmtList().get(ctx.expr().indexOf(exprCtx));

			table.enterScope();
			visit(elseWhenBody);
			table.exitScope();
		});
		// Fourth, handle the else condition (if it exists)
		if (ctx.Else() != null) {
			StmtListContext elseBody = ctx.stmtList().getLast();
			table.enterScope();
			visit(elseBody);
			table.exitScope();
		}
		return null;
	}

	/// Asserts that the type of the expression is the same as the expected type.
	///
	/// @throws MoneyException if the types do not match
	private void assertType(MoneyType type, MoneyType expected,
	                        String in, int line) {
		if (!type.equals(expected)) {
			var msg = "Expected a `%s` in %s but got `%s`"
				.formatted(expected, in, type);
			throw new MoneyException(msg, line);
		}
	}

	/// Does a few checks to ensure that the variable can be declared.
	/// Returns the type of the expression if it's valid or throws an exception
	/// on any error.\
	/// Note that if `exprCtx` is null, the type of the expression is not
	/// resolved (because there's no expression). In this case, the `type`
	/// parameter will be returned as a `MoneyType`.
	///
	/// @throws MoneyException if the variable cannot be declared
	private MoneyType checkDeclValidity(String name, String type,
	                                    int line, ExprContext exprCtx) {
		// first, check that the letName is not already declared
		table.findInCurrentScope(name)
			.ifPresent(symbol -> {
				var msg = "";
				if (symbol.declaredOn() == 0)
					msg = "`%s` is a builtin identifier".formatted(name);
				else msg = "`%s` has already been declared on line %d"
					.formatted(name, symbol.declaredOn());
				throw new MoneyException(msg, line);
			});

		// second, check that the type is a valid type
		if (!MoneyType.isKnown(type)) {
			throw new MoneyException("Unknown type `%s`".formatted(type), line);
		}

		if (exprCtx == null) { // most likely a var decl without an expression
			// surely, I could do better than basically repeating the if stmt above
			return MoneyType.fromString(type);
		}

		// third, resolve the type of the expression
		MoneyType exprType = exprResolver.visit(exprCtx);

		// fourth, check that the type of the expression matches the type attached
		// to the name
		if (!exprType.stringEquals(type)) {
			var msg = "`%s` has type `%s` but the expression has type `%s`"
				.formatted(name, type, exprType);
			throw new MoneyException(msg, line);
		}
		return exprType;
	}
}
