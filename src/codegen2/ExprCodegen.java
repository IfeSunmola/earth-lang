package codegen2;

import lexer.TokenType;
import parser.exprs.*;
import sanity2.NEarthType;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.Opcode;
import java.lang.constant.MethodTypeDesc;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static codegen2.CodegenUtils.*;
import static java.lang.constant.ConstantDescs.*;
import static lexer.TokenType.*;
import static parser.exprs.BinaryExpr.*;
import static sanity2.NEarthType.Base.*;

/// This class simply loads the expression onto the stack, and returns the
/// type of the expression that was loaded
@SuppressWarnings("preview")
class ExprCodegen {
	private final CodeBuilder builder;
	static final Map<String, ClassVariable> classVariables = new HashMap<>();
	final Map<String, MethodVariable> methodVariables;
	private static final List<String> builtIdents = List.of("true", "false");

	public ExprCodegen(CodeBuilder builder) {
		this.builder = builder;
		this.methodVariables = new HashMap<>();
	}

	void loadExpr(Expr expr) {
		switch (expr) {
			case FnCallExpr e -> loadFnCallExpr(e);
			case AdditiveExpr e -> loadAdditiveExpr(e);
			case EqualityExpr e -> loadEqualityExpr(e);
			case LogicalExpr e -> loadLogicalExpr(e);
			case ProductExpr e -> loadProductExpr(e);
			case RelationalExpr e -> loadRelationalExpr(e);
			case GroupedExpr e -> loadExpr(e.expr());
			case IdentExpr e -> loadIdentExpr(e);
			case LitExpr e -> loadLitExpr(e);
			case NegExpr e -> {
				loadExpr(e.expr());
				if (e.dataType() == IntType) builder.ineg();
				else builder.fneg();
			}
			case NotExpr e -> {
				loadExpr(e.expr());
				builder.iconst_1().ixor();
			}
		}
	}

	private void loadRelationalExpr(RelationalExpr e) {
		NEarthType leftType = e.left().dataType();
		NEarthType rightType = e.right().dataType();

		if (leftType == IntType && rightType == IntType) {
			loadExpr(e.left());
			loadExpr(e.right());
			Opcode op = switch (e.op()) {
				case Gt -> Opcode.IF_ICMPGT;
				case Gte -> Opcode.IF_ICMPGE;
				case Lt -> Opcode.IF_ICMPLT;
				case Lte -> Opcode.IF_ICMPLE;
				default -> throw new AssertionError("Should not happen: " + e);
			};
			builder.ifThenElse(op, true_, false_);
			return;
		}

		// One of the operands is a float
		loadExpr(e.left());
		if (leftType == IntType) builder.i2f();

		loadExpr(e.right());
		if (rightType == IntType) builder.i2f();

		switch (e.op()) {
			case Lt -> builder.fcmpg().ifThenElse(Opcode.IFLT,
				true_,
				false_
			);
			case Gt -> builder.fcmpl().ifThenElse(Opcode.IFGT,
				true_,
				false_
			);
			case Lte -> builder.fcmpg().ifThenElse(Opcode.IFLE,
				true_,
				false_
			);
			case Gte -> builder.fcmpl().ifThenElse(Opcode.IFGE,
				true_,
				false_
			);
		}
	}

	/// `*`, `/`, `%` are only valid on integers and floats, and results in an
	/// integer or a float, depending on the combination.\
	/// Any operation involving a float results in a float.\
	private void loadProductExpr(ProductExpr e) {
		NEarthType leftType = e.left().dataType();
		TokenType op = e.op();
		NEarthType rightType = e.right().dataType();

		if (leftType == IntType && rightType == IntType) {
			loadExpr(e.left());
			loadExpr(e.right());
			switch (op) {
				case Star -> builder.imul();
				case Slash -> builder.idiv();
				case Mod -> builder.irem();
			}
			return;
		}

		// one or both of the operands are floats
		loadExpr(e.left());
		if (leftType == IntType) builder.i2f();

		loadExpr(e.right());
		if (rightType == IntType) builder.i2f();

		switch (op) {
			case Star -> builder.fmul();
			case Slash -> builder.fdiv();
			case Mod -> builder.frem();
		}
	}

	private void loadLogicalExpr(LogicalExpr e) {
		loadExpr(e.left());
		// recall that IFEQ compares with 0
		if (e.op() == And) {
			builder.ifThenElse(
				Opcode.IFEQ,
				false_,
				elseBlock -> {
					loadExpr(e.right());
					elseBlock.ifThenElse(
						Opcode.IFEQ,
						false_,
						true_
					);
				});
		}
		else if (e.op() == Or) {
			builder.ifThenElse(
				Opcode.IFEQ,
				ifBlock -> {
					loadExpr(e.right());
					ifBlock.ifThenElse(
						Opcode.IFEQ,
						false_,
						true_
					);
				},
				true_
			);
		}
		else throw new AssertionError("Should not happen: " + e);
	}

