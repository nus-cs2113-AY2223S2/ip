package duke.exceptions;

public class TaskException extends DukeException {
	private final String EMPTY_LIST = "Sorry...Your task list is empty...";

	@Override
	public String getMessage() {
		return EMPTY_LIST;
	}
}
