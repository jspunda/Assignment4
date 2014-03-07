package ast;

/**
 *
 * @author Sjaak Smetsers
 * @version 1.1 -- 02-03-2013
 */
public class MinOp implements BinOp {
	public int eval(int lo, int ro) {
		return lo - ro;
	}
}
