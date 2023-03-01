package errors;

/**
* This is an error class that throws errors related to task deletion
* */
public class DeleteTaskError extends Exception {
    public DeleteTaskError() {
        super("There seems to be an error deleting this task");
    }

    public DeleteTaskError(String message) {
        super(message);
    }


}
