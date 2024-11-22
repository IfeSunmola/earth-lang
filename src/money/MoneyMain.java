import antlr.MoneyLexer;
import antlr.MoneyParser;
import codegen.JVMCodeGen;
import money.MoneyException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import sanity.SanityChecker;

import static antlr.MoneyParser.ProgramContext;

void main() {
	var lexer = new MoneyLexer(CharStreams.fromString("""
		let age: int = 23
		var name: str = "John"
		age = 24
		name = "Doe"
		// let married: bool = false
		// let salary: float = 1000.0
		"""
	));
	var parser = new MoneyParser(new CommonTokenStream(lexer));
	try {
		SanityChecker sanityChecker = new SanityChecker();
		ProgramContext program = parser.program();
		sanityChecker.visit(program);

		new JVMCodeGen(program); // no need to call visit()
	}
	catch (MoneyException e) {
		System.err.println(e.getMessage());
	}
}