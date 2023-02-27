package duke.exceptions;

public class TaskUndoneException extends DukeException {
	private final String TASK_UNDONE = "You have not finished this task yet.";

	@Override
	public String getMessage() {
		return TASK_UNDONE;
	}
}
