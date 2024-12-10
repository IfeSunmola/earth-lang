package codegen.jvm;

import java.lang.constant.ClassDesc;

public class CodegenUtils {
	static final ClassDesc CD_StringBuilder =
		ClassDesc.ofInternalName("java/lang/StringBuilder");

	static final ClassDesc CD_System =
		ClassDesc.ofInternalName("java/lang/System");

	static final ClassDesc CD_PrintStream =
		ClassDesc.ofInternalName("java/io/PrintStream");
}
