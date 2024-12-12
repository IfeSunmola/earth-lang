package codegen.jvm;

import antlr.EarthParser.*;
import antlr.EarthParserBaseVisitor;
import earth.EarthException;
import earth.EarthUtils;
import org.antlr.v4.runtime.tree.ParseTree;
import sanity.EarthType;
import sanity.EarthType.Base;
import sanity.ExprResolver;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.Opcode;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static codegen.jvm.CodegenUtils.*;
import static codegen.jvm.StmtCodeGen.methodSignatures;
import static java.lang.constant.ConstantDescs.*;

/// The main purpose of the methods in this class is to load the
/// expression onto the stack, and return the type of the expression that was
/// loaded
@SuppressWarnings("preview")
class ExprCodegen extends EarthParserBaseVisitor<EarthType> {
	private final CodeBuilder methodBuilder;
	private final ClassDesc classDesc;
	private final TypeResolver typeResolver;
	final List<Variable> variables;

	public ExprCodegen(CodeBuilder builder, String fName) {
		methodBuilder = builder;
		variables = new ArrayList<>();
		classDesc = ClassDesc.of(fName);
		typeResolver = new TypeResolver();
	}

	@Override
	public EarthType visit(ParseTree tree) {
		try {
			return super.visit(tree);
		}
		catch (EarthException e) {
			System.err.println(e.getMessage());
			System.exit(1);
			return null;
		}
	}

	@Override
	public EarthType visitNegExpr(NegExprContext ctx) {
		// -value where value is an int OR float
		EarthType type = visit(ctx.expr()); // load the value onto the stack

		if (type == Base.INT) methodBuilder.ineg();
		else methodBuilder.fneg();

		return type;
	}

	@Override
	public EarthType visitNotExpr(NotExprContext ctx) {
		// !value, where value is a boolean
		visit(ctx.expr()); // load the value onto the stack
		methodBuilder.iconst_1().ixor(); // xor with 1 to negate the value
		return Base.BOOL;
	}

