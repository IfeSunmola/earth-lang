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
		let name: str = "James"
		when 34 % 3 == 0 && 20 % 5 == 0{
		  let name: str = "Ife"
		}
		else when 2 == 23 && 34 == 23{
			var age: int  = 1
		}
		
		else when "My name" == "Ife"{
		_ = 2
		}
		else{
		
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