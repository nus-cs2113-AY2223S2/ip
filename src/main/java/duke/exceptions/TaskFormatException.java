package duke.exceptions;

/**
 * TaskFormatException is used when the task format is wrong
 * The code is inspired by https://github.com/se-edu/addressbook-level2
 */
public class TaskFormatException extends Exception {
    public final String TASK_FORMAT_ERROR =
            "No...please key in proper command...type 'help' to show command list...";

    @Override
    public String getMessage() {
        return TASK_FORMAT_ERROR;
    }
}
