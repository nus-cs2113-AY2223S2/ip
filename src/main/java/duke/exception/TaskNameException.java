package duke.exception;

import duke.exception.DukeException;

public class TaskNameException extends DukeException {
    public TaskNameException(String taskName){
        super("OOPS!!! The description of a " + taskName + " cannot be empty.");
    }
}
