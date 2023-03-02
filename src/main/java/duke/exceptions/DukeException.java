package duke.exceptions;

/**
 * Class that creates new custom duke error.
 */
public class DukeException extends Exception {

    /**
     * Constructor to create a custom duke error.
     *
     * @param error Error message of the error.
     */
    public DukeException(String error) {
        super(error);
    }
}
