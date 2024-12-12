package lexer;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum TokenType {
	Eof("end of file"),
	//Built-in types. I'm assuming in a serious language, these would be
	// classes or regular reserved identifiers
	BuiltInStart("built-in type"),
	StrType("str"), IntType("int"), FloatType("float"),
	BoolType("bool"), NadaType("nada"),
	BuiltInEnd("built-in type"),
	// Keywords
	KeywordStart("keyword start"),
	Var("var"), When("when"), ElseWhen("else when"),
	Else("else"), Fn("fn"), Yeet("yeet"),
	Unnamed("_"), Loop("loop"),
	KeywordEnd("keyword end"),
	// Identifiers/Literals
	StrLit("string literal"), IntLit("integer literal"),
	FloatLit("float literal"), BoolLit("boolean literal"),
	Ident("identifier"),
	// Operators
	EQ("="), GT(">"), LT("<"), GTE(">="), LTE("<="),
	EqEq("=="), BangEq("!="), PLUS("+"), MINUS("-"),
	STAR("*"), SLASH("/"), MOD("%"), BANG("!"),
	AND("&&"), OR("||"),
	// Delimiters
	COLON(":"), COMMA(","), LParen("("), RParen(")"),
	LBrace("{"), RBrace("}");

	public final String desc;

	TokenType(String desc) {
		this.desc = desc;
	}

	/// Could just list out the keywords but eh. Looks like an expensive
	/// operation but it's static, so it's only done once.
	public static final Map<String, lexer.TokenType> keywords =
		Stream.of(values())
			.filter(token -> token.ordinal() > KeywordStart.ordinal())
			.filter(token -> token.ordinal() < KeywordEnd.ordinal())
			.collect(toMap(token -> token.desc, Function.identity()));

	public static final Map<String, lexer.TokenType> builtInTypes =
		Stream.of(values())
			.filter(token -> token.ordinal() > BuiltInStart.ordinal())
			.filter(token -> token.ordinal() < BuiltInEnd.ordinal())
			.collect(toMap(token -> token.desc, Function.identity()));
}

