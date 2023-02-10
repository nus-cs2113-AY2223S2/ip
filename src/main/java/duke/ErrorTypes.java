package duke;

public enum ErrorTypes {
    INVALID_MARK_COMMAND(1),
    NOT_WITHIN_TASK_COUNT(2),
    INVALID_UNMARK_COMMAND(3),
    INVALID_INPUT(4),
    INVALID_TODO(5),
    INVALID_DEADLINE_COMMAND(6),
    INSUFFICIENT_DEADLINE_ARGUMENT(7),
    INVALID_EVENT_COMMAND(8),
    INSUFFICIENT_EVENT_ARGUMENT(9);
    public final int ERROR_TYPE;
    ErrorTypes(int errorType) {
        ERROR_TYPE = errorType;
    }
}
