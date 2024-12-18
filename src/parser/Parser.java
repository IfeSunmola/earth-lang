package parser;

import earth.EarthResult;
import lexer.Token;
import lexer.TokenType;
import parser.ast_helpers.ExprList;
import parser.ast_helpers.StmtList;
import parser.ast_helpers.TypedIdent;
import parser.ast_helpers.TypedIdentList;
import parser.exprs.*;
import parser.stmts.*;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;

import static earth.EarthMain.DEBUG;
import static earth.EarthUtils.LOGGER;
import static lexer.TokenType.*;
import static parser.exprs.BinaryExpr.*;

public class Parser {
	private final List<Token> tokens;
	private int currPos;
	private final Map<TokenType, Supplier<Expr>> prefixParseFns;
	private final Map<TokenType, Function<Expr, Expr>> infixParseFns;

	// interpreter in go page 55
	private enum Precedence {
		LOWEST,
		LOGICAL, // && or ||
		EQUALS, // ==
		LESS_GREATER, // >= OR <= OR < OR >
		SUM, // + or -
		PRODUCT, // *
		UNARY, // -number or !false
		CALL, // function call
	}

	public Parser(List<Token> tokens) {
		this.tokens = tokens;
		currPos = 0;
		prefixParseFns = new HashMap<>();
		infixParseFns = new HashMap<>();
		registerPrefixFns();
		registerInfixFns();
	}

	public EarthResult<StmtList> parse() {
		var errors = new ArrayList<String>();
		try {
			StmtList stmts = parseStmtList(null, Eof);
			if (stmts.isEmpty())
				return EarthResult.err(List.of("Nothing to compile"));
			return EarthResult.ok(stmts);
		}
		catch (ParserException e) {
			if (DEBUG) LOGGER.log(Level.SEVERE, e.getMessage(), e);
			errors.add(e.msg);
		}
		return EarthResult.err(errors);
	}

	private void registerInfixFns() {
		infixParseFns.put(Star, this::parseProductExpr);
		infixParseFns.put(Slash, this::parseProductExpr);
		infixParseFns.put(Mod, this::parseProductExpr);

		infixParseFns.put(PLus, this::parseAdditiveExpr);
		infixParseFns.put(Minus, this::parseAdditiveExpr);

		infixParseFns.put(Lt, this::parseRelationalExpr);
		infixParseFns.put(Gt, this::parseRelationalExpr);
		infixParseFns.put(Lte, this::parseRelationalExpr);
		infixParseFns.put(Gte, this::parseRelationalExpr);

		infixParseFns.put(EqEq, this::parseEqualityExpr);
		infixParseFns.put(BangEq, this::parseEqualityExpr);

		infixParseFns.put(And, this::parseLogicalExpr);
		infixParseFns.put(Or, this::parseLogicalExpr);

		infixParseFns.put(LParen, this::parseFnCallExpr);
	}

	private void registerPrefixFns() {
		prefixParseFns.put(Ident, this::parseIdentExpr);
		prefixParseFns.put(StrLit, this::parseLiteralExpr);
		prefixParseFns.put(IntLit, this::parseLiteralExpr);
		prefixParseFns.put(FloatLit, this::parseLiteralExpr);
		prefixParseFns.put(BoolLit, this::parseLiteralExpr);
		prefixParseFns.put(NadaLit, this::parseLiteralExpr);
		prefixParseFns.put(Minus, this::parseNegExpr);
		prefixParseFns.put(Bang, this::parseNotExpr);
		prefixParseFns.put(LParen, this::parseGroupedExpr);
	}

	// Statements
	private StmtList parseStmtList(TokenType start, TokenType end) {
		// start == null implies we're at the beginning of the file
		if (start != null) expect(start);

		if (peekType() == end) { // no statements to parse
			consume(); // consume the end token
			return new StmtList(List.of());
		}

		var stmts = new StmtList();
		while (peekType() != Eof && peekType() != end) {
			stmts.add(parseStmt());
		}
		expect(end); // consume the end token
		return stmts;
	}

	private Stmt parseStmt() {
		return switch (peekType()) {
			case Var -> parseDeclStmt();
			case Yeet -> parseYeetStmt();
			case Unnamed -> parseUnnamedStmt();
			case Ident -> parseReassignStmt();
			case Loop -> parseLoopStmt();
			case Fn -> parseFnDefStmt();
			case When -> parseWhenStmt();
			default ->
				throw new ParserException("Unknown statement type: `%s`".formatted(peekType().desc), peekLine());
		};
	}

