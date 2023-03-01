package errors;

/**
* This is an error class that throws errors related to task deletion
* */
public class DeleteTaskError extends Exception {

    public DeleteTaskError(String message) {
        super(message);
    }


}
