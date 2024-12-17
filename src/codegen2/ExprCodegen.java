package codegen2;

import codegen2.CodegenUtils.ClassVariable;
import codegen2.CodegenUtils.MethodVariable;
import parser.exprs.*;

import java.lang.classfile.CodeBuilder;
import java.util.HashMap;
import java.util.Map;

import static sanity2.NEarthType.Base.*;

/// This class simply loads the expression onto the stack, and returns the
/// type of the expression that was loaded
@SuppressWarnings("preview")
class ExprCodegen {
	private final CodeBuilder builder;
	final Map<String, ClassVariable> classVariables;
	final Map<String, MethodVariable> methodVariables;

	public ExprCodegen(CodeBuilder builder) {
		this.builder = builder;
		this.classVariables = new HashMap<>();
		this.methodVariables = new HashMap<>();
	}

	void loadExpr(Expr expr) {
		switch (expr) {
			case FnCallExpr e -> {
			}
			case BinaryExpr binaryExpr -> {
			}
			case GroupedExpr groupedExpr -> {
			}
			case IdentExpr identExpr -> {
			}
			case LitExpr litExpr -> loadLitExpr(litExpr);
			case NegExpr negExpr -> {
			}
			case NotExpr notExpr -> {
			}
		}
	}

	private void loadLitExpr(LitExpr e) {
		String value = e.value();

		switch (e.dataType()) {
			case IntType, BoolType -> builder.bipush((Integer.parseInt(value)));
			case StrType -> builder.ldc(value);
			case FloatType -> builder.ldc(Float.parseFloat(value));
			case NadaType -> throw new AssertionError();
			case FuncType _ -> throw new AssertionError();
		}
	}
}
