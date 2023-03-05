package duke.exceptions;

/**
 * Exception when there is an inputted error or Duke program fails
 */
public class DukeException extends Exception {

    /**
     * Default constructor
     */
    public DukeException() {}

    /**
     * Instantiates message
     *
     * @param message exception cause
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Overrides toString to include exception message
     *
     * @return exception message
     */
    @Override
    public String toString() {
        return "____________________________________________________________\n"
                + super.getMessage()
                + "____________________________________________________________\n";
    }
}
