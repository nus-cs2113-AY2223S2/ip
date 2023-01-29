package duke;

public class DukeException extends Exception {
    public DukeException(String message, Throwable err) {
        super(message,err);
    }
}