package duke.exceptions;

public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super("Please enter according to input formats");
    }

}
