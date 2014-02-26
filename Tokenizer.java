package parsing;

import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author Sjaak Smetsers
 * @version 1.1 -- 02-03-2013
 */
public class Tokenizer
{
	public enum Token {
		NumberToken ("<number>"),
		PlusToken   ("+"),
		MinusToken  ("-"),
		EOFToken    ("<EOF>");

		public final String tokenString;

		private Token (String str) {
			tokenString = str;
		}

	}
    
	private static final int EOF_CHAR = -1;
	private Reader input;
    private int currentChar;

	private Token currentToken;

    private int numberToken;

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

	public int getNumber () throws ParseError{
		if (currentToken == Token.NumberToken)
			return numberToken;
		else
			throw new ParseError ( "No number available for: " +  currentToken);
	}


	private void skipLayout () throws IOException {
		while (currentChar != EOF_CHAR && Character.isWhitespace (currentChar)) {
			currentChar = input.read();
        }
	}

	private void scan () throws IOException, ParseError {
		if ( currentChar == EOF_CHAR)
			currentToken = Token.EOFToken;
		else {
			switch ( currentChar ) {
            case '+': currentChar = input.read();
                      currentToken = Token.PlusToken;
                      break;
            case '-': currentChar = input.read();
                      currentToken = Token.MinusToken;
                      break;
            default:
                if ( Character.isDigit( currentChar ))
                    scanNumber ( );
                else
                    throw new ParseError ( "Illegal input character: " + ((char) currentChar));
			}
		}
	}

	private void scanNumber ( ) throws IOException {
		numberToken  = Character.digit ( currentChar, 10 );
		currentToken = Token.NumberToken;

		for (currentChar = input.read(); Character.isDigit(currentChar);
			 currentChar = input.read()) {
			numberToken = numberToken*10 + Character.digit ( currentChar, 10 );
        }
	}

}
