package duke;

public class DukeException extends Exception {
    
    public DukeException(String message) {
        super("😣OOPS!!! " + message);
    }
}
