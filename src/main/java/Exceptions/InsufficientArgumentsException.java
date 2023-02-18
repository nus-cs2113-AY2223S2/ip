package Exceptions;

/**
 * Called when user input has command word but insufficient arguments provided
 */
public class InsufficientArgumentsException extends DukeException {
    /**
     * Constructor for printing missing argument error message
     * @param command command input
     * @param format proper format of command
     */
    public InsufficientArgumentsException(String command, String format) {
        super("Oops! The format of " + command + " is: " + format);
    }

    /**
     * Constructor for printing missing argument error origins
     * @param command command input
     * @param format proper format of command
     * @param err Error
     */
    public InsufficientArgumentsException(String command, String format, Throwable err) {
        super("Oops! The format of " + command + " is: " + format, err);
    }
}