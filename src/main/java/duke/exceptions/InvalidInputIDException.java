package duke.exceptions;

/**
 * Exception thrown if the ID of the task is out of range of the tasks in the list.
 */
public class InvalidInputIDException extends Exception {
    private static final String MESSAGE = "Invalid task ID entered.";

    public InvalidInputIDException() {
        super(MESSAGE);
    }
}
