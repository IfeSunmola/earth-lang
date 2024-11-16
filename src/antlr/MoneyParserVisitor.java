// Generated from ./src/antlr/MoneyParser.g4 by ANTLR 4.13.2
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MoneyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MoneyParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MoneyParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MoneyParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#stmtList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtList(MoneyParser.StmtListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(MoneyParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#declStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclStmt(MoneyParser.DeclStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#reassignStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReassignStmt(MoneyParser.ReassignStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#whenElseStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenElseStmt(MoneyParser.WhenElseStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#yeetStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYeetStmt(MoneyParser.YeetStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#fnDefStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnDefStmt(MoneyParser.FnDefStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#unnamedStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnnamedStmt(MoneyParser.UnnamedStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#loopStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStmt(MoneyParser.LoopStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(MoneyParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(MoneyParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpr(MoneyParser.NegExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicationExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationExpr(MoneyParser.MultiplicationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(MoneyParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code additiveExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(MoneyParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relationalExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpr(MoneyParser.RelationalExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalityExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr(MoneyParser.EqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(MoneyParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code groupedExpr}
	 * labeled alternative in {@link MoneyParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupedExpr(MoneyParser.GroupedExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link MoneyParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpr(MoneyParser.LiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code untypedIdentExpr}
	 * labeled alternative in {@link MoneyParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUntypedIdentExpr(MoneyParser.UntypedIdentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fnCallExpr}
	 * labeled alternative in {@link MoneyParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnCallExpr(MoneyParser.FnCallExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#typedIdentExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedIdentExpr(MoneyParser.TypedIdentExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#typedIdentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedIdentList(MoneyParser.TypedIdentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(MoneyParser.ExprListContext ctx);
}