	private WhenStmt parseWhenStmt() {
		int line = peekLine();

		expect(When);
		Expr condition = parseExpr(Precedence.LOWEST);
		StmtList body = parseStmtList(LBrace, RBrace);
		WhenStmt.When when = new WhenStmt.When(condition, body);

		List<WhenStmt.When> elseWhens = new ArrayList<>();
		StmtList elseBlock = new StmtList();
		while (peekType() == Else) {
			consume();
			if (peekType() == When) { // else when
				consume();
				elseWhens.add(
					new WhenStmt.When(
						parseExpr(Precedence.LOWEST),
						parseStmtList(LBrace, RBrace))
				);
			}
			else {
				// else block
				elseBlock.addAll(parseStmtList(LBrace, RBrace));
			}
		}

		return new WhenStmt(when, elseWhens, elseBlock, line);
	}

	private FnDefStmt parseFnDefStmt() {
		int line = expect(Fn).line();
		IdentExpr name = parseIdentExpr();

		expect(LParen);
		TypedIdentList params = parseTypedIdentList();
		expect(RParen);

		Expr expr = tryParseExpr()
			.orElse(IdentExpr.nada(line));

		if (!(expr instanceof IdentExpr returnType)) {
			throw new ParserException(
				"Expected a return type after the function parameters",
				expr.line());
		}

		StmtList body = parseStmtList(LBrace, RBrace);
		if (body.isEmpty()) {
			var newYeet = new YeetStmt(IdentExpr.nada(line), line);
			body.add(newYeet);
		}
		else if (!(body.getLast() instanceof YeetStmt)) {
			int yeetLine = body.getLast().line();
			var newYeet = new YeetStmt(IdentExpr.nada(yeetLine), yeetLine);
			body.add(newYeet);
		}

		return new FnDefStmt(name, params, returnType, body, line);
	}

	private LoopStmt parseLoopStmt() {
		int line = expect(Loop).line();
		DeclStmt init = parseDeclStmt();
		expect(COMMA);
		Expr condition = parseExpr(Precedence.LOWEST);
		expect(COMMA);
		ReassignStmt update = parseReassignStmt();
		StmtList body = parseStmtList(LBrace, RBrace);

		return new LoopStmt(init, condition, update, body, line);
	}

	private ReassignStmt parseReassignStmt() {
		IdentExpr ident = parseIdentExpr();
		expect(Eq);
		Expr value = parseExpr(Precedence.LOWEST);
		return new ReassignStmt(ident, value, ident.line());
	}

	private UnnamedStmt parseUnnamedStmt() {
		int line = expect(Unnamed).line();
		expect(Eq);
		Expr value = parseExpr(Precedence.LOWEST);

		return new UnnamedStmt(value, line);
	}

	private YeetStmt parseYeetStmt() {
		int line = expect(Yeet).line();
		// not sure if there's any difference between defaulting to literal nada,
		// or identifier nada
		Expr value = tryParseExpr()
			.orElse(IdentExpr.nada(line));

		return new YeetStmt(value, line);
	}

	private DeclStmt parseDeclStmt() {
		int line = expect(Var).line();
		TypedIdent identAndType = parseTypedIdent();
		expect(Eq);
		Expr value = parseExpr(Precedence.LOWEST);
		return new DeclStmt(identAndType, value, line);
	}

	private Expr parseExpr(Precedence prec) {
		var prefixFn = prefixParseFns.get(peekType());
		if (prefixFn == null) {
			Token peeked = peek();
			throw new ParserException(
				// change to not an expression
				"Prefix function not found: %s".formatted(peeked)
				, peeked.line()
			);
		}
		Expr left = prefixFn.get();
		// Are you wondering how this works? Me too!
		// Writing an interpreter in Go, page 67
		// while not a keyword i.e. end of statement, and some shit
		while (peekType() != Eof && !keywords.containsValue(peekType())
		       && prec.ordinal() < getPrecedence(peekType()).ordinal()) {
			var infixFn = infixParseFns.get(peekType());
			if (infixFn == null) return left;
			left = infixFn.apply(left);
		}
		return left;
	}

	private FnCallExpr parseFnCallExpr(Expr expr) {
		if (!(expr instanceof IdentExpr fnName)) {
			throw new ParserException(
				"Expected an identifier as the name of a function",
				expr.line());
		}
		int line = expect(LParen).line();
		ExprList params = parseExprList();
		expect(RParen);

		return new FnCallExpr(fnName, params, line);
	}

	private ProductExpr parseProductExpr(Expr left) {
		Token expected = expect(List.of(Star, Slash, Mod));
		int line = expected.line();
		TokenType op = expected.type();

		Expr right = parseExpr(getPrecedence(op));
		return new ProductExpr(left, op, right, line);
	}

	private AdditiveExpr parseAdditiveExpr(Expr left) {
		Token expected = expect(List.of(PLus, Minus));
		int line = expected.line();
		TokenType op = expected.type();

		Expr right = parseExpr(getPrecedence(op));
		return new AdditiveExpr(left, op, right, line);
	}

	private RelationalExpr parseRelationalExpr(Expr left) {
		Token expected = expect(List.of(Lte, Gte, Lt, Gt));
		int line = expected.line();
		TokenType op = expected.type();

		Expr right = parseExpr(getPrecedence(op));
		return new RelationalExpr(left, op, right, line);
	}

