package ast;

/**
 *
 * @author Sjaak Smetsers
 * @version 1.1 -- 02-03-2013
 */
public class NumExp extends Exp {
	private int num;

	public NumExp (int num) {
		this.num = num;
	}

	public int eval () {
		return num;
	}
}
