package btb.exceptions;

public class EmptyTaskNumberException extends DukeException {
    public String getMessage() {
        return "\t You did not enter a task number, please try again!";
    }
}
