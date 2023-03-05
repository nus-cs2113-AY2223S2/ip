package errors;

/**
 * This is an error class that throws errors related to task adding errors
 * */
public class TaskAddError extends Exception {

    public TaskAddError(String message) {
        super(message);
    }
}
