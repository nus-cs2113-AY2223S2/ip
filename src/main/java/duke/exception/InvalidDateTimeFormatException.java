package duke.exception;
import duke.outputs.Messages;
public class InvalidDateTimeFormatException extends DukeException{
    public String[] getDukeMessages() {
        return new String[] { Messages.ERROR_MESSAGE_INVALID_DATETIME };
    }
}
