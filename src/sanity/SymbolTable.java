package sanity;

import earth.EarthUtils;

import java.util.*;
import java.util.stream.Stream;

import static sanity.Kind.Builtin;
import static sanity.Kind.FnDecl;
import static sanity.EarthType.Base.*;

enum Kind {VarDecl, FnDecl, Builtin}

record Symbol(String name, int declaredOn, Kind kind, EarthType type) {}

enum SymbolTable {
	instance;
	// Stack class is synchronized, So ArrayDeque, and operations on the last
	// element is preferred
	private final Stack<Map<String, Symbol>> scopes;

	SymbolTable() {
		scopes = new Stack<>();
		enterScope();
		Stream.of(EarthType.Base.values())
			.forEach(t -> addSymbol(t.type, 0, Builtin, t));

		addSymbol("true", 0, Builtin, BoolType);
		addSymbol("false", 0, Builtin, BoolType);

		addSymbol("print", 0, FnDecl, new FuncType(List.of(StrType), NadaType));
		addSymbol("println", 0, FnDecl, new FuncType(List.of(StrType), NadaType));

		addSymbol("intToStr", 0, FnDecl, new FuncType(List.of(IntType), StrType));
		addSymbol("floatToStr", 0, FnDecl, new FuncType(List.of(FloatType),
			StrType));
		addSymbol("boolToStr", 0, FnDecl, new FuncType(List.of(BoolType),
			StrType));
	}


	void enterScope() {
		scopes.push(new HashMap<>());
	}

	void exitScope() {
		EarthUtils.ensure(scopes.size() > 1, "Cannot exit the global scope");
		scopes.pop();
	}

	void addSymbol(String name, int declaredOn, Kind kind,
	               EarthType type) {
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
