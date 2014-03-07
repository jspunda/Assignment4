package parsing;

import java.io.StringReader;

/**
 * Main. Roept testparser aan en parseert test_tekst
 * Support op het moment nog geen afhandeling van haakjes.
 * @author Sjaak Smetsers
 * @author Laurens van den Bercken, s4057384 
 * @author Jeftha Spunda, s4174615
 */
public class Main {

    public static void main(String[] args) {
		TestParser ();
    }

	private static void TestParser () {
		/*
		 * And = &
		 * Or = |
		 * Implicatie = >
		 * Dubbele implicatie = <>
		 * Not = ~
		 * Vars = a t/m z (upper en lower case)
		 */
		
		String test_tekst = "a > b"; //Voer hier input in.

		try {
			StringReader input = new StringReader(test_tekst);
			RDparser parser = new RDparser (input, test_tekst);

			parser.parse();
			
			System.out.println("Correcte input");
			//System.out.printf ("Result: %d\n", e.eval());


		} catch (ParseError parseError) {
			System.out.println(parseError);
		}
    }

}
