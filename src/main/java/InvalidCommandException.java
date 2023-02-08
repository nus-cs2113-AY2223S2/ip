public class InvalidCommandException extends DukeException{
    private static final String DEFAULT_MESSAGE = "I'm sorry but I couldn't understand you :(";

    public InvalidCommandException(String message){
        super(message);
    }

    public InvalidCommandException () {
        super(DEFAULT_MESSAGE);
    }
}
