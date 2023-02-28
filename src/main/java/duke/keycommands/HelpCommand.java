package duke.keycommands;

public class HelpCommand {

    private static final String HELP_USAGE = "help: to view the instructions for all commands";

    private static final String HELP_EXAMPLE = "Example: help\n";

    private static final String BYE_USAGE = "bye: to end the program";

    private static final String BYE_EXAMPLE = "Example: bye\n";

    private static final String LIST_USAGE = "list: to view all the tasks in your list";

    private static final String LIST_EXAMPLE = "Example: list\n";

    private static final String MARK_USAGE = "mark: to mark a task as done";

    private static final String MARK_FORMAT = "Format: mark {Number}";

    private static final String MARK_EXAMPLE = "Example: mark 1\n";

    private static final String UNMARK_USAGE = "unmark: to unmark a task as not done yet";

    private static final String UNMARK_FORMAT = "Format: unmark {Number}";

    private static final String UNMARK_EXAMPLE = "Example: unmark 1\n";

    private static final String TODO_USAGE = "todo: to add a todo task in your list";

    private static final String TODO_FORMAT = "Format: todo {your task}";

    private static final String TODO_EXAMPLE = "Example: todo read book\n";

    private static final String DEADLINE_USAGE = "deadline: to add a deadline task in your list";

    private static final String DEADLINE_FORMAT = "Format: deadline {your task} /by {deadline date}";

    private static final String DEADLINE_EXAMPLE = "Example: deadline return book /by 2020-08-08\n";

    private static final String EVENT_USAGE = "event: to add an event task in your list";

    private static final String EVENT_FORMAT = "Format: event {your task} /from {begin time} /to {end time}";

    private static final String EVENT_EXAMPLE = "Example: event project meeting /from 2020-08-08 2pm /to 2020-08-08 4pm\n";

    private static final String DELETE_USAGE = "delete: to delete a task";

    private static final String DELETE_FORMAT = "Format: delete {Number}";

    private static final String DELETE_EXAMPLE = "Example: delete 1\n";

    private static final String FIND_USAGE = "find: to find all the tasks' content that contain a piece of keyword";

    private static final String FIND_FORMAT = "Format: find {keyword}";

    private static final String FIND_EXAMPLE = "Example: find book\n";

    private static final String INTRODUCTION_TO_HELP_COMMAND = "This is the list of our commands\n";

    /**
     * Represents the command to guide the user on how to use the program.
     */
    public HelpCommand() {
        doHelpCommand();
    }

    /**
     * Prints out the instructions for all commands.
     */
    public void doHelpCommand() {
        System.out.println(INTRODUCTION_TO_HELP_COMMAND);
        System.out.println(HELP_USAGE);
        System.out.println(HELP_EXAMPLE);
        System.out.println(BYE_USAGE);
        System.out.println(BYE_EXAMPLE);
        System.out.println(LIST_USAGE);
        System.out.println(LIST_EXAMPLE);
        System.out.println(MARK_USAGE);
        System.out.println(MARK_FORMAT);
        System.out.println(MARK_EXAMPLE);
        System.out.println(UNMARK_USAGE);
        System.out.println(UNMARK_FORMAT);
        System.out.println(UNMARK_EXAMPLE);
        System.out.println(TODO_USAGE);
        System.out.println(TODO_FORMAT);
        System.out.println(TODO_EXAMPLE);
        System.out.println(DEADLINE_USAGE);
        System.out.println(DEADLINE_FORMAT);
        System.out.println(DEADLINE_EXAMPLE);
        System.out.println(EVENT_USAGE);
        System.out.println(EVENT_FORMAT);
        System.out.println(EVENT_EXAMPLE);
        System.out.println(DELETE_USAGE);
        System.out.println(DELETE_FORMAT);
        System.out.println(DELETE_EXAMPLE);
        System.out.println(FIND_USAGE);
        System.out.println(FIND_FORMAT);
        System.out.println(FIND_EXAMPLE);
    }

}
