package duke.exceptions;

/**
 * Creates a new invalid input exception.
 */
public class InvalidInputException extends DukeException {

    /**
     * Error where user has entered an input that does not match the required format.
     */
    public InvalidInputException() {
        super("Please enter according to input formats");
    }

}
