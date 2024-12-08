package codegen;

import antlr.MoneyParser.*;
import antlr.MoneyParserBaseVisitor;
import money.MoneyUtils;
import sanity.MoneyType;
import sanity.MoneyType.Base;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.CodeBuilder.BlockCodeBuilder;
import java.lang.classfile.Opcode;
import java.lang.constant.MethodTypeDesc;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static codegen.CodegenUtils.CD_StringBuilder;
import static codegen.StmtCodeGen.OUTPUT_DESC;
import static codegen.StmtCodeGen.methodSignatures;
import static java.lang.constant.ConstantDescs.*;

/// The main purpose of the methods in this class is to load the
/// expression onto the stack, and return the type of the expression that was
/// loaded
@SuppressWarnings("preview")
public class ExprCodegen extends MoneyParserBaseVisitor<MoneyType> {
	private final CodeBuilder methodBuilder;
	private static final Consumer<BlockCodeBuilder> setTrue =
		CodeBuilder::iconst_1;
	private static final Consumer<BlockCodeBuilder> setFalse =
		CodeBuilder::iconst_0;
	final List<Variable> variables;

	public ExprCodegen(CodeBuilder builder) {
		methodBuilder = builder;
		variables = new ArrayList<>();
	}

	@Override
	public MoneyType visitNegExpr(NegExprContext ctx) {
		// -value where value is an int OR float
		MoneyType type = visit(ctx.expr()); // load the value onto the stack

		if (type == Base.INT) methodBuilder.ineg();
		else methodBuilder.fneg();

		return type;
	}

	@Override
	public MoneyType visitNotExpr(NotExprContext ctx) {
		// !value, where value is a boolean
		visit(ctx.expr()); // load the value onto the stack
		methodBuilder.iconst_1().ixor(); // xor with 1 to negate the value
		return Base.BOOL;
	}

	@Override
	public MoneyType visitMultiplicationExpr(MultiplicationExprContext ctx) {
		// val1 op val2 where op is '*' or '/'or '%'
		// val1 and val2 are either int or float
		// If one of the operands is a float, the result is a float

		MoneyType leftType = visit(ctx.left);
		String op = ctx.op.getText();
		MoneyType rightType = visit(ctx.right);

		if (leftType.is(Base.INT) && rightType.is(Base.INT)) {
			visit(ctx.left);
			visit(ctx.right);
			switch (op) {
				case "*" -> methodBuilder.imul();
				case "/" -> methodBuilder.idiv();
				case "%" -> methodBuilder.irem();
			}
			return Base.INT;
		}

		// one of the operands is a float
		visit(ctx.left);
		if (leftType.is(Base.INT)) methodBuilder.i2f();

		visit(ctx.right);
		if (rightType.is(Base.INT)) methodBuilder.i2f();

		switch (op) {
			case "*" -> methodBuilder.fmul();
			case "/" -> methodBuilder.fdiv();
			case "%" -> methodBuilder.frem();
		}

		return Base.FLOAT;
	}

	@Override
	public MoneyType visitAdditiveExpr(AdditiveExprContext ctx) {
		// val1 op val2 where op is '+' or '-'
		// val1 and val2 are either int or float
		// if op is '+', val1 AND val2 are strings, then string concat is done
		// int + float = float
		// int + int = int

		MoneyType leftType = visit(ctx.left);
		MoneyType rightType = visit(ctx.right);
		Function<String, MoneyType> numbersOp = op -> {
			if (leftType.is(Base.INT) && rightType.is(Base.INT)) {
				visit(ctx.left);
				visit(ctx.right);
				if (op.equals("+")) methodBuilder.iadd();
				else methodBuilder.isub();
				return Base.INT;
			}
			// one of the operands is a float
			visit(ctx.left);
			if (leftType.is(Base.INT)) methodBuilder.i2f();

			visit(ctx.right);
			if (rightType.is(Base.INT)) methodBuilder.i2f();

			if (op.equals("+")) methodBuilder.fadd();
			else methodBuilder.fsub();
			return Base.FLOAT;

		};

		return switch (ctx.op.getText()) {
			case "+" -> {
				if (leftType.is(Base.STRING) && rightType.is(Base.STRING)) {
					methodBuilder
						.new_(CD_StringBuilder)
						.dup()
						.invokespecial(CD_StringBuilder, INIT_NAME, MTD_void);

					visit(ctx.left);
					methodBuilder.invokevirtual(CD_StringBuilder, "append",
						MethodTypeDesc.of(CD_StringBuilder, CD_String));

					visit(ctx.right);
					methodBuilder.invokevirtual(CD_StringBuilder, "append",
						MethodTypeDesc.of(CD_StringBuilder, CD_String));

					methodBuilder.invokevirtual(CD_StringBuilder, "toString",
						MethodTypeDesc.of(CD_String));
					yield Base.STRING;
				}
				else yield numbersOp.apply("+");
			}
			case "-" -> numbersOp.apply("-");
			default ->
				throw new RuntimeException("Unexpected value: " + ctx.op.getText());
		};
	}

