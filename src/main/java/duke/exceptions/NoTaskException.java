package duke.exceptions;

public class NoTaskException extends Exception {
    private static final String MESSAGE = "There are no tasks available.";

    public NoTaskException() {
        super(MESSAGE);
    }
}
