import codegen.StmtCodegen;
import earth.EarthResult;
import earth.EarthUtils;
import lexer.Lexer;
import lexer.Token;
import parser.Parser;
import parser.ast_helpers.StmtList;
import sanity.SanityChecker;
import sanity.TypeValidator;

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
	System.out.println("Running with DEBUG = " + EarthUtils.DEBUG);

	if (args.length == 0) {
		printHelp();
		return;
	}

	if (args.length == 1) { // earth <command|file>
		String arg = args[0];

		if (arg.equals("help")) printHelp();
		else if (arg.equals("version")) System.out.println(COMPILER_NAME_VERSION);
		else compileAndRun(arg);
		return;
	}

	// now the args are in form earth <something> <other things...>
	if (args[0].equals("compile")) compile(args[1], true);
	else if (args[0].equals("run")) EarthUtils.runClassFile(Path.of(args[1]));

	else System.err.println("Invalid command: " + Arrays.toString(args));
}

Path compile(String fPath, boolean printMsg) {
	// Lex
	EarthResult<List<Token>> lexResult = new Lexer(fPath).lex();
	lexResult.quitOnError();
	List<Token> tokens = lexResult.value();

	// Parser
	EarthResult<StmtList> result = new Parser(tokens).parse();
	result.quitOnError();
	StmtList program = result.value();

	// SanityChecker
	result = SanityChecker.run(program);
	result.quitOnError();
	program = result.value();
	TypeValidator.validateStmts(program);

	// Codegen
	EarthResult<byte[]> codegen = new StmtCodegen(fPath).generate(program);
	result.quitOnError(); // Shouldn't happen but eh
	byte[] classFile = codegen.value();

	return EarthUtils.writeToFile(
		classFile, fPath, printMsg
	);
}

void compileAndRun(String fPath) {
	Path compiledPath = compile(fPath, false);
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