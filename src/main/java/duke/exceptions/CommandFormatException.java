package duke.exceptions;

/**
 * CommandFormatException is used when the command line is in wrong format
 * The code is inspired by https://github.com/se-edu/addressbook-level2
 */
public class CommandFormatException extends DukeException {
	public final String COMMAND_FORMAT_ERROR =
			"No...please key in proper command...type 'help' to show command list...";

	@Override
	public String getMessage() {
		return COMMAND_FORMAT_ERROR;
	}
}
