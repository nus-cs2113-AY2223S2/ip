package btb.exceptions;

public class InvalidEventCommandException extends DukeException {
    @Override
    public String getMessage() {
        return "\t Oops ...(*￣０￣)ノ, please enter a valid Event command.";
    }
}
