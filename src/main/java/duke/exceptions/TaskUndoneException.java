package duke.exceptions;

/**
 * TaskUndoneException is used
 * when if the user wants to unmark the undone task
 * The code is inspired by https://github.com/se-edu/addressbook-level2
 */

public class TaskUndoneException extends DukeException {
    public final String TASK_UNDONE = "You have not finished this task yet.";

    @Override
    public String getMessage() {
        return TASK_UNDONE;
    }
}
