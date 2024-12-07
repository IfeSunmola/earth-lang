package codegen;

import antlr.MoneyParser.*;
import antlr.MoneyParserBaseVisitor;
import sanity.ExprResolver;
import sanity.MoneyType;
import sanity.MoneyType.Base;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.CodeBuilder.BlockCodeBuilder;
import java.lang.classfile.Opcode;
import java.lang.constant.MethodTypeDesc;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.constant.ConstantDescs.*;
import static money.MoneyUtils.CD_StringBuilder;

/// The main purpose of the methods in this class is simply to load the
/// expression onto the stack, AND nothing else.
@SuppressWarnings("preview")
public class ExprCodegen extends MoneyParserBaseVisitor<Void> {
	private final CodeBuilder methodBuilder;
	private final ExprResolver exprResolver = new ExprResolver();
	private final Consumer<BlockCodeBuilder> setTrue = CodeBuilder::iconst_1;
	private final Consumer<BlockCodeBuilder> setFalse = CodeBuilder::iconst_0;
	final List<Variable> variables;

	public ExprCodegen(CodeBuilder builder) {
		methodBuilder = builder;
		variables = new ArrayList<>();
	}

	@Override
	public Void visitNegExpr(NegExprContext ctx) {
		// -value where value is an int OR float
		visit(ctx.expr()); // load the value onto the stack

		MoneyType type = exprResolver.visitNegExpr(ctx);
		if (type == Base.INT) methodBuilder.ineg();
		else methodBuilder.fneg();

		return null;
	}

	@Override
	public Void visitNotExpr(NotExprContext ctx) {
		// !value, where value is a boolean
		visit(ctx.expr()); // load the value onto the stack
		methodBuilder.iconst_1().ixor(); // xor with 1 to negate the value
		return null;
	}

	@Override
	public Void visitMultiplicationExpr(MultiplicationExprContext ctx) {
		// val1 op val2 where op is '*' or '/'or '%'
		// val1 and val2 are either int or float
		// If one of the operands is a float, the result is a float

		MoneyType leftType = exprResolver.visit(ctx.left);
		String op = ctx.op.getText();
		MoneyType rightType = exprResolver.visit(ctx.right);

		if (leftType.is(Base.INT) && rightType.is(Base.INT)) {
			visit(ctx.left);
			visit(ctx.right);
			switch (op) {
				case "*" -> methodBuilder.imul();
				case "/" -> methodBuilder.idiv();
				case "%" -> methodBuilder.irem();
			}
		}
		else {
			visit(ctx.left);
			if (leftType.is(Base.INT)) methodBuilder.i2f();

			visit(ctx.right);
			if (rightType.is(Base.INT)) methodBuilder.i2f();

			switch (op) {
				case "*" -> methodBuilder.fmul();
				case "/" -> methodBuilder.fdiv();
				case "%" -> methodBuilder.frem();
			}
		}

		return null;
	}

	@Override
	public Void visitAdditiveExpr(AdditiveExprContext ctx) {
		// val1 op val2 where op is '+' or '-'
		// val1 and val2 are either int or float
		// if op is '+', val1 AND val2 are strings, then string concat is done
		// int + float = float
		// int + int = int

		MoneyType leftType = exprResolver.visit(ctx.left);
		MoneyType rightType = exprResolver.visit(ctx.right);
		Consumer<String> numbersOp = op -> {
			if (leftType.is(Base.INT) && rightType.is(Base.INT)) {
				visit(ctx.left);
				visit(ctx.right);
				if (op.equals("+")) methodBuilder.iadd();
				else methodBuilder.isub();
			}
			else { // one of the operands is a float
				visit(ctx.left);
				if (leftType.is(Base.INT)) methodBuilder.i2f();

				visit(ctx.right);
				if (rightType.is(Base.INT)) methodBuilder.i2f();

				if (op.equals("+")) methodBuilder.fadd();
				else methodBuilder.fsub();
			}
		};
		switch (ctx.op.getText()) {
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
				}
				else numbersOp.accept("+");
			}
			case "-" -> numbersOp.accept("-");
		}

		return null;
	}

	@Override
	public Void visitRelationalExpr(RelationalExprContext ctx) {
		// Lte | Gte | Lt | Gt. Only works for ints and floats
		// If either left or right is float, floating point comparison is done
		// Otherwise, integer comparison is done
		MoneyType leftType = exprResolver.visit(ctx.left);
		MoneyType rightType = exprResolver.visit(ctx.right);

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
			return null;
		}

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
		return null;
	}

	@Override
	public Void visitEqualityExpr(EqualityExprContext ctx) {
		// recall that the operands must be of the same type
		// ExprResolver would throw an error if they're not
		MoneyType leftType = exprResolver.visit(ctx.left);
		boolean isEquals = ctx.op.getText().equals("==");

		// load the values of the left and right expressions onto the stack
		visit(ctx.left);
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
		return null;
	}

	@Override
	public Void visitAndExpr(AndExprContext ctx) {
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

		return null;
	}

	@Override
	public Void visitOrExpr(OrExprContext ctx) {
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
		return null;
	}

	@Override
	public Void visitGroupedExpr(GroupedExprContext ctx) {
		return visit(ctx.expr());
	}

	@Override
	public Void visitLiteralExpr(LiteralExprContext ctx) {
		if (ctx.FloatLit() != null)
			methodBuilder.ldc(Float.parseFloat(ctx.FloatLit().getText()));

		else if (ctx.IntLit() != null)
			methodBuilder.ldc(Integer.parseInt(ctx.IntLit().getText()));

		else if (ctx.StrLit() != null)
			methodBuilder.ldc(ctx.StrLit().getText()
				.substring(1, ctx.StrLit().getText().length() - 1)); // remove quotes

		else if (ctx.BoolLit() != null)
			if (ctx.BoolLit().getText().equals("true")) methodBuilder.iconst_1();
			else methodBuilder.iconst_0();

		else
			throw new RuntimeException("Should not reach here");

		return null;
	}

	@Override
	public Void visitUntypedIdentExpr(UntypedIdentExprContext ctx) {
		// Load the value of the untyped identifier onto the stack.
		Variable ident = variables.stream()
			.filter(v -> v.name().equals(ctx.UntypedIdent().getText()))
			.findFirst().orElseThrow();

		methodBuilder.loadLocal(ident.type(), ident.slot());
		return null;
	}
}
