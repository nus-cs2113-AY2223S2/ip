package duke.exceptions;

public class InvalidCommandException extends Exception {
    private static final String MESSAGE = "Unrecognised command, try again.";

    public InvalidCommandException() {
        super(MESSAGE);
    }
}
