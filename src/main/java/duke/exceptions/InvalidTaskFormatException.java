package duke.exceptions;

import duke.constants.ExceptionMessageConstants;
import duke.constants.TasksConstants;
import duke.tasks.TaskEnum;

/**
 * Exception when the input task command does not follow the required format.
 */
public class InvalidTaskFormatException extends Exception {
    private final TaskEnum taskType;

    /**
     * Class constructor for the task the user is trying to create.
     *
     * @param taskType TaskEnum corresponding to the task type
     */
    public InvalidTaskFormatException(TaskEnum taskType) {
        this.taskType = taskType;
    }

    /**
     * Get the input format of the task the user is attempting to create.
     *
     * @return The error message
     */
    @Override
    public String getMessage() {
        String errorMessage = ExceptionMessageConstants.EXCEPTION_INVALID_FORMAT_HEADER;
        switch (taskType) {
        case TODO:
            errorMessage += TasksConstants.FORMAT_TODO;
            break;
        case DEADLINE:
            errorMessage += TasksConstants.FORMAT_DEADLINE;
            break;
        case EVENT:
            errorMessage += TasksConstants.FORMAT_EVENT;
            break;
        }
        return errorMessage;
    }
}
