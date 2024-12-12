package codegen.jvm;

import sanity.EarthType;
import sanity.EarthType.Base;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.TypeKind;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.util.function.Consumer;

import static java.lang.constant.ConstantDescs.*;

@SuppressWarnings("preview")
class CodegenUtils {
	static final ClassDesc CD_StringBuilder =
		ClassDesc.ofInternalName("java/lang/StringBuilder");

	static final ClassDesc CD_System =
		ClassDesc.ofInternalName("java/lang/System");

	static final ClassDesc CD_PrintStream =
		ClassDesc.ofInternalName("java/io/PrintStream");

	static final Consumer<CodeBuilder.BlockCodeBuilder>
		true_ = CodeBuilder::iconst_1, false_ = CodeBuilder::iconst_0;

	static EarthType descToEarthType(ClassDesc desc) {
		return switch (desc.displayName()) {
			case "int" -> Base.INT;
			case "float" -> Base.FLOAT;
			case "String" -> Base.STRING;
			case "boolean" -> Base.BOOL;
			case "void" -> Base.VOID;
			default ->
				throw new AssertionError("Unexpected value: " + desc.displayName());
		};
	}

	static TypeKind earthTypeToTypeKind(EarthType type) {
		return switch (type) {
			case Base.INT -> TypeKind.IntType;
			case Base.FLOAT -> TypeKind.FloatType;
			case Base.STRING -> TypeKind.ReferenceType;
			case Base.BOOL -> TypeKind.BooleanType;
			case Base.VOID -> throw new AssertionError();
			case EarthType.Func _ -> throw new AssertionError();
		};
	}

	static ClassDesc earthTypeToDesc(EarthType type) {
		return switch (type) {
			case Base.INT -> CD_int;
			case Base.FLOAT -> CD_float;
			case Base.STRING -> CD_String;
			case Base.BOOL -> CD_boolean;
			case Base.VOID -> CD_void;
			case EarthType.Func _ -> throw new AssertionError();
		};
	}

	// I know it's redundant to store both types of type, but sometimes I need
	// one value, sometimes I need the other. Converting from typekind to
	// earth type works, but it breaks when trying to differentiate reference
	// types. TypeKind is from the ClassFile API
	record MethodVariable(String name, EarthType earthType, TypeKind typeKind,
	                      int slot) {}

	record ClassVariable(String name, ClassDesc type) {}

	// Getters and setters? What are thoseeeee
	static final class Method {
		final CodeBuilder builder;
		int slot; // this is literally the only reason I didn't use a record
		final ExprCodegen exprCodegen;
		final MethodTypeDesc signature;

		Method(CodeBuilder builder, int slot, MethodTypeDesc sig, String fName) {
			this.builder = builder;
			this.slot = slot;
			this.exprCodegen = new ExprCodegen(builder, fName);
			this.signature = sig;
		}

		@Override
		public String toString() {
			var result = new StringBuilder();
			result.append("Next Slot: ").append(slot);
			result.append("\nVariables:\t");
			for (var variable : exprCodegen.methodVariables) {
				result.append(variable).append(", ");
			}
			return result.toString();
		}
	}
}

