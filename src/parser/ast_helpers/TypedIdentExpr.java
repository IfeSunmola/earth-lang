package parser.ast_helpers;

import parser.exprs.UntypedIdentExpr;

public record TypedIdentExpr(UntypedIdentExpr name, UntypedIdentExpr type) {}
