package parser.ast_printer;

import parser.ast_helpers.ExprList;
import parser.ast_helpers.StmtList;
import parser.ast_helpers.TypedIdent;
import parser.ast_helpers.TypedIdentList;
import parser.exprs.*;
import parser.stmts.*;

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
			case ElseWhenStmt s -> throw new RuntimeException();
			case FnDefStmt s -> fnDefStmtStr(s);
			case LoopStmt s -> loopStmtStr(s);
			case ReassignStmt s -> reassignStmtStr(s);
			case UnnamedStmt s -> unnamedStmtStr(s);
			case YeetStmt s -> yeetStmtStr(s);
		};
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
			case BinaryExpr e -> binaryExprStr(e);
			case FnCallExpr e -> fnCallExprStr(e);
			case GroupedExpr e -> groupedExprStr(e);
			case LitExpr e -> litExprStr(e);
			case UnaryExpr e -> unaryExprStr(e);
		};
	}

	private static AstPrinter fnCallExprStr(FnCallExpr e) {
		return new ListPrinter("Fn Call Expr",
			createLine(e.line()),
			new SinglePrinter("Name", identExprStr(e.name())),
			new SinglePrinter("Params", exprsStr(e.params()))
		);
	}

	private static AstPrinter binaryExprStr(BinaryExpr e) {
		return new ListPrinter("Binary Expr",
			createLine(e.line()),
			new KeyValuePrinter("Op", e.op().desc),
			new SinglePrinter("Left", exprStr(e.left())),
			new SinglePrinter("Right", exprStr(e.right()))
		);
	}

	private static AstPrinter litExprStr(LitExpr e) {
		return new ListPrinter("Literal Expr",
			createLine(e.line()),
			new KeyValuePrinter(e.type(), e.value())
		);
	}

	private static AstPrinter unaryExprStr(UnaryExpr e) {
		return new ListPrinter("Unary Expr",
			createLine(e.line()),
			new KeyValuePrinter("Op", e.op().toString()),
			new SinglePrinter("Expr", exprStr(e.expr()))
		);
	}

	private static AstPrinter groupedExprStr(GroupedExpr e) {
		return new ListPrinter("Grouped Expr",
			createLine(e.line()),
			new SinglePrinter("Expr", exprStr(e.expr()))
		);
	}

	private static AstPrinter identExprStr(IdentExpr e) {
		return new ListPrinter("Ident",
			createLine(e.line()),
			new KeyValuePrinter("Name", e.name())
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

	private static AstPrinter empty() {
		return new KeyValuePrinter("Empty", "");
	}
}
