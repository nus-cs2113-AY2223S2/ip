package duke;

public class DukeException extends Exception {
    /**
     * Super class for all other exceptions that will be thrown
     * in project
     * @param message To be printed
     * @param err Root of error that caused exception to be raised
     */
    public DukeException(String message, Throwable err) {
        super(message,err);
    }
}