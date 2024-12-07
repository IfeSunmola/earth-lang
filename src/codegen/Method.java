package codegen;

import sanity.MoneyType;

import java.lang.classfile.CodeBuilder;
import java.util.ArrayList;
import java.util.List;

record Variable(String name, MoneyType type, int slot) {}

// Getters and setters? What are thoseeeee
@SuppressWarnings("preview")
final class Method {
	CodeBuilder builder;
	int slot;
	final List<Variable> variables;
	ExprCodegen exprCodegen;

	Method(CodeBuilder builder, int slot) {
		this.builder = builder;
		this.slot = slot;
		variables = new ArrayList<>();
		this.exprCodegen = new ExprCodegen(builder);
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
