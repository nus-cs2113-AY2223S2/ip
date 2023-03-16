package duke.exceptions;

/**
 * TaskMatchException is used when there is no task matching the keywords in the command
 * The code is inspired by https://github.com/se-edu/addressbook-level2
 */

public class TaskMatchException extends DukeException {
	public final String TASK_MATCH_ERROR =
			"Sorry, I am unable to find the task.";

	@Override
	public String getMessage() {
		return TASK_MATCH_ERROR;
	}
}