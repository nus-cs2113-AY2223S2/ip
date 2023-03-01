package king.exceptions;

/**
 * An exception representing an input string which is not a valid Command
 */
public class InvalidCommandException extends KingException {
    private static final String MESSAGE = "My utmost apologies, this is incomprehensible my good sir";

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException() {
        super(MESSAGE);
    }
}
