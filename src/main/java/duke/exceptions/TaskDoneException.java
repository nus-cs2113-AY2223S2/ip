package duke.exceptions;

/**
 * TaskDoneException is used when the task has been done
 * The code is inspired by https://github.com/se-edu/addressbook-level2
 */
public class TaskDoneException extends DukeException {
    public final String TASK_DONE = "This task has been done!";

    @Override
    public String getMessage() {
        return TASK_DONE;
    }
}
