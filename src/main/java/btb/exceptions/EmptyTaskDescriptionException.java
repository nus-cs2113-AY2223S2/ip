package btb.exceptions;

public class EmptyTaskDescriptionException extends DukeException {
    @Override
    public String getMessage() {
        return "\t (╬▔皿▔)╯ OOPS!!! The description of a task cannot be empty.";
    }
}
