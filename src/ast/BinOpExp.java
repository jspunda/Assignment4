package ast;

/**
 *
 * @author Sjaak Smetsers
 * @version 1.1 -- 02-03-2013
 */
public class BinOpExp extends Exp {
	private Exp lo, ro;
	private BinOp op;

	public BinOpExp (Exp lo, Exp ro, BinOp op) {
		this.lo = lo;
		this.ro = ro;
		this.op = op;
	}

	public boolean eval () {
		return op.eval(lo.eval(),ro.eval());
	}

}
