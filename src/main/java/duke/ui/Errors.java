package duke.ui;

/**
 * Enum that holds all the error messages to be displayed.
 */
public enum Errors {
    INVALID_COMMAND("Oops... you need to provide a valid command."),
    INVALID_FORMAT ("Oops... you need to provide only one of each argument."),
    INVALID_INDEX  ("Oops... you need to provide a valid task index."),
    INVALID_SAVE   ("Oops... your saved task list is corrupted, a new task list will be created."),

    MISSING_DESCRIPTION("Oops... you need to provide a description for your task."),
    MISSING_INDEX      ("Oops... you need to provide the index of a task."),
    MISSING_TIME       ("Oops... you need to provide the time(s) for this task."),
    MISSING_FILTER     ("Oops... you need to provide a string to search for."),

    FAILED_SAVE("Oops... could not save your task list.");

    public final String MESSAGE;

    /**
     * Constructs an enum that returns the requested message.
     *
     * @param message The message to be displayed.
     */
    Errors(String message) {
        MESSAGE = message;
    }
}
