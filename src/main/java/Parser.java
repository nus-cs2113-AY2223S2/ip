public class Parser {
	private String userInput;

	public Parser(String userInput) {
		this.userInput = userInput;
	}

	public Parser() {
	}

	/**
	 * Splits user input into an action and a description array.
	 *
	 * @param userInput The user's input.
	 * @return An array containing the action and description of the input.
	 */
	public String[] splitActionAndDescription(String userInput){
		String[] commandAndArgs = userInput.split(" ",2);
		if(commandAndArgs.length == 1){
			return new String[]{commandAndArgs[0],""};
		}
		return commandAndArgs;
	}
}
