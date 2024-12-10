import antlr.EarthLexer;
import antlr.EarthParser;
import antlr.EarthParser.ProgramContext;
import codegen.jvm.StmtCodeGen;
import earth.EarthUtils;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import sanity.SanityChecker;

import static earth.EarthUtils.COMPILER_NAME_VERSION;

/*
earth -> print usage
earth <file> -> compile and run the program
earth compile <file> -> compile the program to .class file
earth run <.class> -> run the .class file
earth help -> print usage
earth version -> print version
* */

void main(String... args) {
	EarthUtils.validateJavaRuntime();

	if (args.length == 0) {
		printHelp();
		return;
	}

	if (args.length == 1) { // earth <command|file>
		String arg = args[0];

		if (arg.equals("help")) printHelp();
		else if (arg.equals("version")) System.out.println(COMPILER_NAME_VERSION);
		else compileAndRun(Path.of(arg));
		return;
	}

	// now the args are in form earth <something> <other things...>
	if (args[0].equals("compile")) compile(Path.of(args[1]), true);
	else if (args[0].equals("run")) EarthUtils.runClassFile(Path.of(args[1]));

	else System.err.println("Invalid command: " + Arrays.toString(args));
}

Path compile(Path fPath, boolean printMsg) {
	EarthLexer lexer = null;
	try {
		lexer = new EarthLexer(CharStreams.fromPath(fPath));
	}
	catch (IOException e) {
		System.err.println("Failed to read file: " + e.getMessage());
		System.exit(1);
	}
	var parser = new EarthParser(new CommonTokenStream(lexer));

	SanityChecker sanityChecker = new SanityChecker();
	ProgramContext program = parser.program();
	sanityChecker.visit(program);

	byte[] classFile = new StmtCodeGen(program, removeExt(fPath))
		.getClassFile();

	return EarthUtils.writeToFile(
		classFile, removeExt(fPath), printMsg
	);
}

void compileAndRun(Path filePath) {
	Path compiledPath = compile(filePath, false);
	EarthUtils.runClassFile(compiledPath);
	try {
		Files.delete(compiledPath);
	}
	catch (IOException e) {
		System.err.println("Failed to delete compiled file: " + e.getMessage());
	}
}

void printHelp() {
	System.out.printf("""
		Help Menu for %s:
		earth                     - print this help message
		earth         <file>      - compile and run the .earth file
		earth compile <file>      - compile the .earth file to a .class file
		earth run     <.class>    - run the .class file using the custom Java runtime
		earth help                - print this help message
		earth version             - print the version of the Earth compiler
		""", COMPILER_NAME_VERSION
	);
}

Path removeExt(Path path) {
	String temp = path.getFileName().toString();
	temp = temp.substring(0, temp.lastIndexOf('.'));
	return path.resolveSibling(temp);
}