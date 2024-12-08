import antlr.EarthLexer;
import antlr.EarthParser;
import antlr.EarthParser.ProgramContext;
import codegen.StmtCodeGen;
import earth.EarthException;
import earth.EarthUtils;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import sanity.SanityChecker;

void main(String... args) {
	if (args.length == 0) {
		System.err.println("Usage: earth <file>");
		return;
	}

	EarthUtils.validateJavaRuntime();

	Path filePath = Path.of(args[0]);

	String temp = filePath.getFileName().toString();
	temp = temp.substring(0, temp.lastIndexOf('.'));
	Path fPathNoExt = filePath.resolveSibling(temp);

	try {
		var lexer = new EarthLexer(CharStreams.fromPath(filePath));
		var parser = new EarthParser(new CommonTokenStream(lexer));

		SanityChecker sanityChecker = new SanityChecker();
		ProgramContext program = parser.program();
		sanityChecker.visit(program);

		byte[] classFile = new StmtCodeGen(program, fPathNoExt).getClassFile();
		Path classFilePath = EarthUtils.writeToFile(classFile, fPathNoExt);
		EarthUtils.runClassFile(classFilePath);
	}
	catch (EarthException e) {
		System.err.println(e.getMessage());
	}
	catch (IOException e) {
		System.err.println("Failed to read file: " + e.getMessage());
	}
	catch (Exception e) {
		System.err.println("An error occurred: " + e.getMessage());
	}
}