package exception;

public enum ErrorMessage {
    INVALID_COMMAND("I am sorry, I do not understand what this command means"),
    MISSING_TODO_PARAMETER("I am sorry, you are missing a parameter for todo eg. todo borrow book"),
    MISSING_TWO_EVENT_PARAMETER("I am sorry, you are missing a start & end time."),
    MISSING_ONE_EVENT_PARAMETER("I am sorry, you are missing an end time"),
    MISSING_EVENT_PARAMETER("I am sorry, you are missing a description for event."),
    MISSING_DEADLINE_BY_PARAMETER("I am sorry, you are missing the by parameter for your deadline"),
    EMPTY_DEADLINE_BY_PARAMETER("I am sorry, you are inputting an empty by parameter for your deadline"),
    MISSING_DEADLINE_PARAMETER("I am sorry, you are missing a description for deadline."),
    INVALID_TASK("I am sorry, you are trying to mark/unmark a task that does not exist."),
    EMPTY_DELETE_PARAMETER("I am sorry, please indicate the task number you want to delete"),
    EMPTY_MARK_OR_UNMARK_PARAMETER("I am sorry, you need to indicate the task number you want to mark or unmark"),
    INVALID_NUMBER("Please enter a valid task number"),
    MISSING_FIND_PARAMETER("I am sorry, pls enter the parameter you are trying to find"),
    INVALID_DELETE("I am sorry, you are trying to delete a task that does not exist."),
    EMPTY_LIST("I am sorry you need to have something in your list before I can show you"),
    NO_MATCHING_TASKS("I am sorry there is no matching tasks");
    private final String error;

    ErrorMessage(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
