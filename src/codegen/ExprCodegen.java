package codegen;

import antlr.MoneyParser.LiteralExprContext;
import antlr.MoneyParser.RelationalExprContext;
import antlr.MoneyParserBaseVisitor;
import sanity.ExprResolver;
import sanity.MoneyType;
import sanity.MoneyType.Base;

import java.lang.classfile.CodeBuilder;
import java.lang.classfile.CodeBuilder.BlockCodeBuilder;
import java.lang.classfile.Opcode;
import java.util.function.Consumer;

@SuppressWarnings("preview")
public class ExprCodegen extends MoneyParserBaseVisitor<Void> {
	private final CodeBuilder methodBuilder;
	private final ExprResolver exprResolver = new ExprResolver();

	public ExprCodegen(CodeBuilder builder) {
		methodBuilder = builder;
	}

	/// Loads the value of the literal onto the stack
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

	/// Stack will be 0 if the comparison is false, 1 if true
	@Override
	public Void visitRelationalExpr(RelationalExprContext ctx) {
		// Lte | Gte | Lt | Gt. Only works for ints and floats
		// If either left or right is float, floating point comparison is done
		// Otherwise, integer comparison is done
		MoneyType leftType = exprResolver.visit(ctx.left);
		MoneyType rightType = exprResolver.visit(ctx.right);

		Consumer<BlockCodeBuilder> setTrue = CodeBuilder::iconst_1;
		Consumer<BlockCodeBuilder> setFalse = CodeBuilder::iconst_0;

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
}
