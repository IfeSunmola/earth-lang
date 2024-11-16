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
		IntLit=14, FloatLit=15, Eq=16, Gt=17, Lt=18, Gte=19, Lte=20, EqEq=21, 
		BangEq=22, Plus=23, Minus=24, Star=25, Slash=26, Mod=27, Bang=28, And=29, 
		Or=30, Colon=31, Comma=32, LParen=33, RParen=34, LBrace=35, RBrace=36;
	public static final int
		RULE_program = 0, RULE_stmtList = 1, RULE_stmt = 2, RULE_letStmt = 3, 
		RULE_varStmt = 4, RULE_reassignStmt = 5, RULE_whenElseStmt = 6, RULE_yeetStmt = 7, 
		RULE_fnDefStmt = 8, RULE_unnamedStmt = 9, RULE_loopStmt = 10, RULE_expr = 11, 
		RULE_primary = 12, RULE_typedIdentExpr = 13, RULE_typedIdentList = 14, 
		RULE_exprList = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stmtList", "stmt", "letStmt", "varStmt", "reassignStmt", 
			"whenElseStmt", "yeetStmt", "fnDefStmt", "unnamedStmt", "loopStmt", "expr", 
			"primary", "typedIdentExpr", "typedIdentList", "exprList"
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
			"IntLit", "FloatLit", "Eq", "Gt", "Lt", "Gte", "Lte", "EqEq", "BangEq", 
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
			setState(32);
			stmtList();
			setState(33);
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
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8048L) != 0)) {
				{
				{
				setState(35);
				stmt();
				}
				}
				setState(40);
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
			setState(49);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Let:
				enterOuterAlt(_localctx, 1);
				{
				setState(41);
				letStmt();
				}
				break;
			case Var:
				enterOuterAlt(_localctx, 2);
				{
				setState(42);
				varStmt();
				}
				break;
			case UntypedIdent:
				enterOuterAlt(_localctx, 3);
				{
				setState(43);
				reassignStmt();
				}
				break;
			case When:
				enterOuterAlt(_localctx, 4);
				{
				setState(44);
				whenElseStmt();
				}
				break;
			case Yeet:
				enterOuterAlt(_localctx, 5);
				{
				setState(45);
				yeetStmt();
				}
				break;
			case Fn:
				enterOuterAlt(_localctx, 6);
				{
				setState(46);
				fnDefStmt();
				}
				break;
			case Unnamed:
				enterOuterAlt(_localctx, 7);
				{
				setState(47);
				unnamedStmt();
				}
				break;
			case Loop:
				enterOuterAlt(_localctx, 8);
				{
				setState(48);
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
		public TerminalNode Eq() { return getToken(MoneyParser.Eq, 0); }
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
			setState(51);
			match(Let);
			setState(52);
			typedIdentExpr();
			setState(53);
			match(Eq);
			setState(54);
			expr(0);
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
		public TerminalNode Eq() { return getToken(MoneyParser.Eq, 0); }
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
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				_localctx = new VarWithInitContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				match(Var);
				setState(57);
				typedIdentExpr();
				setState(58);
				match(Eq);
				setState(59);
				expr(0);
				}
				break;
			case 2:
				_localctx = new VarWithoutInitContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				match(Var);
				setState(62);
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
		public TerminalNode UntypedIdent() { return getToken(MoneyParser.UntypedIdent, 0); }
		public TerminalNode Eq() { return getToken(MoneyParser.Eq, 0); }
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
			setState(65);
			match(UntypedIdent);
			setState(66);
			match(Eq);
			setState(67);
			expr(0);
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
			setState(69);
			match(When);
			setState(70);
			expr(0);
			setState(71);
			match(LBrace);
			setState(72);
			stmtList();
			setState(73);
			match(RBrace);
			setState(83);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(74);
					match(Else);
					setState(75);
					match(When);
					setState(76);
					expr(0);
					setState(77);
					match(LBrace);
					setState(78);
					stmtList();
					setState(79);
					match(RBrace);
					}
					} 
				}
				setState(85);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Else) {
				{
				setState(86);
				match(Else);
				setState(87);
				match(LBrace);
				setState(88);
				stmtList();
				setState(89);
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
			setState(93);
			match(Yeet);
			setState(94);
			expr(0);
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
			setState(96);
			match(Fn);
			setState(97);
			match(UntypedIdent);
			setState(98);
			match(LParen);
			setState(99);
			typedIdentList();
			setState(100);
			match(RParen);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UntypedIdent) {
				{
				setState(101);
				match(UntypedIdent);
				}
			}

			setState(104);
			match(LBrace);
			setState(105);
			stmtList();
			setState(106);
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
		public TerminalNode Eq() { return getToken(MoneyParser.Eq, 0); }
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
			setState(108);
			match(Unnamed);
			setState(109);
			match(Eq);
			setState(110);
			expr(0);
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
			setState(112);
			match(Loop);
			setState(113);
			varStmt();
			setState(114);
			match(Comma);
			setState(115);
			expr(0);
			setState(116);
			match(Comma);
			setState(117);
			reassignStmt();
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
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExprContext extends ExprContext {
		public TerminalNode Bang() { return getToken(MoneyParser.Bang, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryExprContext extends ExprContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public PrimaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitPrimaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegExprContext extends ExprContext {
		public TerminalNode Minus() { return getToken(MoneyParser.Minus, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NegExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitNegExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicationExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Star() { return getToken(MoneyParser.Star, 0); }
		public TerminalNode Slash() { return getToken(MoneyParser.Slash, 0); }
		public TerminalNode Mod() { return getToken(MoneyParser.Mod, 0); }
		public MultiplicationExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitMultiplicationExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Or() { return getToken(MoneyParser.Or, 0); }
		public OrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Plus() { return getToken(MoneyParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MoneyParser.Minus, 0); }
		public AdditiveExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitAdditiveExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationalExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Lte() { return getToken(MoneyParser.Lte, 0); }
		public TerminalNode Gte() { return getToken(MoneyParser.Gte, 0); }
		public TerminalNode Lt() { return getToken(MoneyParser.Lt, 0); }
		public TerminalNode Gt() { return getToken(MoneyParser.Gt, 0); }
		public RelationalExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitRelationalExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualityExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EqEq() { return getToken(MoneyParser.EqEq, 0); }
		public TerminalNode BangEq() { return getToken(MoneyParser.BangEq, 0); }
		public EqualityExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitEqualityExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode And() { return getToken(MoneyParser.And, 0); }
		public AndExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Minus:
				{
				_localctx = new NegExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(123);
				match(Minus);
				setState(124);
				expr(9);
				}
				break;
			case Bang:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(125);
				match(Bang);
				setState(126);
				expr(8);
				}
				break;
			case UntypedIdent:
			case StrLit:
			case IntLit:
			case FloatLit:
			case LParen:
				{
				_localctx = new PrimaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(127);
				primary();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(150);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(148);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicationExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(130);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(131);
						((MultiplicationExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 234881024L) != 0)) ) {
							((MultiplicationExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(132);
						expr(8);
						}
						break;
					case 2:
						{
						_localctx = new AdditiveExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(133);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(134);
						((AdditiveExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
							((AdditiveExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(135);
						expr(7);
						}
						break;
					case 3:
						{
						_localctx = new RelationalExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(136);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(137);
						((RelationalExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1966080L) != 0)) ) {
							((RelationalExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(138);
						expr(6);
						}
						break;
					case 4:
						{
						_localctx = new EqualityExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(139);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(140);
						((EqualityExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EqEq || _la==BangEq) ) {
							((EqualityExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(141);
						expr(5);
						}
						break;
					case 5:
						{
						_localctx = new AndExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(142);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(143);
						match(And);
						setState(144);
						expr(4);
						}
						break;
					case 6:
						{
						_localctx = new OrExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(145);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(146);
						match(Or);
						setState(147);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(152);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
	 
		public PrimaryContext() { }
		public void copyFrom(PrimaryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GroupedExprContext extends PrimaryContext {
		public TerminalNode LParen() { return getToken(MoneyParser.LParen, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MoneyParser.RParen, 0); }
		public GroupedExprContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitGroupedExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LiteralExprContext extends PrimaryContext {
		public TerminalNode IntLit() { return getToken(MoneyParser.IntLit, 0); }
		public TerminalNode FloatLit() { return getToken(MoneyParser.FloatLit, 0); }
		public TerminalNode StrLit() { return getToken(MoneyParser.StrLit, 0); }
		public LiteralExprContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UntypedIdentExprContext extends PrimaryContext {
		public TerminalNode UntypedIdent() { return getToken(MoneyParser.UntypedIdent, 0); }
		public UntypedIdentExprContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitUntypedIdentExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FnCallExprContext extends PrimaryContext {
		public TerminalNode UntypedIdent() { return getToken(MoneyParser.UntypedIdent, 0); }
		public TerminalNode LParen() { return getToken(MoneyParser.LParen, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MoneyParser.RParen, 0); }
		public FnCallExprContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitFnCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_primary);
		int _la;
		try {
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new GroupedExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				match(LParen);
				setState(154);
				expr(0);
				setState(155);
				match(RParen);
				}
				break;
			case 2:
				_localctx = new LiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
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
				break;
			case 3:
				_localctx = new UntypedIdentExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(158);
				match(UntypedIdent);
				}
				break;
			case 4:
				_localctx = new FnCallExprContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(159);
				match(UntypedIdent);
				setState(160);
				match(LParen);
				setState(161);
				exprList();
				setState(162);
				match(RParen);
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
			setState(166);
			match(UntypedIdent);
			setState(167);
			match(Colon);
			setState(168);
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
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UntypedIdent) {
				{
				setState(170);
				typedIdentExpr();
				setState(175);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(171);
						match(Comma);
						setState(172);
						typedIdentExpr();
						}
						} 
					}
					setState(177);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(178);
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
		enterRule(_localctx, 30, RULE_exprList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8875208704L) != 0)) {
				{
				setState(183);
				expr(0);
				setState(188);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(184);
						match(Comma);
						setState(185);
						expr(0);
						}
						} 
					}
					setState(190);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				}
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(191);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001$\u00c5\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0005\u0001%\b\u0001"+
		"\n\u0001\f\u0001(\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00022\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004@\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0005\u0006R\b\u0006\n\u0006\f\u0006U\t\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\\\b\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0003\bg\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u0081\b\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u0095\b\u000b\n"+
		"\u000b\f\u000b\u0098\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00a5\b\f\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00ae"+
		"\b\u000e\n\u000e\f\u000e\u00b1\t\u000e\u0001\u000e\u0003\u000e\u00b4\b"+
		"\u000e\u0003\u000e\u00b6\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0005"+
		"\u000f\u00bb\b\u000f\n\u000f\f\u000f\u00be\t\u000f\u0001\u000f\u0003\u000f"+
		"\u00c1\b\u000f\u0003\u000f\u00c3\b\u000f\u0001\u000f\u0000\u0001\u0016"+
		"\u0010\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e\u0000\u0005\u0001\u0000\u0019\u001b\u0001\u0000\u0017"+
		"\u0018\u0001\u0000\u0011\u0014\u0001\u0000\u0015\u0016\u0001\u0000\r\u000f"+
		"\u00d1\u0000 \u0001\u0000\u0000\u0000\u0002&\u0001\u0000\u0000\u0000\u0004"+
		"1\u0001\u0000\u0000\u0000\u00063\u0001\u0000\u0000\u0000\b?\u0001\u0000"+
		"\u0000\u0000\nA\u0001\u0000\u0000\u0000\fE\u0001\u0000\u0000\u0000\u000e"+
		"]\u0001\u0000\u0000\u0000\u0010`\u0001\u0000\u0000\u0000\u0012l\u0001"+
		"\u0000\u0000\u0000\u0014p\u0001\u0000\u0000\u0000\u0016\u0080\u0001\u0000"+
		"\u0000\u0000\u0018\u00a4\u0001\u0000\u0000\u0000\u001a\u00a6\u0001\u0000"+
		"\u0000\u0000\u001c\u00b5\u0001\u0000\u0000\u0000\u001e\u00c2\u0001\u0000"+
		"\u0000\u0000 !\u0003\u0002\u0001\u0000!\"\u0005\u0000\u0000\u0001\"\u0001"+
		"\u0001\u0000\u0000\u0000#%\u0003\u0004\u0002\u0000$#\u0001\u0000\u0000"+
		"\u0000%(\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000\u0000&\'\u0001\u0000"+
		"\u0000\u0000\'\u0003\u0001\u0000\u0000\u0000(&\u0001\u0000\u0000\u0000"+
		")2\u0003\u0006\u0003\u0000*2\u0003\b\u0004\u0000+2\u0003\n\u0005\u0000"+
		",2\u0003\f\u0006\u0000-2\u0003\u000e\u0007\u0000.2\u0003\u0010\b\u0000"+
		"/2\u0003\u0012\t\u000002\u0003\u0014\n\u00001)\u0001\u0000\u0000\u0000"+
		"1*\u0001\u0000\u0000\u00001+\u0001\u0000\u0000\u00001,\u0001\u0000\u0000"+
		"\u00001-\u0001\u0000\u0000\u00001.\u0001\u0000\u0000\u00001/\u0001\u0000"+
		"\u0000\u000010\u0001\u0000\u0000\u00002\u0005\u0001\u0000\u0000\u0000"+
		"34\u0005\u0004\u0000\u000045\u0003\u001a\r\u000056\u0005\u0010\u0000\u0000"+
		"67\u0003\u0016\u000b\u00007\u0007\u0001\u0000\u0000\u000089\u0005\u0005"+
		"\u0000\u00009:\u0003\u001a\r\u0000:;\u0005\u0010\u0000\u0000;<\u0003\u0016"+
		"\u000b\u0000<@\u0001\u0000\u0000\u0000=>\u0005\u0005\u0000\u0000>@\u0003"+
		"\u001a\r\u0000?8\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000@\t"+
		"\u0001\u0000\u0000\u0000AB\u0005\f\u0000\u0000BC\u0005\u0010\u0000\u0000"+
		"CD\u0003\u0016\u000b\u0000D\u000b\u0001\u0000\u0000\u0000EF\u0005\u0006"+
		"\u0000\u0000FG\u0003\u0016\u000b\u0000GH\u0005#\u0000\u0000HI\u0003\u0002"+
		"\u0001\u0000IS\u0005$\u0000\u0000JK\u0005\u0007\u0000\u0000KL\u0005\u0006"+
		"\u0000\u0000LM\u0003\u0016\u000b\u0000MN\u0005#\u0000\u0000NO\u0003\u0002"+
		"\u0001\u0000OP\u0005$\u0000\u0000PR\u0001\u0000\u0000\u0000QJ\u0001\u0000"+
		"\u0000\u0000RU\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001"+
		"\u0000\u0000\u0000T[\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000"+
		"VW\u0005\u0007\u0000\u0000WX\u0005#\u0000\u0000XY\u0003\u0002\u0001\u0000"+
		"YZ\u0005$\u0000\u0000Z\\\u0001\u0000\u0000\u0000[V\u0001\u0000\u0000\u0000"+
		"[\\\u0001\u0000\u0000\u0000\\\r\u0001\u0000\u0000\u0000]^\u0005\t\u0000"+
		"\u0000^_\u0003\u0016\u000b\u0000_\u000f\u0001\u0000\u0000\u0000`a\u0005"+
		"\b\u0000\u0000ab\u0005\f\u0000\u0000bc\u0005!\u0000\u0000cd\u0003\u001c"+
		"\u000e\u0000df\u0005\"\u0000\u0000eg\u0005\f\u0000\u0000fe\u0001\u0000"+
		"\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hi\u0005"+
		"#\u0000\u0000ij\u0003\u0002\u0001\u0000jk\u0005$\u0000\u0000k\u0011\u0001"+
		"\u0000\u0000\u0000lm\u0005\n\u0000\u0000mn\u0005\u0010\u0000\u0000no\u0003"+
		"\u0016\u000b\u0000o\u0013\u0001\u0000\u0000\u0000pq\u0005\u000b\u0000"+
		"\u0000qr\u0003\b\u0004\u0000rs\u0005 \u0000\u0000st\u0003\u0016\u000b"+
		"\u0000tu\u0005 \u0000\u0000uv\u0003\n\u0005\u0000vw\u0005#\u0000\u0000"+
		"wx\u0003\u0002\u0001\u0000xy\u0005$\u0000\u0000y\u0015\u0001\u0000\u0000"+
		"\u0000z{\u0006\u000b\uffff\uffff\u0000{|\u0005\u0018\u0000\u0000|\u0081"+
		"\u0003\u0016\u000b\t}~\u0005\u001c\u0000\u0000~\u0081\u0003\u0016\u000b"+
		"\b\u007f\u0081\u0003\u0018\f\u0000\u0080z\u0001\u0000\u0000\u0000\u0080"+
		"}\u0001\u0000\u0000\u0000\u0080\u007f\u0001\u0000\u0000\u0000\u0081\u0096"+
		"\u0001\u0000\u0000\u0000\u0082\u0083\n\u0007\u0000\u0000\u0083\u0084\u0007"+
		"\u0000\u0000\u0000\u0084\u0095\u0003\u0016\u000b\b\u0085\u0086\n\u0006"+
		"\u0000\u0000\u0086\u0087\u0007\u0001\u0000\u0000\u0087\u0095\u0003\u0016"+
		"\u000b\u0007\u0088\u0089\n\u0005\u0000\u0000\u0089\u008a\u0007\u0002\u0000"+
		"\u0000\u008a\u0095\u0003\u0016\u000b\u0006\u008b\u008c\n\u0004\u0000\u0000"+
		"\u008c\u008d\u0007\u0003\u0000\u0000\u008d\u0095\u0003\u0016\u000b\u0005"+
		"\u008e\u008f\n\u0003\u0000\u0000\u008f\u0090\u0005\u001d\u0000\u0000\u0090"+
		"\u0095\u0003\u0016\u000b\u0004\u0091\u0092\n\u0002\u0000\u0000\u0092\u0093"+
		"\u0005\u001e\u0000\u0000\u0093\u0095\u0003\u0016\u000b\u0003\u0094\u0082"+
		"\u0001\u0000\u0000\u0000\u0094\u0085\u0001\u0000\u0000\u0000\u0094\u0088"+
		"\u0001\u0000\u0000\u0000\u0094\u008b\u0001\u0000\u0000\u0000\u0094\u008e"+
		"\u0001\u0000\u0000\u0000\u0094\u0091\u0001\u0000\u0000\u0000\u0095\u0098"+
		"\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0096\u0097"+
		"\u0001\u0000\u0000\u0000\u0097\u0017\u0001\u0000\u0000\u0000\u0098\u0096"+
		"\u0001\u0000\u0000\u0000\u0099\u009a\u0005!\u0000\u0000\u009a\u009b\u0003"+
		"\u0016\u000b\u0000\u009b\u009c\u0005\"\u0000\u0000\u009c\u00a5\u0001\u0000"+
		"\u0000\u0000\u009d\u00a5\u0007\u0004\u0000\u0000\u009e\u00a5\u0005\f\u0000"+
		"\u0000\u009f\u00a0\u0005\f\u0000\u0000\u00a0\u00a1\u0005!\u0000\u0000"+
		"\u00a1\u00a2\u0003\u001e\u000f\u0000\u00a2\u00a3\u0005\"\u0000\u0000\u00a3"+
		"\u00a5\u0001\u0000\u0000\u0000\u00a4\u0099\u0001\u0000\u0000\u0000\u00a4"+
		"\u009d\u0001\u0000\u0000\u0000\u00a4\u009e\u0001\u0000\u0000\u0000\u00a4"+
		"\u009f\u0001\u0000\u0000\u0000\u00a5\u0019\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a7\u0005\f\u0000\u0000\u00a7\u00a8\u0005\u001f\u0000\u0000\u00a8\u00a9"+
		"\u0005\f\u0000\u0000\u00a9\u001b\u0001\u0000\u0000\u0000\u00aa\u00af\u0003"+
		"\u001a\r\u0000\u00ab\u00ac\u0005 \u0000\u0000\u00ac\u00ae\u0003\u001a"+
		"\r\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u00b1\u0001\u0000\u0000"+
		"\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b3\u0001\u0000\u0000\u0000\u00b1\u00af\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b4\u0005 \u0000\u0000\u00b3\u00b2\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b5\u00aa\u0001\u0000\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b6\u001d\u0001\u0000\u0000\u0000\u00b7\u00bc\u0003\u0016\u000b\u0000"+
		"\u00b8\u00b9\u0005 \u0000\u0000\u00b9\u00bb\u0003\u0016\u000b\u0000\u00ba"+
		"\u00b8\u0001\u0000\u0000\u0000\u00bb\u00be\u0001\u0000\u0000\u0000\u00bc"+
		"\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd"+
		"\u00c0\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00bf"+
		"\u00c1\u0005 \u0000\u0000\u00c0\u00bf\u0001\u0000\u0000\u0000\u00c0\u00c1"+
		"\u0001\u0000\u0000\u0000\u00c1\u00c3\u0001\u0000\u0000\u0000\u00c2\u00b7"+
		"\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u001f"+
		"\u0001\u0000\u0000\u0000\u0010&1?S[f\u0080\u0094\u0096\u00a4\u00af\u00b3"+
		"\u00b5\u00bc\u00c0\u00c2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}