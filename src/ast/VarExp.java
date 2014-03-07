package ast;

public class VarExp extends Exp {

	/**
	 * Klasse voor variabelen
	 */

	private boolean c;
	
	public VarExp (boolean c) {
		this.c = c;
	}

	public boolean eval() {
		return c;
	}
	
}
