package Arsdorint;

public class MessageList {
    public static final String EXIT_MESSAGE = " Bye. Hope to see you again soon!\n";
    public static final String HELLO_MESSAGE =
            " Hello! I'm Arsdorint, a member of Arsdorint Team.\n" +
                    " Please Type The Command As Follow:\n";
    public static final String COMMAND_LIST_MESSAGE =
            "> Type \"list\" to list all the tasks. \n" +
                    "> Type \"mark\" follow by a number x to mark tasks x in the list. \n" +
                    "> Type \"unmark\" follow by a number y to unmark tasks y in the list. \n" +
                    "> Type \"todo\" follow by a string x to add a work that need to be done. \n" +
                    "> Type \"deadline x /y\" with x is the type of work, y is the time or date of the deadline. \n" +
                    "> Type \"event x /y\" with x is the event, y is the time or date of that event. \n" +
                    "> Type \"date YYYY-MM-DD\" to know how many tasks occurs in day DD, month MM, year YYYY. \n" +
                    "> Type \"delete\" follow by a number z to delete task z in the the list. \n" +
                    "> Type \"bye\" to exit. \n";
    public static final String QUESTION = " What can I do for you?";
    public static final String MESSAGE_NEW_FILE = "File created";
    public static final String MESSAGE_OVERWRITE_FILE = "File overwritten";
    public static final String MESSAGE_LOAD_FILE = "File loaded";
    public static final String MESSAGE_NO_FILE = "There is no existing file";
    public static final String MESSAGE_WRONG_FILE = "The storage files entry is invalid. Please save to overwrite";
    public static final String MESSAGE_DIVIDER =
            "________________________________________________________________________________";
    public static final String MESSAGE_DIVIDER_LIST =
            "______________________________________LIST______________________________________";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task:";
    public static final String MESSAGE_UNKNOWN = "unknown message";

    public static final String ERROR_MESSAGE_BYE = " ";
    public static final String ERROR_MESSAGE_LIST = " ";
    public static final String ERROR_MESSAGE_MARK =
            "=( OOPS!!! The description of a mark cannot be empty.\n" + "Syntax for mark\n\t" +
                    ">>> mark <item index number> \n" + "Note: item index must exist in the current list";
    public static final String ERROR_MESSAGE_UNMARK =
            "=( OOPS!!! The description of an unmark cannot be empty.\n" + "Syntax for unmark\n\t" +
                    ">>> unmark <item index number> \n" + "Note: item index must exist in the current list";
    public static final String ERROR_MESSAGE_TODO =
            "=( OOPS!!! The description of a todo cannot be empty.\n" + "Syntax for todo\n\t>>> todo <task>";
    public static final String ERROR_MESSAGE_DEADLINE =
            "=( OOPS!!! The description of a deadline cannot be empty.\n" +
                    "Syntax for deadline\n\t>>> deadline <task> /<date of deadline";
    public static final String ERROR_MESSAGE_EVENT =
            "=( OOPS!!! The description of an event cannot be empty.\n" +
                    "Syntax for event\n\t>>> event <task> /<date of event";
    public static final String ERROR_MESSAGE_DELETE = "Syntax for delete item \n\t>>> delete <item index number> \n" +
            "Note: item index must exist in the current list";

    public static final int OFFSET = 1;
}
