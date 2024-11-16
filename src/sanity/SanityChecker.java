package sanity;

import antlr.MoneyParser.DeclStmtContext;
import antlr.MoneyParser.ExprContext;
import antlr.MoneyParser.ReassignStmtContext;
import antlr.MoneyParserBaseVisitor;
import money.MoneyException;

public class SanityChecker extends MoneyParserBaseVisitor<Void> {
	private final SymbolTable table = SymbolTable.instance;
	private final TypeResolver typeResolver = new TypeResolver();

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
		MoneyType newType = typeResolver.visit(ctx.expr());

		// third, check that the type of the new expression matches the type of the
		// variable being reassigned
		if (!newType.equals(variable.type())) {
			var msg = "`%s` has type `%s` but the expression has type `%s`"
				.formatted(name, variable.type(), newType);
			throw new MoneyException(msg, line);
		}
		return null;
	}

	/// Does a few checks to ensure that the variable can be declared.
	/// Returns the type of the expression if it's valid or throws an exception
	/// on any error.
	/// Note that if `exprCtx` is null, the type of the expression is not
	/// resolved (because there's no expression). In this case, the type
	/// attached to the name will be returned as a `MoneyType`.
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
		MoneyType exprType = typeResolver.visit(exprCtx);

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
