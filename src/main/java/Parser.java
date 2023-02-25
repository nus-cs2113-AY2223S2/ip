
public class Parser {

    private String userInput;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    public Parser() {
    }

    public String[] splitIntoCommand(String userInput){
        String[] commandAndArgs = userInput.split(" ",2);
        if(commandAndArgs.length == 1){
            return new String[]{commandAndArgs[0],""};
        }
        return commandAndArgs;
    }
}
