package codegen2;

import codegen2.CodegenUtils.ClassVariable;
import codegen2.CodegenUtils.MethodVariable;
import parser.exprs.*;

import java.lang.classfile.CodeBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			case BinaryExpr binaryExpr -> {
			}
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

	private void loadFnCallExpr(FnCallExpr e) {
		// load all the expressions on the stack
		e.params().forEach(this::loadExpr);
		String fnName = e.name().name();
		CodegenUtils.Method method = StmtCodegen.methods.get(fnName);

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
			case IntType, BoolType -> builder.bipush((Integer.parseInt(value)));
			case StrType -> builder.ldc(value);
			case FloatType -> builder.ldc(Float.parseFloat(value));
			case NadaType -> throw new AssertionError();
			case FuncType _ -> throw new AssertionError();
		}
	}
}
