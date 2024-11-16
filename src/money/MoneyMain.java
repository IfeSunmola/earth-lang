import antlr.MoneyLexer;
import antlr.MoneyParser;
import money.MoneyException;
import money.TreeUtils;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import sanity.SanityChecker;

import static antlr.MoneyParser.ProgramContext;

void main() {
	var lexer = new MoneyLexer(CharStreams.fromString("""
		let age: int = 23
		"""
	));
	var parser = new MoneyParser(new CommonTokenStream(lexer));

	try {
		SanityChecker sanityChecker = new SanityChecker();
		ProgramContext program = parser.program();
		TreeUtils.toPrettyTree(program, parser.getRuleNames());
		sanityChecker.visit(program);
	}
	catch (MoneyException e) {
		System.err.println(e.getMessage());
	}
}