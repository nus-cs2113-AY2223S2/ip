package exception;

/**
 * List of error messages
 */
public enum ErrorMessage {
    INVALID_COMMAND("I am sorry, I do not understand what this command means\n" +
            "Commands are available as follows:\n" +
            "1.todo\n2.event\n3.deadline\n4.list\n5.mark\n6.unmark\n7.delete\n8.find\n9.bye"),
    MISSING_TODO_PARAMETER("I am sorry, you are missing a parameter for todo.\nExample: todo borrow book"),
    MISSING_TWO_EVENT_PARAMETER("I am sorry, you are missing a start & end time.\n" +
            "Example: event project meeting /from Mon 2pm /to 4pm"),
    MISSING_ONE_EVENT_PARAMETER("I am sorry, you are missing an end time\n" +
            "Example: event project meeting /from Mon 2pm /to 4pm"),
    INVALID_FROM_TO_ORDER("I am sorry, you /from field has to be before the /to field"),
    MISSING_EVENT_PARAMETER("I am sorry, you are missing a description for event.\n" +
            "Example: event project meeting /from Mon 2pm /to 4pm"),
    MISSING_DEADLINE_BY_PARAMETER("I am sorry, you are missing the by parameter for your deadline.\n" +
            "Example: deadline return book /by Sunday"),
    EMPTY_DEADLINE_BY_PARAMETER("I am sorry, you are inputting an empty by parameter for your deadline.\n" +
            "Example: deadline return book /by Sunday"),
    MISSING_DEADLINE_PARAMETER("I am sorry, you are missing a description for deadline.\n" +
            "Example: deadline return book /by Sunday"),
    INVALID_TASK("I am sorry, you are trying to mark/unmark a task that does not exist."),
    EMPTY_DELETE_PARAMETER("I am sorry, please indicate the task number you want to delete"),
    EMPTY_MARK_OR_UNMARK_PARAMETER("I am sorry, you need to indicate the task number you want to mark or unmark.\n" +
            "Example: mark 3"),
    INVALID_NUMBER("I am sorry, please enter a valid task number\n" +
            "Example: mark 3 or delete 1"),
    MISSING_FIND_PARAMETER("I am sorry, pls enter the parameter you are trying to find\n" +
            "Example: find borrow book"),
    INVALID_DELETE("I am sorry, you are trying to delete a task that does not exist."),
    EMPTY_LIST("I am sorry, you need to have something in your list before I can show you"),
    NO_MATCHING_TASKS("I am sorry, there is no matching tasks");
    private final String error;

    ErrorMessage(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
