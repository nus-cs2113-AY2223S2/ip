package duke.ui;

import duke.main.Storage;

/**
 * Enum that holds all the notification and help messages to be displayed.
 */
public enum Messages {
    LINE  ("_".repeat(72)),
    START ("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?"),
    EXIT  ("Bye. Hope to see you again soon!"),

    ADD_TASK   ("Got it. I've added this task:"),
    LIST_TASKS ("Here are the tasks in your list:"),
    EMPTY_LIST ("Your task list is currently empty."),
    MARK_TASK  ("Nice! I've marked this task as done:"),
    UNMARK_TASK("OK, I've marked this task as not done yet:"),
    TASK_COUNT ("Now you have %d task%s in your list."),
    DELETE_TASK("Noted. I've removed this task:"),
    FIND_TASKS ("Here are the matching tasks in your list:"),
    EMPTY_FIND ("No matching tasks were found."),

    TODO_HELP    ("Usage: todo <description>"),
    DEADLINE_HELP("Usage: deadline <description> /by <time>"),
    EVENT_HELP   ("Usage: event <description> /from <time> /by <time>"),
    MARK_HELP    ("Usage: mark <task index>"),
    UNMARK_HELP  ("Usage: unmark <task index>"),
    DELETE_HELP  ("Usage: delete <task index>"),
    SAVE_HELP    ("Check that you have read & write permissions for "
            + Storage.SAVE_PATH
            + " and do not modify it yourself."),
    FIND_HELP    ("Usage: find <search string>"),
    GENERIC_HELP ("Valid commands are: todo, deadline, event, list, mark, unmark, bye");

    public final String MESSAGE;

    /**
     * Constructs an enum that returns the requested message.
     *
     * @param message The message to be displayed.
     */
    Messages(String message) {
        MESSAGE = message;
    }
}
