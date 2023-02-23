package duke.exceptions;

import duke.parser.DateTimeParser;

public class InvalidDateTimeException extends Exception {
    private static final String MESSAGE = "Invalid date or time format. Please use the following format:\n"
            + "<" + DateTimeParser.getFormat() + "> and ensure that time is in 24-hour format";

    public InvalidDateTimeException() {
        super(MESSAGE);
    }
}
