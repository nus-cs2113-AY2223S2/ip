package duke.exceptions;

import duke.constants.ExceptionMessageConstants;

/**
 * Exception thrown if the user inputs a command not supported.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super(ExceptionMessageConstants.EXCEPTION_INVALID_COMMAND);
    }
}
