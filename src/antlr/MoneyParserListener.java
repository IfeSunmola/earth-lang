// Generated from /home/ifesunmola/Documents/dev/java/money-lang/src/antlr/MoneyParser.g4 by ANTLR 4.13.1
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MoneyParser}.
 */
public interface MoneyParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MoneyParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MoneyParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MoneyParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MoneyParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MoneyParser#stmtList}.
	 * @param ctx the parse tree
	 */
	void enterStmtList(MoneyParser.StmtListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MoneyParser#stmtList}.
	 * @param ctx the parse tree
	 */
	void exitStmtList(MoneyParser.StmtListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MoneyParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(MoneyParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MoneyParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(MoneyParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MoneyParser#letStmt}.
	 * @param ctx the parse tree
	 */
	void enterLetStmt(MoneyParser.LetStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MoneyParser#letStmt}.
	 * @param ctx the parse tree
	 */
	void exitLetStmt(MoneyParser.LetStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varWithInit}
	 * labeled alternative in {@link MoneyParser#varStmt}.
	 * @param ctx the parse tree
	 */
	void enterVarWithInit(MoneyParser.VarWithInitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varWithInit}
	 * labeled alternative in {@link MoneyParser#varStmt}.
	 * @param ctx the parse tree
	 */
	void exitVarWithInit(MoneyParser.VarWithInitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varWithoutInit}
	 * labeled alternative in {@link MoneyParser#varStmt}.
	 * @param ctx the parse tree
	 */
	void enterVarWithoutInit(MoneyParser.VarWithoutInitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varWithoutInit}
	 * labeled alternative in {@link MoneyParser#varStmt}.
	 * @param ctx the parse tree
	 */
	void exitVarWithoutInit(MoneyParser.VarWithoutInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MoneyParser#reassignStmt}.
	 * @param ctx the parse tree
	 */
	void enterReassignStmt(MoneyParser.ReassignStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MoneyParser#reassignStmt}.
	 * @param ctx the parse tree
	 */
	void exitReassignStmt(MoneyParser.ReassignStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MoneyParser#whenElseStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhenElseStmt(MoneyParser.WhenElseStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MoneyParser#whenElseStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhenElseStmt(MoneyParser.WhenElseStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MoneyParser#yeetStmt}.
	 * @param ctx the parse tree
	 */
	void enterYeetStmt(MoneyParser.YeetStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MoneyParser#yeetStmt}.
	 * @param ctx the parse tree
	 */
	void exitYeetStmt(MoneyParser.YeetStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MoneyParser#fnDefStmt}.
	 * @param ctx the parse tree
	 */
	void enterFnDefStmt(MoneyParser.FnDefStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MoneyParser#fnDefStmt}.
	 * @param ctx the parse tree
	 */
	void exitFnDefStmt(MoneyParser.FnDefStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MoneyParser#unnamedStmt}.
	 * @param ctx the parse tree
	 */
	void enterUnnamedStmt(MoneyParser.UnnamedStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MoneyParser#unnamedStmt}.
	 * @param ctx the parse tree
	 */
	void exitUnnamedStmt(MoneyParser.UnnamedStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MoneyParser#loopStmt}.
	 * @param ctx the parse tree
	 */
	void enterLoopStmt(MoneyParser.LoopStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MoneyParser#loopStmt}.
	 * @param ctx the parse tree
	 */
	void exitLoopStmt(MoneyParser.LoopStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(MoneyParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(MoneyParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(MoneyParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(MoneyParser.PrimaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegExpr(MoneyParser.NegExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegExpr(MoneyParser.NegExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicationExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationExpr(MoneyParser.MultiplicationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicationExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationExpr(MoneyParser.MultiplicationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(MoneyParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(MoneyParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code additiveExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(MoneyParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code additiveExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(MoneyParser.AdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relationalExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(MoneyParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relationalExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(MoneyParser.RelationalExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalityExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr(MoneyParser.EqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalityExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr(MoneyParser.EqualityExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(MoneyParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link MoneyParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(MoneyParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code groupedExpr}
	 * labeled alternative in {@link MoneyParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterGroupedExpr(MoneyParser.GroupedExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code groupedExpr}
	 * labeled alternative in {@link MoneyParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitGroupedExpr(MoneyParser.GroupedExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link MoneyParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpr(MoneyParser.LiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link MoneyParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpr(MoneyParser.LiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code untypedIdentExpr}
	 * labeled alternative in {@link MoneyParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterUntypedIdentExpr(MoneyParser.UntypedIdentExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code untypedIdentExpr}
	 * labeled alternative in {@link MoneyParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitUntypedIdentExpr(MoneyParser.UntypedIdentExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fnCallExpr}
	 * labeled alternative in {@link MoneyParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterFnCallExpr(MoneyParser.FnCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fnCallExpr}
	 * labeled alternative in {@link MoneyParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitFnCallExpr(MoneyParser.FnCallExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MoneyParser#typedIdentExpr}.
	 * @param ctx the parse tree
	 */
	void enterTypedIdentExpr(MoneyParser.TypedIdentExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MoneyParser#typedIdentExpr}.
	 * @param ctx the parse tree
	 */
	void exitTypedIdentExpr(MoneyParser.TypedIdentExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MoneyParser#typedIdentList}.
	 * @param ctx the parse tree
	 */
	void enterTypedIdentList(MoneyParser.TypedIdentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MoneyParser#typedIdentList}.
	 * @param ctx the parse tree
	 */
	void exitTypedIdentList(MoneyParser.TypedIdentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MoneyParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(MoneyParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MoneyParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(MoneyParser.ExprListContext ctx);
}