	private EqualityExpr parseEqualityExpr(Expr left) {
		Token expected = expect(List.of(EqEq, BangEq));
		int line = expected.line();
		TokenType op = expected.type();

		Expr right = parseExpr(getPrecedence(op));
		return new EqualityExpr(left, op, right, line);
	}

	private LogicalExpr parseLogicalExpr(Expr left) {
		Token expected = expect(List.of(And, Or));
		int line = expected.line();
		TokenType op = expected.type();

		Expr right = parseExpr(getPrecedence(op));
		return new LogicalExpr(left, op, right, line);
	}

	private LitExpr parseLiteralExpr() {
		Token expected = expect(TokenType.literals);
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

	private NegExpr parseNegExpr() {
		Token expected = expect(Minus);
		int line = expected.line();

		return new NegExpr(parseExpr(Precedence.UNARY), line);
	}

	private NotExpr parseNotExpr() {
		Token expected = expect(Bang);
		int line = expected.line();

		return new NotExpr(parseExpr(Precedence.UNARY), line);
	}

	private GroupedExpr parseGroupedExpr() {
		int line = expect(LParen).line();
		Expr expr = parseExpr(Precedence.LOWEST);
		expect(RParen);
		return new GroupedExpr(expr, line);
	}

	private IdentExpr parseIdentExpr() {
		Token ident = expect(TokenType.Ident);
		return new IdentExpr(ident.literal(), ident.line());
	}

	private Optional<Expr> tryParseExpr() {
		int temp = currPos;
		try {
			return Optional.of(parseExpr(Precedence.LOWEST));
		}
		catch (ParserException e) {
			currPos = temp;
			return Optional.empty();
		}
	}

	private Optional<IdentExpr> tryParseIdentExpr() {
		int temp = currPos;
		try {
			return Optional.of(parseIdentExpr());
		}
		catch (ParserException e) {
			currPos = temp;
			return Optional.empty();
		}
	}

	private Optional<TypedIdent> tryParseTypedIdent() {
		int temp = currPos;
		try {
			return Optional.of(parseTypedIdent());
		}
		catch (ParserException e) {
			currPos = temp;
			return Optional.empty();
		}
	}

	// AST Helpers parse methods
	private TypedIdent parseTypedIdent() {
		IdentExpr name = parseIdentExpr();
		expect(Colon);
		IdentExpr type = tryParseIdentExpr()
			.orElseThrow(() -> new ParserException(
				"Expected a type after the colon",
				peekLine()
			));
		return new TypedIdent(name, type);
	}

	private ExprList parseExprList() {
		Optional<Expr> expr = tryParseExpr();
		if (expr.isEmpty()) return new ExprList(List.of());

		var exprs = new ExprList();
		exprs.add(expr.get());
		while (peekType() == COMMA) {
			consume();
			// Optional trailing comma
			tryParseExpr().ifPresent(exprs::add);
		}
		return exprs;
	}

	private TypedIdentList parseTypedIdentList() {
		Optional<TypedIdent> expr = tryParseTypedIdent();
		if (expr.isEmpty()) return new TypedIdentList(List.of());

		List<TypedIdent> exprs = new ArrayList<>();
		exprs.add(expr.get());
		while (peekType() == COMMA) {
			consume();
			// Optional trailing comma
			if (peekType() == Ident) exprs.add(parseTypedIdent());
		}
		return new TypedIdentList(exprs);
	}

	private Token expect(TokenType expected) {
		Token next = consume();
		if (next.type() != expected)
			throw new ParserException("Expected %s, got %s: %s"
				.formatted(expected.desc, next.type().desc, next.literal()),
				next.line()
			);
		return next;
	}

	private Token expect(Collection<TokenType> expected) {
		Token next = consume();
		for (TokenType type : expected) {
			if (next.type() == type) return next;
		}

		String msg = expected
			.stream()
			.map(type -> type.desc)
			.reduce((a, b) -> a + ", " + b)
			.orElseThrow();

		msg = "Expected one of %s, got %s `%s`".formatted(msg,
			next.type().desc, next.literal());
		throw new ParserException(msg, next.line()
		);
	}

	private Token peek() {
		if (currPos >= tokens.size())
			return new Token(Eof, Eof.desc, -1);
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

	private static Precedence getPrecedence(TokenType type) {
		return (switch (type) {
			case EqEq, BangEq -> Precedence.EQUALS;
			case And, Or -> Precedence.LOGICAL;
			case Lt, Gt, Lte, Gte -> Precedence.LESS_GREATER;
			case PLus, Minus -> Precedence.SUM;
			case Star, Slash, Mod -> Precedence.PRODUCT;
			case LParen -> Precedence.CALL;
			default -> Precedence.LOWEST;
		});
	}
}
