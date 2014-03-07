package ast;

public class AndOp implements BinOp {

	@Override
	public boolean eval(boolean lo, boolean ro) {
		return lo && ro;
	}

}
