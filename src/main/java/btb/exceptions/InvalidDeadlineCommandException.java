package btb.exceptions;

public class InvalidDeadlineCommandException extends DukeException {
    @Override
    public String getMessage() {
        return "\t Oops ..., please enter a valid deadline command.";
    }
}
