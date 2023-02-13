/**
 * Represents an exception that will print a Duke Chat Bot specific error message.
 */
public class DukeException extends Exception {
    protected String errorMessage;

    public DukeException (String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
