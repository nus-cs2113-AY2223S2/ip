package duke.exception;

/**
 * A class that represents custom exception that is thrown when the command is considered invalid
 */
public class InvalidCommandException extends DukeException {
    /**
     * Default message when message is not specified
     */
    private static final String DEFAULT_MESSAGE = "I'm sorry but I couldn't understand you :(";

    /**
     * Constructor that initializes the message of the exception
     *
     * @param message The message related to the exception
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Empty constructor that initializes with default message
     */
    public InvalidCommandException() {
        super(DEFAULT_MESSAGE);
    }
}
