package ast;

public class DArrowOp implements BinOp{

	@Override
	public boolean eval(boolean lo, boolean ro) {
		return !(lo^ro);
	}

}
