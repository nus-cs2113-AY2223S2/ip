package duke.exceptions;
/**
 * TaskOutOfBoundsException is used when the user input the invalid task number
 * The code is inspired by https://github.com/se-edu/addressbook-level2
 */

public class TaskOutOfBoundsException extends DukeException {
	public final String OUT_OF_BOUNDS = "Please enter the correct task number!";
	@Override
	public String getMessage() {
		return OUT_OF_BOUNDS;
	}
}
