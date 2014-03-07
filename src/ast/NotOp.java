package ast;

public class NotOp implements UnOp{

	@Override
	public boolean eval(boolean o) {
		return !o;
	}

}
