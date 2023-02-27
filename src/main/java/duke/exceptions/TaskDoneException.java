package duke.exceptions;

public class TaskDoneException extends DukeException {
	public final String TASK_DONE = "This task has been done!";

	@Override
	public String getMessage() {
		return TASK_DONE;
	}
}
