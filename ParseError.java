package parsing;

import parsing.Tokenizer.Token;

/**
 *
 * @author Sjaak Smetsers
 * @version 1.1 -- 02-03-2013
 */
public class ParseError extends Exception
{
	/**
	* Constructs an instance of ParseError with the message.
	* @param msg the detail message.
	*/
	public ParseError ( String msg ) {
		super ( msg );
	}

	public ParseError(String msg, Token iso_token) {
		super ( msg + " instead of " + iso_token.tokenString);
	}
}