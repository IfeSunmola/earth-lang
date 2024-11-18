package sanity;

import antlr.MoneyParserBaseVisitor;
import money.MoneyException;
import sanity.MoneyType.Func;

import java.util.List;

import static antlr.MoneyParser.*;
import static sanity.MoneyType.Base.BOOL;
import static sanity.MoneyType.Base.VOID;

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
		String name = ctx.typedIdentExpr().name.getText();
		String type = ctx.typedIdentExpr().type.getText();
		int line = ctx.getStart().getLine();

		MoneyType exprType = checkDeclValidity(name, type, line, ctx.expr());
		Kind kind = ctx.letType.getType() == Let ? Kind.ImmutDecl : Kind.MutDecl;
		table.addSymbol(name, line, kind, exprType);
		return null;
	}

	@Override
	public Void visitReassignStmt(ReassignStmtContext ctx) {
		String name = ctx.ident.getText();
		int line = ctx.getStart().getLine();

		// first, check that the name is declared
		Symbol variable = table.findInAllScopes(name)
			.orElseThrow(() -> new MoneyException(("`%s` is not a known identifier")
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
	public Void visitWhenElseStmt(WhenElseStmtContext ctx) {
		// First, check that the condition in the when stmt is a boolean expression
		ExprContext whenCondition = ctx.expr().getFirst();
		assertSameType(exprResolver.visit(whenCondition),
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
			assertSameType(elseWhenType, BOOL, "elseWhen condition",
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

	@Override
	public Void visitYeetStmt(YeetStmtContext ctx) {
		// yeet stmt -> return stmt -> yeet 23
		// simply validate that the expression is a valid expression
		exprResolver.visit(ctx.expr());
		return null;
	}

	@Override
	public Void visitFnDefStmt(FnDefStmtContext ctx) {
		String name = ctx.name.getText();
		TypedIdentListContext paramsCtx = ctx.params;
		String temp;
		if (ctx.retType != null) temp = ctx.retType.getText();
		else temp = "void";
		MoneyType retType = assertValidType(temp, ctx.start.getLine());

		// First, check that the name has not been declared AS A FUNCTION
		table.findInCurrentScope(name)
			.ifPresent(symbol -> {
				if (symbol.kind() != Kind.Function) return;

				var msg = "";
				if (symbol.declaredOn() == 0)
					msg = "`%s` is a builtin function".formatted(name);
				else
					msg = "%s has already been declared as a function on line %d"
						.formatted(name, symbol.declaredOn());
				throw new MoneyException(msg, ctx.getStart().getLine());
			});

		// Second, enter a new scope
		table.enterScope();
		// Third, add the parameters to the symbol table. It's a new scope, so no
		// need to check if the parameters have been declared before.
		paramsCtx.typedIdentExpr().forEach(typedIdentExprContext -> {
			String paramName = typedIdentExprContext.name.getText();
			String paramType = typedIdentExprContext.type.getText();
			int line = typedIdentExprContext.getStart().getLine();

			MoneyType type = checkDeclValidity(paramName, paramType, line, null);
			table.addSymbol(paramName, line, Kind.ImmutDecl, type);
		});

		// Fourth, sanity check the function body
		visit(ctx.body);

		List<StmtContext> fnBody = ctx.body.stmt();
		// Fifth, if the function is empty, the return type must be void
		if (fnBody.isEmpty() && !retType.is(VOID)) {
			throw new MoneyException(
				"Function `%s` returns `%s` but has an empty body"
					.formatted(name, temp), ctx.retType.getLine()
			);
		}

		// Sixth, Function does not return anything, but the last statement is a
		// return statement
		if (retType.is(VOID) && !fnBody.isEmpty()) {
			StmtContext lastStmt = fnBody.getLast();
			if (lastStmt.yeetStmt() != null) {
				throw new MoneyException("""
					Function `%s` does not return anything, but the last statement
					is a return statement"""
					.formatted(name), lastStmt.yeetStmt().getStart().getLine()
				);
			}
		}

		// Seventh, check that the type of the expression in return is the same as
		// the return type
		if (!fnBody.isEmpty()) {
			StmtContext lastStmt = fnBody.getLast();
			if (lastStmt.yeetStmt() != null) {
				YeetStmtContext yeetStmt = lastStmt.yeetStmt();
				MoneyType returnType = exprResolver.visit(yeetStmt.expr());
				if (!returnType.equals(retType)) {
					var msg = """
						Function `%s` returns `%s` but the return statement has type `%s`"""
						.formatted(name, temp, returnType);
					throw new MoneyException(msg, yeetStmt.getStart().getLine());
				}
			}
		}
		// happy path
		table.exitScope();

		// Eighth, add the function to the symbol table
		List<MoneyType.Base> list = paramsCtx.typedIdentExpr()
			.stream()
			.map(context -> {
				MoneyType type = MoneyType.fromString(context.type.getText());
				if (type instanceof MoneyType.Base) return (MoneyType.Base) type;

				throw new MoneyException("Function parameters must be base types",
					context.getStart().getLine());
			})
			.toList();

		if (!(retType instanceof MoneyType.Base retTypeBase))
			throw new MoneyException(
				"Function return type must be a base type", ctx.retType.getLine()
			);

		Func fnType = new Func(list, retTypeBase);

		table.addSymbol(name, ctx.getStart().getLine(), Kind.Function, fnType);
		return null;
	}

	@Override
	public Void visitUnnamedStmt(UnnamedStmtContext ctx) {
		// simply validate that the expression is a valid expression
		exprResolver.visit(ctx.expr());
		return null;
	}

	@Override
	public Void visitLoopStmt(LoopStmtContext ctx) {
		table.enterScope();
		// Null check not needed because antlr will have reported an error
		// First, validate the initializer.
		visitDeclStmt(ctx.initializer);

		// Second, validate the condition
		ExprContext condition = ctx.condition;
		MoneyType conditionType = exprResolver.visit(condition);
		assertSameType(conditionType, BOOL, "loop condition",
			condition.getStart().getLine()
		);


		// Third, validate the reassignment stmt
		visitReassignStmt(ctx.update);

		// Fourth, validate the body
		visitStmtList(ctx.body);

		table.exitScope();
		return null;
	}

	private MoneyType assertValidType(String type, int line) {
		if (!MoneyType.isKnown(type)) {
			throw new MoneyException("Unknown type `%s`".formatted(type), line);
		}
		// Doing double work here
		return MoneyType.fromString(type);
	}

	/// Asserts that the type of the expression is the same as the expected type.
	///
	/// @throws MoneyException if the types do not match
	private void assertSameType(MoneyType type, MoneyType expected,
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
		assertValidType(type, line);

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
