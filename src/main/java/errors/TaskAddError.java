package errors;

/**
 * This is an error class that throws errors related to task adding errors
 * */
public class TaskAddError extends Exception {

    public TaskAddError() {
        super("There seems to be some error with adding a task\n");
    }

    public TaskAddError(String message) {
        super(message);
    }
}
