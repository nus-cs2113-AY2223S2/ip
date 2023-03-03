package duke.exception;

import duke.exception.DukeException;

public class TaskParameterException extends DukeException {
    public TaskParameterException(String taskName, String additionalMessage){
        super("OOPS!!! There is an invalid input of the parameters for " + taskName + ".\n" +
                additionalMessage);
    }
}
