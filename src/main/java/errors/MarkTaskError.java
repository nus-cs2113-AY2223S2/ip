package errors;

/**
 * This is an error class that throws errors related to task marking errors
 * */
public class MarkTaskError extends Exception {
    public MarkTaskError() {
        super("There seems like some error with marking this task");
    }

    public MarkTaskError(String message) {
        super(message);
    }

}
