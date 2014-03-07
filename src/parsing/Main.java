package parsing;

import java.io.StringReader;
import ast.*;

/**
 * Main. Split invoer op : (turnstile)
 * parseert links van : en parseert rechts van : en vervolgens het resultaat van beide met turnstile
 * Support op het moment nog geen afhandeling van haakjes.
 * @author Sjaak Smetsers
 * @author Laurens van den Bercken, s4057384 
 * @author Jeftha Spunda, s4174615
 */
public class Main {

    public static void main(String[] args){
		TestParser ();
    }

	private static void TestParser () {
		/*
		 * And = &
		 * Or = |
		 * Implicatie = >
		 * Dubbele implicatie = <>
		 * Not = ~
		 * Vars = 1 of 0
		 * turnstile = : 
		 */
		
		String test_tekst = "1, 1 | 0 : 0 & 1"; //Voer hier input in.
		
		String strings [] = test_tekst.split(":");
		try {
			if (strings.length != 2)
				throw new ParseError("Not a valid expression.");
		} catch (ParseError parseError) {
			System.out.println(parseError);
			return;
		}
		
		try {
			Exp e = new BinOpExp (new RDparser(new StringReader (strings[0].toString()), strings[0], true).parse(),
								  new RDparser(new StringReader (strings[1].toString()), strings[1], false).parse(),
								  new EqualsOp());
			System.out.println("Correcte input");
			System.out.println (e.eval());
			
		} catch (ParseError parseError) {
			System.out.println(parseError);
		}
    }

}
