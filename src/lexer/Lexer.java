package lexer;

import earth.EarthException;
import earth.EarthResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static lexer.TokenType.*;

public class Lexer {
	private final StringBuilder buffer;
	private int currLine;
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
		currLine = 1;
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
					case '\n' -> currLine++;
					// Delimiters
					case ':' -> tokens.add(createToken(COLON));
					case ',' -> tokens.add(createToken(COMMA));
					case '(' -> tokens.add(createToken(LPAREN));
					case ')' -> tokens.add(createToken(RPAREN));
					case '{' -> tokens.add(createToken(LBRACE));
					case '}' -> tokens.add(createToken(RBRACE));
					// Operators
					case '=' -> tokens.add(matchEqual(EQ_EQ, EQ));
					case '>' -> tokens.add(matchEqual(GTE, GT));
					case '<' -> tokens.add(matchEqual(LTE, LT));
					case '!' -> tokens.add(matchEqual(BANG_EQ, BANG));
					case '+' -> tokens.add(createToken(PLUS));
					case '-' -> tokens.add(createToken(MINUS));
					case '*' -> tokens.add(createToken(STAR));
					case '/' -> tokens.add(createToken(SLASH));
					case '%' -> tokens.add(createToken(MOD));
					case '&' -> tokens.add(matchPair(AND));
					case '|' -> tokens.add(matchPair(OR));
					default -> throw new AssertionError("lex has not been implemented");
				}
			}
			catch (LexerException e) {
				errors.add(e.msg);
			}
		}
		if (errors.isEmpty()) return EarthResult.ok(tokens);
		return EarthResult.err(errors);
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
		if (peek() == expected.desc.charAt(0)) {
			consume();
			return createToken(expected);
		}
		throw unrecognizedToken();
	}

	/// Helper method to keep `unrecognized token` handling consistent
	private LexerException unrecognizedToken() {
		char c = consume();

		String temp;
		if (c == '\0') temp = "EOF";
		else if (c == '\n') temp = "\\n";
		else if (c == '\t') temp = "\\t";
		else if (c == '\r') temp = "\\r";
		else temp = String.valueOf(c);

		return new LexerException(
			"Unrecognized character: `" + temp + "`",
			currLine
		);
	}

	private Token createToken(TokenType type) {
		return new Token(type, type.desc, currLine);
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
		if (isEof()) return '\0';
		return (char) srcCode[currPos];
	}

	private boolean isEof() {
		return currPos >= srcCode.length;
	}
}
