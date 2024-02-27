package duke.exception;

/**
 * A class that represents basic custom exception for Duke
 */

public class DukeException extends Exception {
    /**
     * Prefix for exception message
     */
    private static final String DUKE_PREFIX = "Duke Error: ";


    /**
     * Constructor that initiates the exception message
     *
     * @param message The message corresponding to the exception
     */
    public DukeException(String message) {
        super(DUKE_PREFIX + message);
    }

}
