package ast;

public class VarExp extends Exp {

	private boolean c;
	
	public VarExp (boolean c) {
		this.c = c;
	}

	public boolean eval() {
		return c;
	}
	
}
