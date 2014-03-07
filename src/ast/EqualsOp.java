package ast;

public class EqualsOp implements BinOp {

	/**
	 * Turnstile klasse
	 *
	 */
	
	@Override
	public boolean eval(boolean lo, boolean ro) {
		return lo == ro;
	}
}
