package Exceptions;

public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super("\t"+errorMessage);
    }

    public DukeException(String errorMessage, Throwable err) {
        super("\t"+errorMessage, err);
    }
}
