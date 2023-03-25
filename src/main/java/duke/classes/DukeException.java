package duke.classes;

/**
 * DukeException represents an exception that can be thrown.
 * It is an extension of the standard Exception class.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param errorMessage a string containing the error message to be associated with this exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
