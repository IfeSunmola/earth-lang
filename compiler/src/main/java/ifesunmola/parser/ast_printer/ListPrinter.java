package ifesunmola.parser.ast_printer;



record ListPrinter(String name,
                   AstPrinter... children) implements AstPrinter {
	@Override
	public String stringify(String indent, boolean isLast) {
		StringBuilder result = new StringBuilder();
		String prefix = isLast ? "└─" : "├─";
		result.append(indent).append(prefix).append(name).append("\n");

		String childIndent = indent + (isLast ? "  " : "│ ");
		for (int i = 0; i < children.length; i++) {
			boolean lastChild = (i == children.length - 1);
			result.append(children[i].stringify(childIndent, lastChild));
		}
		return result.toString();
	}
}