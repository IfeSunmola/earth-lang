lexer grammar MoneyLexer;

Whitespace: [ \t\r\n]+ -> skip;
SingleLineComment: '//' ~[\r\n]* -> skip;
MultiLineComment: '/*' .*? '*/' -> skip;

// Keywords
Let: 'let';
Var: 'var';
When: 'when';
Else: 'else';
Fn: 'fn';
Yeet: 'yeet';
Unnamed: '_';
Loop: 'loop';
// Identifiers/Literals
StrLit: '"' .*? '"';
IntLit: [0-9]+;
FloatLit: [0-9]+'.'[0-9]+;
BoolLit: 'true' | 'false';
UntypedIdent: [a-zA-Z][a-zA-Z0-9]*;
// Operators
Eq: '=';
Gt: '>';
Lt: '<';
Gte: '>=';
Lte: '<=';
EqEq: '==';
BangEq: '!=';
Plus: '+';
Minus: '-';
Star: '*';
Slash: '/';
Mod: '%';
Bang: '!';
And: '&&';
Or: '||';
// Delimiters
Colon: ':';
Comma: ',';
LParen: '(';
RParen: ')';
LBrace: '{';
RBrace: '}';