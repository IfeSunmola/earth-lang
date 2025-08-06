package ifesunmola.parser.ast_helpers;


import ifesunmola.parser.exprs.IdentExpr;

public record TypedIdent(IdentExpr name, IdentExpr type) {}
