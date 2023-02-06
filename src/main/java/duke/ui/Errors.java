package duke.ui;

public enum Errors {
    INVALID_COMMAND("Oops... you need to provide a valid command."),
    INVALID_FORMAT("Oops... you need to provide only one of each argument"),
    INVALID_INDEX("Oops... you need to provide a valid task index"),

    MISSING_DESCRIPTION("Oops... you need to provide a description for your task."),
    MISSING_INDEX("Oops... you need to provide the index of a task"),
    MISSING_TIME("Oops... you need to provide the time(s) for this task");

    public final String MESSAGE;

    Errors(String message) {
        MESSAGE = message;
    }
}
