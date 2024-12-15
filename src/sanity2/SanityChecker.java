package sanity2;

import earth.EarthResult;
import parser.ast_helpers.StmtList;
import parser.exprs.Expr;
import parser.stmts.*;

import java.util.ArrayList;

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
			case FnDefStmt s -> {
			}
			case LoopStmt s -> {
			}
			case ReassignStmt s -> {
			}
			case UnnamedStmt s -> {
			}
			case WhenStmt s -> {
			}
			case YeetStmt s -> {
			}
		}
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
	private static NEarthType validateDecl(String name, String strType, int line,
	                                       Expr toDeclare) {
		// First, check that the type is known
		NEarthType declType = NEarthType.strToType.get(strType);
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
