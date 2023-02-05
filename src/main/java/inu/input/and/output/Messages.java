package inu.input.and.output;

public class Messages {

    public static final String MESSAGE_GREETING = " Woof Woof! I'm Inu! Your personal Shib-Assistant ^.^";

    public static final String MESSAGE_PROMPT = " What can I do for you today?";

    public static final String MESSAGE_FAREWELL = " Bye. Hope to see you again soon ^.^!";

    public static final String MESSAGE_LIST_HEADER = " Woof! Here are your current tasks ^.^:";

    public static final String MESSAGE_MARK_TASK = " Woof! I've marked this task as completed for you:";

    public static final String MESSAGE_UNMARK_TASK = " Woof! I've marked this task as incompleted for you:";

    public static final String MESSAGE_DIVIDER = "___________________________________________________________________";

    public static final String MESSAGE_INVALID = " Sorry! Please type a valid command! ^.^";

    public static final String MESSAGE_PROMPT_VALID_INPUT = " Sorry! "
            + "You need a space between your command and task input!" + "\n"
            + "Please enter your task as such: <todo/deadline/event> <your task> ^.^";

    public static final String MESSAGE_PROMPT_VALID_DEADLINE = " Sorry! Please indicate the deadline first"
            + "\n" + "And its corresponding due date with </>! "
            + "\n" + "Format: deadline <your deadline> /<due date>";

    public static final String MESSAGE_PROMPT_VALID_EVENT = " Sorry! "
            + "Please indicate your event first, following with when it starts and ends with </>!"
            + "\n" + "Format: event <My Task> /<from when> /<to when>";

    public static final String MESSAGE_PROMPT_VALID_TASK_ENTRY = " Sorry! "
            + "Please provide some information about your task! ^.^";

    public static final String MESSAGE_PROMPT_VALID_DEAD_LINE_ENTRY_AFTER_SLASH = " Sorry! "
            + "Please provide some information on your deadline" + "\n"
            + "Namely, when it is due following the </> symbol! ^.^";

    public static final String MESSAGE_PROMPT_VALID_EVENT_ENTRY_AFTER_SLASH = " Sorry! "
            + "Please provide some information on your event" + "\n"
            + "Namely, when it starts and ends after following the </> symbol respectively! ^.^" + "\n"
            + "For example: /<from when> /<to when>";

    public static final String MESSAGE_PROMPT_VALID_MARK_AND_UN_MARK_INDEX = " Sorry! "
            + "This task does not exist in your list! ^.^";

    public static final String MESSAGE_PROMPT_VALID_MARK_AND_UN_MARK = " Sorry! "
            + "You need a space between your command and mark or unmark input!" + "\n"
            + "Please enter your task to mark or unmark as such: <mark/unmark> <task number> ^.^";

    public static final String MESSAGE_PROMPT_VALID_MARK_ENTRY = " Sorry! "
            + "Please provide some information on which task you would like to mark as complete! ^.^";

    public static final String MESSAGE_PROMPT_VALID_UN_MARK_ENTRY = " Sorry! "
            + "Please provide some information on which task you would like to mark as incomplete! ^.^";


    public static final String MESSAGE_PROMPT_EMPTY_TASK_LIST= " Woof Woof! Your task list is empty! ^.^";

}