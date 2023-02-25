/**
 * Deals with making sense of the user command.
 */
public class Parser {

    private String userInput;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    public Parser() {
    }

    /**
     * Splits user input into 2 parts: commandAndArgs[0] and commandAndArgs[1],
     * which are command and description of task respectively.
     * @param userInput Sentence that user has typed in CLI.
     * @return 2d string array that contains command and description in index 0 and 1 respectively.
     */
    public String[] splitIntoCommand(String userInput){
        String[] commandAndArgs = userInput.split(" ",2);
        if(commandAndArgs.length == 1){
            return new String[]{commandAndArgs[0],""};
        }
        return commandAndArgs;
    }
}
