package duke.exceptions;

public class DukeException extends Exception {

    public DukeException() {}

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "____________________________________________________________\n"
                + super.getMessage()
                + "____________________________________________________________\n";
    }
}
