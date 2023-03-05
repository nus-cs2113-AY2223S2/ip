package errors;

/**
 * This is an error class that throws errors related to incorrect command input
 * */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

}
