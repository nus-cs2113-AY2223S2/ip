package duke.keycommands;

public class HelpCommand {
    private static final String HELP_USAGE = "help: to view the instructions for all commands\n";
    private static final String BYE_USAGE = "bye: to end the program\n";
    private static final String LIST_USAGE = "list: to view the all the tasks\n";
    private static final String MARK_USAGE = "mark: to mark a task as done";
    private static final String MARK_FORMAT = "Format: mark {Number}\n";
    private static final String UNMARK_USAGE = "unmark: to unmark a task as not done yet";
    private static final String UNMARK_FORMAT = "Format: unmark {Number}\n";
    private static final String TODO_USAGE = "todo: to add a todo task in your list";
    private static final String TODO_FORMAT = "Format: todo {your task}\n";
    private static final String DEADLINE_USAGE = "deadline: to add a deadline task in your list";
    private static final String DEADLINE_FORMAT = "Format: deadline {your task} /by {deadline date}\n";
    private static final String EVENT_USAGE = "event: to add an event task in your list";
    private static final String EVENT_FORMAT = "Format: event {your task} /from {begin date} /to {end date}\n";
    private static final String DELETE_USAGE = "delete: to delete a task";
    private static final String DELETE_FORMAT = "Format: delete {Number}\n";
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
        System.out.println(BYE_USAGE);
        System.out.println(LIST_USAGE);
        System.out.println(MARK_USAGE);
        System.out.println(MARK_FORMAT);
        System.out.println(UNMARK_USAGE);
        System.out.println(UNMARK_FORMAT);
        System.out.println(TODO_USAGE);
        System.out.println(TODO_FORMAT);
        System.out.println(DEADLINE_USAGE);
        System.out.println(DEADLINE_FORMAT);
        System.out.println(EVENT_USAGE);
        System.out.println(EVENT_FORMAT);
        System.out.println(DELETE_USAGE);
        System.out.println(DELETE_FORMAT);
    }

}
