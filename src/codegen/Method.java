package codegen;

import sanity.MoneyType;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.TypeKind;

// I know it's redundant to store both types of type, but sometimes I need
// one value and sometimes I need the other. Converting from typekind to
// money type works, but it breaks when trying to differentiate reference
// types
// TypeKind is from the ClassFile API
@SuppressWarnings("preview")
record Variable(String name, MoneyType moneyType, TypeKind typeKind,
                int slot) {}

// Getters and setters? What are thoseeeee
@SuppressWarnings("preview")
final class Method {
	final CodeBuilder builder;
	int slot;
	final ExprCodegen exprCodegen;

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
