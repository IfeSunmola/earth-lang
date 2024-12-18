package lexer;

import earth.EarthException;
import earth.EarthResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;

import static earth.EarthMain.DEBUG;
import static earth.EarthUtils.LOGGER;
import static lexer.TokenType.*;

public class Lexer {
	private final StringBuilder buffer;
	private int line;
	private final byte[] srcCode;
	private int currPos; // in source code

	public Lexer(String srcPath) {
		try {
			srcCode = Files.readAllBytes(Path.of(srcPath));
		}
		catch (IOException e) {
			throw new EarthException("Failed to read source file: " + srcPath);
		}
		currPos = 0;
		buffer = new StringBuilder();
		line = 1;
	}

	public EarthResult<List<Token>> lex() {
		var tokens = new ArrayList<Token>();
		var errors = new ArrayList<String>();

		while (!isEof()) {
			char c = consume();
			try {
				switch (c) {
					case ' ', '\t', '\r' -> {
					}
					case '\n' -> line++;
					// Delimiters
					case ':' -> tokens.add(createToken(Colon));
					case ',' -> tokens.add(createToken(COMMA));
					case '(' -> tokens.add(createToken(LParen));
					case ')' -> tokens.add(createToken(RParen));
					case '{' -> tokens.add(createToken(LBrace));
					case '}' -> tokens.add(createToken(RBrace));
					// Operators
					case '=' -> tokens.add(matchEqual(EqEq, Eq));
					case '>' -> tokens.add(matchEqual(Gte, Gt));
					case '<' -> tokens.add(matchEqual(Lte, Lt));
					case '!' -> tokens.add(matchEqual(BangEq, Bang));
					case '+' -> tokens.add(createToken(PLus));
					case '-' -> tokens.add(createToken(Minus));
					case '*' -> tokens.add(createToken(Star));
					case '/' -> lexSlash(tokens);
					case '%' -> tokens.add(createToken(Mod));
					case '&' -> tokens.add(matchPair(And));
					case '|' -> tokens.add(matchPair(Or));
					// others
					case '"' -> tokens.add(lexStringLit());
					default -> {
						if (Character.isDigit(c))
							tokens.add(lexNumber(c));
						else if (Character.isLetter(c) || c == '_')
							tokens.add(lexKeywordOrIdent(c));
						else throw unrecognizedToken(c);
					}
				}
			}
			catch (LexerException e) {
				if (DEBUG) LOGGER.log(Level.SEVERE, e.getMessage(), e);
				errors.add(e.msg);
			}
		}
		if (errors.isEmpty()) return EarthResult.ok(tokens);
		return EarthResult.err(errors);
	}

	private void lexSlash(ArrayList<Token> tokens) {
		// already consumed one slash, single line comment
		if (peek() == '/') while (peek() != 0 && peek() != '\n') consume();
		else if (peek() == '*') { // multi line comment
			consume(); // consume `*`
			while (true) {
				if (peek() == 0)
					throw new LexerException("Unterminated multi-line comment", line);
				if (peek() == '\n') line++;
				if (consume() == '*' && peek() == '/') {
					consume(); // consume `/`
					break;
				}
			}
		}
		else tokens.add(createToken(Slash));
	}

	private Token lexNumber(char firstNum) {
		Supplier<String> consumeNum = () -> {
			StringBuilder num = getBuffer();

			while (Character.isDigit(peek())) num.append(consume());
			return num.toString();
		};

		String intPart = firstNum + consumeNum.get();
		if (peek() == '.') { // decimal part found
			consume(); // consume `.`
			String floatLit = intPart + "." + consumeNum.get();
			return new Token(FloatLit, floatLit, line);
		}
		return new Token(IntLit, intPart, line);
	}

	/// An identifier can start with either an underscore, or a letter,
	/// followed by letters, numbers, or underscores
	private Token lexKeywordOrIdent(char firstChar) {
		StringBuilder buffer = getBuffer();
		buffer.append(firstChar);

		while (true) {
			char peeked = peek();
			if (Character.isLetterOrDigit(peeked) || peeked == '_')
				buffer.append(consume());
			else break;
		}

		String value = buffer.toString();

		// First check builtins, then keywords, or default to identifier
		return new Token(
			keywords.getOrDefault(value, Ident),
			value,
			line
		);
	}

	private Token lexStringLit() {
		// opening " has been consumed
		StringBuilder buffer = getBuffer();

		while (peek() != '"') {
			char consumed = consume();

			if (consumed == 0) { // reached EOF without closing "
				// Show maximum of 30 characters
				var errStr = buffer.toString();
				int length = errStr.length();
				length = Math.min(length, 30);
				errStr = errStr.substring(0, length).strip();

				var msg = "Unterminated string literal near `%s`...".formatted(errStr);
				throw new LexerException(msg, line);
			}
			if (consumed == '\n') line++;
			buffer.append(consumed);
		}
		consume(); // consume closing quote
		return new Token(StrLit, buffer.toString(), line);
	}

	/// Matches tokens such as `==`, `=` `!=`, `<`, `<=` and creates a new
	/// token based on the match result.\
	/// E.g. if the parameters are `GTE`, `GT` and the next character is `=`, it
	/// will create a `GTE` token, otherwise it will create a `GT` token.
	private Token matchEqual(TokenType matchOk, TokenType matchFail) {
		if (peek() == '=') {
			consume();
			return createToken(matchOk);
		}
		return createToken(matchFail);
	}

	/// Matches a pair of characters e.g. '&&', '||' or throws a
	/// `LexerException` if the next character does not match the expected
	private Token matchPair(TokenType expected) {
		char consumed = consume();
		if (consumed == expected.desc.charAt(0)) return createToken(expected);
		throw unrecognizedToken(consumed);
	}

	/// Helper method to keep `unrecognized token` handling consistent
	private LexerException unrecognizedToken(char c) {
		String temp;
		if (c == '\0') temp = "EOF";
		else if (c == '\n') temp = "\\n";
		else if (c == '\t') temp = "\\t";
		else if (c == '\r') temp = "\\r";
		else temp = String.valueOf(c);

		return new LexerException(
			"Unrecognized character: `" + temp + "`",
			line
		);
	}

	private Token createToken(TokenType type) {
		return new Token(type, type.desc, line);
	}

	private StringBuilder getBuffer() {
		buffer.setLength(0);
		return buffer;
	}

	private char consume() {
		char peeked = peek();
		currPos++;
		return peeked;
	}

	private char peek() {
		if (isEof()) return 0;
		return (char) srcCode[currPos];
	}

	private boolean isEof() {
		return currPos >= srcCode.length;
	}
}
