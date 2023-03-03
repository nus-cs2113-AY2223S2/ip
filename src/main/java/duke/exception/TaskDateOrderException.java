package duke.exception;

import duke.exception.TaskParameterException;

public class TaskDateOrderException extends TaskParameterException {
    public TaskDateOrderException(String taskName){
        super(taskName, "You have inputted the dates in an impossible order.");
    }
}