	/// `==` and `!=` are valid on the same types. E.g. int == int, float ==
	/// float. An int can also be compared to a float. The result is a boolean.
	/// or an exception is thrown.
	private void loadEqualityExpr(EqualityExpr e) {
		boolean isEquals = e.op() == EqEq;
		NEarthType leftType = e.left().dataType();
		NEarthType rightType = e.right().dataType();

		if (leftType == rightType) {
			loadExpr(e.left());
			loadExpr(e.right());
			switch (leftType) {
				case IntType, BoolType -> {
					Opcode opcode = isEquals ? Opcode.IF_ICMPEQ : Opcode.IF_ICMPNE;
					builder.ifThenElse(opcode, true_, false_);
				}
				case FloatType -> {
					Opcode opcode = isEquals ? Opcode.IFEQ : Opcode.IFNE;
					builder.fcmpl().ifThenElse(opcode, true_, false_);
				}
				case StrType -> {
					builder.invokevirtual(CD_String, "equals",
						MethodTypeDesc.of(CD_boolean, CD_Object));

					// xor with 1 to get the opposite of the result if op is !=
					if (!isEquals) builder.iconst_1().ixor();
				}
				default -> throw new AssertionError("Should not happen: " + leftType);
			}
			return;
		}

		// One of the operands is a float
		loadExpr(e.left());
		if (leftType == IntType) builder.i2f();

		loadExpr(e.right());
		if (rightType == IntType) builder.i2f();

		Opcode opcode = isEquals ? Opcode.IFEQ : Opcode.IFNE;
		builder.fcmpl().ifThenElse(opcode, true_,
			false_);
	}

	/// Everything except the following are invalid:\
	/// int (+ | -) int = int\
	/// int (+ | -) float = float\
	/// str + str = str
	private void loadAdditiveExpr(AdditiveExpr e) {
		NEarthType leftType = e.left().dataType();
		NEarthType rightType = e.right().dataType();

		Consumer<TokenType> doNumbersOp = op -> {
			if (leftType == IntType && rightType == IntType) {
				loadExpr(e.left());
				loadExpr(e.right());
				if (op == PLus) builder.iadd();
				else builder.isub();
				return;
			}
			// One of the operands is a float
			loadExpr(e.left());
			if (leftType == IntType) builder.i2f();

			loadExpr(e.right());
			if (rightType == IntType) builder.i2f();

			if (op == PLus) builder.fadd();
			else builder.fsub();
		};

		switch (e.op()) {
			case PLus -> {
				if (leftType == StrType && rightType == StrType) {
					builder
						.new_(CD_StringBuilder)
						.dup()
						.invokespecial(CD_StringBuilder, INIT_NAME, MTD_void);

					loadExpr(e.left());
					builder.invokevirtual(CD_StringBuilder, "append",
						MethodTypeDesc.of(CD_StringBuilder, CD_String));

					loadExpr(e.right());
					builder.invokevirtual(CD_StringBuilder, "append",
						MethodTypeDesc.of(CD_StringBuilder, CD_String));

					builder.invokevirtual(CD_StringBuilder, "toString",
						MethodTypeDesc.of(CD_String));
				}
				else doNumbersOp.accept(PLus);
			}
			case Minus -> doNumbersOp.accept(Minus);
			default -> throw new AssertionError("Should not happen: " + e.op());
		}
	}

	private void loadFnCallExpr(FnCallExpr e) {
		// load all the expressions on the stack
		e.params().forEach(this::loadExpr);
		String fnName = e.name().name();
		Method method = StmtCodegen.methods.get(fnName);

		builder.invokestatic(method.owner, fnName, method.signature);
	}

	private void loadIdentExpr(IdentExpr e) {
		String name = e.name();

		if (builtIdents.contains(name)) {
			switch (name) {
				case "true" -> builder.iconst_1();
				case "false" -> builder.iconst_0();
			}
			return;
		}

		MethodVariable methodVar = methodVariables.get(name);

		if (methodVar == null) {
			ClassVariable classVar = classVariables.get(name);
			if (classVar == null)
				throw new AssertionError("Unknown identifier: " + e);
			builder.getstatic(classVar.owner(), classVar.name(), classVar.type());
		}
		else {
			builder.loadLocal(methodVar.typeKind(), methodVar.slot());
		}
	}

	private void loadLitExpr(LitExpr e) {
		String value = e.value();

		switch (e.dataType()) {
			case IntType -> builder.ldc((Integer.parseInt(value)));
			// Could probably add it to IntType but eh
			case BoolType -> builder.bipush((value.equals("true") ? 1 : 0));
			case StrType -> builder.ldc(value);
			case FloatType -> builder.ldc(Float.parseFloat(value));
			case NadaType -> throw new AssertionError();
			case FuncType _ -> throw new AssertionError();
		}
	}
}
