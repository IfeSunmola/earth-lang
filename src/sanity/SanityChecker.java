package sanity;

import earth.EarthResult;
import parser.ast_helpers.StmtList;
import parser.ast_helpers.TypedIdent;
import parser.ast_helpers.TypedIdentList;
import parser.exprs.Expr;
import parser.exprs.IdentExpr;
import parser.stmts.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;

import static earth.EarthMain.DEBUG;
import static earth.EarthUtils.LOGGER;
import static sanity.EarthType.Base.BoolType;
import static sanity.Kind.VarDecl;

public class SanityChecker {
	public static EarthResult<StmtList> run(StmtList stmts) {
		var errors = new ArrayList<String>();

		try {
			// Stop if there's no main method
			stmts.stream()
				.filter(stmt -> stmt instanceof FnDefStmt)
				.map(stmt -> (FnDefStmt) stmt)
				.filter(stmt -> stmt.name().name().equals("main"))
				.findFirst()
				.orElseThrow(() -> new SanityException(
					"Main function not found", 0
				));
			StmtList result = typeStmts(stmts);
			return EarthResult.ok(result);
		}
		catch (SanityException e) {
			if (DEBUG) LOGGER.log(Level.SEVERE, e.getMessage(), e);
			errors.add(e.getMessage());
		}
		return EarthResult.err(errors);
	}

	private static StmtList typeStmts(StmtList stmts) {
		return new StmtList(stmts.stream()
			.map(SanityChecker::typeStmt)
			.toList());
	}

	private static Stmt typeStmt(Stmt stmt) {
		return switch (stmt) {
			case DeclStmt s -> typeDeclStmt(s);
			case FnDefStmt s -> fnDefStmt(s);
			case LoopStmt s -> typedLoopStmt(s);
			case ReassignStmt s -> typeReassignStmt(s);
			case UnnamedStmt s -> typeUnnamedStmt(s);
			case WhenStmt s -> typedWhenStmt(s);
			case YeetStmt s -> typedYeetStmt(s);
		};
	}

	private static FnDefStmt fnDefStmt(FnDefStmt s) {
		String name = s.name().name();
		int line = s.line();
		TypedIdentList params = s.params();
		StmtList fnBody = s.body();
		EarthType temp = EarthType.fromString(s.returnType().name(), line);

		if (!(temp instanceof EarthType.Base returnType))
			throw new SanityException(
				"Uhh, I don't wanna implement functions returning functions" + s.returnType().name(),
				line
			);

		// First, check that the name has not been declared as a function
		SymbolTable.instance.findInCurrentScope(name)
			.ifPresent(symbol -> {
				if (symbol.kind() == Kind.FnDecl) {
					var msg = "";
					if (symbol.declaredOn() == 0)
						msg = "`%s` is a builtin function".formatted(name);
					else msg = "`%s` has already been declared as a function on line %s"
						.formatted(name, symbol.declaredOn());

					throw new SanityException(msg, line);
				}
			});

		// If the name of the function is main, the function must take no
		// parameters and return nada
		if (name.equals("main")) {
			if (!params.isEmpty())
				throw new SanityException(
					"Function `main` must take no parameters", line
				);

			if (returnType != EarthType.Base.NadaType)
				throw new SanityException(
					"Function `main` must return `nada`, but returns `%s`"
						.formatted(returnType.string()),
					line
				);
		}

		SymbolTable.instance.enterScope();

		// Second, add the parameters to the symbol table.
		var typedParams = new TypedIdentList();
		params.forEach(param -> {
			String paramName = param.name().name();
			String paramType = param.type().name();

			EarthType type = getDeclTypeOrThrow(paramName, paramType, line);

			SymbolTable.instance.addSymbol(paramName, line, VarDecl, type);

			// Get the expression but with type information attached to it
			TypedIdent typedParam = ExprTyper.typedIdentExpr(param);
			typedParams.add(typedParam);
		});

		// Third, throw an exception if the statement body contains a `yeet`
		// statement, but the yeet statement is not the last statement
		fnBody.stream()
			.filter(stmt -> stmt instanceof YeetStmt)
			.findFirst()
			.ifPresent(stmt -> {
				if (stmt != fnBody.getLast())
					throw new SanityException(
						"Yeet statement must be the last statement in a function",
						stmt.line()
					);
			});

		// Fourth, check the body
		var typedBody = typeStmts(fnBody);


		// Because a yeet statement is automatically added in the parser when
		// there's none, the last statement is ALWAYS a yeet statement.
		// Fifth, get the type of the expression in the yeet statement
		YeetStmt last = (YeetStmt) typedBody.getLast();
		EarthType lastType = last.yeetValue().dataType();

		// Fifth, check that the type of the expression in the yeet statement
		// matches the return type of the function
		if (lastType != returnType)
			throw new SanityException(
				"Function `%s` returns `%s`, but the yeet statement has type `%s`"
					.formatted(name, returnType.string(), lastType.string()),
				last.line()
			);

		SymbolTable.instance.exitScope();

		// Sixth, add the function to the symbol table

		List<EarthType.Base> symbolParams = params.stream()
			.map(param -> EarthType.fromString(param.type().name(), line))
			.map(type -> (EarthType.Base) type)
			.toList();

		SymbolTable.instance.addSymbol(name, line, Kind.FnDecl,
			new EarthType.FuncType(symbolParams, returnType));


		return new FnDefStmt(
			ExprTyper.typeIdentExpr(s.name()),
			typedParams,
			ExprTyper.typeIdentExpr(s.returnType()),
			typedBody,
			line
		);
	}

