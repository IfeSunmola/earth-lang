package parser.ast_helpers;

import parser.exprs.IdentExpr;

public record TypedIdent(IdentExpr name, IdentExpr type) {}
