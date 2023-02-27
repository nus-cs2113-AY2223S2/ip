package Exceptions;

public class DukeException extends Exception {

    /**
     * Returns message from exception error
     * @param error error message
     */
    public DukeException(String error) {
        super(error);
    }
}
