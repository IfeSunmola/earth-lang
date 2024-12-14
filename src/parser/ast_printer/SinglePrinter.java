package parser.ast_printer;

record SinglePrinter(String key,
                     AstPrinter toPrint) implements AstPrinter {
	@Override
	public String stringify(String indent, boolean isLast) {
		String prefix = isLast ? "└─" : "├─";
		return indent + prefix + key + "\n" +
		       toPrint.stringify(indent + (isLast ? "   " : "│  "), true);
	}
}
