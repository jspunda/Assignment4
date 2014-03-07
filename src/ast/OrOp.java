package ast;

public class OrOp implements BinOp {

	@Override
	public boolean eval(boolean lo, boolean ro) {
		return lo || ro;
	}

}
