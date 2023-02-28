package exceptions;

/**
 * Exceptions related to the Duke program.
 */
public class DukeException extends Exception {
    /**
     * It will print out a message when an exception regarding duke is hit.
     *
     * @param message The message to print.
     */
    public DukeException(String message) {
        super(message);
    }
}
