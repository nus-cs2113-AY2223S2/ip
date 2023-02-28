package common;

/**
 * A class containing the list of messages that will be shown to the user.
 */
public class Messages {
    public static final String MESSAGE_VALID_COMMAND_LIST = "List of valid commands:" +
            "\nUsage: todo <description>" +
            "\nUsage: deadline /by <specify by when>" +
            "\nUsage: event /from <specify from when> /to <specify to when>" +
            "\nUsage: mark <task number>" +
            "\nUsage: unmark <task number>" +
            "\nUsage: list" +
            "\nUsage: find <keyword>" +
            "\nUsage: delete <integer>" +
            "\nUsage: bye";
    public static final String MESSAGE_COMMAND_LIST_SIZE = "Now you have %d tasks in the list.";
    public static final String MESSAGE_COMMAND_ADD_TASK = "Got it. I've added this task:";
    public static final String MESSAGE_COMMAND_BYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_COMMAND_FIND_MATCH = "Here are the matching tasks in your list:";
    public static final String MESSAGE_COMMAND_FIND_EMPTY_LIST = "Wow! you currently have no task! " +
            "Add a task to use the find feature.";
    public static final String MESSAGE_COMMAND_LIST_EMPTY = "Wow! you currently have no task!";
    public static final String MESSAGE_COMMAND_LIST_TASKS = "Here are the tasks in your list:";
    public static final String MESSAGE_COMMAND_DELETE = "Noted. I've removed this task:";
    public static final String MESSAGE_COMMAND_MARK = "Nice! I've marked this task as done:";
    public static final String MESSAGE_COMMAND_UNMARK = "OK, I've marked this task as not done yet:";
    public static final String ERROR_TODO_COMMAND = "OOPS!!! please give me a Todo." +
            "\nUsage: todo <description>";
    public static final String ERROR_COMMAND_MARK = "Please give me a valid integer that is within the list!" +
            "\nUsage: mark <integer>";
    public static final String ERROR_COMMAND_UNMARK = "Please give me a valid integer that is within the list!" +
            "\nUsage: unmark <integer>";
    public static final String ERROR_COMMAND_DELETE = "Please give me a valid integer that is within the list!" +
            "\nUsage: delete <integer>";
    public static final String ERROR_EVENT_COMMAND = "OOPS!!! please give me a event." +
            "\nUsage: event /from <specify from when> /to <specify to when>";
    public static final String ERROR_DEADLINE_COMMAND = "OOPS!!! please give me a deadline." +
            "\nUsage: deadline /by <specify by when>";
    public static final String ERROR_CREATE_DIRECTORY = "Unable to create directory at\n%s";
    public static final String ERROR_STORAGE_FILE_NOT_FOUND = "File was not found. " +
            "A new file will be created when a new task is added.";
    public static final String GENERIC_ERROR = "Something went wrong please refer to below:\n%s";
}
