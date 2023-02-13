package btb.exceptions;

public class InvalidDeadlineCommandException extends DukeException {
    @Override
    public String getMessage() {
        return "\t Oops ...(*￣０￣)ノ, please enter a valid deadline command.";
    }
}
