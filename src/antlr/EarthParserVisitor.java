// Generated from ./src/antlr/EarthParser.g4 by ANTLR 4.13.2
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EarthParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EarthParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EarthParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(EarthParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#stmtList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtList(EarthParser.StmtListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(EarthParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#declStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclStmt(EarthParser.DeclStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#reassignStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReassignStmt(EarthParser.ReassignStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#when}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhen(EarthParser.WhenContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#elseWhen}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseWhen(EarthParser.ElseWhenContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse(EarthParser.ElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#whenElseStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenElseStmt(EarthParser.WhenElseStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#yeetStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYeetStmt(EarthParser.YeetStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#fnDefStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnDefStmt(EarthParser.FnDefStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#unnamedStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnnamedStmt(EarthParser.UnnamedStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#loopStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStmt(EarthParser.LoopStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link EarthParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(EarthParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryExpr}
	 * labeled alternative in {@link EarthParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(EarthParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link EarthParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpr(EarthParser.NegExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicationExpr}
	 * labeled alternative in {@link EarthParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationExpr(EarthParser.MultiplicationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link EarthParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(EarthParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code additiveExpr}
	 * labeled alternative in {@link EarthParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(EarthParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relationalExpr}
	 * labeled alternative in {@link EarthParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpr(EarthParser.RelationalExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalityExpr}
	 * labeled alternative in {@link EarthParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr(EarthParser.EqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link EarthParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(EarthParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code groupedExpr}
	 * labeled alternative in {@link EarthParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupedExpr(EarthParser.GroupedExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link EarthParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpr(EarthParser.LiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code untypedIdentExpr}
	 * labeled alternative in {@link EarthParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUntypedIdentExpr(EarthParser.UntypedIdentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fnCallExpr}
	 * labeled alternative in {@link EarthParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnCallExpr(EarthParser.FnCallExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#typedIdentExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedIdentExpr(EarthParser.TypedIdentExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#typedIdentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedIdentList(EarthParser.TypedIdentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EarthParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(EarthParser.ExprListContext ctx);
}