package errors;

public class DeleteTaskError extends Exception {
    public DeleteTaskError() {
        super("There seems to be an error deleting this task");
    }

    public DeleteTaskError(String message) {
        super(message);
    }

}
