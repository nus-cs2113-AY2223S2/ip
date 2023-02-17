package duke.exceptions;

public class InvalidInputIDException extends Exception {
    private static final String MESSAGE = "Invalid task ID entered.";

    public InvalidInputIDException() {
        super(MESSAGE);
    }
}
