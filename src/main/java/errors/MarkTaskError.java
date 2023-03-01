package errors;

/**
 * This is an error class that throws errors related to task marking errors
 * */
public class MarkTaskError extends Exception {

    public MarkTaskError(String message) {
        super(message);
    }

}
