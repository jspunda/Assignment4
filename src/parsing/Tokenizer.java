package parsing;


import java.io.IOException;
import java.io.Reader;

import parsing.ParseError;


/**
 * Tokenizer klasse. Bekijkt input en zet deze om naar overeenkomstige tokens
 * @author Sjaak Smetsers
 * @author Laurens van den Bercken, s4057384 
 * @author Jeftha Spunda, s4174615
 */
public class Tokenizer
{
	
	public enum Token {
		EOFToken    ("<EOF>"),
		ArrowToken(">"),
		DArrowToken("<>"),
		AndToken("&"),
		OrToken("|"),
		VarToken("<var>"),
		CommaToken(","),
		NotToken("~");

		public final String tokenString;

		private Token (String str) {
			tokenString = str;
		}

	}	
	private static final int EOF_CHAR = -1;
	private Reader input;
    private int currentChar;

	private Token currentToken;

    private int varToken;

	public Tokenizer ( Reader input ) throws ParseError {
		this.input = input;
		try {
			currentChar = input.read();
			skipLayout ();
		} catch (IOException ex) {
            throw new ParseError ( "IOException: " + ex);
		}
		nextToken ();
	}

	public void nextToken () throws ParseError {
		try {
			scan();
			skipLayout();
		} catch (IOException ex) {
            throw new ParseError ( "IOException: " + ex);
		}
	}

	public Token currentToken () {
		return currentToken;
	}

	private void skipLayout () throws IOException {
		while (currentChar != EOF_CHAR && Character.isWhitespace (currentChar)) {
			currentChar = input.read();
        }
	}
	
	public boolean getVarToken() {
		return (char) varToken == '1';
	}
	
	private void scan () throws IOException, ParseError {
		if ( currentChar == EOF_CHAR)
			currentToken = Token.EOFToken;
		else {
			switch ( currentChar ) {
            case '>': currentChar = input.read();
                      currentToken = Token.ArrowToken;
                      break;
            case '<': currentChar = input.read();
            		  if (currentChar != 62) //62 is Ascii code voor ">"
            			  throw new ParseError ( "Illegal input character: " + ((char) currentChar));
            		  currentChar = input.read();
                      currentToken = Token.DArrowToken;
                      break;
            case '&': currentChar = input.read();
            		  currentToken = Token.AndToken;
            	      break;
            case '|': currentChar = input.read();
                      currentToken = Token.OrToken;
                      break;
            case ',': currentChar = input.read();
            		  currentToken = Token.CommaToken;
            		  break;
            case'~':  currentChar = input.read();
            		  currentToken = Token.NotToken;
            		  break;
            default:
                if ( is01((char) currentChar)) {
                    varToken = currentChar;
                    currentChar = input.read();
                	currentToken = Token.VarToken;
                }
                else {
                	
                	throw new ParseError ( "Illegal input character: " + ((char) currentChar));
                }
			}
		}
	}
	
	private boolean is01(char currentchar) {
		if (currentchar == '1' || currentchar == '0' ) {
			return true;
		}
		else
			return false;
	}
}
