package duke.exceptions;

/**
 * Exception thrown when there are no tasks in the list.
 */
public class NoTaskException extends Exception {
    private static final String MESSAGE = "There are no tasks available.";

    public NoTaskException() {
        super(MESSAGE);
    }
}
