package duke.exception;

import duke.exception.TaskParameterException;

public class TaskDateFormatException extends TaskParameterException {
    public TaskDateFormatException(String taskName){
        super(taskName, "Please input the date in the format yyyy-mm-dd.");
    }
}
