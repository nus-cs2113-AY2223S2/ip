package duke.exception;
import duke.outputs.Messages;

public class UnknownCommandException extends DukeException{

    public String[] getDukeMessages() {
        return new String[] {Messages.MESSAGE_UNKNOWN_COMMAND};
    }
}
