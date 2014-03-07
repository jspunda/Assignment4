package ast;

/**
 *
 * @author Sjaak Smetsers
 * @version 1.1 -- 06-03-2013
 */
public class PlusOp implements BinOp {
	public int eval(int lo, int ro) {
		return lo + ro;
	}
}