	@Override
	public EarthType visitMultiplicationExpr(MultiplicationExprContext ctx) {
		// val1 op val2 where op is '*' or '/'or '%'
		// val1 and val2 are either int or float
		// If one of the operands is a float, the result is a float

		EarthType leftType = typeResolver.visit(ctx.left);
		String op = ctx.op.getText();
		EarthType rightType = typeResolver.visit(ctx.right);

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
	public EarthType visitAdditiveExpr(AdditiveExprContext ctx) {
		// val1 op val2 where op is '+' or '-'
		// val1 and val2 are either int or float
		// if op is '+', val1 AND val2 are strings, then string concat is done
		// int + float = float
		// int + int = int

		EarthType leftType = typeResolver.visit(ctx.left);
		EarthType rightType = typeResolver.visit(ctx.right);
		Function<String, EarthType> numbersOp = op -> {
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
	public EarthType visitRelationalExpr(RelationalExprContext ctx) {
		// Lte | Gte | Lt | Gt. Only works for ints and floats
		// If either left or right is float, floating point comparison is done
		// Otherwise, integer comparison is done
		// Recall that typeResolver simply returns the type WITHOUT loading the
		// value on the stack
		EarthType leftType = typeResolver.visit(ctx.left);
		EarthType rightType = typeResolver.visit(ctx.right);

		if (leftType.is(Base.FLOAT) || rightType.is(Base.FLOAT)) {
			visit(ctx.left);
			if (leftType.is(Base.INT)) methodBuilder.i2f();

			visit(ctx.right);
			if (rightType.is(Base.INT)) methodBuilder.i2f();

			switch (ctx.op.getText()) {
				case "<" -> methodBuilder.fcmpg().ifThenElse(Opcode.IFLT,
					true_,
					false_
				);
				case ">" -> methodBuilder.fcmpl().ifThenElse(Opcode.IFGT,
					true_,
					false_
				);
				case "<=" -> methodBuilder.fcmpg().ifThenElse(Opcode.IFLE,
					true_,
					false_
				);
				case ">=" -> methodBuilder.fcmpl().ifThenElse(Opcode.IFGE,
					true_,
					false_
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

		methodBuilder.ifThenElse(op, true_, false_);
		return Base.BOOL;
	}

	@Override
	public EarthType visitEqualityExpr(EqualityExprContext ctx) {
		// recall that the operands must be of the same type
		// ExprResolver would throw an error if they're not

		// load the values of the left expression onto the stack
		boolean isEquals = ctx.op.getText().equals("==");
		EarthType leftType = visit(ctx.left);
		visit(ctx.right);

		switch (leftType) {
			case Base.INT, Base.BOOL -> {
				Opcode opcode = isEquals ? Opcode.IF_ICMPEQ : Opcode.IF_ICMPNE;
				methodBuilder.ifThenElse(opcode, true_,
					false_);
			}
			case Base.FLOAT -> {
				Opcode opcode = isEquals ? Opcode.IFEQ : Opcode.IFNE;
				methodBuilder.fcmpl().ifThenElse(opcode, true_,
					false_);
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
	public EarthType visitAndExpr(AndExprContext ctx) {
		// val1 && val2, where val1 and val2 are booleans
		// if val1 is false, result is false
		// if val2 is false, result is false
		// else result is true

		visit(ctx.left);
		// recall that IFEQ compares with 0
		methodBuilder.ifThenElse(
			Opcode.IFEQ,
			false_,
			elseBlock -> {
				visit(ctx.right);
				elseBlock.ifThenElse(
					Opcode.IFEQ,
					false_,
					true_
				);
			});

		return Base.BOOL;
	}

	@Override
	public EarthType visitOrExpr(OrExprContext ctx) {
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
					false_,
					true_
				);
			},
			true_
		);
		return Base.BOOL;
	}

	@Override
	public EarthType visitGroupedExpr(GroupedExprContext ctx) {
		return visit(ctx.expr());
	}

	@Override
	public EarthType visitLiteralExpr(LiteralExprContext ctx) {
		if (ctx.FloatLit() != null) {
			methodBuilder.ldc(Float.parseFloat(ctx.FloatLit().getText()));
			return Base.FLOAT;
		}

		if (ctx.IntLit() != null) {
			methodBuilder.bipush(Integer.parseInt(ctx.IntLit().getText()));
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

		throw new AssertionError("Should not reach here");
	}

	@Override
	public EarthType visitUntypedIdentExpr(UntypedIdentExprContext ctx) {
		// Load the value of the untyped identifier onto the stack.
		Variable ident = variables.stream()
			.filter(v -> v.name().equals(ctx.UntypedIdent().getText()))
			.findFirst().orElseThrow();

		methodBuilder.loadLocal(ident.typeKind(), ident.slot());
		return ident.earthType();
	}

	@Override
	public EarthType visitFnCallExpr(FnCallExprContext ctx) {
		// load all the expressions on the stack
		ctx.exprList().expr().forEach(this::visit);

		String fnName = ctx.fnName.getText();
		MethodTypeDesc desc = methodSignatures.get(fnName);
		EarthUtils.ensure(desc != null);

		methodBuilder.invokestatic(classDesc, fnName, desc);
		return descToEarthType(desc.returnType());
	}


	/// I felt gross making this class but here's the explanation, so I don't get
	/// lost later.
	///
	/// The {@link ExprCodegen} class loads the expression on the stack, AND
	/// returns the type of the expression, but sometimes, we need to know the
	/// type of the expression, without putting it on the stack.
	///
	/// We could use {@link ExprResolver}, but because scope
	/// information is lost, variables declared in a different scope would not
	/// be found. So, here we are.
	///
	/// This class could be the same as {@link ExprResolver}, but since
	/// any type errors would have been caught before reaching this class, we can
	/// just return the type of the expression, without checking if it's valid.
	///
	/// I also tried using switch or instanceof, but for some reason, it didn't
	/// work. I compared the  {@link ExprContext#getRuleContext()} with for
	/// example, {@link NegExprContext}, but it didn't work.
	///
	/// THERE HAS TO BE A BETTER SOLUTION THAT DOESN'T INVOLVE CREATING A NEW
	/// CLASS. THIS IS A CRY FOR HELP
	private class TypeResolver extends EarthParserBaseVisitor<EarthType> {
		@Override
		public EarthType visitNegExpr(NegExprContext ctx) {
			// type of negated expression is the same as the type of the expression
			return visit(ctx.expr());
		}

		@Override
		public EarthType visitNotExpr(NotExprContext ctx) {
			return Base.BOOL;
		}

		@Override
		public EarthType visitMultiplicationExpr(MultiplicationExprContext ctx) {
			// if one of the operands is a float, then the result is a float
			EarthType leftType = visit(ctx.left);
			EarthType rightType = visit(ctx.right);

			if (leftType == Base.FLOAT || rightType == Base.FLOAT) return Base.FLOAT;
			if (leftType == Base.INT && rightType == Base.INT) return Base.INT;

			throw new AssertionError("Should not reach here");
		}

		@Override
		public EarthType visitAdditiveExpr(AdditiveExprContext ctx) {
			// Any type errors would have been caught before reaching this class.
			// So no need to be as strict as the ExprResolver
			// float + int = float
			// int + int = int
			// str + str = str
			EarthType leftType = visit(ctx.left);
			EarthType rightType = visit(ctx.right);

			if (leftType == Base.FLOAT || rightType == Base.FLOAT) return Base.FLOAT;
			if (leftType == Base.INT && rightType == Base.INT) return Base.INT;
			if (leftType == Base.STRING && rightType == Base.STRING)
				return Base.STRING;

			throw new AssertionError("Should not reach here");
		}

		@Override
		public EarthType visitRelationalExpr(RelationalExprContext ctx) {
			return Base.BOOL;
		}

		@Override
		public EarthType visitEqualityExpr(EqualityExprContext ctx) {
			return Base.BOOL;
		}

		@Override
		public EarthType visitAndExpr(AndExprContext ctx) {
			return Base.BOOL;
		}

		@Override
		public EarthType visitOrExpr(OrExprContext ctx) {
			return Base.BOOL;
		}

		@Override
		public EarthType visitGroupedExpr(GroupedExprContext ctx) {
			return visit(ctx.expr());
		}

		@Override
		public EarthType visitLiteralExpr(LiteralExprContext ctx) {
			if (ctx.FloatLit() != null) return Base.FLOAT;
			if (ctx.IntLit() != null) return Base.INT;
			if (ctx.StrLit() != null) return Base.STRING;
			if (ctx.BoolLit() != null) return Base.BOOL;

			throw new AssertionError("Should not reach here");
		}

		// THESE ARE LITERALLY THE ONLY TWO METHODS THAT ARE DIFFERENT
		@Override
		public EarthType visitUntypedIdentExpr(UntypedIdentExprContext ctx) {
			String varName = ctx.UntypedIdent().getText();
			return variables.stream()
				.filter(v -> v.name().equals(varName))
				.findFirst()
				.map(Variable::earthType)
				.orElseThrow(() -> new EarthException(
					"`%s` is not a known identifier".formatted(varName),
					ctx.getStart().getLine()
				));
		}

		@Override
		public EarthType visitFnCallExpr(FnCallExprContext ctx) {
			String fnName = ctx.fnName.getText();
			MethodTypeDesc desc = methodSignatures.get(fnName);
			EarthUtils.ensure(desc != null);
			return descToEarthType(desc.returnType());
		}
	}
}
