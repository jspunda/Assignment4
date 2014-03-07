package ast;

public class ArrowOp implements BinOp {

	/**
	 * Implicatie klasse a -> b = not a \/ b
	 */

	@Override
	public boolean eval(boolean lo, boolean ro) {
		return !lo || ro;
	}

}
