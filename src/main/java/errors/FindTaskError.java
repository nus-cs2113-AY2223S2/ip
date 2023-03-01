package errors;

/**
 * This is an error class that throws errors related to Task Errors
 */
public class FindTaskError extends Exception {

    public FindTaskError(String message) {
        super(message);
    }
}
