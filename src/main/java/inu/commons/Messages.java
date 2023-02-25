package inu.commons;

/**
 * Container for messages shown to user.
 */
public class Messages {

    public static final String MESSAGE_GREETING = " Woof Woof! I'm Inu! Your personal Shib-Assistant ^.^";

    public static final String MESSAGE_PROMPT = " What can I do for you today?";

    public static final String MESSAGE_EXIT = " Woof! Saving your task list before exiting...";

    public static final String MESSAGE_FAREWELL = " Bye. Hope to see you again soon ^.^!";

    public static final String MESSAGE_LIST_HEADER = " Woof! Here are your current tasks ^.^:";

    public static final String MESSAGE_MARK_TASK = " Woof! I've marked this task as completed for you:";

    public static final String MESSAGE_UNMARK_TASK = " Woof! I've marked this task as incompleted for you:";

    public static final String MESSAGE_DIVIDER = "___________________________________________________________________";

    public static final String MESSAGE_INVALID = " Sorry! Please type a valid command! ^.^";

    public static final String MESSAGE_PROMPT_VALID_STRING_INPUT = " Sorry! "
            + " You need a space between your command and task input!" + "\n"
            + " Please enter your task as such: <todo/deadline/event> <your task> ^.^";

    public static final String MESSAGE_PROMPT_VALID_DEADLINE = " Sorry! Please follow the deadline format!"
            + "\n" + " Format: " + "\n"
            + " deadline <your deadline> /by <due date and time in DD/MM/YYYY HH:MM (24HR clock)";

    public static final String MESSAGE_PROMPT_VALID_EVENT = " Sorry! Please follow the event format!"
            + "\n" + " Format: " + "\n"
            + " event <My Task> "
            + "/from <start date in DD/MM/YYYY HH:MM (24HR clock)> /to <end date in DD/MM/YYYY HH:MM (24HR clock)>";

    public static final String MESSAGE_PROMPT_VALID_TODO = " Sorry! Please follow the todo format!"
            + "\n" + " Format: " + "\n"
            + " todo <your task description>";
    public static final String MESSAGE_PROMPT_VALID_MARK = "Sorry! Please follow the mark format!"
            + "\n" + " Format: " + "\n"
            + " mark <task number> " + "\n" + "\n"
            + "the task number must be a valid number displayed from the task list!";
    public static final String MESSAGE_PROMPT_VALID_UNMARK = "Sorry! Please follow the unmark format!"
            + "\n" + " Format: " + "\n"
            + " unmark <task number> "  + "\n" + "\n"
            + "the task number must be a valid number displayed from the task list!";
    public static final String MESSAGE_PROMPT_VALID_DELETE = "Sorry! Please follow the delete format!"
            + "\n" + " Format: " + "\n"
            + " delete <task number> " + "\n" + "\n"
            + "the task number must be a valid number displayed from the task list!";
    public static final String MESSAGE_PROMPT_EMPTY_TASK_LIST = " Woof Woof! Your task list is empty! ^.^";

    public static final String MESSAGE_DELETE_TASK = " Woof! I've deleted this task for you:";

    public static final String MESSAGE_PROMPT_VALID_DATE = " Sorry! Please provide a valid date and time in "
            + "<DD/MM/YYYY>";
    public static final String MESSAGE_LIST_HEADER_WITH_DATE = " Woof! Here are your current tasks occurring on: ";
    public static final String MESSAGE_LIST_HEADER_WITH_KEYWORD = " Woof! Here are your matching tasks with keyword:  ";
    public static final String MESSAGE_PROMPT_VALID_FIND = " Sorry! Please follow the find format!"
            + "\n" + " Format: " + "\n"
            + " find <keyword> ";

}