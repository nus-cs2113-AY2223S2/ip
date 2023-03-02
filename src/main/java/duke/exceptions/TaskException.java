package duke.exceptions;

/**
 * TaskException is used when the task list is empty
 * The code is inspired by https://github.com/se-edu/addressbook-level2
 */

public class TaskException extends DukeException {
	public final String EMPTY_LIST = "Sorry...Your task list is empty...";

	@Override
	public String getMessage() {
		return EMPTY_LIST;
	}
}
