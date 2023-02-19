package Exceptions;

/**
 * Called when user input has command word but no arguments provided
 */
public class EmptyArgumentException extends DukeException {
    /**
     * Constructor for printing missing argument error message
     * @param emptyArgument missing argument
     */
    public EmptyArgumentException(String emptyArgument) {
        super("Oops! " + emptyArgument + " cannot be empty!");
    }

    /**
     * Constructor for printing error origins
     * @param emptyArgument missing argument
     * @param err Error
     */
    public EmptyArgumentException(String emptyArgument,Throwable err) {
        super("Oops! " + emptyArgument + " cannot be empty!", err);
    }
}
