package parsing;

import java.io.Reader;
import ast.*;

/**
 * Parseer klasse voor propositielogica
 * @author Sjaak Smetsers
 * @author Laurens van den Bercken, s4057384 
 * @author Jeftha Spunda, s4174615
 * boolean left geeft aan of expressie links of rechts van : (turnstile) is
 */
public class RDparser {
	private Tokenizer tokens;
	
	/**
	 * priority array die nodig is voor de operatoren
	 * globale counter die positie in de priority array aangeeft
	 */
	private boolean [] priority;
	private int counter;
	private boolean left;
	
    public RDparser ( Reader input, String s, boolean left) throws ParseError {
    	tokens = new Tokenizer( input );
		counter = 0;
		this.left = left;
		/*
		 * Creeert nieuwe prioriteiten array adhv String s
		 */
		PriorityDetermine p = new PriorityDetermine(s);
		p.determine();
		priority = p.getPriority();
	}

	public Exp parse ( ) throws ParseError {
		Exp e = parseExp ();
		if ( tokens.currentToken() == Tokenizer.Token.EOFToken ) {
			return e;
        } else {
			throw new ParseError ( "End of input expected", tokens.currentToken());
        }
	}

	private BinOp parseLowPrioOp ( ) throws ParseError {
		switch ( tokens.currentToken() ) {
        case ArrowToken:  tokens.nextToken();
                         return new ArrowOp (); 
        case DArrowToken: tokens.nextToken();
                         return new DArrowOp (); 
        case OrToken:	 tokens.nextToken();
        				 return new OrOp();
        case AndToken:	 tokens.nextToken();
		 				return new AndOp();
        case CommaToken: tokens.nextToken();
        				 if (left)
        					 return new AndOp();
        				 else
        					 return new OrOp();
        default:         return null;
		}
	}
    
	/**
	 * Parseert expressies. Houdt een counter bij die gebruikt wordt om in de priority array te kijken
	 * of je de operator mag uitvoeren met het volgende fact of nadat de rest van de expressie is uitgewerkt.
	 * @return
	 * @throws ParseError
	 */
	private Exp parseExp ( ) throws ParseError {
		Exp t = parseFact ();
		for (BinOp op = parseLowPrioOp (); op != null; op = parseLowPrioOp ()) {
			if (priority[counter]) {
				counter ++;
				t = new BinOpExp (t, parseFact (), op);
			}
			else {
				counter ++;
				t = new BinOpExp(t, parseExp(), op);	
			}
		}
		return t;
	}

	private Exp parseFact ( ) throws ParseError {
        if ( tokens.currentToken().equals(Tokenizer.Token.VarToken) ) {
        	VarExp v = new VarExp(tokens.getVarToken());
            tokens.nextToken ( );
            return v;
		}
        /*
         * Not afhandeling. Creeert nieuwe unaire expressie
         */
        else if (tokens.currentToken().equals(Tokenizer.Token.NotToken)){
        	tokens.nextToken();//Hier al aanroepen zodat parseFact() in de constructor van UnOpExp
        					   //de variabele erachter returnt.
        	UnOpExp u = new UnOpExp(parseFact(),new NotOp());
        	return u;
        }
        else {
                throw new ParseError( "Factor expected", tokens.currentToken ( ) );
		}
	}
}