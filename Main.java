package parsing;

import java.io.StringReader;
import ast.Exp;

/**
 *
 * @author Sjaak Smetsers
 * @version 1.1 -- 02-03-2013
 */
public class Main {

    public static void main(String[] args) {
		TestParser ();
    }

	private static void TestParser () {
		String test_tekst = "1 + aap - 41";
		try {
			StringReader input = new StringReader(test_tekst);
			RDparser parser = new RDparser (input);

			Exp e = parser.parse();
			System.out.printf ("Result: %d\n", e.eval());


		} catch (ParseError parseError) {
			System.out.println(parseError);
		}
    }

}
