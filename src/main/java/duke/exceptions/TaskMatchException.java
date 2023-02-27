package duke.exceptions;

public class TaskMatchException extends DukeException {
	public final String TASK_MATCH_ERROR =
			"Sorry, I am unable to find the task.";

	public String getMessage() {
		return TASK_MATCH_ERROR;
	}
}