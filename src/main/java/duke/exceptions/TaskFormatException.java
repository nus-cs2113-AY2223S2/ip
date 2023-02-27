package duke.exceptions;

public class TaskFormatException extends Exception {
	public final String TASK_FORMAT_ERROR =
			"No...please key in proper command...type 'help' to show command list...";

	public String getMessage() {
		return TASK_FORMAT_ERROR;
	}
}
