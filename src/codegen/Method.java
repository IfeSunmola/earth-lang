package codegen;

import sanity.MoneyType;

import java.lang.classfile.CodeBuilder;
import java.util.ArrayList;
import java.util.List;

record Variable(String name, MoneyType type, int slot) {}

@SuppressWarnings("preview")
final class Method {
	CodeBuilder builder;
	int slot;
	final List<Variable> variables;

	Method(CodeBuilder builder, int slot) {
		this.builder = builder;
		this.slot = slot;
		variables = new ArrayList<>();
	}

	@Override
	public String toString() {
		var result = new StringBuilder();
		result.append("Next Slot: ").append(slot);
		result.append("\nVariables:\t");
		for (var variable : variables) {
			result.append(variable).append(", ");
		}
		return result.toString();
	}
}
