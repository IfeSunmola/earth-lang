package parser.ast_printer;

import parser.ast_helpers.ExprList;
import parser.ast_helpers.StmtList;
import parser.ast_helpers.TypedIdent;
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
			case FnDefStmt s -> throw new RuntimeException();
			case LoopStmt s -> throw new RuntimeException();
			case ReassignStmt s -> reassignStmtStr(s);
			case UnnamedStmt s -> unnamedStmtStr(s);
			case YeetStmt s -> yeetStmtStr(s);
		};
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
			new SinglePrinter("Name", typedIdentPrinter(s.nameAndType())),
			new SinglePrinter("Expr", exprStr(s.value()))
		);
	}


	// Identifiers
	private static AstPrinter typedIdentPrinter(TypedIdent e) {
		return new ListPrinter("Typed Ident",
			new SinglePrinter("Name", identExprStr(e.name())),
			new SinglePrinter("Type", identExprStr(e.type()))
		);
	}

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
			case FnCallExpr e -> throw new RuntimeException();
			case GroupedExpr e -> groupedExprStr(e);
			case LitExpr e -> litExprStr(e);
			case UnaryExpr e -> unaryExprStr(e);
		};
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

	private static AstPrinter createLine(int line) {
		return new KeyValuePrinter("line", line + "");
	}

	private static AstPrinter empty() {
		return new KeyValuePrinter("Empty", "");
	}
	//
	//	// Dispatchers
	//	private static AstPrinter stmt(Stmt stmt) {
	//		return switch (stmt) {
	//			case ExprStmt exprStmt -> exprStmt(exprStmt);
	//			case FnDefStmt defStmt -> fnDefStmt(defStmt);
	//			case LetStmt letStmt -> letStmt(letStmt);
	//			case StmtList stmtList -> stmtList(stmtList);
	//			case ReturnStmt returnStmt -> returnStmt(returnStmt);
	//		};
	//	}
	//
	//	private static AstPrinter expr(Expr expr) {
	//		return switch (expr) {
	//			case IdentExpr.Typed typed -> typedIdent(typed);
	//			case IdentExpr.Untyped untyped -> untypedIdent(untyped);
	//			case ExprList exprList -> exprList(exprList);
	//			case FnCallExpr callExpr -> fnCallExpr(callExpr);
	//			case LiteralExpr literalExpr -> literalExpr(literalExpr);
	//			case TypedIdentList idents -> typedIdentList(idents);
	//		};
	//	}
	//
	//	// List Nodes
	//	private static AstPrinter stmtList(StmtList stmtList) {
	//		// Stmts(List<Stmt> stmts, int line)
	//		if (stmtList.isEmpty()) {
	//			return empty();
	//		}
	//		AstPrinter[] list = stmtList
	//			.stream()
	//			.map(AstPrinter::stmt).
	//			toArray(AstPrinter[]::new);
	//
	//		return new ListPrinter("Stmts", list);
	//	}
	//
	//	private static AstPrinter exprList(ExprList exprList) {
	//		// Exprs(List<Expr> exprs, int line)
	//		if (exprList.isEmpty()) {
	//			return empty();
	//		}
	//		AstPrinter[] list = exprList
	//			.stream()
	//			.map(AstPrinter::expr)
	//			.toArray(AstPrinter[]::new);
	//
	//		return new ListPrinter("Exprs", list);
	//	}
	//
	//	private static AstPrinter typedIdentList(TypedIdentList params) {
	//		// TypedIdents(List<IdentExpr.Typed> typedIdents, int line)
	//		if (params.isEmpty()) {
	//			return empty();
	//		}
	//		AstPrinter[] list = params
	//			.stream()
	//			.map(AstPrinter::typedIdent)
	//			.toArray(AstPrinter[]::new);
	//
	//		return new ListPrinter("Typed Idents", list);
	//	}
	//
	//	// Single Nodes
	//	private static AstPrinter fnDefStmt(FnDefStmt stmt) {
	//		// FnDefStmt(IdentExpr.Untyped fnName,TypedIdents params,IdentExpr
	//		// .Untyped returnType, Stmts body,  int line)
	//		return new ListPrinter("Fn Def Stmt",
	//			createLine(stmt.line()),
	//			new SinglePrinter("Fn Name", untypedIdent(stmt.fnName())),
	//			new SinglePrinter("Params", typedIdentList(stmt.params())),
	//			new SinglePrinter("Return Type", untypedIdent(stmt.returnType())),
	//			new SinglePrinter("Body", stmtList(stmt.body()))
	//		);
	//	}
	//
	//	private static AstPrinter exprStmt(ExprStmt stmt) {
	//		// ExprStmt(Expr expr, int line)
	//		return new ListPrinter("Expr Stmt",
	//			createLine(stmt.line()),
	//			new SinglePrinter("Expr", expr(stmt.expr()))
	//		);
	//	}
	//
	//	static AstPrinter returnStmt(ReturnStmt stmt) {
	//		// ReturnStmt(Expr toReturn, int line)
	//		return new ListPrinter("Return Stmt",
	//			createLine(stmt.line()),
	//			new SinglePrinter("To Return", expr(stmt.toReturn()))
	//		);
	//	}
	//
	//	private static AstPrinter letStmt(LetStmt stmt) {
	//		// LetStmt(IdentExpr.Typed typedIdent, Expr expr, int line)
	//		return new ListPrinter("Let Stmt",
	//			createLine(stmt.line()),
	//			new SinglePrinter("Let Name", typedIdent(stmt.typedIdent())),
	//			new SinglePrinter("Expr", expr(stmt.expr()))
	//		);
	//	}
	//
	//	private static AstPrinter fnCallExpr(FnCallExpr expr) {
	//		// FnCallExpr(IdentExpr.Untyped name, Exprs params, int line)
	//		return new ListPrinter("Fn Call Expr",
	//			createLine(expr.line()),
	//			new SinglePrinter("Name", untypedIdent(expr.name())),
	//			new SinglePrinter("Params", exprList(expr.params()))
	//		);
	//	}
	//
	//	private static AstPrinter literalExpr(LiteralExpr lit) {
	//		return new ListPrinter("Literal Expr",
	//			createLine(lit.line()),
	//			switch (lit) {
	//				case LiteralExpr.Float f ->
	//					new KeyValuePrinter("Float", f.value() + "");
	//				case LiteralExpr.Int i -> new KeyValuePrinter("Int", i.value() +
	//				"");
	//				case LiteralExpr.Str s -> new KeyValuePrinter("String", s.value());
	//			}
	//		);
	//	}
	//
	//	// Identifiers
	//	private static AstPrinter untypedIdent(IdentExpr.Untyped untyped) {
	//		// Untyped(String name, int line)
	//		return new ListPrinter("Untyped Ident",
	//			createLine(untyped.line()),
	//			new KeyValuePrinter("Name", untyped.name())
	//		);
	//	}
	//
	//	private static AstPrinter typedIdent(IdentExpr.Typed typedIdent) {
	//		// Typed(String name, String type, int line)
	//		return new ListPrinter("Typed Ident",
	//			createLine(typedIdent.line()),
	//			new KeyValuePrinter("Name", typedIdent.name()),
	//			new KeyValuePrinter("Type", typedIdent.type())
	//		);
	//	}
	//
	//	// Helpers
	//	private static AstPrinter createLine(int line) {
	//		return new KeyValuePrinter("line", line + "");
	//	}
	//
	//	private static AstPrinter empty() {
	//		return new KeyValuePrinter("Empty", "");
	//	}

}
