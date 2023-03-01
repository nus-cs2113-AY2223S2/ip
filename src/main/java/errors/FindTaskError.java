package errors;

/**
 * This is an error class that throws errors related to Task Errors
 */
public class FindTaskError extends Exception {
    public FindTaskError() {
        super("There seems like some error in finding this task");
    }

    public FindTaskError(String message) {
        super(message);
    }
}
