public enum ErrorMessage {
    INVALID_COMMAND("I am sorry, I do not understand what this command means"),
    MISSING_TODO_PARAMETER("I am sorry, you are missing something"),
    MISSING_TWO_EVENT_PARAMETER("I am sorry, you are missing a start & end time."),
    MISSING_ONE_EVENT_PARAMETER("I am sorry, you are missing an end time"),
    MISSING_EVENT_PARAMETER("I am sorry, you are missing a description for event."),
    MISSING_DEADLINE_BY_PARAMETER("I am sorry, you are missing the by parameter for your deadline"),
    EMPTY_DEADLINE_BY_PARAMETER("I am sorry, you are inputing an empty by parameter for your deadline"),
    MISSING_DEADLINE_PARAMETER("I am sorry, you are missing a description for deadline."),
    INVALID_TASK("I am sorry, you are trying to mark/unmark a task that does not exist."),
    EMPTY_LIST("I am sorry you need to have something in your list before I can show you");
    private final String error;

    ErrorMessage(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
