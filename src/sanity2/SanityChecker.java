package sanity2;

import earth.EarthResult;
import parser.ast_helpers.StmtList;
import parser.ast_helpers.TypedIdentList;
import parser.exprs.Expr;
import parser.stmts.*;
import sanity2.NEarthType.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static sanity2.NEarthType.Base.BoolType;

public interface SanityChecker {
	static EarthResult<?> run(StmtList stmts) {
		var errors = new ArrayList<String>();
		try {
			checkStmts(stmts);
		}
		catch (SanityException e) {
			errors.add(e.getMessage());
		}

		if (errors.isEmpty()) return EarthResult.ok(null);
		else return EarthResult.err(errors);
	}

	private static void checkStmts(StmtList stmts) {
		stmts.forEach(SanityChecker::checkStmt);
	}

	private static void checkStmt(Stmt stmt) {
		switch (stmt) {
			case DeclStmt s -> checkDeclStmt(s);
			case FnDefStmt s -> checkFnDefStmt(s);
			case LoopStmt s -> checkLoopStmt(s);
			case ReassignStmt s -> checkReassignStmt(s);
			case UnnamedStmt s -> checkUnnamedStmt(s);
			case WhenStmt s -> checkWhenStmt(s);
			case YeetStmt s -> checkYeetStmt(s);
		}
	}

	static void checkFnDefStmt(FnDefStmt s) {
		String name = s.name().name();
		int line = s.line();
		TypedIdentList params = s.params();
		StmtList fnBody = s.body();
		NEarthType temp = NEarthType.fromString(s.returnType().name(), line);
		if (!(temp instanceof Base returnType))
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

		SymbolTable.instance.enterScope();

		// Second, add the parameters to the symbol table.
		params.forEach(param -> {
			String paramName = param.name().name();
			String paramType = param.type().name();

			NEarthType type = validateDecl(paramName, paramType, line, null);
			SymbolTable.instance.addSymbol(paramName, line, Kind.VarDecl, type);
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
		checkStmts(fnBody);


		// Because a yeet statement is automatically added in the parser when
		// there's none, the last statement is ALWAYS a yeet statement.
		// Fifth, get the type of the expression in the yeet statement
		YeetStmt last = (YeetStmt) fnBody.getLast();
		NEarthType lastType = ExprResolver.exprType(last.yeetValue());

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

		List<Base> symbolParams = params.stream()
			.map(param -> NEarthType.fromString(param.type().name(), line))
			.map(type -> (Base) type)
			.toList();

		SymbolTable.instance.addSymbol(name, line, Kind.FnDecl,
			new NEarthType.FuncType(symbolParams, returnType));
	}

	static void checkWhenStmt(WhenStmt s) {
		Consumer<WhenStmt.When> whenChecker = when -> {
			// The condition must be a boolean expression
			NEarthType condType = ExprResolver.exprType(when.condition());
			if (condType != BoolType)
				throw new SanityException(
					"Expected a boolean expression in when condition, but got `%s`"
						.formatted(condType.string()),
					s.line()
				);

			SymbolTable.instance.enterScope();
			checkStmts(when.body());
			SymbolTable.instance.exitScope();
		};

		// First check the condition and the body of the when block.
		whenChecker.accept(s.when());

		// Second check the else-when blocks. Repeat the same checks as above
		s.elseWhen().forEach(whenChecker);

		// Finally, check the else block
		SymbolTable.instance.enterScope();
		checkStmts(s.elseBody());
		SymbolTable.instance.exitScope();
	}

	static void checkLoopStmt(LoopStmt s) {
		SymbolTable.instance.enterScope();
		// Check the initializer
		checkDeclStmt(s.initializer());

		// Check that the condition has BoolType
		NEarthType condType = ExprResolver.exprType(s.condition());
		if (condType != BoolType)
			throw new SanityException(
				"Expected a boolean expression in loop condition, but got `%s`"
					.formatted(condType.string()),
				s.line()
			);

		// Check the update statement
		checkReassignStmt(s.update());

		// Check the body
		checkStmts(s.body());

		SymbolTable.instance.exitScope();
	}

	static void checkUnnamedStmt(UnnamedStmt s) {
		// Just check that the expression is valid. An exception will be thrown
		// if not
		ExprResolver.exprType(s.expr());
	}

	static void checkYeetStmt(YeetStmt s) {
		// Just check that the expression is valid. An exception will be thrown
		// if not
		ExprResolver.exprType(s.yeetValue());
	}

	static void checkReassignStmt(ReassignStmt s) {
		String name = s.name().name();
		int line = s.line();

		// Check that the name is declared as a variable
		Symbol variable = SymbolTable.instance
			.findInAllScopes(name)
			.orElseThrow(() -> new SanityException(
				"`%s` is not a known identifier".formatted(name), line
			));

		if (variable.kind() != Kind.VarDecl)
			throw new SanityException(
				"`%s` is not a variable".formatted(name), line
			);

		// Find the type of the new expression
		NEarthType exprType = ExprResolver.exprType(s.newValue());

		// Check that the type of the new expression matches the type of the
		// variable being assigned
		if (variable.type() != exprType)
			throw new SanityException(
				"`%s` has type `%s` but the expression has type `%s`"
					.formatted(name, variable.type().string(), exprType.string()),
				line
			);
	}

	static void checkDeclStmt(DeclStmt s) {
		// What the flip is this lmao
		String name = s.nameAndType().name().name();
		String type = s.nameAndType().type().name();
		int line = s.line();

		NEarthType exprType = validateDecl(name, type, line, s.value());
		SymbolTable.instance.addSymbol(name, line, Kind.VarDecl, exprType);
	}

	/// Returns the type of the expression if a declaration is possible, or
	/// throws a `SanityException` if the declaration is invalid
	private static NEarthType validateDecl(String name, String strType,
	                                       int line,
	                                       Expr toDeclare) {
		// First, check that the type is known
		NEarthType declType = NEarthType.fromString(strType, line);
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

		// If to declare is null, then the type is just the decl type. This will
		// be the case when this function is called when handling function
		// parameters
		if (toDeclare == null) return declType;

		// Third, find the type of the expression
		NEarthType exprType = ExprResolver.exprType(toDeclare);

		// Fourth, check that the type of the expression matches the type of the
		// declaration
		if (exprType != declType)
			throw new SanityException(
				"`%s` has type `%s`, but the expression has type `%s`"
					.formatted(name, declType.string(), exprType.string()),
				line
			);

		return declType; // happy path
	}
}
