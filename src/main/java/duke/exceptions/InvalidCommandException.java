package duke.exceptions;

/**
 * Exception thrown if the user inputs a command not supported.
 */
public class InvalidCommandException extends Exception {
    private static final String MESSAGE = "Unrecognised command, try again.";

    public InvalidCommandException() {
        super(MESSAGE);
    }
}
