package parsing;
/**
 * Deze klasse wordt gebruikt om de prioriteit van operaties te bepalen
 * @author Laurens van den Bercken, s4057384 
 * @author Jeftha Spunda, s4174615
 */
public class PriorityDetermine {
	
	/**
	 * Input
	 * Boolean rij die per operator bepaalt of deze meteen mag worden uitgewerkt of niet
	 */
	private String input;
	private boolean[] priority;
	
	/**
	 * Constructor
	 * @param input
	 */
	public PriorityDetermine(String input) {
		this.input = input;
		prepareInput();

	}
	
	/**
	 * Zorgt ervoor dat input alleen nog maar operators overhoud
	 * not operator is niet nodig want die wordt altijd direct afgehandeld
	 */
	private void prepareInput() {
		for(int i =0; i < input.length(); i++) {
			if(Character.isDigit(input.charAt(i))) {
				input = input.replace(""+input.charAt(i)," ");
			}
		}
		input = input.replace(" ","");
		input = input.replace("~","");
		input = input.replace("<>","<");
		priority = new boolean[input.length()];
	}
	
	/**
	 * Kijkt per operator of hij een hogere prioriteit heeft dan zijn opvolger.
	 * Zo ja, mag de waarde in prioriteit die overeenkomt met die operator op true;
	 * @throws ParseError
	 */
	public void determine() throws ParseError {
		for(int i = 0; i < input.length(); i++ ) {
			if(i == input.length() - 1) { //Laatse operator moet altijd met het fact erna worden uitgevoerd
				priority[i] = true;
			} else {
				switch(input.charAt(i)) {
				case '&':
					priority[i] = true;
					break;
				case '|':
					if (input.charAt(i+1) == '&' || input.charAt(i+1) == '|')
						priority[i] = false;
					else
						priority[i] = true;
					break;
				case '>':
					if (input.charAt(i+1) == '<' && input.charAt(i+1) == '>')
						priority[i] = true;
					else
						priority[i] =false;
					break;
				case '<':
					priority[i] = false;
					break;
				case ',':
					priority[i] = false;
					break;
				default: 
					throw new ParseError ( "Illegal input character: " + ((char) input.charAt(i)));
				}
			}	
		}
	}
	
	/**
	 * @return priority array
	 */
	public boolean[] getPriority () {
		return priority;
	}
}