	@Override
	public MoneyType visitRelationalExpr(RelationalExprContext ctx) {
		// Lte | Gte | Lt | Gt. Only works for ints and floats
		// If either left or right is float, floating point comparison is done
		// Otherwise, integer comparison is done
		MoneyType leftType = visit(ctx.left);
		MoneyType rightType = visit(ctx.right);

		if (leftType.is(Base.FLOAT) || rightType.is(Base.FLOAT)) {
			visit(ctx.left);
			if (leftType.is(Base.INT)) methodBuilder.i2f();

			visit(ctx.right);
			if (rightType.is(Base.INT)) methodBuilder.i2f();

			switch (ctx.op.getText()) {
				case "<" -> methodBuilder.fcmpg().ifThenElse(Opcode.IFLT,
					setTrue,
					setFalse
				);
				case ">" -> methodBuilder.fcmpl().ifThenElse(Opcode.IFGT,
					setTrue,
					setFalse
				);
				case "<=" -> methodBuilder.fcmpg().ifThenElse(Opcode.IFLE,
					setTrue,
					setFalse
				);
				case ">=" -> methodBuilder.fcmpl().ifThenElse(Opcode.IFGE,
					setTrue,
					setFalse
				);
			}
			return Base.BOOL;
		}

		// both are ints
		visit(ctx.left);
		visit(ctx.right);
		Opcode op = switch (ctx.op.getText()) {
			case "<" -> Opcode.IF_ICMPLT;
			case ">" -> Opcode.IF_ICMPGT;
			case "<=" -> Opcode.IF_ICMPLE;
			case ">=" -> Opcode.IF_ICMPGE;
			default ->
				throw new IllegalStateException("Unexpected value: " + ctx.op.getText());
		};

		methodBuilder.ifThenElse(op, setTrue, setFalse);
		return Base.BOOL;
	}

	@Override
	public MoneyType visitEqualityExpr(EqualityExprContext ctx) {
		// recall that the operands must be of the same type
		// ExprResolver would throw an error if they're not

		// load the values of the left expression onto the stack
		boolean isEquals = ctx.op.getText().equals("==");
		MoneyType leftType = visit(ctx.left);
		visit(ctx.right);

		switch (leftType) {
			case Base.INT, Base.BOOL -> {
				Opcode opcode = isEquals ? Opcode.IF_ICMPEQ : Opcode.IF_ICMPNE;
				methodBuilder.ifThenElse(opcode, setTrue, setFalse);
			}
			case Base.FLOAT -> {
				Opcode opcode = isEquals ? Opcode.IFEQ : Opcode.IFNE;
				methodBuilder.fcmpl().ifThenElse(opcode, setTrue, setFalse);
			}
			case Base.STRING -> {
				// call .equals. 1st item on the stack is the left string. 2nd
				// item on
				// the stack is the right string. i.e. left.equals(right)
				methodBuilder.invokevirtual(CD_String, "equals",
					MethodTypeDesc.of(CD_boolean, CD_Object));

				// xor with 1 to negate if evaluating !=
				if (!isEquals) methodBuilder.iconst_1().ixor();
			}
			default -> throw new RuntimeException("Well Shit");
		}
		return Base.BOOL;
	}

	@Override
	public MoneyType visitAndExpr(AndExprContext ctx) {
		// val1 && val2, where val1 and val2 are booleans
		// if val1 is false, result is false
		// if val2 is false, result is false
		// else result is true

		visit(ctx.left);
		// recall that IFEQ compares with 0
		methodBuilder.ifThenElse(
			Opcode.IFEQ,
			setFalse,
			elseBlock -> {
				visit(ctx.right);
				elseBlock.ifThenElse(
					Opcode.IFEQ,
					setFalse,
					setTrue
				);
			});

		return Base.BOOL;
	}

	@Override
	public MoneyType visitOrExpr(OrExprContext ctx) {
		// val1 || val2, where val1 and val2 are booleans
		// if val1 is true, result is true
		// if val2 is true, result is true
		// else false

		visit(ctx.left);
		methodBuilder.ifThenElse(
			Opcode.IFEQ,
			ifBlock -> {
				visit(ctx.right);
				ifBlock.ifThenElse(
					Opcode.IFEQ,
					setFalse,
					setTrue
				);
			},
			setTrue
		);
		return Base.BOOL;
	}

	@Override
	public MoneyType visitGroupedExpr(GroupedExprContext ctx) {
		return visit(ctx.expr());
	}

	@Override
	public MoneyType visitLiteralExpr(LiteralExprContext ctx) {
		if (ctx.FloatLit() != null) {
			methodBuilder.ldc(Float.parseFloat(ctx.FloatLit().getText()));
			return Base.FLOAT;
		}

		if (ctx.IntLit() != null) {
			methodBuilder.ldc(Integer.parseInt(ctx.IntLit().getText()));
			return Base.INT;
		}

		if (ctx.StrLit() != null) {
			methodBuilder.ldc(ctx.StrLit().getText()
				.substring(1, ctx.StrLit().getText().length() - 1)); // remove quotes

			return Base.STRING;
		}

		if (ctx.BoolLit() != null) {
			if (ctx.BoolLit().getText().equals("true")) methodBuilder.iconst_1();
			else methodBuilder.iconst_0();
			return Base.BOOL;
		}

		throw new RuntimeException("Should not reach here");
	}

	@Override
	public MoneyType visitUntypedIdentExpr(UntypedIdentExprContext ctx) {
		// Load the value of the untyped identifier onto the stack.
		Variable ident = variables.stream()
			.filter(v -> v.name().equals(ctx.UntypedIdent().getText()))
			.findFirst().orElseThrow();

		methodBuilder.loadLocal(ident.typeKind(), ident.slot());
		return ident.moneyType();
	}

	@Override
	public MoneyType visitFnCallExpr(FnCallExprContext ctx) {
		// load all the expressions on the stack
		ctx.exprList().expr().forEach(this::visit);

		String fnName = ctx.fnName.getText();
		MethodTypeDesc desc = methodSignatures.get(fnName);
		MoneyUtils.ensure(desc != null);

		methodBuilder.invokestatic(OUTPUT_DESC, fnName, desc);
		return MoneyType.fromClassDesc(desc.returnType());
	}
}
