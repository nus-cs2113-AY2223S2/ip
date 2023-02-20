package duke.exception;

/**
 * A class that represents all the errors that can occur while the program is running.
 */
public class DukeException extends Exception {
    /**
     * Constructs an Exception object with the given error message.
     *
     * @param description The error message.
     */
    public DukeException(String description) {
        super(description);
    }
}
