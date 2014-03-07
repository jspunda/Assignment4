package ast;

public class OrOp implements BinOp {

	/**
	 * Or operatie
	 */	

	@Override
	public boolean eval(boolean lo, boolean ro) {
		return lo || ro;
	}

}
