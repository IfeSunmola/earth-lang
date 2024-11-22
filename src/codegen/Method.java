package codegen;

import java.lang.classfile.CodeBuilder;

@SuppressWarnings("preview")
final class Method {
	CodeBuilder builder;
	int slot;

	Method(CodeBuilder builder, int slot) {
		this.builder = builder;
		this.slot = slot;
	}

	@Override
	public String toString() {
		return "Method[" +
		       "builder=" + builder + ", " +
		       "slot=" + slot + ']';
	}
}
