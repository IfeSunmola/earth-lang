package lexer;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum TokenType {
	EOF("end of file"),
	// Keywords
	KeywordStart("keyword start"),
	VAR("var"), WHEN("when"), ELSE_WHEN("else when"),
	ELSE("else"), FN("fn"), YEET("yeet"),
	UNNAMED("_"), LOOP("loop"),
	KeywordEnd("keyword end"),
	// Identifiers/Literals
	STR_LIT("string literal"), INT_LIT("integer literal"),
	FLOAT_LIT("float literal"), BOOL_LIT("boolean literal"),
	UNTYPED_IDENT("identifier"),
	// Operators
	EQ("="), GT(">"), LT("<"), GTE(">="), LTE("<="),
	EQ_EQ("=="), BANG_EQ("!="), PLUS("+"), MINUS("-"),
	STAR("*"), SLASH("/"), MOD("%"), BANG("!"),
	AND("&&"), OR("||"),
	// Delimiters
	COLON(":"), COMMA(","), LPAREN("("), RPAREN(")"),
	LBRACE("{"), RBRACE("}");

	public final String desc;

	TokenType(String desc) {
		this.desc = desc;
	}

	/// Could just list out the keywords but eh
	public static final Map<String, lexer.TokenType> keywords =
		Stream.of(values())
			.filter(token -> token.ordinal() > KeywordStart.ordinal())
			.filter(token -> token.ordinal() < KeywordEnd.ordinal())
			.collect(toMap(token -> token.desc, Function.identity()));
}

