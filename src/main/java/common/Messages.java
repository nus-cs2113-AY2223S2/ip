package common;

public class Messages {
    public static final String MESSAGE_VALID_COMMAND_LIST = "List of valid commands:" +
            "\nUsage: todo <description>" +
            "\nUsage: mark/unmark <task number>" +
            "\nUsage: deadline /by <specify by when>" +
            "\nUsage: event /from <specify from when> /to <specify to when>" +
            "\nUsage: delete <integer>" +
            "\nUsage: bye";
    public static final String MESSAGE_COMMAND_LIST_SIZE = "Now you have %d tasks in the list.";
    public static final String MESSAGE_COMMAND_ADD_TASK = "Got it. I've added this task:";
    public static final String MESSAGE_COMMAND_BYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_COMMAND_LIST_EMPTY = "Wow! you currently have no task!";
    public static final String MESSAGE_COMMAND_LIST_NOT_EMPTY = "Here are the tasks in your list:";
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
    public static final String ERROR_CREATE_DIRECTORY = "Unable to create directory at %s";
    public static final String GENERIC_ERROR = "Something went wrong please refer to below:\n %s";
}
