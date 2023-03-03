package duke.exceptions;

import duke.constants.ExceptionMessageConstants;

/**
 * Exception thrown if the ID of the task is out of range of the tasks in the list.
 */
public class InvalidInputIDException extends Exception {
    public InvalidInputIDException() {
        super(ExceptionMessageConstants.EXCEPTION_INVALID_ID);
    }
}
