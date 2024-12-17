package codegen2;

import sanity2.NEarthType;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.TypeKind;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.util.function.Consumer;

import static java.lang.constant.ConstantDescs.*;
import static sanity2.NEarthType.Base;
import static sanity2.NEarthType.FuncType;

@SuppressWarnings("preview")
public class CodegenUtils {
	static final ClassDesc CD_StringBuilder =
		ClassDesc.ofInternalName("java/lang/StringBuilder");

	static final ClassDesc CD_System =
		ClassDesc.ofInternalName("java/lang/System");

	static final ClassDesc CD_PrintStream =
		ClassDesc.ofInternalName("java/io/PrintStream");

	static final Consumer<CodeBuilder.BlockCodeBuilder>
		true_ = CodeBuilder::iconst_1, false_ = CodeBuilder::iconst_0;

	record ClassVariable(String name, ClassDesc type, ClassDesc owner) {}

	record MethodVariable(String name, NEarthType earthType, TypeKind typeKind,
	                      int slot) {}

	static final class Method {
		final CodeBuilder builder;
		int slot; // this is literally the only reason I didn't use a record
		final ExprCodegen exprCodegen;
		final MethodTypeDesc signature;
		final ClassDesc owner;

		Method(CodeBuilder builder, MethodTypeDesc sig, ClassDesc owner) {
			this.builder = builder;
			this.slot = sig.parameterCount();
			this.exprCodegen = new ExprCodegen(builder);
			this.signature = sig;
			this.owner = owner;
		}
	}

	static ClassDesc earthTypeToDesc(NEarthType type) {
		return switch (type) {
			case Base.IntType -> CD_int;
			case Base.FloatType -> CD_float;
			case Base.StrType -> CD_String;
			case Base.BoolType -> CD_boolean;
			case Base.NadaType -> CD_void;
			case FuncType _ -> throw new AssertionError();
		};
	}

	static TypeKind earthTypeToTypeKind(NEarthType type) {
		return switch (type) {
			case Base.IntType -> TypeKind.IntType;
			case Base.FloatType -> TypeKind.FloatType;
			case Base.StrType -> TypeKind.ReferenceType;
			case Base.BoolType -> TypeKind.BooleanType;
			case Base.NadaType -> throw new AssertionError();
			case FuncType _ -> throw new AssertionError();
		};
	}
}
