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

    public static final String MESSAGE_PROMPT_VALID_EVENT = " Sorry! Please indicate your event format!"
            + "\n" + " Format: " + "\n"
            + " event <My Task> "
            + "/from <start date in DD/MM/YYYY HH:MM (24HR clock)> /to <end date in DD/MM/YYYY HH:MM (24HR clock)>";

    public static final String MESSAGE_PROMPT_VALID_TASK_ENTRY = " Sorry! "
            + "Please provide some information about your task! ^.^";

    public static final String MESSAGE_PROMPT_VALID_BY_DATE_ENTRY_AFTER_SLASH = " Sorry! "
            + "You did not provide information on your deadline!" + "\n"
            + " Please include when it is due! ^.^";

    public static final String MESSAGE_PROMPT_VALID_FROM_AND_TO_ENTRY_AFTER_SLASH = " Sorry! "
            + "You did not provide information on your deadline!" + "\n"
            + " Please include when it begins and ends! ^.^";

    public static final String MESSAGE_PROMPT_VALID_TASK_INDEX = " Sorry! "
            + "This task does not exist in your list! ^.^";

    public static final String MESSAGE_PROMPT_VALID_INTEGER_INPUT = " Sorry! "
            + "You need a space between your command and task number to mark/unmark/delete!" + "\n"
            + " Please enter your task to mark or unmark as such: <mark/unmark/delete> <task number> ^.^";

    public static final String MESSAGE_PROMPT_VALID_MARK_ENTRY = " Sorry! "
            + "Please provide some information on which task you would like to mark as complete! ^.^";

    public static final String MESSAGE_PROMPT_VALID_UN_MARK_ENTRY = " Sorry! "
            + "Please provide some information on which task you would like to mark as incomplete! ^.^";

    public static final String MESSAGE_PROMPT_VALID_DELETE_ENTRY = " Sorry! "
            + "Please provide some information on which task you would like to delete! ^.^";

    public static final String MESSAGE_PROMPT_EMPTY_TASK_LIST = " Woof Woof! Your task list is empty! ^.^";

    public static final String MESSAGE_DELETE_TASK = " Woof! I've deleted this task for you:";

    public static final String MESSAGE_PROMPT_VALID_DATE = " Sorry! Please provide a valid date and time in "
            + "<DD/MM/YYYY>";

    public static final String MESSAGE_PROMPT_VALID_DATE_TIME = " Sorry! Please provide a valid date and time in "
            + "<DD/MM/YYYY HH:MM> (24HR clock format)";

    public static final String MESSAGE_LIST_HEADER_WITH_DATE = " Woof! Here are your current tasks occurring on: ";

    public static final String MESSAGE_LIST_HEADER_WITH_KEYWORD = " Woof! Here are your matching tasks with keyword:  ";

    public static final String MESSAGE_PROMPT_FOR_INDEX_INPUT = " Sorry! Please provide a task number!";

    public static final String MESSAGE_PROMPT_VALID_KEYWORD = " Sorry! Please provide a key word!";

}