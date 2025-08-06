package ifesunmola.earth;

import ifesunmola.codegen.StmtCodegen;
import ifesunmola.lexer.Lexer;
import ifesunmola.lexer.Token;
import ifesunmola.parser.Parser;
import ifesunmola.parser.ast_helpers.StmtList;
import ifesunmola.sanity.SanityChecker;
import ifesunmola.sanity.TypeValidator;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static ifesunmola.earth.EarthUtils.COMPILER_NAME;
import static ifesunmola.earth.EarthUtils.COMPILER_NAME_VERSION;


public class EarthMain {
	public static boolean DEBUG = false; // Can't make this final :(

	public static void main(String... args) {
		EarthUtils.validateJavaRuntime();

		CommandLine parsed;
		Options options = createOptions();
		var helpPrinter = new HelpFormatter();

		try {
			parsed = new DefaultParser().parse(options, args);
		}
		catch (ParseException e) {
			System.err.println("Error parsing command line arguments: " + e.getMessage());
			helpPrinter.printHelp(COMPILER_NAME, options);
			return;
		}

		if (parsed.hasOption("h") || args.length == 0) {
			helpPrinter.printHelp(COMPILER_NAME, options);
			return;
		}
		if (parsed.hasOption("v")) {
			System.out.println(COMPILER_NAME_VERSION);
			return;
		}

		if (parsed.hasOption("d")) DEBUG = true;

		if (args.length == 1) { // one file passed.
			Path compiledPath = compile(args[0], false);
			EarthUtils.runClassFile(compiledPath);
			try {
				Files.delete(compiledPath);
			}
			catch (IOException e) {
				System.err.println("Failed to delete class file: " + e.getMessage());
			}
		}

		else if (parsed.hasOption("compile"))
			compile(parsed.getOptionValue("compile"), true);

		else if (parsed.hasOption("run")) {
			String classFile = parsed.getOptionValue("run");
			EarthUtils.runClassFile(Path.of(classFile));
		}
		else {
			System.err.println("Invalid command: " + Arrays.toString(args));
			System.exit(1);
		}
	}

	private static Options createOptions() {
		var options = new Options();
		options.addOption("d", "debug", false,
			"Print stack trace of any error that happened during compilation");

		options.addOption("h", "help", false, "Print this help message");

		options.addOption("v", "version", false,
			"Print the version of the Earth compiler");

		options.addOption(Option.builder()
			.longOpt("compile")
			.option("c")
			.type(String.class)
			.argName(".earth file")
			.hasArg()
			.desc("Compile the given .earth file to .class file")
			.build()
		);
		options.addOption(Option.builder()
			.longOpt("run")
			.option("r")
			.type(String.class)
			.argName(".class file")
			.hasArg()
			.desc("Run the given .class file")
			.build()
		);
		return options;
	}

	/// Compiles the code at the given file path, and returns the path to the
	/// class file
	private static Path compile(String fPath, boolean printMsg) {
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
}