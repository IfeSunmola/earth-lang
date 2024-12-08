import antlr.MoneyLexer;
import antlr.MoneyParser;
import codegen.StmtCodeGen;
import money.MoneyException;
import money.MoneyUtils;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import sanity.SanityChecker;

import static antlr.MoneyParser.ProgramContext;

void main(String... args) {
	if (args.length == 0) {
		System.err.println("Usage: money <file>");
		return;
	}

	MoneyUtils.validateJavaRuntime();

	Path filePath = Path.of(args[0]);

	String temp = filePath.getFileName().toString();
	temp = temp.substring(0, temp.lastIndexOf('.'));
	Path fPathNoExt = filePath.resolveSibling(temp);

	try {
		var lexer = new MoneyLexer(CharStreams.fromPath(filePath));
		var parser = new MoneyParser(new CommonTokenStream(lexer));

		SanityChecker sanityChecker = new SanityChecker();
		ProgramContext program = parser.program();
		sanityChecker.visit(program);

		byte[] classFile = new StmtCodeGen(program, fPathNoExt).getClassFile();
		Path classFilePath = MoneyUtils.writeToFile(classFile, fPathNoExt);
		MoneyUtils.runClassFile(classFilePath);
	}
	catch (MoneyException e) {
		System.err.println(e.getMessage());
	}
	catch (IOException e) {
		System.err.println("Failed to read file: " + e.getMessage());
	}
	catch (Exception e) {
		System.err.println("An error occurred: " + e.getMessage());
	}
}