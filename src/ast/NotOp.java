package ast;

public class NotOp implements UnOp{

	/**
	 * not operatie (unair)
	 */	

	@Override
	public boolean eval(boolean o) {
		return !o;
	}

}
