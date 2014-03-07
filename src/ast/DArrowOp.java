package ast;

public class DArrowOp implements BinOp{
	
	/**
	 * Dubbele implicatie a <-> b = not(a xor b)
	 *
	 */

	@Override
	public boolean eval(boolean lo, boolean ro) {
		return !(lo ^ ro);
	}

}
