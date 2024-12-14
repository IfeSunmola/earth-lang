package lexer;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum TokenType {
	Eof("end of file"),
	// Keywords
	KeywordStart("keyword"),
	Var("var"), When("when"), ElseWhen("else when"),
	Else("else"), Fn("fn"), Yeet("yeet"),
	Unnamed("_"), Loop("loop"),
	KeywordEnd("keyword"),
	// Identifiers/Literals
	LitStart("literal"),
	StrLit("string literal"), IntLit("integer literal"),
	FloatLit("float literal"), BoolLit("boolean literal"),
	NadaLit("nada literal"),
	LitEnd("literal"),
	Ident("an identifier"),
	// Operators
	Eq("="), Gt(">"), Lt("<"), Gte(">="), Lte("<="),
	EqEq("=="), BangEq("!="), PLus("+"), Minus("-"),
	Star("*"), Slash("/"), Mod("%"), Bang("!"),
	And("&&"), Or("||"),
	// Delimiters
	Colon(":"), COMMA(","), LParen("("), RParen(")"),
	LBrace("{"), RBrace("}");

	public final String desc;

	TokenType(String desc) {
		this.desc = desc;
	}

	/// Could just list out the keywords but eh. Looks like an expensive
	/// operation, but it's static, so it's only done once.
	public static final Map<String, lexer.TokenType> keywords =
		Stream.of(values())
			.filter(token -> token.ordinal() > KeywordStart.ordinal())
			.filter(token -> token.ordinal() < KeywordEnd.ordinal())
			.collect(toMap(token -> token.desc, Function.identity()));

	public static final List<TokenType> literals = Stream.of(values())
		.filter(token -> token.ordinal() > LitStart.ordinal())
		.filter(token -> token.ordinal() < LitEnd.ordinal())
		.toList();
}

