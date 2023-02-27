package duke.exceptions;

public class CommandFormatException extends DukeException {
	public final String COMMAND_FORMAT_ERROR =
			"No...please key in proper command...type 'help' to show command list...";

	@Override
	public String getMessage() {
		return COMMAND_FORMAT_ERROR;
	}
}
