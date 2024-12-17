package parser.ast_printer;

import parser.ast_helpers.ExprList;
import parser.ast_helpers.StmtList;
import parser.ast_helpers.TypedIdent;
import parser.ast_helpers.TypedIdentList;
import parser.exprs.*;
import parser.stmts.*;
import sanity2.NEarthType;

import java.util.function.Function;

// The program should still work as expected if the ast_printer package is
// not compiled
public sealed interface AstPrinter permits KeyValuePrinter, ListPrinter,
	SinglePrinter {
	String stringify(String indent, boolean isLast);

	static void print(StmtList s) {
		String result = AstPrinter.stmtsStr(s)
			.stringify("", true)
			.strip();
		System.out.println(result);
	}

	private static AstPrinter stmtStr(Stmt stmt) {
		return switch (stmt) {
			case DeclStmt s -> declStmtStr(s);
			case WhenStmt s -> whenStmtStr(s);
			case FnDefStmt s -> fnDefStmtStr(s);
			case LoopStmt s -> loopStmtStr(s);
			case ReassignStmt s -> reassignStmtStr(s);
			case UnnamedStmt s -> unnamedStmtStr(s);
			case YeetStmt s -> yeetStmtStr(s);
		};
	}

	private static AstPrinter whenStmtStr(WhenStmt s) {
		Function<WhenStmt.When, AstPrinter> whenStr =
			when -> new ListPrinter(
				"when",
				new SinglePrinter("Condition", exprStr(when.condition())),
				new SinglePrinter("Body", stmtsStr(when.body()))
			);

		AstPrinter[] array = s.elseWhen().stream()
			.map(whenStr)
			.toArray(AstPrinter[]::new);

		return new ListPrinter("When Stmt",
			createLine(s.line()),
			new SinglePrinter("--", whenStr.apply(s.when())),
			new ListPrinter("Else", array),
			new SinglePrinter("Else Body", stmtsStr(s.elseBody()))
		);
	}

	private static AstPrinter fnDefStmtStr(FnDefStmt s) {
		return new ListPrinter("Fn Def Stmt",
			createLine(s.line()),
			new SinglePrinter("Name", identExprStr(s.name())),
			new SinglePrinter("Params", typedIdentListStr(s.params())),
			new SinglePrinter("Return Type", identExprStr(s.returnType())),
			new SinglePrinter("Body", stmtsStr(s.body()))
		);
	}

	private static AstPrinter loopStmtStr(LoopStmt s) {
		return new ListPrinter("Loop Stmt",
			createLine(s.line()),
			new SinglePrinter("Init", declStmtStr(s.initializer())),
			new SinglePrinter("Condition", exprStr(s.condition())),
			new SinglePrinter("Update", reassignStmtStr(s.update())),
			new SinglePrinter("Body", stmtsStr(s.body()))
		);
	}

	private static AstPrinter reassignStmtStr(ReassignStmt s) {
		return new ListPrinter("Reassign Stmt",
			createLine(s.line()),
			new SinglePrinter("Name", identExprStr(s.name())),
			new SinglePrinter("Expr", exprStr(s.newValue()))
		);
	}

	private static AstPrinter unnamedStmtStr(UnnamedStmt s) {
		return new ListPrinter("Unnamed Stmt",
			createLine(s.line()),
			new SinglePrinter("Expr", exprStr(s.expr()))
		);
	}

	private static AstPrinter yeetStmtStr(YeetStmt s) {
		return new ListPrinter("Yeet Stmt",
			createLine(s.line()),
			new SinglePrinter("Expr", exprStr(s.yeetValue()))
		);
	}

	private static AstPrinter stmtsStr(StmtList s) {
		if (s.isEmpty()) {
			return empty();
		}
		AstPrinter[] list = s
			.stream()
			.map(AstPrinter::stmtStr)
			.toArray(AstPrinter[]::new);

		return new ListPrinter("Stmts", list);
	}

	private static AstPrinter declStmtStr(DeclStmt s) {
		return new ListPrinter("Decl Stmt",
			createLine(s.line()),
			new SinglePrinter("Name", typedIdentStr(s.nameAndType())),
			new SinglePrinter("Expr", exprStr(s.value()))
		);
	}


	// expressions
	private static AstPrinter exprsStr(ExprList e) {
		if (e.isEmpty()) {
			return empty();
		}
		AstPrinter[] list = e
			.stream()
			.map(AstPrinter::exprStr)
			.toArray(AstPrinter[]::new);

		return new ListPrinter("Exprs", list);
	}

	private static AstPrinter exprStr(Expr expr) {
		return switch (expr) {
			case IdentExpr e -> identExprStr(e);
			case FnCallExpr e -> fnCallExprStr(e);
			case GroupedExpr e -> groupedExprStr(e);
			case LitExpr e -> litExprStr(e);
			case BinaryExpr e -> binaryExprStr(e);
			case NegExpr e -> negExprStr(e);
			case NotExpr e -> notExprStr(e);
		};
	}

	private static AstPrinter binaryExprStr(BinaryExpr e) {
		return new ListPrinter(e.getClass().getSimpleName(),
			createLine(e.line()),
			new KeyValuePrinter("Op", e.op().desc),
			new SinglePrinter("Left", exprStr(e.left())),
			new SinglePrinter("Right", exprStr(e.right())),
			createType(e.dataType())
		);
	}

	private static AstPrinter notExprStr(NotExpr e) {
		return new ListPrinter("Not Expr",
			createLine(e.line()),
			new SinglePrinter("Expr", exprStr(e.expr())),
			createType(e.dataType())
		);
	}

	private static AstPrinter negExprStr(NegExpr e) {
		return new ListPrinter("Neg Expr",
			createLine(e.line()),
			new SinglePrinter("Expr", exprStr(e.expr())),
			createType(e.dataType())
		);
	}

	private static AstPrinter fnCallExprStr(FnCallExpr e) {
		return new ListPrinter("Fn Call Expr",
			createLine(e.line()),
			new SinglePrinter("Name", identExprStr(e.name())),
			new SinglePrinter("Params", exprsStr(e.params())),
			createType(e.dataType())
		);
	}

	private static AstPrinter litExprStr(LitExpr e) {
		return new ListPrinter("Literal Expr",
			createLine(e.line()),
			new KeyValuePrinter(e.type(), e.value()),
			createType(e.dataType())
		);
	}

	private static AstPrinter groupedExprStr(GroupedExpr e) {
		return new ListPrinter("Grouped Expr",
			createLine(e.line()),
			new SinglePrinter("Expr", exprStr(e.expr())),
			createType(e.dataType())
		);
	}

	private static AstPrinter identExprStr(IdentExpr e) {
		System.out.println("Type of " + e.name() + " is " + e.dataType());
		return new ListPrinter("Ident",
			createLine(e.line()),
			new KeyValuePrinter("Name", e.name()),
			createType(e.dataType())
		);
	}

	private static AstPrinter typedIdentStr(TypedIdent e) {
		return new ListPrinter("Typed Ident",
			new SinglePrinter("Name", identExprStr(e.name())),
			new SinglePrinter("Type", identExprStr(e.type()))
		);
	}

	private static AstPrinter typedIdentListStr(TypedIdentList e) {
		if (e.isEmpty()) {
			return empty();
		}
		AstPrinter[] list = e
			.stream()
			.map(AstPrinter::typedIdentStr)
			.toArray(AstPrinter[]::new);

		return new ListPrinter("Typed Idents", list);
	}

	private static AstPrinter createLine(int line) {
		return new KeyValuePrinter("line", line + "");
	}

	private static AstPrinter createType(NEarthType type) {
		if (type == null) {
			return new KeyValuePrinter("EarthType", "UnknownType");
		}
		return new KeyValuePrinter("EarthType", type.string());
	}

	private static AstPrinter empty() {
		return new KeyValuePrinter("Empty", "");
	}
}
