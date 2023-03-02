package inu.commons;

/**
 * Container for messages shown to user.
 */
public class Messages {
    public static final String MESSAGE_GREETING = " Woof Woof! I'm Inu! Your personal Shib-Assistant! ^.^ + \n" + "\n"
            + " Allow me to provide more assistance through the <help> command!" + "\n"
            + " Format: help";
    public static final String MESSAGE_PROMPT = " What can I do for you today?";
    public static final String MESSAGE_EXIT = " Woof! Saving your task list before exiting...";
    public static final String MESSAGE_FAREWELL = " Bye. Hope to see you again soon ^.^!";
    public static final String MESSAGE_LIST_HEADER = " Woof! Here are your current tasks ^.^:";
    public static final String MESSAGE_MARK_TASK = " Woof! I've marked this task as completed for you:";
    public static final String MESSAGE_UNMARK_TASK = " Woof! I've marked this task as incompleted for you:";
    public static final String MESSAGE_DIVIDER = "___________________________________________________________________";
    public static final String MESSAGE_INVALID = " Sorry! Please type a valid command! ^.^" + "\n"
            + " Allow me to provide more assistance through the <help> command!" + "\n"
            + " Format: help";
    public static final String MESSAGE_PROMPT_VALID_DEADLINE = " Sorry! Please follow the deadline format!"
            + "\n" + " Format: " + "\n"
            + " deadline <your deadline> /by <due date and time in DD/MM/YYYY HH:MM> (24HR clock)";
    public static final String MESSAGE_PROMPT_VALID_EVENT = " Sorry! Please follow the event format!"
            + "\n" + " Format: " + "\n"
            + " event <My Task> "
            + "/from <start date in DD/MM/YYYY HH:MM (24HR clock)> /to <end date in DD/MM/YYYY HH:MM> (24HR clock)";
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
    public static final String MESSAGE_PROMPT_VALID_DEADLINE_DATE = " Sorry! Please provide a valid date and time in "
            + "<DD/MM/YYYY HH:mm>" + "\n" + "\n"
            + " The date provided cannot be in the past!";
    public static final String MESSAGE_PROMPT_VALID_EVENT_DATE = " Sorry! Please provide a valid date and time in "
            + "<DD/MM/YYYY HH:mm>" + "\n" + "\n"
            + " 1. The date provided cannot be in the past!" + "\n"
            + " 2. The event's starting date cannot be after its ending date!";
    public static final String MESSAGE_LIST_HEADER_WITH_DATE = " Woof! Here are your current tasks occurring on: ";
    public static final String MESSAGE_LIST_HEADER_WITH_KEYWORD = " Woof! Here are your matching tasks with keyword:  ";
    public static final String MESSAGE_PROMPT_VALID_FIND = " Sorry! Please follow the find format!"
            + "\n" + " Format: " + "\n"
            + " find <keyword> ";
    public static final String MESSAGE_HELP = " 1. Adding a Todo: todo\n" +
            "    Adds a todo to the task list.\n" +
            "\n" +
            "    Format: todo <description>\n" + MESSAGE_DIVIDER +
            "\n" +
            " 2. Adding a Deadline: deadline\n" +
            "    Adds a Deadline to the task list.\n" +
            "\n" +
            "    Format: deadline <description> /by <due date and time>" + "\n" +
            "    Date and Time in dd/mm/yyyy HH:mm (24 hour clock) format!" + "\n" + MESSAGE_DIVIDER +
            "\n" +
            " 3. Adding an Event: event\n" +
            "    Adds an Event to the task list.\n" +
            "\n" +
            "    Format: event <description> /from <from date and time> /to <to date and time>" + "\n" +
            "    Date and Time in dd/mm/yyyy HH:mm (24 hour clock) format!" + "\n" + MESSAGE_DIVIDER +
            "\n" +
            " 4. Listing all tasks: list" + "\n" +
            "    Lists all the task in the tasks list." + "\n" +
            "\n" +
            "    Format: list" + "\n" + MESSAGE_DIVIDER +
            "\n" +
            " 5. Listing all tasks occurring on a specific date: list <date>" + "\n" +
            "    Lists all the task occurring on a specific date in the tasks list." + "\n" +
            "\n" +
            "    Format: list <date>." + "\n" +
            "\n" +
            "    Format of date is dd/mm/yyyy." + "\n" +
            "    No provision of date lists all tasks instead." + "\n" + MESSAGE_DIVIDER +
            "\n" +
            " 6. Finding tasks: find" + "\n" +
            "    Find tasks with a keyword." + "\n" +
            "\n" +
            "    Format: find <keyword>" + "\n" + MESSAGE_DIVIDER +
            "\n" +
            " 7. Marking a task: mark" + "\n" +
            "    Marks a task as done." + "\n" +
            "\n" +
            "    Format: mark <index>" + "\n" +
            "\n" +
            "    Marks the task at the specified <index>" + "\n" + MESSAGE_DIVIDER +
            "\n" +
            " 8. Unmarking a task: unmark" + "\n" +
            "    Unmarks a task, set is as not done." + "\n" +
            "\n" +
            "    Format: unmark <index>" + "\n" +
            "\n" +
            "    Unmarks the task at the specified <index>" + "\n" + MESSAGE_DIVIDER +
            "\n" +
            " 9. Deleting a task: delete" + "\n" +
            "    Deletes a task from the task list." + "\n" +
            "\n" +
            "    Format: delete <index>\n" +
            "\n" +
            "    Deletes the task at the specified <index>\n" + MESSAGE_DIVIDER +
            "\n" +
            " 10. Exiting the program: bye\n" +
            "    Exits the program.\n" +
            "\n" +
            "    Format: bye\n" + MESSAGE_DIVIDER +
            "\n" +
            " 11. Saving the data\n" +
            "    Inu data are saved in the hard disk automatically after each successful command." + "\n" +
            "    There is no need to save manually.";

}