	private static WhenStmt typedWhenStmt(WhenStmt s) {
		Function<WhenStmt.When, WhenStmt.When> whenMapper = when -> {
			Expr typedCond = ExprTyper.typeExpr(when.condition());
			if (typedCond.dataType() != BoolType)
				throw new SanityException(
					"Expected a boolean expression in when condition, but got `%s`"
						.formatted(typedCond.dataType().string()),
					s.line()
				);

			// Check if the body contains any yeet stmt. Can't handle that shit
			when.body().stream()
				.filter(stmt -> stmt instanceof YeetStmt)
				.findFirst()
				.ifPresent(stmt -> {
					throw new SanityException(
						"When statements cannot contain yeet statements",
						stmt.line()
					);
				});
			SymbolTable.instance.enterScope();

			StmtList typedBody = typeStmts(when.body());
			SymbolTable.instance.exitScope();

			return new WhenStmt.When(typedCond, typedBody);
		};

		// First check the condition and the body of the when block.
		WhenStmt.When newWhen = whenMapper.apply(s.when());

		// Second check the else-when blocks. Repeat the same checks as above
		List<WhenStmt.When> typedElseWhen = s.elseWhen().stream()
			.map(whenMapper).toList();

		// Finally, check the else block
		SymbolTable.instance.enterScope();
		StmtList typedElseBlock = typeStmts(s.elseBody());
		SymbolTable.instance.exitScope();

		return new WhenStmt(newWhen, typedElseWhen, typedElseBlock, s.line());
	}

	private static LoopStmt typedLoopStmt(LoopStmt s) {
		SymbolTable.instance.enterScope();
		// Check the initializer
		DeclStmt typedDeclStmt = typeDeclStmt(s.initializer());

		// Check that the condition has BoolType
		Expr typedCond = ExprTyper.typeExpr(s.condition());
		if (typedCond.dataType() != BoolType)
			throw new SanityException(
				"Expected a boolean expression in loop condition, but got `%s`"
					.formatted(typedCond.dataType().string()),
				s.line()
			);

		// Check the update statement
		ReassignStmt typedReassignStmt = typeReassignStmt(s.update());

		// Check the body
		s.body().stream()
			.filter(stmt -> stmt instanceof YeetStmt)
			.findFirst()
			.ifPresent(stmt -> {
				throw new SanityException(
					"Loop statements cannot contain yeet statements",
					stmt.line()
				);
			});
		StmtList typedBody = typeStmts(s.body());

		SymbolTable.instance.exitScope();

		return new LoopStmt(
			typedDeclStmt, typedCond, typedReassignStmt, typedBody, s.line()
		);
	}

