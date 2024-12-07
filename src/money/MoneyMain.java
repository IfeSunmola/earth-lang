import antlr.MoneyLexer;
import antlr.MoneyParser;
import codegen.StmtCodeGen;
import money.MoneyException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import sanity.SanityChecker;

import static antlr.MoneyParser.ProgramContext;

void main() {
	var lexer = new MoneyLexer(CharStreams.fromString("""
		let isTrue: bool = true
		let isFalse: bool = true
		
		let and: bool = isTrue || isFalse
		"""
	));
	var parser = new MoneyParser(new CommonTokenStream(lexer));
	try {
		SanityChecker sanityChecker = new SanityChecker();
		ProgramContext program = parser.program();
		sanityChecker.visit(program);

		new StmtCodeGen(program); // no need to call visit()
	}
	catch (MoneyException e) {
		System.err.println(e.getMessage());
	}
}