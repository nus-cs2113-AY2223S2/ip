package max.command;

/**
 * This exception is thrown when there is a logical error dealing with Commands
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