	private static YeetStmt typedYeetStmt(YeetStmt s) {
		Expr typedExpr = ExprTyper.typeExpr(s.yeetValue());
		return new YeetStmt(typedExpr, s.line());
	}

	private static UnnamedStmt typeUnnamedStmt(UnnamedStmt s) {
		Expr typedExpr = ExprTyper.typeExpr(s.expr());
		return new UnnamedStmt(typedExpr, s.line());
	}

	private static ReassignStmt typeReassignStmt(ReassignStmt s) {
		String name = s.name().name();
		int line = s.line();

		// Check that the name is declared as a variable
		Symbol variable = SymbolTable.instance
			.findInAllScopes(name)
			.orElseThrow(() -> new SanityException(
				"`%s` is not a known identifier".formatted(name), line
			));

		if (variable.kind() != VarDecl)
			throw new SanityException(
				"`%s` is not a variable".formatted(name), line
			);

		// Find the type of the new expression
		Expr typedExpr = ExprTyper.typeExpr(s.newValue());

		// Check that the type of the new expression matches the type of the
		// variable being assigned
		if (variable.type() != typedExpr.dataType())
			throw new SanityException(
				"`%s` has type `%s` but the expression has type `%s`"
					.formatted(name, variable.type().string(),
						typedExpr.dataType().string()),
				line
			);

		IdentExpr typedName = ExprTyper.typeIdentExpr(s.name());
		return new ReassignStmt(typedName, typedExpr, line);
	}

	private static DeclStmt typeDeclStmt(DeclStmt s) {
		String name = s.nameAndType().name().name();
		String strType = s.nameAndType().type().name();
		int line = s.line();

		EarthType type = getDeclTypeOrThrow(name, strType, line);

		Expr toDeclare = validateDecl(name, type, line, s.value());

		SymbolTable.instance.addSymbol(
			name, line, VarDecl, toDeclare.dataType()
		);

		return new DeclStmt(
			ExprTyper.typedIdentExpr(s.nameAndType()),
			toDeclare,
			line
		);
	}

	private static Expr validateDecl(String name, EarthType expectedType,
	                                 int line, Expr toDeclare) {
		//  find the type of the expression
		Expr typedExpr = ExprTyper.typeExpr(toDeclare);

		// Fourth, check that the type of the expression matches the type of the
		// declaration
		if (typedExpr.dataType() != expectedType)
			throw new SanityException(
				"`%s` has type `%s`, but the expression has type `%s`"
					.formatted(name, expectedType.string(),
						typedExpr.dataType().string()),
				line
			);

		return typedExpr; // happy path
	}

	/// Returns the type of the expression if a declaration with the name is
	/// possible, or throws a `SanityException` if not
	private static EarthType getDeclTypeOrThrow(String name, String strType,
	                                            int line) {
		// First, check that the type is known
		EarthType declType = EarthType.fromString(strType, line);
		if (declType == null)
			throw new SanityException("Unknown type: " + strType, line);

		// Second, check that the name, and type does not already exist in the
		// current scope
		SymbolTable.instance.findInCurrentScope(name)
			.ifPresent(symbol -> {
				String msg;
				if (symbol.kind() == Kind.Builtin || symbol.declaredOn() == 0)
					msg = "`%s` is a builtin identifier".formatted(name);
				else msg = "`%s` has already been declared on line %s"
					.formatted(name, symbol.declaredOn());

				throw new SanityException(msg, line);
			});

		return declType;
	}
}
