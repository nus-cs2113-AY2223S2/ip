package duke.exceptions;

import duke.constants.ExceptionMessageConstants;

/**
 * Exception thrown when there are no tasks in the list.
 */
public class NoTaskException extends Exception {
    public NoTaskException() {
        super(ExceptionMessageConstants.EXCEPTION_NO_TASKS);
    }
}
