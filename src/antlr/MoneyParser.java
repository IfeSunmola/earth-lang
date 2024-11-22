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
		Else=7, Fn=8, Yeet=9, Unnamed=10, Loop=11, StrLit=12, IntLit=13, FloatLit=14, 
		BoolLit=15, UntypedIdent=16, Eq=17, Gt=18, Lt=19, Gte=20, Lte=21, EqEq=22, 
		BangEq=23, Plus=24, Minus=25, Star=26, Slash=27, Mod=28, Bang=29, And=30, 
		Or=31, Colon=32, Comma=33, LParen=34, RParen=35, LBrace=36, RBrace=37;
	public static final int
		RULE_program = 0, RULE_stmtList = 1, RULE_stmt = 2, RULE_declStmt = 3, 
		RULE_reassignStmt = 4, RULE_whenElseStmt = 5, RULE_yeetStmt = 6, RULE_fnDefStmt = 7, 
		RULE_unnamedStmt = 8, RULE_loopStmt = 9, RULE_expr = 10, RULE_primary = 11, 
		RULE_typedIdentExpr = 12, RULE_typedIdentList = 13, RULE_exprList = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stmtList", "stmt", "declStmt", "reassignStmt", "whenElseStmt", 
			"yeetStmt", "fnDefStmt", "unnamedStmt", "loopStmt", "expr", "primary", 
			"typedIdentExpr", "typedIdentList", "exprList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'let'", "'var'", "'when'", "'else'", "'fn'", 
			"'yeet'", "'_'", "'loop'", null, null, null, null, null, "'='", "'>'", 
			"'<'", "'>='", "'<='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'", "'%'", 
			"'!'", "'&&'", "'||'", "':'", "','", "'('", "')'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Whitespace", "SingleLineComment", "MultiLineComment", "Let", "Var", 
			"When", "Else", "Fn", "Yeet", "Unnamed", "Loop", "StrLit", "IntLit", 
			"FloatLit", "BoolLit", "UntypedIdent", "Eq", "Gt", "Lt", "Gte", "Lte", 
			"EqEq", "BangEq", "Plus", "Minus", "Star", "Slash", "Mod", "Bang", "And", 
			"Or", "Colon", "Comma", "LParen", "RParen", "LBrace", "RBrace"
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
			setState(30);
			stmtList();
			setState(31);
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
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69488L) != 0)) {
				{
				{
				setState(33);
				stmt();
				}
				}
				setState(38);
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
		public DeclStmtContext declStmt() {
			return getRuleContext(DeclStmtContext.class,0);
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
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Let:
			case Var:
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				declStmt();
				}
				break;
			case UntypedIdent:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				reassignStmt();
				}
				break;
			case When:
				enterOuterAlt(_localctx, 3);
				{
				setState(41);
				whenElseStmt();
				}
				break;
			case Yeet:
				enterOuterAlt(_localctx, 4);
				{
				setState(42);
				yeetStmt();
				}
				break;
			case Fn:
				enterOuterAlt(_localctx, 5);
				{
				setState(43);
				fnDefStmt();
				}
				break;
			case Unnamed:
				enterOuterAlt(_localctx, 6);
				{
				setState(44);
				unnamedStmt();
				}
				break;
			case Loop:
				enterOuterAlt(_localctx, 7);
				{
				setState(45);
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
	public static class DeclStmtContext extends ParserRuleContext {
		public Token letType;
		public TypedIdentExprContext typedIdentExpr() {
			return getRuleContext(TypedIdentExprContext.class,0);
		}
		public TerminalNode Eq() { return getToken(MoneyParser.Eq, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode Let() { return getToken(MoneyParser.Let, 0); }
		public TerminalNode Var() { return getToken(MoneyParser.Var, 0); }
		public DeclStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitDeclStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclStmtContext declStmt() throws RecognitionException {
		DeclStmtContext _localctx = new DeclStmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			((DeclStmtContext)_localctx).letType = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==Let || _la==Var) ) {
				((DeclStmtContext)_localctx).letType = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(49);
			typedIdentExpr();
			setState(50);
			match(Eq);
			setState(51);
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
	public static class ReassignStmtContext extends ParserRuleContext {
		public Token ident;
		public TerminalNode Eq() { return getToken(MoneyParser.Eq, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode UntypedIdent() { return getToken(MoneyParser.UntypedIdent, 0); }
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
		enterRule(_localctx, 8, RULE_reassignStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			((ReassignStmtContext)_localctx).ident = match(UntypedIdent);
			setState(54);
			match(Eq);
			setState(55);
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
		enterRule(_localctx, 10, RULE_whenElseStmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(When);
			setState(58);
			expr(0);
			setState(59);
			match(LBrace);
			setState(60);
			stmtList();
			setState(61);
			match(RBrace);
			setState(71);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(62);
					match(Else);
					setState(63);
					match(When);
					setState(64);
					expr(0);
					setState(65);
					match(LBrace);
					setState(66);
					stmtList();
					setState(67);
					match(RBrace);
					}
					} 
				}
				setState(73);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Else) {
				{
				setState(74);
				match(Else);
				setState(75);
				match(LBrace);
				setState(76);
				stmtList();
				setState(77);
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
		enterRule(_localctx, 12, RULE_yeetStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(Yeet);
			setState(82);
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
		public Token name;
		public TypedIdentListContext params;
		public Token retType;
		public StmtListContext body;
		public TerminalNode Fn() { return getToken(MoneyParser.Fn, 0); }
		public TerminalNode LParen() { return getToken(MoneyParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MoneyParser.RParen, 0); }
		public TerminalNode LBrace() { return getToken(MoneyParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(MoneyParser.RBrace, 0); }
		public List<TerminalNode> UntypedIdent() { return getTokens(MoneyParser.UntypedIdent); }
		public TerminalNode UntypedIdent(int i) {
			return getToken(MoneyParser.UntypedIdent, i);
		}
		public TypedIdentListContext typedIdentList() {
			return getRuleContext(TypedIdentListContext.class,0);
		}
		public StmtListContext stmtList() {
			return getRuleContext(StmtListContext.class,0);
		}
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
		enterRule(_localctx, 14, RULE_fnDefStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(Fn);
			setState(85);
			((FnDefStmtContext)_localctx).name = match(UntypedIdent);
			setState(86);
			match(LParen);
			setState(87);
			((FnDefStmtContext)_localctx).params = typedIdentList();
			setState(88);
			match(RParen);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UntypedIdent) {
				{
				setState(89);
				((FnDefStmtContext)_localctx).retType = match(UntypedIdent);
				}
			}

			setState(92);
			match(LBrace);
			setState(93);
			((FnDefStmtContext)_localctx).body = stmtList();
			setState(94);
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
		enterRule(_localctx, 16, RULE_unnamedStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(Unnamed);
			setState(97);
			match(Eq);
			setState(98);
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
		public DeclStmtContext initializer;
		public ExprContext condition;
		public ReassignStmtContext update;
		public StmtListContext body;
		public TerminalNode Loop() { return getToken(MoneyParser.Loop, 0); }
		public List<TerminalNode> Comma() { return getTokens(MoneyParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MoneyParser.Comma, i);
		}
		public TerminalNode LBrace() { return getToken(MoneyParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(MoneyParser.RBrace, 0); }
		public DeclStmtContext declStmt() {
			return getRuleContext(DeclStmtContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReassignStmtContext reassignStmt() {
			return getRuleContext(ReassignStmtContext.class,0);
		}
		public StmtListContext stmtList() {
			return getRuleContext(StmtListContext.class,0);
		}
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
		enterRule(_localctx, 18, RULE_loopStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(Loop);
			setState(101);
			((LoopStmtContext)_localctx).initializer = declStmt();
			setState(102);
			match(Comma);
			setState(103);
			((LoopStmtContext)_localctx).condition = expr(0);
			setState(104);
			match(Comma);
			setState(105);
			((LoopStmtContext)_localctx).update = reassignStmt();
			setState(106);
			match(LBrace);
			setState(107);
			((LoopStmtContext)_localctx).body = stmtList();
			setState(108);
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
		public ExprContext left;
		public Token op;
		public ExprContext right;
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
		public ExprContext left;
		public ExprContext right;
		public TerminalNode Or() { return getToken(MoneyParser.Or, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExprContext extends ExprContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
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
		public ExprContext left;
		public Token op;
		public ExprContext right;
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
		public ExprContext left;
		public Token op;
		public ExprContext right;
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
		public ExprContext left;
		public ExprContext right;
		public TerminalNode And() { return getToken(MoneyParser.And, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
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
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Minus:
				{
				_localctx = new NegExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(111);
				match(Minus);
				setState(112);
				expr(9);
				}
				break;
			case Bang:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(113);
				match(Bang);
				setState(114);
				expr(8);
				}
				break;
			case StrLit:
			case IntLit:
			case FloatLit:
			case BoolLit:
			case UntypedIdent:
			case LParen:
				{
				_localctx = new PrimaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(115);
				primary();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(138);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(136);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicationExprContext(new ExprContext(_parentctx, _parentState));
						((MultiplicationExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(118);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(119);
						((MultiplicationExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 469762048L) != 0)) ) {
							((MultiplicationExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(120);
						((MultiplicationExprContext)_localctx).right = expr(8);
						}
						break;
					case 2:
						{
						_localctx = new AdditiveExprContext(new ExprContext(_parentctx, _parentState));
						((AdditiveExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(121);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(122);
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
						setState(123);
						((AdditiveExprContext)_localctx).right = expr(7);
						}
						break;
					case 3:
						{
						_localctx = new RelationalExprContext(new ExprContext(_parentctx, _parentState));
						((RelationalExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(124);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(125);
						((RelationalExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3932160L) != 0)) ) {
							((RelationalExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(126);
						((RelationalExprContext)_localctx).right = expr(6);
						}
						break;
					case 4:
						{
						_localctx = new EqualityExprContext(new ExprContext(_parentctx, _parentState));
						((EqualityExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(127);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(128);
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
						setState(129);
						((EqualityExprContext)_localctx).right = expr(5);
						}
						break;
					case 5:
						{
						_localctx = new AndExprContext(new ExprContext(_parentctx, _parentState));
						((AndExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(130);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(131);
						match(And);
						setState(132);
						((AndExprContext)_localctx).right = expr(4);
						}
						break;
					case 6:
						{
						_localctx = new OrExprContext(new ExprContext(_parentctx, _parentState));
						((OrExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(133);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(134);
						match(Or);
						setState(135);
						((OrExprContext)_localctx).right = expr(3);
						}
						break;
					}
					} 
				}
				setState(140);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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
		public TerminalNode BoolLit() { return getToken(MoneyParser.BoolLit, 0); }
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
		public Token fnName;
		public ExprListContext params;
		public TerminalNode LParen() { return getToken(MoneyParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MoneyParser.RParen, 0); }
		public TerminalNode UntypedIdent() { return getToken(MoneyParser.UntypedIdent, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public FnCallExprContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MoneyParserVisitor ) return ((MoneyParserVisitor<? extends T>)visitor).visitFnCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_primary);
		int _la;
		try {
			setState(152);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new GroupedExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				match(LParen);
				setState(142);
				expr(0);
				setState(143);
				match(RParen);
				}
				break;
			case 2:
				_localctx = new LiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 61440L) != 0)) ) {
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
				setState(146);
				match(UntypedIdent);
				}
				break;
			case 4:
				_localctx = new FnCallExprContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(147);
				((FnCallExprContext)_localctx).fnName = match(UntypedIdent);
				setState(148);
				match(LParen);
				setState(149);
				((FnCallExprContext)_localctx).params = exprList();
				setState(150);
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
		public Token name;
		public Token type;
		public TerminalNode Colon() { return getToken(MoneyParser.Colon, 0); }
		public List<TerminalNode> UntypedIdent() { return getTokens(MoneyParser.UntypedIdent); }
		public TerminalNode UntypedIdent(int i) {
			return getToken(MoneyParser.UntypedIdent, i);
		}
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
		enterRule(_localctx, 24, RULE_typedIdentExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			((TypedIdentExprContext)_localctx).name = match(UntypedIdent);
			setState(155);
			match(Colon);
			setState(156);
			((TypedIdentExprContext)_localctx).type = match(UntypedIdent);
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
		enterRule(_localctx, 26, RULE_typedIdentList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UntypedIdent) {
				{
				setState(158);
				typedIdentExpr();
				setState(163);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(159);
						match(Comma);
						setState(160);
						typedIdentExpr();
						}
						} 
					}
					setState(165);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(166);
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
		enterRule(_localctx, 28, RULE_exprList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17750421504L) != 0)) {
				{
				setState(171);
				expr(0);
				setState(176);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(172);
						match(Comma);
						setState(173);
						expr(0);
						}
						} 
					}
					setState(178);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(179);
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
		case 10:
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
		"\u0004\u0001%\u00b9\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0005\u0001#\b\u0001\n\u0001\f\u0001&\t\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002/\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005F\b\u0005\n\u0005\f\u0005I\t\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005P\b\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007[\b\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\nu\b\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u0089"+
		"\b\n\n\n\f\n\u008c\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u0099\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\r\u0001\r\u0001\r\u0005\r\u00a2\b\r\n\r\f\r\u00a5\t\r\u0001\r\u0003"+
		"\r\u00a8\b\r\u0003\r\u00aa\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005"+
		"\u000e\u00af\b\u000e\n\u000e\f\u000e\u00b2\t\u000e\u0001\u000e\u0003\u000e"+
		"\u00b5\b\u000e\u0003\u000e\u00b7\b\u000e\u0001\u000e\u0000\u0001\u0014"+
		"\u000f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u0000\u0006\u0001\u0000\u0004\u0005\u0001\u0000\u001a\u001c"+
		"\u0001\u0000\u0018\u0019\u0001\u0000\u0012\u0015\u0001\u0000\u0016\u0017"+
		"\u0001\u0000\f\u000f\u00c4\u0000\u001e\u0001\u0000\u0000\u0000\u0002$"+
		"\u0001\u0000\u0000\u0000\u0004.\u0001\u0000\u0000\u0000\u00060\u0001\u0000"+
		"\u0000\u0000\b5\u0001\u0000\u0000\u0000\n9\u0001\u0000\u0000\u0000\fQ"+
		"\u0001\u0000\u0000\u0000\u000eT\u0001\u0000\u0000\u0000\u0010`\u0001\u0000"+
		"\u0000\u0000\u0012d\u0001\u0000\u0000\u0000\u0014t\u0001\u0000\u0000\u0000"+
		"\u0016\u0098\u0001\u0000\u0000\u0000\u0018\u009a\u0001\u0000\u0000\u0000"+
		"\u001a\u00a9\u0001\u0000\u0000\u0000\u001c\u00b6\u0001\u0000\u0000\u0000"+
		"\u001e\u001f\u0003\u0002\u0001\u0000\u001f \u0005\u0000\u0000\u0001 \u0001"+
		"\u0001\u0000\u0000\u0000!#\u0003\u0004\u0002\u0000\"!\u0001\u0000\u0000"+
		"\u0000#&\u0001\u0000\u0000\u0000$\"\u0001\u0000\u0000\u0000$%\u0001\u0000"+
		"\u0000\u0000%\u0003\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000\u0000"+
		"\'/\u0003\u0006\u0003\u0000(/\u0003\b\u0004\u0000)/\u0003\n\u0005\u0000"+
		"*/\u0003\f\u0006\u0000+/\u0003\u000e\u0007\u0000,/\u0003\u0010\b\u0000"+
		"-/\u0003\u0012\t\u0000.\'\u0001\u0000\u0000\u0000.(\u0001\u0000\u0000"+
		"\u0000.)\u0001\u0000\u0000\u0000.*\u0001\u0000\u0000\u0000.+\u0001\u0000"+
		"\u0000\u0000.,\u0001\u0000\u0000\u0000.-\u0001\u0000\u0000\u0000/\u0005"+
		"\u0001\u0000\u0000\u000001\u0007\u0000\u0000\u000012\u0003\u0018\f\u0000"+
		"23\u0005\u0011\u0000\u000034\u0003\u0014\n\u00004\u0007\u0001\u0000\u0000"+
		"\u000056\u0005\u0010\u0000\u000067\u0005\u0011\u0000\u000078\u0003\u0014"+
		"\n\u00008\t\u0001\u0000\u0000\u00009:\u0005\u0006\u0000\u0000:;\u0003"+
		"\u0014\n\u0000;<\u0005$\u0000\u0000<=\u0003\u0002\u0001\u0000=G\u0005"+
		"%\u0000\u0000>?\u0005\u0007\u0000\u0000?@\u0005\u0006\u0000\u0000@A\u0003"+
		"\u0014\n\u0000AB\u0005$\u0000\u0000BC\u0003\u0002\u0001\u0000CD\u0005"+
		"%\u0000\u0000DF\u0001\u0000\u0000\u0000E>\u0001\u0000\u0000\u0000FI\u0001"+
		"\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000"+
		"HO\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000JK\u0005\u0007\u0000"+
		"\u0000KL\u0005$\u0000\u0000LM\u0003\u0002\u0001\u0000MN\u0005%\u0000\u0000"+
		"NP\u0001\u0000\u0000\u0000OJ\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000"+
		"\u0000P\u000b\u0001\u0000\u0000\u0000QR\u0005\t\u0000\u0000RS\u0003\u0014"+
		"\n\u0000S\r\u0001\u0000\u0000\u0000TU\u0005\b\u0000\u0000UV\u0005\u0010"+
		"\u0000\u0000VW\u0005\"\u0000\u0000WX\u0003\u001a\r\u0000XZ\u0005#\u0000"+
		"\u0000Y[\u0005\u0010\u0000\u0000ZY\u0001\u0000\u0000\u0000Z[\u0001\u0000"+
		"\u0000\u0000[\\\u0001\u0000\u0000\u0000\\]\u0005$\u0000\u0000]^\u0003"+
		"\u0002\u0001\u0000^_\u0005%\u0000\u0000_\u000f\u0001\u0000\u0000\u0000"+
		"`a\u0005\n\u0000\u0000ab\u0005\u0011\u0000\u0000bc\u0003\u0014\n\u0000"+
		"c\u0011\u0001\u0000\u0000\u0000de\u0005\u000b\u0000\u0000ef\u0003\u0006"+
		"\u0003\u0000fg\u0005!\u0000\u0000gh\u0003\u0014\n\u0000hi\u0005!\u0000"+
		"\u0000ij\u0003\b\u0004\u0000jk\u0005$\u0000\u0000kl\u0003\u0002\u0001"+
		"\u0000lm\u0005%\u0000\u0000m\u0013\u0001\u0000\u0000\u0000no\u0006\n\uffff"+
		"\uffff\u0000op\u0005\u0019\u0000\u0000pu\u0003\u0014\n\tqr\u0005\u001d"+
		"\u0000\u0000ru\u0003\u0014\n\bsu\u0003\u0016\u000b\u0000tn\u0001\u0000"+
		"\u0000\u0000tq\u0001\u0000\u0000\u0000ts\u0001\u0000\u0000\u0000u\u008a"+
		"\u0001\u0000\u0000\u0000vw\n\u0007\u0000\u0000wx\u0007\u0001\u0000\u0000"+
		"x\u0089\u0003\u0014\n\byz\n\u0006\u0000\u0000z{\u0007\u0002\u0000\u0000"+
		"{\u0089\u0003\u0014\n\u0007|}\n\u0005\u0000\u0000}~\u0007\u0003\u0000"+
		"\u0000~\u0089\u0003\u0014\n\u0006\u007f\u0080\n\u0004\u0000\u0000\u0080"+
		"\u0081\u0007\u0004\u0000\u0000\u0081\u0089\u0003\u0014\n\u0005\u0082\u0083"+
		"\n\u0003\u0000\u0000\u0083\u0084\u0005\u001e\u0000\u0000\u0084\u0089\u0003"+
		"\u0014\n\u0004\u0085\u0086\n\u0002\u0000\u0000\u0086\u0087\u0005\u001f"+
		"\u0000\u0000\u0087\u0089\u0003\u0014\n\u0003\u0088v\u0001\u0000\u0000"+
		"\u0000\u0088y\u0001\u0000\u0000\u0000\u0088|\u0001\u0000\u0000\u0000\u0088"+
		"\u007f\u0001\u0000\u0000\u0000\u0088\u0082\u0001\u0000\u0000\u0000\u0088"+
		"\u0085\u0001\u0000\u0000\u0000\u0089\u008c\u0001\u0000\u0000\u0000\u008a"+
		"\u0088\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b"+
		"\u0015\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008d"+
		"\u008e\u0005\"\u0000\u0000\u008e\u008f\u0003\u0014\n\u0000\u008f\u0090"+
		"\u0005#\u0000\u0000\u0090\u0099\u0001\u0000\u0000\u0000\u0091\u0099\u0007"+
		"\u0005\u0000\u0000\u0092\u0099\u0005\u0010\u0000\u0000\u0093\u0094\u0005"+
		"\u0010\u0000\u0000\u0094\u0095\u0005\"\u0000\u0000\u0095\u0096\u0003\u001c"+
		"\u000e\u0000\u0096\u0097\u0005#\u0000\u0000\u0097\u0099\u0001\u0000\u0000"+
		"\u0000\u0098\u008d\u0001\u0000\u0000\u0000\u0098\u0091\u0001\u0000\u0000"+
		"\u0000\u0098\u0092\u0001\u0000\u0000\u0000\u0098\u0093\u0001\u0000\u0000"+
		"\u0000\u0099\u0017\u0001\u0000\u0000\u0000\u009a\u009b\u0005\u0010\u0000"+
		"\u0000\u009b\u009c\u0005 \u0000\u0000\u009c\u009d\u0005\u0010\u0000\u0000"+
		"\u009d\u0019\u0001\u0000\u0000\u0000\u009e\u00a3\u0003\u0018\f\u0000\u009f"+
		"\u00a0\u0005!\u0000\u0000\u00a0\u00a2\u0003\u0018\f\u0000\u00a1\u009f"+
		"\u0001\u0000\u0000\u0000\u00a2\u00a5\u0001\u0000\u0000\u0000\u00a3\u00a1"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a7"+
		"\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a6\u00a8"+
		"\u0005!\u0000\u0000\u00a7\u00a6\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001"+
		"\u0000\u0000\u0000\u00a8\u00aa\u0001\u0000\u0000\u0000\u00a9\u009e\u0001"+
		"\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u001b\u0001"+
		"\u0000\u0000\u0000\u00ab\u00b0\u0003\u0014\n\u0000\u00ac\u00ad\u0005!"+
		"\u0000\u0000\u00ad\u00af\u0003\u0014\n\u0000\u00ae\u00ac\u0001\u0000\u0000"+
		"\u0000\u00af\u00b2\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000\u00b1\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b3\u00b5\u0005!\u0000\u0000"+
		"\u00b4\u00b3\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b7\u0001\u0000\u0000\u0000\u00b6\u00ab\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u001d\u0001\u0000\u0000\u0000"+
		"\u000f$.GOZt\u0088\u008a\u0098\u00a3\u00a7\u00a9\u00b0\u00b4\u00b6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}