package tusky.exceptions;

/**
 * Exception that is thrown when the description of a task is empty.
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException (String message) {
        super(message);
    }
}
