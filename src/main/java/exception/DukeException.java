package exception;

/**
 * Represents an exception specific to Duke that is thrown when error occurs
 */
public class DukeException extends Exception {

    public DukeException (String errorMessage) {
        super(errorMessage);
    }
}
