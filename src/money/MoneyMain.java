import antlr.MoneyLexer;
import antlr.MoneyParser;
import money.MoneyException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import sanity.SanityChecker;
import sanity.SymbolTable;

import static antlr.MoneyParser.ProgramContext;

void main() {
	var lexer = new MoneyLexer(CharStreams.fromString("""
		fn name (age: int, age: str) str {
				yeet "23"
		}
		
		"""
	));
	var parser = new MoneyParser(new CommonTokenStream(lexer));
	try {
		SanityChecker sanityChecker = new SanityChecker();
		ProgramContext program = parser.program();
		sanityChecker.visit(program);
		System.out.println(SymbolTable.instance);
	}
	catch (MoneyException e) {
		System.err.println(e.getMessage());
	}
}