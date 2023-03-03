package duke.exceptions;

import duke.parser.datetime.DateTimeParser;

/**
 * Exception when the user inputs an invalid date and time.
 */
public class InvalidDateTimeException extends Exception {
    private static final String MESSAGE = "Invalid date or time format. Please use the following format:"
            + System.lineSeparator()
            + "<" + DateTimeParser.getFormat() + "> and ensure that time is in 24-hour format";

    public InvalidDateTimeException() {
        super(MESSAGE);
    }
}
