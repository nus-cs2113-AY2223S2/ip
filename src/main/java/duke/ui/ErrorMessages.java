package duke.ui;

public enum ErrorMessages {
    INVALID_MARK_MESSAGE(" Invalid input! Valid input format: \"mark <number>\""),
    OVER_TASK_COUNT_MESSAGE(" Invalid task number provided, please give a number <= %d"),
    INVALID_UNMARK_MESSAGE(" Invalid input! Valid input format: \"unmark <number>\""),
    INVALID_TODO_MESSAGE(" Invalid input! Valid input format: \"todo <task name>\""),
    MISSING_DEADLINE_KEYWORD_MESSAGE(" Invalid input! Valid input format: \"deadline <task name> /by <date>\""),
    INSUFFICIENT_DEADLINE_FIELD_MESSAGE(" Invalid input! Please provide enough arguments! "
            + Ui.NEW_LINE + " Valid input format: \"deadline <task name> /by <date>\""),
    INVALID_EVENT_FORMAT_MESSAGE(" Invalid input! Valid input format: \"event <task name> /from <date> /to <date>\""),
    INSUFFICIENT_EVENT_FIELD_MESSAGE(" Invalid input! Please provide enough arguments! "
            + Ui.NEW_LINE + " Valid input format: \"event <task name> /from <date> /to <date>\""),
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
