package sanity;

import antlr.MoneyParserBaseVisitor;
import money.MoneyException;

import static antlr.MoneyParser.LetStmtContext;

public class SanityChecker extends MoneyParserBaseVisitor<Void> {
	private final SymbolTable table = SymbolTable.instance;
	private final TypeResolver typeResolver = new TypeResolver();

	@Override
	public Void visitLetStmt(LetStmtContext ctx) {
		// let age: int = 23 | letStmt: Let typedIdentExpr Eq expr ;
		String letName = ctx.typedIdentExpr().UntypedIdent(0).getText();
		String letType = ctx.typedIdentExpr().UntypedIdent(1).getText();
		int line = ctx.getStart().getLine();

		// first, check that the letName is not already declared
		table.findInCurrentScope(letName)
			.ifPresent(symbol -> {
				var msg = "";
				if (symbol.declaredOn() == 0)
					msg = "`%s` is a builtin identifier".formatted(letName);
				else msg = "`%s` has already been declared on line %d"
					.formatted(letName, symbol.declaredOn());
				throw new MoneyException(msg, line);
			});

		// second, check that the letType is a valid type
		if (!MoneyType.isKnown(letType)) {
			throw new MoneyException("Unknown type `%s`".formatted(letType), line);
		}

		// third, resolve the type of the expression
		MoneyType exprType = typeResolver.visit(ctx.expr());

		// fourth, check that the type of the expression matches the type attached
		// to the letName
		if (!exprType.stringEquals(letType)) {
			var msg = "`%s` has type `%s` but the expression has type `%s`"
				.formatted(letName, letType, exprType);
			throw new MoneyException(msg, line);
		}

		// fifth, add the symbol to the symbol table
		table.addSymbol(letName, line, Kind.ImmutDecl, exprType);
		System.out.println(table);
		return null;
	}
}
