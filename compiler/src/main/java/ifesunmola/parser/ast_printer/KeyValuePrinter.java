package ifesunmola.parser.ast_printer;



record KeyValuePrinter(String key, String value) implements AstPrinter {
	@Override
	public String stringify(String indent, boolean isLast) {
		String prefix = isLast ? "└─" : "├─";
		if (value.isEmpty()) {
			return indent + prefix + key + "\n";
		}
		return indent + prefix + key + ": " + value + "\n";
	}
}