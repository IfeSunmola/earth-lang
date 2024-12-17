package codegen2;

import codegen2.CodegenUtils.ClassVariable;
import codegen2.CodegenUtils.CurrentBuilder;
import codegen2.CodegenUtils.MethodVariable;
import earth.EarthResult;
import parser.ast_helpers.StmtList;
import parser.exprs.Expr;
import parser.stmts.*;

import java.lang.classfile.ClassBuilder;
import java.lang.classfile.ClassFile;
import java.lang.classfile.TypeKind;
import java.lang.classfile.attribute.SourceFileAttribute;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.nio.file.Path;
import java.util.ArrayList;

import static codegen2.CodegenUtils.earthTypeToDesc;
import static codegen2.CodegenUtils.earthTypeToTypeKind;
import static java.lang.classfile.ClassFile.*;
import static java.lang.constant.ConstantDescs.*;

@SuppressWarnings("preview")
public class StmtCodegen {
	private final Path srcPath;
	private final ClassDesc thisClass;
	private boolean inMethod = false;
	private ClassBuilder classBuilder;
	private CurrentBuilder currentBuilder;

	// TODO: Assert that any method named main must have no parameters
	public StmtCodegen(Path path) {
		srcPath = path;
		thisClass = ClassDesc.of(srcPath.getFileName().toString());
	}

	public EarthResult<byte[]> generate(StmtList stmts) {
		var errors = new ArrayList<String>();
		byte[] result = ClassFile.of().build(thisClass, classBuilder -> {
			this.classBuilder = classBuilder;
			var mainDesc = MethodTypeDesc.of(CD_void, CD_String.arrayType());

			classBuilder
				.with(SourceFileAttribute.of(srcPath + ".earth"))
				.withFlags(ACC_PUBLIC | ACC_FINAL)
				.withMethodBody(INIT_NAME, MTD_void, ACC_PUBLIC, builder -> builder
					.aload(0)
					.invokespecial(CD_Object, INIT_NAME, MTD_void)
					.return_()
				)
				.withMethodBody("main", mainDesc,
					ACC_PUBLIC | ACC_STATIC, builder -> {
						this.currentBuilder = new CurrentBuilder(builder, mainDesc);
						try {
							generateStmts(stmts);
						}
						catch (CodegenException e) {
							errors.add(e.getMessage());
						}
						builder.return_();
					}
				);
		});

		if (!errors.isEmpty()) return EarthResult.err(errors);
		return EarthResult.ok(result);
	}

	private void generateStmts(StmtList stmts) {
		stmts.forEach(this::generateStmt);
	}

	private void generateStmt(Stmt stmt) {
		switch (stmt) {
			case DeclStmt s -> generateDeclStmt(s);
			case FnDefStmt s -> generateFnDefStmt(s);
			case LoopStmt s -> generateLoopStmt(s);
			case ReassignStmt s -> generateReassignStmt(s);
			case UnnamedStmt s -> generateUnnamedStmt(s);
			case WhenStmt s -> generateWhenStmt(s);
			case YeetStmt s -> generateYeetStmt(s);
		}
	}

	private void generateDeclStmt(DeclStmt s) {
		String name = s.nameAndType().name().name();
		Expr toDeclare = s.value();
		ClassDesc desc = earthTypeToDesc(toDeclare.dataType());

		if (!inMethod) {
			classBuilder.withField(name, desc, ACC_STATIC);
			currentBuilder.exprCodegen.loadExpr(toDeclare);
			currentBuilder.builder.putstatic(thisClass, name, desc);

			currentBuilder.exprCodegen.classVariables
				.put(name, new ClassVariable(name, desc));
			return;
		}

		currentBuilder.exprCodegen.loadExpr(toDeclare);
		int slot = currentBuilder.slot++;
		TypeKind typeKind = earthTypeToTypeKind(toDeclare.dataType());
		currentBuilder.builder.storeLocal(typeKind, slot);

		currentBuilder.exprCodegen.methodVariables
			.put(name,
				new MethodVariable(name, toDeclare.dataType(), typeKind, slot));
	}

	private void generateFnDefStmt(FnDefStmt s) {
		throw new AssertionError("generateFnDefStmt has not been implemented");
	}

	private void generateLoopStmt(LoopStmt s) {
		throw new AssertionError("generateLoopStmt has not been implemented");
	}

	private void generateReassignStmt(ReassignStmt s) {
		throw new AssertionError("generateReassignStmt has not been implemented");
	}

	private void generateUnnamedStmt(UnnamedStmt s) {
		throw new AssertionError("generateUnnamedStmt has not been implemented");
	}

	private void generateWhenStmt(WhenStmt s) {
		throw new AssertionError("generateWhenStmt has not been implemented");
	}

	private void generateYeetStmt(YeetStmt s) {
		throw new AssertionError("generateYeetStmt has not been implemented");
	}
}
