package duke.exception;

/**
 * Exception thrown when user inputs a command which is not part of the accepted commands
 */
public class UnknownCommandException extends Exception {
    public String unknownCommand;
    public UnknownCommandException(String unknownCommand) {
        this.unknownCommand = unknownCommand;
    }
}

