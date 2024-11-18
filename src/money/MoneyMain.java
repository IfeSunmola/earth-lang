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
		loop var i: int = 0, i < 100, x = i + 1 {
		       when i % 3 == 0 && i % 5 == 0{
		           _ = println("FizzBuzz")
		       }
		       else when i % 3 == 0 {
		           _ = println("Fizz")
		       }
		       else when i % 5 == 0 {
		           _ = println("Buzz")
		       }
		       else {
		           _ = println(i)
		       }
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