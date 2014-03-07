package ast;

public class AndOp implements BinOp {

	/**
	 * And klasse
	 */	

	@Override
	public boolean eval(boolean lo, boolean ro) {
		return lo && ro;
	}

}
