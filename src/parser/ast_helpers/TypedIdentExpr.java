package parser.ast_helpers;

import parser.exprs.IdentExpr;

public record TypedIdentExpr(IdentExpr name, IdentExpr type) {}
