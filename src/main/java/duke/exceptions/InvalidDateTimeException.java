package duke.exceptions;

import duke.constants.ExceptionMessageConstants;

/**
 * Exception when the user inputs an invalid date and time.
 */
public class InvalidDateTimeException extends Exception {
    public InvalidDateTimeException() {
        super(ExceptionMessageConstants.EXCEPTION_INVALID_DATE_TIME);
    }
}
