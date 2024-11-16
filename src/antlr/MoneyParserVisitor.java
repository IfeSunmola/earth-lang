// Generated from /home/ifesunmola/Documents/dev/java/money-lang/src/antlr/MoneyParser.g4 by ANTLR 4.13.1
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
	 * Visit a parse tree produced by {@link MoneyParser#letStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetStmt(MoneyParser.LetStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varWithInit}
	 * labeled alternative in {@link MoneyParser#varStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarWithInit(MoneyParser.VarWithInitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varWithoutInit}
	 * labeled alternative in {@link MoneyParser#varStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarWithoutInit(MoneyParser.VarWithoutInitContext ctx);
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
	 * Visit a parse tree produced by {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(MoneyParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(MoneyParser.ExprListContext ctx);
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
	 * Visit a parse tree produced by {@link MoneyParser#untypedIdentExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUntypedIdentExpr(MoneyParser.UntypedIdentExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#literals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiterals(MoneyParser.LiteralsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#specialExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecialExpr(MoneyParser.SpecialExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#mathExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathExpr(MoneyParser.MathExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#comparisonExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpr(MoneyParser.ComparisonExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#logicalExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpr(MoneyParser.LogicalExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#unaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(MoneyParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MoneyParser#fnCallExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnCallExpr(MoneyParser.FnCallExprContext ctx);
}