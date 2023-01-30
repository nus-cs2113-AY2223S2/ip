/*
* Use this class to store text output for PAPA.
* All of these are constants.
* Use this class to verify input as well.
*/
public final class Command {

    /**
     * Inputs go here
     */
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_TASK_TODO = "todo";
    private static final String COMMAND_TASK_DEADLINE = "deadline";
    private static final String COMMAND_TASK_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";

    public static final String MESSAGE_HELP = "PAPA is your personal task tracker. Options:\n" +
            "Add tasks: \n" +
            String.format("   %s <task_name>\n", COMMAND_TASK_TODO) +
            String.format("   %s <task_name> /by <due_date>\n", COMMAND_TASK_DEADLINE) +
            String.format("   %s <task_name> /from <start> /to <end>\n", COMMAND_TASK_EVENT) +
            String.format("%s        Show these tips.\n", COMMAND_HELP) +
            String.format("%s        List out existing tasks.\n", COMMAND_LIST) +
            String.format("%s         Exit PAPA.\n", COMMAND_BYE) +
            String.format("%s <n>    Mark the n-th task as done.\n", COMMAND_MARK) +
            String.format("%s <n>  Mark the n-th task as undone.", COMMAND_UNMARK);

    public static final String MESSAGE_LOGO = "██████╗  █████╗ ██████╗  █████╗ \n" +
            "██╔══██╗██╔══██╗██╔══██╗██╔══██╗\n" +
            "██████╔╝███████║██████╔╝███████║\n" +
            "██╔═══╝ ██╔══██║██╔═══╝ ██╔══██║\n" +
            "██║     ██║  ██║██║     ██║  ██║\n" +
            "╚═╝     ╚═╝  ╚═╝╚═╝     ╚═╝  ╚═╝";

    public static final String MESSAGE_INTRO = "Hello! I'm PAPA, your Personal Assistant, Personal Angel.\n"
            + "What can I do for you? Type 'help' for a list of commands.";

    public static final String MESSAGE_OUTRO = "Bye. Hope to see you again soon!";

    public static final String MESSAGE_TASKS_EMPTY = "Looks like you don't have anything to do. Nice!";
    public static final String MESSAGE_TASK_DONE = "Great! This task is now done: ";
    public static final String MESSAGE_TASK_UNDONE = "Okay, I've marked this task as not done yet: ";

    /**
     * Prints a horizontal line of 32 '=' characters.
     */
    public static void printHLine() {
        System.out.println("================================");
    }

    /**
     * Greets the user.
     */
    public static void greet() {
        System.out.println(MESSAGE_LOGO);
        // Greet message
        printHLine();
        System.out.println(MESSAGE_INTRO);
        printHLine();
    }

    /**
     * Exit, goodbye message.
     */
    public static void exit() {
        System.out.println(MESSAGE_OUTRO);
        printHLine();
    }

    /**
     * Split input into 1st arg and subsequent line.
     * Taken from Lecture - Contacts (Wk 4 content)
     * @param rawUserInput A line of input.
     */
    private static String[] splitCommandAndArgs(String rawUserInput) {
        // Trim whitespace, then split once. Output is [command, args].
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[]{split[0], ""}; // else case: no parameters
    }
}
