// Generated from ./src/antlr/EarthParser.g4 by ANTLR 4.13.2
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
public class EarthParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Whitespace=1, SingleLineComment=2, MultiLineComment=3, Let=4, Var=5, When=6, 
		ElseWhen=7, Else=8, Fn=9, Yeet=10, Unnamed=11, Loop=12, StrLit=13, IntLit=14, 
		FloatLit=15, BoolLit=16, UntypedIdent=17, Eq=18, Gt=19, Lt=20, Gte=21, 
		Lte=22, EqEq=23, BangEq=24, Plus=25, Minus=26, Star=27, Slash=28, Mod=29, 
		Bang=30, And=31, Or=32, Colon=33, Comma=34, LParen=35, RParen=36, LBrace=37, 
		RBrace=38;
	public static final int
		RULE_program = 0, RULE_stmtList = 1, RULE_stmt = 2, RULE_declStmt = 3, 
		RULE_reassignStmt = 4, RULE_when = 5, RULE_elseWhen = 6, RULE_else = 7, 
		RULE_whenElseStmt = 8, RULE_yeetStmt = 9, RULE_fnDefStmt = 10, RULE_unnamedStmt = 11, 
		RULE_loopStmt = 12, RULE_expr = 13, RULE_primary = 14, RULE_typedIdentExpr = 15, 
		RULE_typedIdentList = 16, RULE_exprList = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stmtList", "stmt", "declStmt", "reassignStmt", "when", "elseWhen", 
			"else", "whenElseStmt", "yeetStmt", "fnDefStmt", "unnamedStmt", "loopStmt", 
			"expr", "primary", "typedIdentExpr", "typedIdentList", "exprList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'let'", "'var'", "'when'", "'else when'", "'else'", 
			"'fn'", "'yeet'", "'_'", "'loop'", null, null, null, null, null, "'='", 
			"'>'", "'<'", "'>='", "'<='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'", 
			"'%'", "'!'", "'&&'", "'||'", "':'", "','", "'('", "')'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Whitespace", "SingleLineComment", "MultiLineComment", "Let", "Var", 
			"When", "ElseWhen", "Else", "Fn", "Yeet", "Unnamed", "Loop", "StrLit", 
			"IntLit", "FloatLit", "BoolLit", "UntypedIdent", "Eq", "Gt", "Lt", "Gte", 
			"Lte", "EqEq", "BangEq", "Plus", "Minus", "Star", "Slash", "Mod", "Bang", 
			"And", "Or", "Colon", "Comma", "LParen", "RParen", "LBrace", "RBrace"
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
	public String getGrammarFileName() { return "EarthParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EarthParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public StmtListContext stmtList() {
			return getRuleContext(StmtListContext.class,0);
		}
		public TerminalNode EOF() { return getToken(EarthParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			stmtList();
			setState(37);
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
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitStmtList(this);
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
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 138864L) != 0)) {
				{
				{
				setState(39);
				stmt();
				}
				}
				setState(44);
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
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_stmt);
		try {
			setState(52);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Let:
			case Var:
				enterOuterAlt(_localctx, 1);
				{
				setState(45);
				declStmt();
				}
				break;
			case UntypedIdent:
				enterOuterAlt(_localctx, 2);
				{
				setState(46);
				reassignStmt();
				}
				break;
			case When:
				enterOuterAlt(_localctx, 3);
				{
				setState(47);
				whenElseStmt();
				}
				break;
			case Yeet:
				enterOuterAlt(_localctx, 4);
				{
				setState(48);
				yeetStmt();
				}
				break;
			case Fn:
				enterOuterAlt(_localctx, 5);
				{
				setState(49);
				fnDefStmt();
				}
				break;
			case Unnamed:
				enterOuterAlt(_localctx, 6);
				{
				setState(50);
				unnamedStmt();
				}
				break;
			case Loop:
				enterOuterAlt(_localctx, 7);
				{
				setState(51);
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
		public TerminalNode Eq() { return getToken(EarthParser.Eq, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode Let() { return getToken(EarthParser.Let, 0); }
		public TerminalNode Var() { return getToken(EarthParser.Var, 0); }
		public DeclStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitDeclStmt(this);
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
			setState(54);
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
			setState(55);
			typedIdentExpr();
			setState(56);
			match(Eq);
			setState(57);
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
		public TerminalNode Eq() { return getToken(EarthParser.Eq, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode UntypedIdent() { return getToken(EarthParser.UntypedIdent, 0); }
		public ReassignStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reassignStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitReassignStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReassignStmtContext reassignStmt() throws RecognitionException {
		ReassignStmtContext _localctx = new ReassignStmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_reassignStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			((ReassignStmtContext)_localctx).ident = match(UntypedIdent);
			setState(60);
			match(Eq);
			setState(61);
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
	public static class WhenContext extends ParserRuleContext {
		public ExprContext condition;
		public StmtListContext body;
		public TerminalNode When() { return getToken(EarthParser.When, 0); }
		public TerminalNode LBrace() { return getToken(EarthParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(EarthParser.RBrace, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtListContext stmtList() {
			return getRuleContext(StmtListContext.class,0);
		}
		public WhenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_when; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitWhen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhenContext when() throws RecognitionException {
		WhenContext _localctx = new WhenContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_when);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(When);
			setState(64);
			((WhenContext)_localctx).condition = expr(0);
			setState(65);
			match(LBrace);
			setState(66);
			((WhenContext)_localctx).body = stmtList();
			setState(67);
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
	public static class ElseWhenContext extends ParserRuleContext {
		public ExprContext condition;
		public StmtListContext body;
		public TerminalNode ElseWhen() { return getToken(EarthParser.ElseWhen, 0); }
		public TerminalNode LBrace() { return getToken(EarthParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(EarthParser.RBrace, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtListContext stmtList() {
			return getRuleContext(StmtListContext.class,0);
		}
		public ElseWhenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseWhen; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitElseWhen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseWhenContext elseWhen() throws RecognitionException {
		ElseWhenContext _localctx = new ElseWhenContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_elseWhen);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(ElseWhen);
			setState(70);
			((ElseWhenContext)_localctx).condition = expr(0);
			setState(71);
			match(LBrace);
			setState(72);
			((ElseWhenContext)_localctx).body = stmtList();
			setState(73);
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
	public static class ElseContext extends ParserRuleContext {
		public StmtListContext body;
		public TerminalNode Else() { return getToken(EarthParser.Else, 0); }
		public TerminalNode LBrace() { return getToken(EarthParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(EarthParser.RBrace, 0); }
		public StmtListContext stmtList() {
			return getRuleContext(StmtListContext.class,0);
		}
		public ElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitElse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseContext else_() throws RecognitionException {
		ElseContext _localctx = new ElseContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_else);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Else) {
				{
				setState(75);
				match(Else);
				setState(76);
				match(LBrace);
				setState(77);
				((ElseContext)_localctx).body = stmtList();
				setState(78);
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
	public static class WhenElseStmtContext extends ParserRuleContext {
		public WhenContext when() {
			return getRuleContext(WhenContext.class,0);
		}
		public ElseContext else_() {
			return getRuleContext(ElseContext.class,0);
		}
		public List<ElseWhenContext> elseWhen() {
			return getRuleContexts(ElseWhenContext.class);
		}
		public ElseWhenContext elseWhen(int i) {
			return getRuleContext(ElseWhenContext.class,i);
		}
		public WhenElseStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenElseStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitWhenElseStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhenElseStmtContext whenElseStmt() throws RecognitionException {
		WhenElseStmtContext _localctx = new WhenElseStmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_whenElseStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			when();
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ElseWhen) {
				{
				{
				setState(83);
				elseWhen();
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
			else_();
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
		public TerminalNode Yeet() { return getToken(EarthParser.Yeet, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public YeetStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yeetStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitYeetStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YeetStmtContext yeetStmt() throws RecognitionException {
		YeetStmtContext _localctx = new YeetStmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_yeetStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(Yeet);
			setState(92);
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
		public TerminalNode Fn() { return getToken(EarthParser.Fn, 0); }
		public TerminalNode LParen() { return getToken(EarthParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(EarthParser.RParen, 0); }
		public TerminalNode LBrace() { return getToken(EarthParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(EarthParser.RBrace, 0); }
		public List<TerminalNode> UntypedIdent() { return getTokens(EarthParser.UntypedIdent); }
		public TerminalNode UntypedIdent(int i) {
			return getToken(EarthParser.UntypedIdent, i);
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
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitFnDefStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnDefStmtContext fnDefStmt() throws RecognitionException {
		FnDefStmtContext _localctx = new FnDefStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_fnDefStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(Fn);
			setState(95);
			((FnDefStmtContext)_localctx).name = match(UntypedIdent);
			setState(96);
			match(LParen);
			setState(97);
			((FnDefStmtContext)_localctx).params = typedIdentList();
			setState(98);
			match(RParen);
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UntypedIdent) {
				{
				setState(99);
				((FnDefStmtContext)_localctx).retType = match(UntypedIdent);
				}
			}

			setState(102);
			match(LBrace);
			setState(103);
			((FnDefStmtContext)_localctx).body = stmtList();
			setState(104);
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
		public TerminalNode Unnamed() { return getToken(EarthParser.Unnamed, 0); }
		public TerminalNode Eq() { return getToken(EarthParser.Eq, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnnamedStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unnamedStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitUnnamedStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnnamedStmtContext unnamedStmt() throws RecognitionException {
		UnnamedStmtContext _localctx = new UnnamedStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_unnamedStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(Unnamed);
			setState(107);
			match(Eq);
			setState(108);
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
		public TerminalNode Loop() { return getToken(EarthParser.Loop, 0); }
		public List<TerminalNode> Comma() { return getTokens(EarthParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(EarthParser.Comma, i);
		}
		public TerminalNode LBrace() { return getToken(EarthParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(EarthParser.RBrace, 0); }
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
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitLoopStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopStmtContext loopStmt() throws RecognitionException {
		LoopStmtContext _localctx = new LoopStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_loopStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(Loop);
			setState(111);
			((LoopStmtContext)_localctx).initializer = declStmt();
			setState(112);
			match(Comma);
			setState(113);
			((LoopStmtContext)_localctx).condition = expr(0);
			setState(114);
			match(Comma);
			setState(115);
			((LoopStmtContext)_localctx).update = reassignStmt();
			setState(116);
			match(LBrace);
			setState(117);
			((LoopStmtContext)_localctx).body = stmtList();
			setState(118);
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
		public TerminalNode Bang() { return getToken(EarthParser.Bang, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitNotExpr(this);
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
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitPrimaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegExprContext extends ExprContext {
		public TerminalNode Minus() { return getToken(EarthParser.Minus, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NegExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitNegExpr(this);
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
		public TerminalNode Star() { return getToken(EarthParser.Star, 0); }
		public TerminalNode Slash() { return getToken(EarthParser.Slash, 0); }
		public TerminalNode Mod() { return getToken(EarthParser.Mod, 0); }
		public MultiplicationExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitMultiplicationExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExprContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode Or() { return getToken(EarthParser.Or, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitOrExpr(this);
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
		public TerminalNode Plus() { return getToken(EarthParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(EarthParser.Minus, 0); }
		public AdditiveExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitAdditiveExpr(this);
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
		public TerminalNode Lte() { return getToken(EarthParser.Lte, 0); }
		public TerminalNode Gte() { return getToken(EarthParser.Gte, 0); }
		public TerminalNode Lt() { return getToken(EarthParser.Lt, 0); }
		public TerminalNode Gt() { return getToken(EarthParser.Gt, 0); }
		public RelationalExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitRelationalExpr(this);
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
		public TerminalNode EqEq() { return getToken(EarthParser.EqEq, 0); }
		public TerminalNode BangEq() { return getToken(EarthParser.BangEq, 0); }
		public EqualityExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitEqualityExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndExprContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public TerminalNode And() { return getToken(EarthParser.And, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AndExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitAndExpr(this);
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
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Minus:
				{
				_localctx = new NegExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(121);
				match(Minus);
				setState(122);
				expr(9);
				}
				break;
			case Bang:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(123);
				match(Bang);
				setState(124);
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
				setState(125);
				primary();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(148);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(146);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicationExprContext(new ExprContext(_parentctx, _parentState));
						((MultiplicationExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(128);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(129);
						((MultiplicationExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 939524096L) != 0)) ) {
							((MultiplicationExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(130);
						((MultiplicationExprContext)_localctx).right = expr(8);
						}
						break;
					case 2:
						{
						_localctx = new AdditiveExprContext(new ExprContext(_parentctx, _parentState));
						((AdditiveExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(131);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(132);
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
						setState(133);
						((AdditiveExprContext)_localctx).right = expr(7);
						}
						break;
					case 3:
						{
						_localctx = new RelationalExprContext(new ExprContext(_parentctx, _parentState));
						((RelationalExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(134);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(135);
						((RelationalExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7864320L) != 0)) ) {
							((RelationalExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(136);
						((RelationalExprContext)_localctx).right = expr(6);
						}
						break;
					case 4:
						{
						_localctx = new EqualityExprContext(new ExprContext(_parentctx, _parentState));
						((EqualityExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(137);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(138);
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
						setState(139);
						((EqualityExprContext)_localctx).right = expr(5);
						}
						break;
					case 5:
						{
						_localctx = new AndExprContext(new ExprContext(_parentctx, _parentState));
						((AndExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(140);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(141);
						match(And);
						setState(142);
						((AndExprContext)_localctx).right = expr(4);
						}
						break;
					case 6:
						{
						_localctx = new OrExprContext(new ExprContext(_parentctx, _parentState));
						((OrExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(143);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(144);
						match(Or);
						setState(145);
						((OrExprContext)_localctx).right = expr(3);
						}
						break;
					}
					} 
				}
				setState(150);
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
		public TerminalNode LParen() { return getToken(EarthParser.LParen, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RParen() { return getToken(EarthParser.RParen, 0); }
		public GroupedExprContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitGroupedExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LiteralExprContext extends PrimaryContext {
		public TerminalNode IntLit() { return getToken(EarthParser.IntLit, 0); }
		public TerminalNode FloatLit() { return getToken(EarthParser.FloatLit, 0); }
		public TerminalNode StrLit() { return getToken(EarthParser.StrLit, 0); }
		public TerminalNode BoolLit() { return getToken(EarthParser.BoolLit, 0); }
		public LiteralExprContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UntypedIdentExprContext extends PrimaryContext {
		public TerminalNode UntypedIdent() { return getToken(EarthParser.UntypedIdent, 0); }
		public UntypedIdentExprContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitUntypedIdentExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FnCallExprContext extends PrimaryContext {
		public Token fnName;
		public ExprListContext params;
		public TerminalNode LParen() { return getToken(EarthParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(EarthParser.RParen, 0); }
		public TerminalNode UntypedIdent() { return getToken(EarthParser.UntypedIdent, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public FnCallExprContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitFnCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_primary);
		int _la;
		try {
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new GroupedExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				match(LParen);
				setState(152);
				expr(0);
				setState(153);
				match(RParen);
				}
				break;
			case 2:
				_localctx = new LiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(155);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 122880L) != 0)) ) {
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
				setState(156);
				match(UntypedIdent);
				}
				break;
			case 4:
				_localctx = new FnCallExprContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(157);
				((FnCallExprContext)_localctx).fnName = match(UntypedIdent);
				setState(158);
				match(LParen);
				setState(159);
				((FnCallExprContext)_localctx).params = exprList();
				setState(160);
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
		public TerminalNode Colon() { return getToken(EarthParser.Colon, 0); }
		public List<TerminalNode> UntypedIdent() { return getTokens(EarthParser.UntypedIdent); }
		public TerminalNode UntypedIdent(int i) {
			return getToken(EarthParser.UntypedIdent, i);
		}
		public TypedIdentExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedIdentExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitTypedIdentExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypedIdentExprContext typedIdentExpr() throws RecognitionException {
		TypedIdentExprContext _localctx = new TypedIdentExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_typedIdentExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			((TypedIdentExprContext)_localctx).name = match(UntypedIdent);
			setState(165);
			match(Colon);
			setState(166);
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
		public List<TerminalNode> Comma() { return getTokens(EarthParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(EarthParser.Comma, i);
		}
		public TypedIdentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedIdentList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitTypedIdentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypedIdentListContext typedIdentList() throws RecognitionException {
		TypedIdentListContext _localctx = new TypedIdentListContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_typedIdentList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UntypedIdent) {
				{
				setState(168);
				typedIdentExpr();
				setState(173);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(169);
						match(Comma);
						setState(170);
						typedIdentExpr();
						}
						} 
					}
					setState(175);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(176);
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
		public List<TerminalNode> Comma() { return getTokens(EarthParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(EarthParser.Comma, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EarthParserVisitor ) return ((EarthParserVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_exprList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 35500843008L) != 0)) {
				{
				setState(181);
				expr(0);
				setState(186);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(182);
						match(Comma);
						setState(183);
						expr(0);
						}
						} 
					}
					setState(188);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(189);
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
		case 13:
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
		"\u0004\u0001&\u00c3\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0005\u0001)\b\u0001\n\u0001\f\u0001,\t\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u00025\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"Q\b\u0007\u0001\b\u0001\b\u0005\bU\b\b\n\b\f\bX\t\b\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\ne\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u007f\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0005\r\u0093\b\r\n\r\f\r\u0096\t\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00a3\b\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0005\u0010\u00ac\b\u0010\n\u0010\f\u0010\u00af\t\u0010\u0001\u0010"+
		"\u0003\u0010\u00b2\b\u0010\u0003\u0010\u00b4\b\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0005\u0011\u00b9\b\u0011\n\u0011\f\u0011\u00bc\t\u0011"+
		"\u0001\u0011\u0003\u0011\u00bf\b\u0011\u0003\u0011\u00c1\b\u0011\u0001"+
		"\u0011\u0000\u0001\u001a\u0012\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"\u0000\u0006\u0001\u0000"+
		"\u0004\u0005\u0001\u0000\u001b\u001d\u0001\u0000\u0019\u001a\u0001\u0000"+
		"\u0013\u0016\u0001\u0000\u0017\u0018\u0001\u0000\r\u0010\u00cb\u0000$"+
		"\u0001\u0000\u0000\u0000\u0002*\u0001\u0000\u0000\u0000\u00044\u0001\u0000"+
		"\u0000\u0000\u00066\u0001\u0000\u0000\u0000\b;\u0001\u0000\u0000\u0000"+
		"\n?\u0001\u0000\u0000\u0000\fE\u0001\u0000\u0000\u0000\u000eP\u0001\u0000"+
		"\u0000\u0000\u0010R\u0001\u0000\u0000\u0000\u0012[\u0001\u0000\u0000\u0000"+
		"\u0014^\u0001\u0000\u0000\u0000\u0016j\u0001\u0000\u0000\u0000\u0018n"+
		"\u0001\u0000\u0000\u0000\u001a~\u0001\u0000\u0000\u0000\u001c\u00a2\u0001"+
		"\u0000\u0000\u0000\u001e\u00a4\u0001\u0000\u0000\u0000 \u00b3\u0001\u0000"+
		"\u0000\u0000\"\u00c0\u0001\u0000\u0000\u0000$%\u0003\u0002\u0001\u0000"+
		"%&\u0005\u0000\u0000\u0001&\u0001\u0001\u0000\u0000\u0000\')\u0003\u0004"+
		"\u0002\u0000(\'\u0001\u0000\u0000\u0000),\u0001\u0000\u0000\u0000*(\u0001"+
		"\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+\u0003\u0001\u0000\u0000"+
		"\u0000,*\u0001\u0000\u0000\u0000-5\u0003\u0006\u0003\u0000.5\u0003\b\u0004"+
		"\u0000/5\u0003\u0010\b\u000005\u0003\u0012\t\u000015\u0003\u0014\n\u0000"+
		"25\u0003\u0016\u000b\u000035\u0003\u0018\f\u00004-\u0001\u0000\u0000\u0000"+
		"4.\u0001\u0000\u0000\u00004/\u0001\u0000\u0000\u000040\u0001\u0000\u0000"+
		"\u000041\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u000043\u0001\u0000"+
		"\u0000\u00005\u0005\u0001\u0000\u0000\u000067\u0007\u0000\u0000\u0000"+
		"78\u0003\u001e\u000f\u000089\u0005\u0012\u0000\u00009:\u0003\u001a\r\u0000"+
		":\u0007\u0001\u0000\u0000\u0000;<\u0005\u0011\u0000\u0000<=\u0005\u0012"+
		"\u0000\u0000=>\u0003\u001a\r\u0000>\t\u0001\u0000\u0000\u0000?@\u0005"+
		"\u0006\u0000\u0000@A\u0003\u001a\r\u0000AB\u0005%\u0000\u0000BC\u0003"+
		"\u0002\u0001\u0000CD\u0005&\u0000\u0000D\u000b\u0001\u0000\u0000\u0000"+
		"EF\u0005\u0007\u0000\u0000FG\u0003\u001a\r\u0000GH\u0005%\u0000\u0000"+
		"HI\u0003\u0002\u0001\u0000IJ\u0005&\u0000\u0000J\r\u0001\u0000\u0000\u0000"+
		"KL\u0005\b\u0000\u0000LM\u0005%\u0000\u0000MN\u0003\u0002\u0001\u0000"+
		"NO\u0005&\u0000\u0000OQ\u0001\u0000\u0000\u0000PK\u0001\u0000\u0000\u0000"+
		"PQ\u0001\u0000\u0000\u0000Q\u000f\u0001\u0000\u0000\u0000RV\u0003\n\u0005"+
		"\u0000SU\u0003\f\u0006\u0000TS\u0001\u0000\u0000\u0000UX\u0001\u0000\u0000"+
		"\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000WY\u0001\u0000"+
		"\u0000\u0000XV\u0001\u0000\u0000\u0000YZ\u0003\u000e\u0007\u0000Z\u0011"+
		"\u0001\u0000\u0000\u0000[\\\u0005\n\u0000\u0000\\]\u0003\u001a\r\u0000"+
		"]\u0013\u0001\u0000\u0000\u0000^_\u0005\t\u0000\u0000_`\u0005\u0011\u0000"+
		"\u0000`a\u0005#\u0000\u0000ab\u0003 \u0010\u0000bd\u0005$\u0000\u0000"+
		"ce\u0005\u0011\u0000\u0000dc\u0001\u0000\u0000\u0000de\u0001\u0000\u0000"+
		"\u0000ef\u0001\u0000\u0000\u0000fg\u0005%\u0000\u0000gh\u0003\u0002\u0001"+
		"\u0000hi\u0005&\u0000\u0000i\u0015\u0001\u0000\u0000\u0000jk\u0005\u000b"+
		"\u0000\u0000kl\u0005\u0012\u0000\u0000lm\u0003\u001a\r\u0000m\u0017\u0001"+
		"\u0000\u0000\u0000no\u0005\f\u0000\u0000op\u0003\u0006\u0003\u0000pq\u0005"+
		"\"\u0000\u0000qr\u0003\u001a\r\u0000rs\u0005\"\u0000\u0000st\u0003\b\u0004"+
		"\u0000tu\u0005%\u0000\u0000uv\u0003\u0002\u0001\u0000vw\u0005&\u0000\u0000"+
		"w\u0019\u0001\u0000\u0000\u0000xy\u0006\r\uffff\uffff\u0000yz\u0005\u001a"+
		"\u0000\u0000z\u007f\u0003\u001a\r\t{|\u0005\u001e\u0000\u0000|\u007f\u0003"+
		"\u001a\r\b}\u007f\u0003\u001c\u000e\u0000~x\u0001\u0000\u0000\u0000~{"+
		"\u0001\u0000\u0000\u0000~}\u0001\u0000\u0000\u0000\u007f\u0094\u0001\u0000"+
		"\u0000\u0000\u0080\u0081\n\u0007\u0000\u0000\u0081\u0082\u0007\u0001\u0000"+
		"\u0000\u0082\u0093\u0003\u001a\r\b\u0083\u0084\n\u0006\u0000\u0000\u0084"+
		"\u0085\u0007\u0002\u0000\u0000\u0085\u0093\u0003\u001a\r\u0007\u0086\u0087"+
		"\n\u0005\u0000\u0000\u0087\u0088\u0007\u0003\u0000\u0000\u0088\u0093\u0003"+
		"\u001a\r\u0006\u0089\u008a\n\u0004\u0000\u0000\u008a\u008b\u0007\u0004"+
		"\u0000\u0000\u008b\u0093\u0003\u001a\r\u0005\u008c\u008d\n\u0003\u0000"+
		"\u0000\u008d\u008e\u0005\u001f\u0000\u0000\u008e\u0093\u0003\u001a\r\u0004"+
		"\u008f\u0090\n\u0002\u0000\u0000\u0090\u0091\u0005 \u0000\u0000\u0091"+
		"\u0093\u0003\u001a\r\u0003\u0092\u0080\u0001\u0000\u0000\u0000\u0092\u0083"+
		"\u0001\u0000\u0000\u0000\u0092\u0086\u0001\u0000\u0000\u0000\u0092\u0089"+
		"\u0001\u0000\u0000\u0000\u0092\u008c\u0001\u0000\u0000\u0000\u0092\u008f"+
		"\u0001\u0000\u0000\u0000\u0093\u0096\u0001\u0000\u0000\u0000\u0094\u0092"+
		"\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u001b"+
		"\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0005#\u0000\u0000\u0098\u0099\u0003\u001a\r\u0000\u0099\u009a\u0005"+
		"$\u0000\u0000\u009a\u00a3\u0001\u0000\u0000\u0000\u009b\u00a3\u0007\u0005"+
		"\u0000\u0000\u009c\u00a3\u0005\u0011\u0000\u0000\u009d\u009e\u0005\u0011"+
		"\u0000\u0000\u009e\u009f\u0005#\u0000\u0000\u009f\u00a0\u0003\"\u0011"+
		"\u0000\u00a0\u00a1\u0005$\u0000\u0000\u00a1\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a2\u0097\u0001\u0000\u0000\u0000\u00a2\u009b\u0001\u0000\u0000\u0000"+
		"\u00a2\u009c\u0001\u0000\u0000\u0000\u00a2\u009d\u0001\u0000\u0000\u0000"+
		"\u00a3\u001d\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005\u0011\u0000\u0000"+
		"\u00a5\u00a6\u0005!\u0000\u0000\u00a6\u00a7\u0005\u0011\u0000\u0000\u00a7"+
		"\u001f\u0001\u0000\u0000\u0000\u00a8\u00ad\u0003\u001e\u000f\u0000\u00a9"+
		"\u00aa\u0005\"\u0000\u0000\u00aa\u00ac\u0003\u001e\u000f\u0000\u00ab\u00a9"+
		"\u0001\u0000\u0000\u0000\u00ac\u00af\u0001\u0000\u0000\u0000\u00ad\u00ab"+
		"\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\u00b1"+
		"\u0001\u0000\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00b0\u00b2"+
		"\u0005\"\u0000\u0000\u00b1\u00b0\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b4\u0001\u0000\u0000\u0000\u00b3\u00a8\u0001"+
		"\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4!\u0001\u0000"+
		"\u0000\u0000\u00b5\u00ba\u0003\u001a\r\u0000\u00b6\u00b7\u0005\"\u0000"+
		"\u0000\u00b7\u00b9\u0003\u001a\r\u0000\u00b8\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b9\u00bc\u0001\u0000\u0000\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000"+
		"\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb\u00be\u0001\u0000\u0000\u0000"+
		"\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bd\u00bf\u0005\"\u0000\u0000\u00be"+
		"\u00bd\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf"+
		"\u00c1\u0001\u0000\u0000\u0000\u00c0\u00b5\u0001\u0000\u0000\u0000\u00c0"+
		"\u00c1\u0001\u0000\u0000\u0000\u00c1#\u0001\u0000\u0000\u0000\u000f*4"+
		"PVd~\u0092\u0094\u00a2\u00ad\u00b1\u00b3\u00ba\u00be\u00c0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}