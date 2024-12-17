import antlr.EarthLexer;
import antlr.EarthParser;
import antlr.EarthParser.ProgramContext;
import codegen.jvm.StmtCodeGen;
import earth.EarthResult;
import earth.EarthUtils;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.Parser;
import parser.ast_helpers.StmtList;
import parser.ast_printer.AstPrinter;
import sanity2.SanityChecker;
import sanity2.TypeValidator;

import java.util.logging.Level;

import static earth.EarthUtils.*;

/*
earth -> print usage
earth <file> -> compile and run the program
earth compile <file> -> compile the program to .class file
earth run <.class> -> run the .class file
earth help -> print usage
earth version -> print version
* */

void handwrittenMain(String filepath) {
	// lexer
	var result = new lexer.Lexer(filepath).lex();
	if (result.isErr()) {
		result.errors().forEach(System.err::println);
		System.exit(1);
	}
	List<lexer.Token> tokens = result.value();

	// parser
	var parser = new Parser(tokens);
	EarthResult<StmtList> parse = parser.parse();

	if (parse.isErr()) {
		parse.errors().forEach(System.err::println);
		System.exit(1);
	}

	StmtList program = parse.value();

	// sanity check
	EarthResult<StmtList> run = SanityChecker.run(program);
	if (run.isErr()) {
		run.errors().forEach(System.err::println);
		System.exit(1);
	}

	program = run.value();
	TypeValidator.validateStmts(program);
//	AstPrinter.print(program);

	// codegen
	var codegen = new codegen2.StmtCodegen(Path.of(filepath));
	EarthResult<byte[]> classFile = codegen.generate(program);

	if (classFile.isErr()) {
		classFile.errors().forEach(System.err::println);
		System.exit(1);
	}

	// write to file
	EarthUtils.writeToFile(
		classFile.value(), removeExt(Path.of(filepath)), true
	);

	System.exit(1);
}

void main(String... args) {
	EarthUtils.validateJavaRuntime();
	System.out.println("Running with DEBUG = " + EarthUtils.DEBUG);

	if (args.length == 0) {
		printHelp();
		return;
	}
	handwrittenMain(args[0]);

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
		// I honestly don't care that printStackTrace is "bad" practice. I'm not
		// pulling in a logging library for this.
		if (DEBUG) LOGGER.log(Level.SEVERE, e.getMessage(), e);
		else System.err.println("Failed to read file: " + e.getMessage());

		System.exit(1);
	}
	var parser = new EarthParser(new CommonTokenStream(lexer));
	ProgramContext program = parser.program();

	sanity.SanityChecker sanityChecker = new sanity.SanityChecker();
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