// Generated from ./src/antlr/MoneyParser.g4 by ANTLR 4.13.2
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MoneyParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Whitespace=1, SingleLineComment=2, MultiLineComment=3, Let=4, Var=5, When=6, 
		Else=7, Fn=8, Yeet=9, Unnamed=10, Loop=11, UntypedIdent=12, StrLit=13, 
		IntLit=14, FloatLit=15, Equal=16, Gt=17, Lt=18, Gte=19, Lte=20, EqEq=21, 
		BangEq=22, Plus=23, Minus=24, Star=25, Slash=26, Mod=27, Bang=28, And=29, 
		Or=30, Colon=31, Comma=32, LParen=33, RParen=34, LBrace=35, RBrace=36;
	public static final int
		RULE_program = 0, RULE_stmtList = 1, RULE_stmt = 2, RULE_letStmt = 3, 
		RULE_varStmt = 4, RULE_reassignStmt = 5, RULE_whenElseStmt = 6, RULE_yeetStmt = 7, 
		RULE_fnDefStmt = 8, RULE_unnamedStmt = 9, RULE_loopStmt = 10, RULE_expr = 11, 
		RULE_exprList = 12, RULE_typedIdentExpr = 13, RULE_typedIdentList = 14, 
		RULE_untypedIdentExpr = 15, RULE_literals = 16, RULE_specialExpr = 17, 
		RULE_mathExpr = 18, RULE_comparisonExpr = 19, RULE_logicalExpr = 20, RULE_unaryExpr = 21, 
		RULE_fnCallExpr = 22;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stmtList", "stmt", "letStmt", "varStmt", "reassignStmt", 
			"whenElseStmt", "yeetStmt", "fnDefStmt", "unnamedStmt", "loopStmt", "expr", 
			"exprList", "typedIdentExpr", "typedIdentList", "untypedIdentExpr", "literals", 
			"specialExpr", "mathExpr", "comparisonExpr", "logicalExpr", "unaryExpr", 
			"fnCallExpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'let'", "'var'", "'when'", "'else'", "'fn'", 
			"'yeet'", "'_'", "'loop'", null, null, null, null, "'='", "'>'", "'<'", 
			"'>='", "'<='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'", "'%'", "'!'", 
			"'&&'", "'||'", "':'", "','", "'('", "')'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Whitespace", "SingleLineComment", "MultiLineComment", "Let", "Var", 
			"When", "Else", "Fn", "Yeet", "Unnamed", "Loop", "UntypedIdent", "StrLit", 
			"IntLit", "FloatLit", "Equal", "Gt", "Lt", "Gte", "Lte", "EqEq", "BangEq", 
			"Plus", "Minus", "Star", "Slash", "Mod", "Bang", "And", "Or", "Colon", 
			"Comma", "LParen", "RParen", "LBrace", "RBrace"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MoneyParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MoneyParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public StmtListContext stmtList() {
			return getRuleContext(StmtListContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MoneyParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			stmtList();
			setState(47);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtListContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public StmtListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitStmtList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtListContext stmtList() throws RecognitionException {
		StmtListContext _localctx = new StmtListContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stmtList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8048L) != 0)) {
				{
				{
				setState(49);
				stmt();
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public LetStmtContext letStmt() {
			return getRuleContext(LetStmtContext.class,0);
		}
		public VarStmtContext varStmt() {
			return getRuleContext(VarStmtContext.class,0);
		}
		public ReassignStmtContext reassignStmt() {
			return getRuleContext(ReassignStmtContext.class,0);
		}
		public WhenElseStmtContext whenElseStmt() {
			return getRuleContext(WhenElseStmtContext.class,0);
		}
		public YeetStmtContext yeetStmt() {
			return getRuleContext(YeetStmtContext.class,0);
		}
		public FnDefStmtContext fnDefStmt() {
			return getRuleContext(FnDefStmtContext.class,0);
		}
		public UnnamedStmtContext unnamedStmt() {
			return getRuleContext(UnnamedStmtContext.class,0);
		}
		public LoopStmtContext loopStmt() {
			return getRuleContext(LoopStmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_stmt);
		try {
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Let:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				letStmt();
				}
				break;
			case Var:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				varStmt();
				}
				break;
			case UntypedIdent:
				enterOuterAlt(_localctx, 3);
				{
				setState(57);
				reassignStmt();
				}
				break;
			case When:
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				whenElseStmt();
				}
				break;
			case Yeet:
				enterOuterAlt(_localctx, 5);
				{
				setState(59);
				yeetStmt();
				}
				break;
			case Fn:
				enterOuterAlt(_localctx, 6);
				{
				setState(60);
				fnDefStmt();
				}
				break;
			case Unnamed:
				enterOuterAlt(_localctx, 7);
				{
				setState(61);
				unnamedStmt();
				}
				break;
			case Loop:
				enterOuterAlt(_localctx, 8);
				{
				setState(62);
				loopStmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LetStmtContext extends ParserRuleContext {
		public TerminalNode Let() { return getToken(MoneyParser.Let, 0); }
		public TypedIdentExprContext typedIdentExpr() {
			return getRuleContext(TypedIdentExprContext.class,0);
		}
		public TerminalNode Equal() { return getToken(MoneyParser.Equal, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LetStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitLetStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetStmtContext letStmt() throws RecognitionException {
		LetStmtContext _localctx = new LetStmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_letStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(Let);
			setState(66);
			typedIdentExpr();
			setState(67);
			match(Equal);
			setState(68);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarStmtContext extends ParserRuleContext {
		public VarStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varStmt; }
	 
		public VarStmtContext() { }
		public void copyFrom(VarStmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarWithoutInitContext extends VarStmtContext {
		public TerminalNode Var() { return getToken(MoneyParser.Var, 0); }
		public TypedIdentExprContext typedIdentExpr() {
			return getRuleContext(TypedIdentExprContext.class,0);
		}
		public VarWithoutInitContext(VarStmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitVarWithoutInit(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarWithInitContext extends VarStmtContext {
		public TerminalNode Var() { return getToken(MoneyParser.Var, 0); }
		public TypedIdentExprContext typedIdentExpr() {
			return getRuleContext(TypedIdentExprContext.class,0);
		}
		public TerminalNode Equal() { return getToken(MoneyParser.Equal, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarWithInitContext(VarStmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitVarWithInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarStmtContext varStmt() throws RecognitionException {
		VarStmtContext _localctx = new VarStmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varStmt);
		try {
			setState(77);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				_localctx = new VarWithInitContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(Var);
				setState(71);
				typedIdentExpr();
				setState(72);
				match(Equal);
				setState(73);
				expr();
				}
				break;
			case 2:
				_localctx = new VarWithoutInitContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				match(Var);
				setState(76);
				typedIdentExpr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReassignStmtContext extends ParserRuleContext {
		public UntypedIdentExprContext untypedIdentExpr() {
			return getRuleContext(UntypedIdentExprContext.class,0);
		}
		public TerminalNode Equal() { return getToken(MoneyParser.Equal, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReassignStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reassignStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitReassignStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReassignStmtContext reassignStmt() throws RecognitionException {
		ReassignStmtContext _localctx = new ReassignStmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_reassignStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			untypedIdentExpr();
			setState(80);
			match(Equal);
			setState(81);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhenElseStmtContext extends ParserRuleContext {
		public List<TerminalNode> When() { return getTokens(MoneyParser.When); }
		public TerminalNode When(int i) {
			return getToken(MoneyParser.When, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> LBrace() { return getTokens(MoneyParser.LBrace); }
		public TerminalNode LBrace(int i) {
			return getToken(MoneyParser.LBrace, i);
		}
		public List<StmtListContext> stmtList() {
			return getRuleContexts(StmtListContext.class);
		}
		public StmtListContext stmtList(int i) {
			return getRuleContext(StmtListContext.class,i);
		}
		public List<TerminalNode> RBrace() { return getTokens(MoneyParser.RBrace); }
		public TerminalNode RBrace(int i) {
			return getToken(MoneyParser.RBrace, i);
		}
		public List<TerminalNode> Else() { return getTokens(MoneyParser.Else); }
		public TerminalNode Else(int i) {
			return getToken(MoneyParser.Else, i);
		}
		public WhenElseStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenElseStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitWhenElseStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhenElseStmtContext whenElseStmt() throws RecognitionException {
		WhenElseStmtContext _localctx = new WhenElseStmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_whenElseStmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(When);
			setState(84);
			expr();
			setState(85);
			match(LBrace);
			setState(86);
			stmtList();
			setState(87);
			match(RBrace);
			setState(97);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(88);
					match(Else);
					setState(89);
					match(When);
					setState(90);
					expr();
					setState(91);
					match(LBrace);
					setState(92);
					stmtList();
					setState(93);
					match(RBrace);
					}
					} 
				}
				setState(99);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Else) {
				{
				setState(100);
				match(Else);
				setState(101);
				match(LBrace);
				setState(102);
				stmtList();
				setState(103);
				match(RBrace);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class YeetStmtContext extends ParserRuleContext {
		public TerminalNode Yeet() { return getToken(MoneyParser.Yeet, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public YeetStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yeetStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitYeetStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YeetStmtContext yeetStmt() throws RecognitionException {
		YeetStmtContext _localctx = new YeetStmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_yeetStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(Yeet);
			setState(108);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FnDefStmtContext extends ParserRuleContext {
		public TerminalNode Fn() { return getToken(MoneyParser.Fn, 0); }
		public List<TerminalNode> UntypedIdent() { return getTokens(MoneyParser.UntypedIdent); }
		public TerminalNode UntypedIdent(int i) {
			return getToken(MoneyParser.UntypedIdent, i);
		}
		public TerminalNode LParen() { return getToken(MoneyParser.LParen, 0); }
		public TypedIdentListContext typedIdentList() {
			return getRuleContext(TypedIdentListContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MoneyParser.RParen, 0); }
		public TerminalNode LBrace() { return getToken(MoneyParser.LBrace, 0); }
		public StmtListContext stmtList() {
			return getRuleContext(StmtListContext.class,0);
		}
		public TerminalNode RBrace() { return getToken(MoneyParser.RBrace, 0); }
		public FnDefStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fnDefStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitFnDefStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnDefStmtContext fnDefStmt() throws RecognitionException {
		FnDefStmtContext _localctx = new FnDefStmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_fnDefStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(Fn);
			setState(111);
			match(UntypedIdent);
			setState(112);
			match(LParen);
			setState(113);
			typedIdentList();
			setState(114);
			match(RParen);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UntypedIdent) {
				{
				setState(115);
				match(UntypedIdent);
				}
			}

			setState(118);
			match(LBrace);
			setState(119);
			stmtList();
			setState(120);
			match(RBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnnamedStmtContext extends ParserRuleContext {
		public TerminalNode Unnamed() { return getToken(MoneyParser.Unnamed, 0); }
		public TerminalNode Equal() { return getToken(MoneyParser.Equal, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnnamedStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unnamedStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitUnnamedStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnnamedStmtContext unnamedStmt() throws RecognitionException {
		UnnamedStmtContext _localctx = new UnnamedStmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_unnamedStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(Unnamed);
			setState(123);
			match(Equal);
			setState(124);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LoopStmtContext extends ParserRuleContext {
		public TerminalNode Loop() { return getToken(MoneyParser.Loop, 0); }
		public VarStmtContext varStmt() {
			return getRuleContext(VarStmtContext.class,0);
		}
		public List<TerminalNode> Comma() { return getTokens(MoneyParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MoneyParser.Comma, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReassignStmtContext reassignStmt() {
			return getRuleContext(ReassignStmtContext.class,0);
		}
		public TerminalNode LBrace() { return getToken(MoneyParser.LBrace, 0); }
		public StmtListContext stmtList() {
			return getRuleContext(StmtListContext.class,0);
		}
		public TerminalNode RBrace() { return getToken(MoneyParser.RBrace, 0); }
		public LoopStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitLoopStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopStmtContext loopStmt() throws RecognitionException {
		LoopStmtContext _localctx = new LoopStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_loopStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(Loop);
			setState(127);
			varStmt();
			setState(128);
			match(Comma);
			setState(129);
			expr();
			setState(130);
			match(Comma);
			setState(131);
			reassignStmt();
			setState(132);
			match(LBrace);
			setState(133);
			stmtList();
			setState(134);
			match(RBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TypedIdentExprContext typedIdentExpr() {
			return getRuleContext(TypedIdentExprContext.class,0);
		}
		public UntypedIdentExprContext untypedIdentExpr() {
			return getRuleContext(UntypedIdentExprContext.class,0);
		}
		public LiteralsContext literals() {
			return getRuleContext(LiteralsContext.class,0);
		}
		public MathExprContext mathExpr() {
			return getRuleContext(MathExprContext.class,0);
		}
		public ComparisonExprContext comparisonExpr() {
			return getRuleContext(ComparisonExprContext.class,0);
		}
		public LogicalExprContext logicalExpr() {
			return getRuleContext(LogicalExprContext.class,0);
		}
		public UnaryExprContext unaryExpr() {
			return getRuleContext(UnaryExprContext.class,0);
		}
		public FnCallExprContext fnCallExpr() {
			return getRuleContext(FnCallExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		try {
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				typedIdentExpr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				untypedIdentExpr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				literals();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(139);
				mathExpr();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(140);
				comparisonExpr();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(141);
				logicalExpr();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(142);
				unaryExpr();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(143);
				fnCallExpr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MoneyParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MoneyParser.Comma, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_exprList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 268496896L) != 0)) {
				{
				setState(146);
				expr();
				setState(151);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(147);
						match(Comma);
						setState(148);
						expr();
						}
						} 
					}
					setState(153);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				}
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(154);
					match(Comma);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypedIdentExprContext extends ParserRuleContext {
		public List<TerminalNode> UntypedIdent() { return getTokens(MoneyParser.UntypedIdent); }
		public TerminalNode UntypedIdent(int i) {
			return getToken(MoneyParser.UntypedIdent, i);
		}
		public TerminalNode Colon() { return getToken(MoneyParser.Colon, 0); }
		public TypedIdentExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedIdentExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitTypedIdentExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypedIdentExprContext typedIdentExpr() throws RecognitionException {
		TypedIdentExprContext _localctx = new TypedIdentExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_typedIdentExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(UntypedIdent);
			setState(160);
			match(Colon);
			setState(161);
			match(UntypedIdent);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypedIdentListContext extends ParserRuleContext {
		public List<TypedIdentExprContext> typedIdentExpr() {
			return getRuleContexts(TypedIdentExprContext.class);
		}
		public TypedIdentExprContext typedIdentExpr(int i) {
			return getRuleContext(TypedIdentExprContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MoneyParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MoneyParser.Comma, i);
		}
		public TypedIdentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedIdentList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitTypedIdentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypedIdentListContext typedIdentList() throws RecognitionException {
		TypedIdentListContext _localctx = new TypedIdentListContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_typedIdentList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UntypedIdent) {
				{
				setState(163);
				typedIdentExpr();
				setState(168);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(164);
						match(Comma);
						setState(165);
						typedIdentExpr();
						}
						} 
					}
					setState(170);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(171);
					match(Comma);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UntypedIdentExprContext extends ParserRuleContext {
		public TerminalNode UntypedIdent() { return getToken(MoneyParser.UntypedIdent, 0); }
		public UntypedIdentExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_untypedIdentExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitUntypedIdentExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UntypedIdentExprContext untypedIdentExpr() throws RecognitionException {
		UntypedIdentExprContext _localctx = new UntypedIdentExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_untypedIdentExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(UntypedIdent);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralsContext extends ParserRuleContext {
		public TerminalNode StrLit() { return getToken(MoneyParser.StrLit, 0); }
		public TerminalNode IntLit() { return getToken(MoneyParser.IntLit, 0); }
		public TerminalNode FloatLit() { return getToken(MoneyParser.FloatLit, 0); }
		public LiteralsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literals; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitLiterals(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralsContext literals() throws RecognitionException {
		LiteralsContext _localctx = new LiteralsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_literals);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 57344L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SpecialExprContext extends ParserRuleContext {
		public LiteralsContext literals() {
			return getRuleContext(LiteralsContext.class,0);
		}
		public UntypedIdentExprContext untypedIdentExpr() {
			return getRuleContext(UntypedIdentExprContext.class,0);
		}
		public SpecialExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specialExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitSpecialExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecialExprContext specialExpr() throws RecognitionException {
		SpecialExprContext _localctx = new SpecialExprContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_specialExpr);
		try {
			setState(182);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case StrLit:
			case IntLit:
			case FloatLit:
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				literals();
				}
				break;
			case UntypedIdent:
				enterOuterAlt(_localctx, 2);
				{
				setState(181);
				untypedIdentExpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MathExprContext extends ParserRuleContext {
		public List<SpecialExprContext> specialExpr() {
			return getRuleContexts(SpecialExprContext.class);
		}
		public SpecialExprContext specialExpr(int i) {
			return getRuleContext(SpecialExprContext.class,i);
		}
		public TerminalNode Plus() { return getToken(MoneyParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MoneyParser.Minus, 0); }
		public TerminalNode Star() { return getToken(MoneyParser.Star, 0); }
		public TerminalNode Slash() { return getToken(MoneyParser.Slash, 0); }
		public TerminalNode Mod() { return getToken(MoneyParser.Mod, 0); }
		public MathExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mathExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitMathExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MathExprContext mathExpr() throws RecognitionException {
		MathExprContext _localctx = new MathExprContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_mathExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			specialExpr();
			setState(185);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 260046848L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(186);
			specialExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonExprContext extends ParserRuleContext {
		public List<SpecialExprContext> specialExpr() {
			return getRuleContexts(SpecialExprContext.class);
		}
		public SpecialExprContext specialExpr(int i) {
			return getRuleContext(SpecialExprContext.class,i);
		}
		public TerminalNode Gt() { return getToken(MoneyParser.Gt, 0); }
		public TerminalNode Lt() { return getToken(MoneyParser.Lt, 0); }
		public TerminalNode Gte() { return getToken(MoneyParser.Gte, 0); }
		public TerminalNode Lte() { return getToken(MoneyParser.Lte, 0); }
		public TerminalNode EqEq() { return getToken(MoneyParser.EqEq, 0); }
		public TerminalNode BangEq() { return getToken(MoneyParser.BangEq, 0); }
		public ComparisonExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitComparisonExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonExprContext comparisonExpr() throws RecognitionException {
		ComparisonExprContext _localctx = new ComparisonExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_comparisonExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			specialExpr();
			setState(189);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8257536L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(190);
			specialExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalExprContext extends ParserRuleContext {
		public List<SpecialExprContext> specialExpr() {
			return getRuleContexts(SpecialExprContext.class);
		}
		public SpecialExprContext specialExpr(int i) {
			return getRuleContext(SpecialExprContext.class,i);
		}
		public TerminalNode And() { return getToken(MoneyParser.And, 0); }
		public TerminalNode Else() { return getToken(MoneyParser.Else, 0); }
		public LogicalExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitLogicalExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalExprContext logicalExpr() throws RecognitionException {
		LogicalExprContext _localctx = new LogicalExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_logicalExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			specialExpr();
			setState(193);
			_la = _input.LA(1);
			if ( !(_la==Else || _la==And) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(194);
			specialExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExprContext extends ParserRuleContext {
		public TerminalNode Bang() { return getToken(MoneyParser.Bang, 0); }
		public SpecialExprContext specialExpr() {
			return getRuleContext(SpecialExprContext.class,0);
		}
		public UnaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExprContext unaryExpr() throws RecognitionException {
		UnaryExprContext _localctx = new UnaryExprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_unaryExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(Bang);
			setState(197);
			specialExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FnCallExprContext extends ParserRuleContext {
		public TerminalNode UntypedIdent() { return getToken(MoneyParser.UntypedIdent, 0); }
		public TerminalNode LParen() { return getToken(MoneyParser.LParen, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MoneyParser.RParen, 0); }
		public FnCallExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fnCallExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitFnCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnCallExprContext fnCallExpr() throws RecognitionException {
		FnCallExprContext _localctx = new FnCallExprContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_fnCallExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(UntypedIdent);
			setState(200);
			match(LParen);
			setState(201);
			exprList();
			setState(202);
			match(RParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001$\u00cd\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0005\u00013\b\u0001\n\u0001\f\u00016\t\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002@\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004N\b\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0005\u0006`\b\u0006\n\u0006\f\u0006c\t\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"j\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0003\bu\b\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u0091\b\u000b\u0001\f\u0001\f\u0001\f\u0005\f\u0096\b\f\n\f\f\f"+
		"\u0099\t\f\u0001\f\u0003\f\u009c\b\f\u0003\f\u009e\b\f\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00a7"+
		"\b\u000e\n\u000e\f\u000e\u00aa\t\u000e\u0001\u000e\u0003\u000e\u00ad\b"+
		"\u000e\u0003\u000e\u00af\b\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0003\u0011\u00b7\b\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0000\u0000\u0017\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,\u0000\u0004\u0001"+
		"\u0000\r\u000f\u0001\u0000\u0017\u001b\u0001\u0000\u0011\u0016\u0002\u0000"+
		"\u0007\u0007\u001d\u001d\u00cf\u0000.\u0001\u0000\u0000\u0000\u00024\u0001"+
		"\u0000\u0000\u0000\u0004?\u0001\u0000\u0000\u0000\u0006A\u0001\u0000\u0000"+
		"\u0000\bM\u0001\u0000\u0000\u0000\nO\u0001\u0000\u0000\u0000\fS\u0001"+
		"\u0000\u0000\u0000\u000ek\u0001\u0000\u0000\u0000\u0010n\u0001\u0000\u0000"+
		"\u0000\u0012z\u0001\u0000\u0000\u0000\u0014~\u0001\u0000\u0000\u0000\u0016"+
		"\u0090\u0001\u0000\u0000\u0000\u0018\u009d\u0001\u0000\u0000\u0000\u001a"+
		"\u009f\u0001\u0000\u0000\u0000\u001c\u00ae\u0001\u0000\u0000\u0000\u001e"+
		"\u00b0\u0001\u0000\u0000\u0000 \u00b2\u0001\u0000\u0000\u0000\"\u00b6"+
		"\u0001\u0000\u0000\u0000$\u00b8\u0001\u0000\u0000\u0000&\u00bc\u0001\u0000"+
		"\u0000\u0000(\u00c0\u0001\u0000\u0000\u0000*\u00c4\u0001\u0000\u0000\u0000"+
		",\u00c7\u0001\u0000\u0000\u0000./\u0003\u0002\u0001\u0000/0\u0005\u0000"+
		"\u0000\u00010\u0001\u0001\u0000\u0000\u000013\u0003\u0004\u0002\u0000"+
		"21\u0001\u0000\u0000\u000036\u0001\u0000\u0000\u000042\u0001\u0000\u0000"+
		"\u000045\u0001\u0000\u0000\u00005\u0003\u0001\u0000\u0000\u000064\u0001"+
		"\u0000\u0000\u00007@\u0003\u0006\u0003\u00008@\u0003\b\u0004\u00009@\u0003"+
		"\n\u0005\u0000:@\u0003\f\u0006\u0000;@\u0003\u000e\u0007\u0000<@\u0003"+
		"\u0010\b\u0000=@\u0003\u0012\t\u0000>@\u0003\u0014\n\u0000?7\u0001\u0000"+
		"\u0000\u0000?8\u0001\u0000\u0000\u0000?9\u0001\u0000\u0000\u0000?:\u0001"+
		"\u0000\u0000\u0000?;\u0001\u0000\u0000\u0000?<\u0001\u0000\u0000\u0000"+
		"?=\u0001\u0000\u0000\u0000?>\u0001\u0000\u0000\u0000@\u0005\u0001\u0000"+
		"\u0000\u0000AB\u0005\u0004\u0000\u0000BC\u0003\u001a\r\u0000CD\u0005\u0010"+
		"\u0000\u0000DE\u0003\u0016\u000b\u0000E\u0007\u0001\u0000\u0000\u0000"+
		"FG\u0005\u0005\u0000\u0000GH\u0003\u001a\r\u0000HI\u0005\u0010\u0000\u0000"+
		"IJ\u0003\u0016\u000b\u0000JN\u0001\u0000\u0000\u0000KL\u0005\u0005\u0000"+
		"\u0000LN\u0003\u001a\r\u0000MF\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000"+
		"\u0000N\t\u0001\u0000\u0000\u0000OP\u0003\u001e\u000f\u0000PQ\u0005\u0010"+
		"\u0000\u0000QR\u0003\u0016\u000b\u0000R\u000b\u0001\u0000\u0000\u0000"+
		"ST\u0005\u0006\u0000\u0000TU\u0003\u0016\u000b\u0000UV\u0005#\u0000\u0000"+
		"VW\u0003\u0002\u0001\u0000Wa\u0005$\u0000\u0000XY\u0005\u0007\u0000\u0000"+
		"YZ\u0005\u0006\u0000\u0000Z[\u0003\u0016\u000b\u0000[\\\u0005#\u0000\u0000"+
		"\\]\u0003\u0002\u0001\u0000]^\u0005$\u0000\u0000^`\u0001\u0000\u0000\u0000"+
		"_X\u0001\u0000\u0000\u0000`c\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000"+
		"\u0000ab\u0001\u0000\u0000\u0000bi\u0001\u0000\u0000\u0000ca\u0001\u0000"+
		"\u0000\u0000de\u0005\u0007\u0000\u0000ef\u0005#\u0000\u0000fg\u0003\u0002"+
		"\u0001\u0000gh\u0005$\u0000\u0000hj\u0001\u0000\u0000\u0000id\u0001\u0000"+
		"\u0000\u0000ij\u0001\u0000\u0000\u0000j\r\u0001\u0000\u0000\u0000kl\u0005"+
		"\t\u0000\u0000lm\u0003\u0016\u000b\u0000m\u000f\u0001\u0000\u0000\u0000"+
		"no\u0005\b\u0000\u0000op\u0005\f\u0000\u0000pq\u0005!\u0000\u0000qr\u0003"+
		"\u001c\u000e\u0000rt\u0005\"\u0000\u0000su\u0005\f\u0000\u0000ts\u0001"+
		"\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000"+
		"vw\u0005#\u0000\u0000wx\u0003\u0002\u0001\u0000xy\u0005$\u0000\u0000y"+
		"\u0011\u0001\u0000\u0000\u0000z{\u0005\n\u0000\u0000{|\u0005\u0010\u0000"+
		"\u0000|}\u0003\u0016\u000b\u0000}\u0013\u0001\u0000\u0000\u0000~\u007f"+
		"\u0005\u000b\u0000\u0000\u007f\u0080\u0003\b\u0004\u0000\u0080\u0081\u0005"+
		" \u0000\u0000\u0081\u0082\u0003\u0016\u000b\u0000\u0082\u0083\u0005 \u0000"+
		"\u0000\u0083\u0084\u0003\n\u0005\u0000\u0084\u0085\u0005#\u0000\u0000"+
		"\u0085\u0086\u0003\u0002\u0001\u0000\u0086\u0087\u0005$\u0000\u0000\u0087"+
		"\u0015\u0001\u0000\u0000\u0000\u0088\u0091\u0003\u001a\r\u0000\u0089\u0091"+
		"\u0003\u001e\u000f\u0000\u008a\u0091\u0003 \u0010\u0000\u008b\u0091\u0003"+
		"$\u0012\u0000\u008c\u0091\u0003&\u0013\u0000\u008d\u0091\u0003(\u0014"+
		"\u0000\u008e\u0091\u0003*\u0015\u0000\u008f\u0091\u0003,\u0016\u0000\u0090"+
		"\u0088\u0001\u0000\u0000\u0000\u0090\u0089\u0001\u0000\u0000\u0000\u0090"+
		"\u008a\u0001\u0000\u0000\u0000\u0090\u008b\u0001\u0000\u0000\u0000\u0090"+
		"\u008c\u0001\u0000\u0000\u0000\u0090\u008d\u0001\u0000\u0000\u0000\u0090"+
		"\u008e\u0001\u0000\u0000\u0000\u0090\u008f\u0001\u0000\u0000\u0000\u0091"+
		"\u0017\u0001\u0000\u0000\u0000\u0092\u0097\u0003\u0016\u000b\u0000\u0093"+
		"\u0094\u0005 \u0000\u0000\u0094\u0096\u0003\u0016\u000b\u0000\u0095\u0093"+
		"\u0001\u0000\u0000\u0000\u0096\u0099\u0001\u0000\u0000\u0000\u0097\u0095"+
		"\u0001\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u009b"+
		"\u0001\u0000\u0000\u0000\u0099\u0097\u0001\u0000\u0000\u0000\u009a\u009c"+
		"\u0005 \u0000\u0000\u009b\u009a\u0001\u0000\u0000\u0000\u009b\u009c\u0001"+
		"\u0000\u0000\u0000\u009c\u009e\u0001\u0000\u0000\u0000\u009d\u0092\u0001"+
		"\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u0019\u0001"+
		"\u0000\u0000\u0000\u009f\u00a0\u0005\f\u0000\u0000\u00a0\u00a1\u0005\u001f"+
		"\u0000\u0000\u00a1\u00a2\u0005\f\u0000\u0000\u00a2\u001b\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a8\u0003\u001a\r\u0000\u00a4\u00a5\u0005 \u0000\u0000"+
		"\u00a5\u00a7\u0003\u001a\r\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a7"+
		"\u00aa\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a8"+
		"\u00a9\u0001\u0000\u0000\u0000\u00a9\u00ac\u0001\u0000\u0000\u0000\u00aa"+
		"\u00a8\u0001\u0000\u0000\u0000\u00ab\u00ad\u0005 \u0000\u0000\u00ac\u00ab"+
		"\u0001\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u00af"+
		"\u0001\u0000\u0000\u0000\u00ae\u00a3\u0001\u0000\u0000\u0000\u00ae\u00af"+
		"\u0001\u0000\u0000\u0000\u00af\u001d\u0001\u0000\u0000\u0000\u00b0\u00b1"+
		"\u0005\f\u0000\u0000\u00b1\u001f\u0001\u0000\u0000\u0000\u00b2\u00b3\u0007"+
		"\u0000\u0000\u0000\u00b3!\u0001\u0000\u0000\u0000\u00b4\u00b7\u0003 \u0010"+
		"\u0000\u00b5\u00b7\u0003\u001e\u000f\u0000\u00b6\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b6\u00b5\u0001\u0000\u0000\u0000\u00b7#\u0001\u0000\u0000\u0000"+
		"\u00b8\u00b9\u0003\"\u0011\u0000\u00b9\u00ba\u0007\u0001\u0000\u0000\u00ba"+
		"\u00bb\u0003\"\u0011\u0000\u00bb%\u0001\u0000\u0000\u0000\u00bc\u00bd"+
		"\u0003\"\u0011\u0000\u00bd\u00be\u0007\u0002\u0000\u0000\u00be\u00bf\u0003"+
		"\"\u0011\u0000\u00bf\'\u0001\u0000\u0000\u0000\u00c0\u00c1\u0003\"\u0011"+
		"\u0000\u00c1\u00c2\u0007\u0003\u0000\u0000\u00c2\u00c3\u0003\"\u0011\u0000"+
		"\u00c3)\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005\u001c\u0000\u0000\u00c5"+
		"\u00c6\u0003\"\u0011\u0000\u00c6+\u0001\u0000\u0000\u0000\u00c7\u00c8"+
		"\u0005\f\u0000\u0000\u00c8\u00c9\u0005!\u0000\u0000\u00c9\u00ca\u0003"+
		"\u0018\f\u0000\u00ca\u00cb\u0005\"\u0000\u0000\u00cb-\u0001\u0000\u0000"+
		"\u0000\u000e4?Mait\u0090\u0097\u009b\u009d\u00a8\u00ac\u00ae\u00b6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}