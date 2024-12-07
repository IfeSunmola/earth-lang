package codegen;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.TypeKind;

@SuppressWarnings("preview")
record Variable(String name, TypeKind type, int slot) {}

// Getters and setters? What are thoseeeee
@SuppressWarnings("preview")
final class Method {
	CodeBuilder builder;
	int slot;
	ExprCodegen exprCodegen;

	Method(CodeBuilder builder, int slot) {
		this.builder = builder;
		this.slot = slot;
		this.exprCodegen = new ExprCodegen(builder);
	}

	@Override
	public String toString() {
		var result = new StringBuilder();
		result.append("Next Slot: ").append(slot);
		result.append("\nVariables:\t");
		for (var variable : exprCodegen.variables) {
			result.append(variable).append(", ");
		}
		return result.toString();
	}
}
