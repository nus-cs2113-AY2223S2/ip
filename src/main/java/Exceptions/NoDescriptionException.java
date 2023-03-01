package Exceptions;

/**
 * Called when user did not provide any details for command
 */
public class NoDescriptionException extends DukeException {
    /**
     * Constructor for printing missing details error message
     * @param command command that requires additional details
     */
    public NoDescriptionException(String command) {
        super("Oops! The description of " + command + " cannot be empty.");
    }
}
