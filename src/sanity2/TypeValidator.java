package sanity2;

import parser.ast_helpers.StmtList;
import parser.exprs.*;
import parser.stmts.*;

/// Final Step in SanityChecker. Goes through all the statements in the list
/// and exits the program if any of the statements contains expressions that
/// have their type as null.\
/// Tried using reflection for this but couldn't get it to work :(
public class TypeValidator {
	public static void validateStmts(StmtList stmts) {
		stmts.forEach(TypeValidator::validateStmt);
	}

	private static void validateStmt(Stmt stmt) {
		switch (stmt) {
			case DeclStmt s -> validateDeclStmt(s);
			case FnDefStmt defStmt -> validateFnDefStmt(defStmt);
			case LoopStmt loopStmt -> validateLoopStmt(loopStmt);
			case ReassignStmt reassignStmt -> validateReassignStmt(reassignStmt);
			case UnnamedStmt unnamedStmt -> validateUnnamedStmt(unnamedStmt);
			case WhenStmt whenStmt -> validateWhenStmt(whenStmt);
			case YeetStmt yeetStmt -> validateYeetStmt(yeetStmt);
		}
	}

	private static void validateLoopStmt(LoopStmt stmt) {
		// DeclStmt initializer, Expr condition, ReassignStmt update, StmtList body

		validateDeclStmt(stmt.initializer());
		validateExpr(stmt.condition());
		validateReassignStmt(stmt.update());
		validateStmts(stmt.body());
	}

	private static void validateFnDefStmt(FnDefStmt stmt) {
		// IdentExpr name, TypedIdentList params, IdentExpr returnType, StmtList
		// body

		validateExpr(stmt.name());
		stmt.params().forEach(param -> {
			validateExpr(param.name());
			validateExpr(param.type());
		});

		validateExpr(stmt.returnType());

		validateStmts(stmt.body());
	}

	private static void validateWhenStmt(WhenStmt stmt) {
		// WhenStmt.When when, List <WhenStmt.When> elseWhen, StmtList elseBody
		// When(Expr condition, StmtList body)

		// When
		validateExpr(stmt.when().condition());

		validateStmts(stmt.when().body());

		// elseWhen
		for (WhenStmt.When when : stmt.elseWhen()) {
			validateExpr(when.condition());
			validateStmts(when.body());
		}

		// elseBody
		validateStmts(stmt.elseBody());
	}

	private static void validateYeetStmt(YeetStmt stmt) {
		// Expr yeetValue
		validateExpr(stmt.yeetValue());
	}

	private static void validateUnnamedStmt(UnnamedStmt stmt) {
		// Expr expr
		validateExpr(stmt.expr());
	}

	private static void validateReassignStmt(ReassignStmt stmt) {
		// IdentExpr name, Expr newValue
		validateExpr(stmt.name());
		validateExpr(stmt.newValue());
	}

	private static void validateDeclStmt(DeclStmt s) {
		// TypedIdent nameAndType, Expr value

		validateExpr(s.nameAndType().name());
		validateExpr(s.nameAndType().type());
		validateExpr(s.value());
	}

	// Expressions
	private static void validateExpr(Expr expr) {
		switch (expr) {
			case BinaryExpr e -> {
				validateExpr(e.left());
				validateExpr(e.right());
			}
			case GroupedExpr e -> validateExpr(e);
			case NegExpr e -> validateExpr(e.expr());
			case NotExpr e -> validateExpr(e.expr());
			case FnCallExpr e -> {
				validateExpr(e.name());
				e.params().forEach(TypeValidator::validateExpr);
			}
			case IdentExpr e -> validate(e.dataType(), "IdentExpr type is null");
			case LitExpr e -> validate(e.dataType(), "LitExpr type is null");

		}
	}

	private static void validate(Object o, String msg) {
		if (o == null) {
			System.err.println(msg);
			System.exit(1);
		}
	}
}
