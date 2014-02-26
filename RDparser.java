package parsing;

import java.io.Reader;
import ast.*;

/**
 *
 * @author Sjaak Smetsers
 * @version 1.1 -- 02-03-2013
 */
public class RDparser {
	private Tokenizer tokens;

    public RDparser ( Reader input ) throws ParseError {
		tokens = new Tokenizer( input );
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
        case PlusToken:  tokens.nextToken();
                         return new PlusOp ();
        case MinusToken: tokens.nextToken();
                         return new MinOp ();
        default:         return null;
		}
	}
    
	private Exp parseExp ( ) throws ParseError {
		Exp t = parseFact ();
		for (BinOp op = parseLowPrioOp (); op != null; op = parseLowPrioOp ())
			t = new BinOpExp (t, parseFact (), op);
		return t;
	}

	private Exp parseFact ( ) throws ParseError {
        if ( tokens.currentToken().equals(Tokenizer.Token.NumberToken) ) {
            NumExp ne = new NumExp (tokens.getNumber());
            tokens.nextToken ( );
            return ne;
		} else {
                throw new ParseError( "Factor expected", tokens.currentToken ( ) );
		}
	}
}
