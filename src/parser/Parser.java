package parser;

import lexer.Token;

import java.util.List;

public class Parser {
	private final List<Token> tokens;
	private int currPos;

	public Parser(List<Token> tokens) {
		this.tokens = tokens;
		currPos = 0;
	}
}
