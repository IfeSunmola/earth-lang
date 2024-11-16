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
letStmt: Let typedIdentExpr Equal expr ;
varStmt
    : Var typedIdentExpr Equal expr #varWithInit
    | Var typedIdentExpr #varWithoutInit
    ;
reassignStmt: untypedIdentExpr Equal expr;
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
unnamedStmt: Unnamed Equal expr;
// In sanity checker, assert that the varStmt is initialized, and expr is a bool
loopStmt:
    Loop varStmt Comma expr Comma reassignStmt LBrace stmtList RBrace
    ;
// expressions
expr
    : typedIdentExpr
    | untypedIdentExpr
    | literals
    | mathExpr
    | comparisonExpr
    | logicalExpr
    | unaryExpr
    | fnCallExpr
    ;
exprList: (expr (Comma expr)* Comma?)?;
// identifiers
typedIdentExpr: UntypedIdent Colon UntypedIdent;
typedIdentList: (typedIdentExpr (Comma typedIdentExpr)* Comma?)?;
untypedIdentExpr: UntypedIdent;

// literals
literals: StrLit | IntLit | FloatLit;
// I don't have a better name for these but these are expressions that can be
// used in math operations, comparision, logical, and unary operations. The
// Sanity checker will handle any mismatch
specialExpr
    : literals
    | untypedIdentExpr
    ;
mathExpr: specialExpr (Plus | Minus | Star | Slash | Mod) specialExpr;
comparisonExpr: specialExpr (Gt | Lt | Gte | Lte | EqEq | BangEq) specialExpr;
logicalExpr: specialExpr (And | Else) specialExpr;
unaryExpr: Bang specialExpr;

fnCallExpr: UntypedIdent LParen exprList RParen;