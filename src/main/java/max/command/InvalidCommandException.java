package max.command;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
