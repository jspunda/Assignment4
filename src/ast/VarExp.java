package ast;

public class VarExp extends Exp {

	private char c;
	
	public VarExp (char c) {
		this.c = c;
	}

	public int eval() {
		return c;
	}
	
}
