package ast;
/**
 * Klasse voor unaire operatie
 * @author Laurens van den Bercken, s4057384 
 * @author Jeftha Spunda, s4174615
 */
public class UnOpExp extends Exp{
	
	private UnOp op;
	private Exp o;
	
	public UnOpExp(Exp o, UnOp op) {
		this.op = op;
		this.o = o;
	}
	
	public int eval() {
		return op.eval(o.eval());
	}
}
