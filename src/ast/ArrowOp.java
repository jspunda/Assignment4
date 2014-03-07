package ast;

public class ArrowOp implements BinOp {

	@Override
	public boolean eval(boolean lo, boolean ro) {
		return !lo || ro;
	}

}
