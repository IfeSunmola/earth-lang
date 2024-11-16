parser grammar MoneyParser;
options { tokenVocab=MoneyLexer; }

program: stmtList EOF;

stmtList: stmt*;

stmt
    : letStmt
    | varStmt
    | reassignStmt
    | whenElseStmt // if, else if, else if ... else
    | yeetStmt
    | fnDefStmt
    | unnamedStmt
    | loopStmt
    ;

// statements
letStmt: Let typedIdentExpr Eq expr ;
varStmt
    : Var typedIdentExpr Eq expr #varWithInit
    | Var typedIdentExpr #varWithoutInit
    ;
reassignStmt: UntypedIdent Eq expr;
// the condition must evaluate to a boolean. Sanity checker will enforce this
whenElseStmt: When expr LBrace
        stmtList
        RBrace
        (Else When expr LBrace stmtList RBrace)*
        (Else LBrace stmtList RBrace)?;

yeetStmt: Yeet expr;
// function definitions, optional return type, 0 or more arguments, 0 or more
// statements, trailing comma is allowed in argument list.
fnDefStmt: Fn UntypedIdent LParen typedIdentList RParen UntypedIdent?
    LBrace stmtList RBrace;
unnamedStmt: Unnamed Eq expr;
// In sanity checker, assert that the varStmt is initialized, and expr is a bool
loopStmt:
    Loop varStmt Comma expr Comma reassignStmt LBrace stmtList RBrace
    ;

// expressions. Hevaily stolen from ... erm ... inspired by:
// https://stackoverflow.com/a/15614911/18902234
expr
    : Minus expr                           #negExpr
    | Bang expr                            #notExpr
    | expr op=(Star | Slash | Mod) expr    #multiplicationExpr
    | expr op=(Plus | Minus) expr          #additiveExpr
    | expr op=(Lte | Gte | Lt | Gt) expr   #relationalExpr
    | expr op=(EqEq | BangEq) expr           #equalityExpr
    | expr And expr                        #andExpr
    | expr Or expr                         #orExpr
    | primary                                 #primaryExpr
    ;

primary
    : LParen expr RParen #groupedExpr
    | (IntLit | FloatLit | StrLit | BoolLit)  #literalExpr
    | UntypedIdent #untypedIdentExpr
    | UntypedIdent LParen exprList RParen #fnCallExpr
    ;

//// identifiers
typedIdentExpr: UntypedIdent Colon UntypedIdent;
typedIdentList: (typedIdentExpr (Comma typedIdentExpr)* Comma?)?;
exprList: (expr (Comma expr)* Comma?)?;