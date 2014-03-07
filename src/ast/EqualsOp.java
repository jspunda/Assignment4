package ast;

public class EqualsOp implements BinOp {

	@Override
	public boolean eval(boolean lo, boolean ro) {
		return lo == ro;
	}
}
