parser grammar EarthParser;
options { tokenVocab=EarthLexer; }

program: stmtList EOF;

stmtList: stmt*;

stmt
    : declStmt
    | reassignStmt
    | whenElseStmt // if, else if, else if ... else
    | yeetStmt
    | fnDefStmt
    | unnamedStmt
    | loopStmt
    ;

// statements
declStmt: letType=(Let | Var) typedIdentExpr Eq expr;
reassignStmt: ident=UntypedIdent Eq expr;
// the condition must evaluate to a boolean. Sanity checker will enforce this
whenElseStmt: When expr LBrace
        stmtList
        RBrace
        (Else When expr LBrace stmtList RBrace)*
        (Else LBrace stmtList RBrace)?;

yeetStmt: Yeet expr;
// function definitions, optional return type, 0 or more arguments, 0 or more
// statements, trailing comma is allowed in argument list.
fnDefStmt: Fn name=UntypedIdent
    LParen params=typedIdentList RParen
    retType=UntypedIdent?
    LBrace body=stmtList RBrace;
unnamedStmt: Unnamed Eq expr;
// In sanity checker, assert that the varStmt is initialized, and expr is a bool
loopStmt:
    Loop
    initializer=declStmt Comma
    condition=expr Comma
    update=reassignStmt
    LBrace body=stmtList RBrace
    ;

// expressions. Hevaily stolen from ... erm ... inspired by:
// https://stackoverflow.com/a/15614911/18902234
expr
    : Minus expr                                        #negExpr
    | Bang expr                                         #notExpr
    | left=expr op=(Star | Slash | Mod) right=expr      #multiplicationExpr
    | left=expr op=(Plus | Minus) right=expr            #additiveExpr
    | left=expr op=(Lte | Gte | Lt | Gt) right=expr     #relationalExpr
    | left=expr op=(EqEq | BangEq) right=expr           #equalityExpr
    | left=expr And right=expr                          #andExpr
    | left=expr Or right=expr                           #orExpr
    | primary                                           #primaryExpr
    ;

primary
    : LParen expr RParen                                #groupedExpr
    | (IntLit | FloatLit | StrLit | BoolLit)            #literalExpr
    | UntypedIdent                                      #untypedIdentExpr
    | fnName=UntypedIdent LParen params=exprList RParen #fnCallExpr
    ;

//// identifiers
typedIdentExpr: name=UntypedIdent Colon type=UntypedIdent;
typedIdentList: (typedIdentExpr (Comma typedIdentExpr)* Comma?)?;
exprList: (expr (Comma expr)* Comma?)?;