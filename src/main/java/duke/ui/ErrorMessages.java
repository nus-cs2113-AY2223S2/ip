package duke.ui;

/**
 * Error messages for the error types
 */
public enum ErrorMessages {

    OVER_TASK_COUNT_MESSAGE(" Invalid task number provided, please give a number <= %d"),

    ERROR_IN_SETTING_UP(" Failed to set up, exiting" + Ui.NEW_LINE + " Duke exiting..."),

    CREATE_NEW_FILE_EXCEPTION_MESSAGE(" Exception received from creating file"),

    FILE_NOT_FOUND_EXCEPTION_MESSAGE(" File not found exception from trying to read data from file"),

    IO_EXCEPTION_MESSAGE(" IO exception received from trying to update data"),

    INVALID_FIND_MESSAGE(" Invalid input! Valid input format: \"find <keyword>\"");

    public final String MESSAGE;

    ErrorMessages(String message) {
        MESSAGE = message;
    }
}
