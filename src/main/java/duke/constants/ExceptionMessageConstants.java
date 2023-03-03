package duke.constants;

import duke.parser.datetime.DateTimeParser;

public final class ExceptionMessageConstants {
    private ExceptionMessageConstants() {
    }

    public static final String EXCEPTION_CORRUPT_DATA = "Warning: Save data is corrupted." + System.lineSeparator()
            + "All data will be overwritten after the next command is entered." + System.lineSeparator()
            + "Corrupt data: ";
    public static final String EXCEPTION_INVALID_COMMAND = "Unrecognised command, try again.";
    public static final String EXCEPTION_INVALID_DATE_TIME = "Invalid date or time format. Please use the following format:"
            + System.lineSeparator()
            + "<" + DateTimeParser.getFormatDateTime() + "> and ensure that time is in 24-hour format";
    public static final String EXCEPTION_INVALID_ID = "Invalid task ID entered.";
    public static final String EXCEPTION_INVALID_FORMAT_HEADER = "Invalid input format!" + System.lineSeparator()
            + "Use the following format to create a new task:" + System.lineSeparator();
    public static final String EXCEPTION_NO_TASKS = "There are no tasks available.";
}
