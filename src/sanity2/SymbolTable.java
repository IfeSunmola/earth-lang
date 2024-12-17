package sanity2;

import earth.EarthUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Stream;

import static sanity2.Kind.Builtin;
import static sanity2.NEarthType.Base.BoolType;

enum Kind {VarDecl, FnDecl, Builtin}

record Symbol(String name, int declaredOn, Kind kind, NEarthType type) {}

enum SymbolTable {
	instance;
	// Stack class is synchronized, So ArrayDeque, and operations on the last
	// element is preferred
	private final Stack<Map<String, Symbol>> scopes;

	SymbolTable() {
		scopes = new Stack<>();
		enterScope();
		Stream.of(NEarthType.Base.values())
			.forEach(t -> addSymbol(t.type, 0, Builtin, t));

		addSymbol("true", 0, Builtin, BoolType);
		addSymbol("false", 0, Builtin, BoolType);
	}


	void enterScope() {
		scopes.push(new HashMap<>());
	}

	void exitScope() {
		EarthUtils.ensure(scopes.size() > 1, "Cannot exit the global scope");
		scopes.pop();
	}

	void addSymbol(String name, int declaredOn, Kind kind,
	               NEarthType type) {
		EarthUtils.ensure(!scopes.peek().containsKey(name),
			"Symbol already exists in scope");
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

			scopes.get(i).values()
				.forEach(s -> result.append("\t").append(s).append("\n"));

		}
		return result.toString();
	}
}
