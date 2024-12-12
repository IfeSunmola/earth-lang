package parser.stmts;

public sealed interface Stmt permits DeclStmt, ElseWhenStmt, FnDefStmt,
	LoopStmt, ReassignStmt, UnnamedStmt, YeetStmt {
	int line();
}
