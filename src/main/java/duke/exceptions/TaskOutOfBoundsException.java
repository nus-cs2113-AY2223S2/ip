package duke.exceptions;

public class TaskOutOfBoundsException extends DukeException {
	private final String OUT_OF_BOUNDS = "Please enter the correct task number!";
	@Override
	public String getMessage() {
		return OUT_OF_BOUNDS;
	}
}
