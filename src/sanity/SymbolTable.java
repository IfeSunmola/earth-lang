package sanity;

import money.MoneyUtils;

import java.util.*;

import static sanity.MoneyType.Base.*;

enum Kind {MutDecl, ImmutDecl, Function, Builtin}

record Symbol(String name, int declaredOn, Kind kind, MoneyType type) {
}

public enum SymbolTable {
	instance;
	// Stack of symbol tables, with the top being the current scope
	// Each stack contains a map of symbol names to symbols
	private final Stack<Map<String, Symbol>> scopes;
	private int scopeNum = 0; // only for debugging.

	SymbolTable() {
		scopes = new Stack<>();
		enterScope();
		addSymbol(INT.toString(), 0, Kind.Builtin, INT);
		addSymbol(FLOAT.toString(), 0, Kind.Builtin, FLOAT);
		addSymbol(STRING.toString(), 0, Kind.Builtin, STRING);
		addSymbol(BOOL.toString(), 0, Kind.Builtin, BOOL);
		addSymbol(VOID.toString(), 0, Kind.Builtin, VOID);

		addSymbol("intToStr", 0, Kind.Function, new Func(List.of(INT), STRING));
		addSymbol("floatToStr", 0, Kind.Function, new Func(List.of(FLOAT),
			STRING));
		addSymbol("boolToStr", 0, Kind.Function, new Func(List.of(BOOL), STRING));
		addSymbol("print", 0, Kind.Function, new Func(List.of(STRING), VOID));
		addSymbol("println", 0, Kind.Function, new Func(List.of(STRING), VOID));
	}

	void enterScope() {
		scopeNum++;
		scopes.push(new HashMap<>());
		System.out.println("Entered scope " + scopeNum);
	}

	void exitScope() {
		MoneyUtils.ensure(scopes.size() > 1, "Cannot exit the global scope");
		System.out.println("Exiting scope " + scopeNum);
		System.out.println(this);
		System.out.println("----------------------------------");
		scopes.pop();
	}

	/// Method assumes that the caller has done the necessary checks
	void addSymbol(String name, int declaredOn, Kind kind, MoneyType type) {
		scopes.peek().put(name, new Symbol(name, declaredOn, kind, type));
	}

	/// Says "find in all" but it just checks all scopes below the current one
	Optional<Symbol> findInAllScopes(String name) {
		for (int i = scopes.size() - 1; i >= 0; i--) {
			Symbol symbol = scopes.get(i).get(name);
			if (symbol != null) return Optional.of(symbol);
		}
		return Optional.empty();
	}

	Optional<Symbol> findInCurrentScope(String name) {
		return Optional.ofNullable(scopes.peek().get(name));
	}

	@Override
	public String toString() {
		var result = new StringBuilder();
		for (int i = scopes.size() - 1; i >= 0; i--) {
			result.append("Scope ").append(i).append(":\n");
			Map<String, Symbol> currentScope = scopes.get(i);

			for (Symbol s : currentScope.values()) {
				result.append("\t").append(s).append("\n");
			}
		}
		return result.toString();
	}
}
