package parser.stmts;

public sealed interface Stmt permits DeclStmt, WhenStmt, FnDefStmt,
	LoopStmt, ReassignStmt, UnnamedStmt, YeetStmt {
	int line();
}
