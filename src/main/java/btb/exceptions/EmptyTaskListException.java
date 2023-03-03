package btb.exceptions;

public class EmptyTaskListException extends DukeException {
    @Override
    public String getMessage() {
        return "\t You currently don't have any task added.\n" +
                "\t Please add some tasks.";
    }
}
