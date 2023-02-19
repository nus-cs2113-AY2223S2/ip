package Exceptions;

/**
 * Called when user provided a non positive number for task index
 */
public class NonPositiveNumberException extends DukeException {
    /**
     * Constructor for printing non positive number error message
     */
    public NonPositiveNumberException() {
        super("Oops! Only positive integers can be used as the index!");
    }

    /**
     * Constructor for printing non positive number error origin
     * @param err Error
     */
    public NonPositiveNumberException(Throwable err) {
        super("Oops! Only positive integers can be used as the index!", err);
    }
}
