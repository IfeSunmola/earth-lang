package money;

import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;

import java.util.List;

//https://stackoverflow.com/a/50068645/18902234
public class TreeUtils {

	/**
	 * Platform dependent end-of-line marker
	 */
	public static final String Eol = System.lineSeparator();
	/**
	 * The literal indent char(s) used for pretty-printing
	 */
	public static final String Indents = "  ";
	private static int level;

	private TreeUtils() {
	}

	/**
	 * Pretty print out a whole tree. @link #getNodeText} is used on the node
	 * payloads to get the text
	 * for the nodes. (Derived from Trees.toStringTree(....))
	 */
	public static void toPrettyTree(final Tree t,
	                                final String[] ruleNames) {
		level = 0;
		List<String> ruleNamesList = List.of(ruleNames);

		System.out.println(process(t, ruleNamesList)
			.replaceAll("(?m)^\\s+$", "")
			.replaceAll("\\r?\\n\\r?\\n", Eol));
	}

	private static String process(final Tree t, final List<String> ruleNames) {
		if (t.getChildCount() == 0)
			return Utils.escapeWhitespace(Trees.getNodeText(t, ruleNames), false);
		StringBuilder sb = new StringBuilder();
		sb.append(lead(level));
		level++;
		String s = Utils.escapeWhitespace(Trees.getNodeText(t, ruleNames), false);
		sb.append(s).append(' ');
		for (int i = 0; i < t.getChildCount(); i++) {
			sb.append(process(t.getChild(i), ruleNames));
		}
		level--;
		sb.append(lead(level));
		return sb.toString();
	}

	private static String lead(int level) {
		StringBuilder sb = new StringBuilder();
		if (level > 0) {
			sb.append(Eol);
			sb.append(Indents.repeat(level));
		}
		return sb.toString();
	}
}
