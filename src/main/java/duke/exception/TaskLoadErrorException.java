package duke.exception;
import duke.outputs.Messages;

public class TaskLoadErrorException extends DukeException{
    public String[] getDukeMessages() {
        return new String[] { Messages.ERROR_MESSAGE_LOADING_TASK_ERROR };
    }
}
