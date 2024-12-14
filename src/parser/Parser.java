package parser;

import earth.EarthResult;
import lexer.Token;
import lexer.TokenType;
import parser.ast_helpers.StmtList;
import parser.ast_helpers.TypedIdentExpr;
import parser.exprs.Expr;
import parser.exprs.IdentExpr;
import parser.exprs.LitExpr;
import parser.stmts.DeclStmt;
import parser.stmts.Stmt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;

import static earth.EarthUtils.DEBUG;
import static earth.EarthUtils.LOGGER;
import static lexer.TokenType.*;

public class Parser {
	private final List<Token> tokens;
	private int currPos;
	private final Map<TokenType, Supplier<Expr>> prefixParseFns;
	private final Map<TokenType, Function<Expr, Expr>> infixParseFns;

	// interpreter in go page 55
	private enum Precedence {
		LOWEST,
		EQUALS, // ==
		LESS_GREATER, // >= OR <= OR < OR >
		SUM, // + or -
		PRODUCT, // *
		UNARY, // -number or !false
		CALL // function call
	}

	private static final Map<TokenType, Precedence> precedences = Map.of(
		TokenType.Eq, Precedence.EQUALS,
		TokenType.BangEq, Precedence.EQUALS,
		TokenType.Lt, Precedence.LESS_GREATER,
		TokenType.Gt, Precedence.LESS_GREATER,
		TokenType.Lte, Precedence.LESS_GREATER,
		TokenType.Gte, Precedence.LESS_GREATER,
		TokenType.PLus, Precedence.SUM,
		TokenType.Minus, Precedence.SUM,
		TokenType.Slash, Precedence.PRODUCT,
		TokenType.Star, Precedence.PRODUCT
	);

	public Parser(List<Token> tokens) {
		this.tokens = tokens;
		currPos = 0;
		prefixParseFns = Map.of(
			Ident, this::parseIdentExpr,
			StrLit, this::parseLiteralExpr,
			IntLit, this::parseLiteralExpr,
			FloatLit, this::parseLiteralExpr,
			BoolLit, this::parseLiteralExpr,
			NadaLit, this::parseLiteralExpr
		);
		infixParseFns = Map.of();
	}

	public EarthResult<StmtList> parse() {
		var errors = new ArrayList<String>();
		try {
			StmtList stmts = parseStmtList(null, Eof);
			return EarthResult.ok(stmts);
		}
		catch (ParserException e) {
			if (DEBUG) LOGGER.log(Level.SEVERE, e.getMessage(), e);
			errors.add(e.msg);
		}
		return EarthResult.err(errors);
	}

	// Statements
	private StmtList parseStmtList(TokenType start, TokenType end) {
		// start == null implies we're at the beginning of the file
		if (start != null) expect(start);

		if (peekType() == end) { // no statements to parse
			consume(); // consume the end token
			return new StmtList(List.of());
		}

		List<Stmt> stmts = new ArrayList<>();
		while (peekType() != Eof && peekType() != end) {
			stmts.add(parseStmt());
		}
		expect(end); // consume the end token
		return new StmtList(stmts);
	}

	private Stmt parseStmt() {
		return switch (peekType()) {
			case Var -> parseDeclStmt();
			default ->
				// change to unnamed stmt
				throw new ParserException("Expected a statement but got %s".formatted(peekType()), peekLine());
		};
	}

	private DeclStmt parseDeclStmt() {
		int line = expect(Var).line();
		TypedIdentExpr identAndType = parseTypedIdentExpr();
		expect(Eq);
		Expr value = parseExpr(Precedence.LOWEST);
		return new DeclStmt(identAndType, value, line);
	}

	private Expr parseExpr(Precedence prec) {
		var prefixFn = prefixParseFns.get(peekType());
		if (prefixFn == null) {
			Token peeked = peek();
			throw new ParserException(
				"(Prefix not found) Expected an expression but got " + peeked,
				peeked.line()
			);
		}
		Expr left = prefixFn.get();
		// Are you wondering how this works? Me too!
		// Writing an interpreter in Go, page 67
		// while not a keyword i.e. end of statement, and some shit
		while (!keywords.containsValue(peekType()) &&
		       prec.ordinal() < precedences.getOrDefault(peekType(),
			       Precedence.LOWEST).ordinal()) {
			var infixFn = infixParseFns.get(peekType());
			if (infixFn == null) return left;
			left = infixFn.apply(left);
		}
		return left;
	}

	private LitExpr parseLiteralExpr() {
		Token expected = expect(TokenType.literals, "");
		int line = expected.line();
		String literal = expected.literal();

		return switch (expected.type()) {
			case IntLit -> {
				try {
					int value = Integer.parseInt(literal);
					yield new LitExpr.Int(value, line);
				}
				catch (NumberFormatException e) {
					throw new ParserException("Invalid integer literal: %s".formatted(literal), line);
				}
			}
			case FloatLit -> {
				try {
					float value = Float.parseFloat(literal);
					yield new LitExpr.Float(value, line);
				}
				catch (NumberFormatException e) {
					throw new ParserException("Invalid float literal: %s".formatted(literal), line);
				}
			}
			case StrLit -> new LitExpr.Str(literal, line);
			case BoolLit -> {
				boolean value = Boolean.parseBoolean(literal);
				yield new LitExpr.Bool(value, line);
			}
			case NadaLit -> new LitExpr.Nada(line);
			default -> throw new AssertionError("Should not happen");
		};
	}

	private IdentExpr parseIdentExpr() {
		Token ident = expect(TokenType.Ident);
		return new IdentExpr(ident.literal(), ident.line());
	}

	// AST Helpers parse methods
	private TypedIdentExpr parseTypedIdentExpr() {
		IdentExpr name = parseIdentExpr();
		expect(Colon);
		IdentExpr type = parseIdentExpr();
		return new TypedIdentExpr(name, type);
	}

	private Token expect(TokenType expected) {
		Token next = consume();
		if (next.type() != expected)
			throw new ParserException("Expected %s, got %s"
				.formatted(expected.desc, next.type().desc),
				next.line()
			);
		return next;
	}

	private Token expect(Collection<TokenType> expected, String msg) {
		Token next = consume();
		for (TokenType type : expected) {
			if (next.type() == type) return next;
		}
		if (msg.isEmpty()) {
			msg = expected
				.stream()
				.map(type -> type.desc)
				.reduce((a, b) -> a + ", " + b)
				.orElseThrow();
			msg = "Expected one of %s, got %s".formatted(msg, next.type().desc);
		}
		throw new ParserException(msg, next.line()
		);
	}

	private Token peek() {
		if (currPos >= tokens.size())
			return new Token(Eof, Eof.desc, tokens.getLast().line());
		return tokens.get(currPos);
	}

	private TokenType peekType() {
		return peek().type();
	}

	private int peekLine() {
		return peek().line();
	}

	private Token consume() {
		Token peeked = peek();
		currPos++;
		return peeked;
	}
}
