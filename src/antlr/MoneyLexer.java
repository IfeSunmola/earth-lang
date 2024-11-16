// Generated from /home/ifesunmola/Documents/dev/java/money-lang/src/antlr/MoneyLexer.g4 by ANTLR 4.13.1
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MoneyLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Whitespace=1, SingleLineComment=2, MultiLineComment=3, Let=4, Var=5, When=6, 
		Else=7, Fn=8, Yeet=9, Unnamed=10, Loop=11, UntypedIdent=12, StrLit=13, 
		IntLit=14, FloatLit=15, Equal=16, Gt=17, Lt=18, Gte=19, Lte=20, EqEq=21, 
		BangEq=22, Plus=23, Minus=24, Star=25, Slash=26, Bang=27, And=28, Or=29, 
		Colon=30, Comma=31, LParen=32, RParen=33, LBrace=34, RBrace=35;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"Whitespace", "SingleLineComment", "MultiLineComment", "Let", "Var", 
			"When", "Else", "Fn", "Yeet", "Unnamed", "Loop", "UntypedIdent", "StrLit", 
			"IntLit", "FloatLit", "Equal", "Gt", "Lt", "Gte", "Lte", "EqEq", "BangEq", 
			"Plus", "Minus", "Star", "Slash", "Bang", "And", "Or", "Colon", "Comma", 
			"LParen", "RParen", "LBrace", "RBrace"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'let'", "'var'", "'when'", "'else'", "'fn'", 
			"'yeet'", "'_'", "'loop'", null, null, null, null, "'='", "'>'", "'<'", 
			"'>='", "'<='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'", "'!'", "'&&'", 
			"'||'", "':'", "','", "'('", "')'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Whitespace", "SingleLineComment", "MultiLineComment", "Let", "Var", 
			"When", "Else", "Fn", "Yeet", "Unnamed", "Loop", "UntypedIdent", "StrLit", 
			"IntLit", "FloatLit", "Equal", "Gt", "Lt", "Gte", "Lte", "EqEq", "BangEq", 
			"Plus", "Minus", "Star", "Slash", "Bang", "And", "Or", "Colon", "Comma", 
			"LParen", "RParen", "LBrace", "RBrace"
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


	public MoneyLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MoneyLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000#\u00d6\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0001\u0000\u0004\u0000I\b\u0000\u000b\u0000\f\u0000"+
		"J\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0005\u0001S\b\u0001\n\u0001\f\u0001V\t\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002^\b\u0002"+
		"\n\u0002\f\u0002a\t\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0005\u000b\u008b\b\u000b\n\u000b\f\u000b\u008e"+
		"\t\u000b\u0001\f\u0001\f\u0005\f\u0092\b\f\n\f\f\f\u0095\t\f\u0001\f\u0001"+
		"\f\u0001\r\u0004\r\u009a\b\r\u000b\r\f\r\u009b\u0001\u000e\u0004\u000e"+
		"\u009f\b\u000e\u000b\u000e\f\u000e\u00a0\u0001\u000e\u0001\u000e\u0004"+
		"\u000e\u00a5\b\u000e\u000b\u000e\f\u000e\u00a6\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e"+
		"\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0002"+
		"_\u0093\u0000#\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015"+
		"+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f"+
		"? A!C\"E#\u0001\u0000\u0005\u0003\u0000\t\n\r\r  \u0002\u0000\n\n\r\r"+
		"\u0002\u0000AZaz\u0003\u000009AZaz\u0001\u000009\u00dd\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000"+
		"\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'"+
		"\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000"+
		"\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000"+
		"\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005"+
		"\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000"+
		"\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000"+
		"\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C"+
		"\u0001\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0001H\u0001\u0000"+
		"\u0000\u0000\u0003N\u0001\u0000\u0000\u0000\u0005Y\u0001\u0000\u0000\u0000"+
		"\u0007g\u0001\u0000\u0000\u0000\tk\u0001\u0000\u0000\u0000\u000bo\u0001"+
		"\u0000\u0000\u0000\rt\u0001\u0000\u0000\u0000\u000fy\u0001\u0000\u0000"+
		"\u0000\u0011|\u0001\u0000\u0000\u0000\u0013\u0081\u0001\u0000\u0000\u0000"+
		"\u0015\u0083\u0001\u0000\u0000\u0000\u0017\u0088\u0001\u0000\u0000\u0000"+
		"\u0019\u008f\u0001\u0000\u0000\u0000\u001b\u0099\u0001\u0000\u0000\u0000"+
		"\u001d\u009e\u0001\u0000\u0000\u0000\u001f\u00a8\u0001\u0000\u0000\u0000"+
		"!\u00aa\u0001\u0000\u0000\u0000#\u00ac\u0001\u0000\u0000\u0000%\u00ae"+
		"\u0001\u0000\u0000\u0000\'\u00b1\u0001\u0000\u0000\u0000)\u00b4\u0001"+
		"\u0000\u0000\u0000+\u00b7\u0001\u0000\u0000\u0000-\u00ba\u0001\u0000\u0000"+
		"\u0000/\u00bc\u0001\u0000\u0000\u00001\u00be\u0001\u0000\u0000\u00003"+
		"\u00c0\u0001\u0000\u0000\u00005\u00c2\u0001\u0000\u0000\u00007\u00c4\u0001"+
		"\u0000\u0000\u00009\u00c7\u0001\u0000\u0000\u0000;\u00ca\u0001\u0000\u0000"+
		"\u0000=\u00cc\u0001\u0000\u0000\u0000?\u00ce\u0001\u0000\u0000\u0000A"+
		"\u00d0\u0001\u0000\u0000\u0000C\u00d2\u0001\u0000\u0000\u0000E\u00d4\u0001"+
		"\u0000\u0000\u0000GI\u0007\u0000\u0000\u0000HG\u0001\u0000\u0000\u0000"+
		"IJ\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000"+
		"\u0000KL\u0001\u0000\u0000\u0000LM\u0006\u0000\u0000\u0000M\u0002\u0001"+
		"\u0000\u0000\u0000NO\u0005/\u0000\u0000OP\u0005/\u0000\u0000PT\u0001\u0000"+
		"\u0000\u0000QS\b\u0001\u0000\u0000RQ\u0001\u0000\u0000\u0000SV\u0001\u0000"+
		"\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000UW\u0001"+
		"\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000WX\u0006\u0001\u0000\u0000"+
		"X\u0004\u0001\u0000\u0000\u0000YZ\u0005/\u0000\u0000Z[\u0005*\u0000\u0000"+
		"[_\u0001\u0000\u0000\u0000\\^\t\u0000\u0000\u0000]\\\u0001\u0000\u0000"+
		"\u0000^a\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000_]\u0001\u0000"+
		"\u0000\u0000`b\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000bc\u0005"+
		"*\u0000\u0000cd\u0005/\u0000\u0000de\u0001\u0000\u0000\u0000ef\u0006\u0002"+
		"\u0000\u0000f\u0006\u0001\u0000\u0000\u0000gh\u0005l\u0000\u0000hi\u0005"+
		"e\u0000\u0000ij\u0005t\u0000\u0000j\b\u0001\u0000\u0000\u0000kl\u0005"+
		"v\u0000\u0000lm\u0005a\u0000\u0000mn\u0005r\u0000\u0000n\n\u0001\u0000"+
		"\u0000\u0000op\u0005w\u0000\u0000pq\u0005h\u0000\u0000qr\u0005e\u0000"+
		"\u0000rs\u0005n\u0000\u0000s\f\u0001\u0000\u0000\u0000tu\u0005e\u0000"+
		"\u0000uv\u0005l\u0000\u0000vw\u0005s\u0000\u0000wx\u0005e\u0000\u0000"+
		"x\u000e\u0001\u0000\u0000\u0000yz\u0005f\u0000\u0000z{\u0005n\u0000\u0000"+
		"{\u0010\u0001\u0000\u0000\u0000|}\u0005y\u0000\u0000}~\u0005e\u0000\u0000"+
		"~\u007f\u0005e\u0000\u0000\u007f\u0080\u0005t\u0000\u0000\u0080\u0012"+
		"\u0001\u0000\u0000\u0000\u0081\u0082\u0005_\u0000\u0000\u0082\u0014\u0001"+
		"\u0000\u0000\u0000\u0083\u0084\u0005l\u0000\u0000\u0084\u0085\u0005o\u0000"+
		"\u0000\u0085\u0086\u0005o\u0000\u0000\u0086\u0087\u0005p\u0000\u0000\u0087"+
		"\u0016\u0001\u0000\u0000\u0000\u0088\u008c\u0007\u0002\u0000\u0000\u0089"+
		"\u008b\u0007\u0003\u0000\u0000\u008a\u0089\u0001\u0000\u0000\u0000\u008b"+
		"\u008e\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008c"+
		"\u008d\u0001\u0000\u0000\u0000\u008d\u0018\u0001\u0000\u0000\u0000\u008e"+
		"\u008c\u0001\u0000\u0000\u0000\u008f\u0093\u0005\"\u0000\u0000\u0090\u0092"+
		"\t\u0000\u0000\u0000\u0091\u0090\u0001\u0000\u0000\u0000\u0092\u0095\u0001"+
		"\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0093\u0091\u0001"+
		"\u0000\u0000\u0000\u0094\u0096\u0001\u0000\u0000\u0000\u0095\u0093\u0001"+
		"\u0000\u0000\u0000\u0096\u0097\u0005\"\u0000\u0000\u0097\u001a\u0001\u0000"+
		"\u0000\u0000\u0098\u009a\u0007\u0004\u0000\u0000\u0099\u0098\u0001\u0000"+
		"\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000"+
		"\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c\u001c\u0001\u0000"+
		"\u0000\u0000\u009d\u009f\u0007\u0004\u0000\u0000\u009e\u009d\u0001\u0000"+
		"\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000"+
		"\u0000\u0000\u00a2\u00a4\u0005.\u0000\u0000\u00a3\u00a5\u0007\u0004\u0000"+
		"\u0000\u00a4\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000"+
		"\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000"+
		"\u0000\u00a7\u001e\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005=\u0000\u0000"+
		"\u00a9 \u0001\u0000\u0000\u0000\u00aa\u00ab\u0005>\u0000\u0000\u00ab\""+
		"\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005<\u0000\u0000\u00ad$\u0001\u0000"+
		"\u0000\u0000\u00ae\u00af\u0005>\u0000\u0000\u00af\u00b0\u0005=\u0000\u0000"+
		"\u00b0&\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005<\u0000\u0000\u00b2\u00b3"+
		"\u0005=\u0000\u0000\u00b3(\u0001\u0000\u0000\u0000\u00b4\u00b5\u0005="+
		"\u0000\u0000\u00b5\u00b6\u0005=\u0000\u0000\u00b6*\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b8\u0005!\u0000\u0000\u00b8\u00b9\u0005=\u0000\u0000\u00b9,"+
		"\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005+\u0000\u0000\u00bb.\u0001\u0000"+
		"\u0000\u0000\u00bc\u00bd\u0005-\u0000\u0000\u00bd0\u0001\u0000\u0000\u0000"+
		"\u00be\u00bf\u0005*\u0000\u0000\u00bf2\u0001\u0000\u0000\u0000\u00c0\u00c1"+
		"\u0005/\u0000\u0000\u00c14\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005!"+
		"\u0000\u0000\u00c36\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005&\u0000\u0000"+
		"\u00c5\u00c6\u0005&\u0000\u0000\u00c68\u0001\u0000\u0000\u0000\u00c7\u00c8"+
		"\u0005|\u0000\u0000\u00c8\u00c9\u0005|\u0000\u0000\u00c9:\u0001\u0000"+
		"\u0000\u0000\u00ca\u00cb\u0005:\u0000\u0000\u00cb<\u0001\u0000\u0000\u0000"+
		"\u00cc\u00cd\u0005,\u0000\u0000\u00cd>\u0001\u0000\u0000\u0000\u00ce\u00cf"+
		"\u0005(\u0000\u0000\u00cf@\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005)"+
		"\u0000\u0000\u00d1B\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005{\u0000\u0000"+
		"\u00d3D\u0001\u0000\u0000\u0000\u00d4\u00d5\u0005}\u0000\u0000\u00d5F"+
		"\u0001\u0000\u0000\u0000\t\u0000JT_\u008c\u0093\u009b\u00a0\u00a6\u0001"+
		"\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}