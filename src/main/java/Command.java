/*
* Use this class to store text output for PAPA.
* All of these are constants.
* Use this class to verify input as well.
* Got some inspiration from Contacts (although not in a bad way)
*/
public final class Command {
    /** ==============================================================
     *  This section will cover inputs, and input validation methods
     *  ==============================================================
     */
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";

    /** Add task commands. Sample usage as below. not delimited by spaces.
     *  todo: "todo task_name"
     *  deadline: "deadline task_name /by due_date"
     *  event: "event task_name /from start_time /to end_time"
     */
    private static final String COMMAND_TASK_TODO = "todo";
    private static final String COMMAND_TASK_DEADLINE = "deadline";
    private static final String COMMAND_TASK_DEADLINE_DELIMITER = "/by";
    private static final String COMMAND_TASK_EVENT = "event";
    private static final String COMMAND_TASK_EVENT_DELIMITER1 = "/from";
    private static final String COMMAND_TASK_EVENT_DELIMITER2 = "/to";

    /**
     * Split input into 1st arg and subsequent line.
     * @param inputLine A line of input direct from user
     */
    public static String[] splitCommandAndArgs(String inputLine) {
        // Trim whitespace, then split once. Output is [command, args].
        final String[] split = inputLine.trim().split("\\s+", 2);
        // Else case: no parameters
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    /**
     * Performs each input's methods as long as it exists, if not return error
     * @param inputLine Input directly from command-line
     * @return Feedback string or error string
     */
    public static String executeCommand(String inputLine) {
        final String[] commandTypeAndArgs = splitCommandAndArgs(inputLine);
        final String command = commandTypeAndArgs[0];
        final String commandArgs = commandTypeAndArgs[1];

        // Check command against the set list of commands.
        // If it doesn't exist, default is invalid
        switch(command) {
        case COMMAND_HELP:
            return MESSAGE_HELP;
        case COMMAND_LIST:
            return TaskList.getTaskListString();
        case COMMAND_MARK: // Fallthrough
        case COMMAND_UNMARK:
            return TaskList.executeMarkUnmark(command, commandArgs);
        case COMMAND_TASK_TODO:
            // executeTaskTodo(commandArgs);
        case COMMAND_TASK_DEADLINE:
            // executeTaskDeadline(commandArgs);
        case COMMAND_TASK_EVENT:
            //executeTaskEvent(commandArgs);
            // add new task WIP
            return "WIP";
        case COMMAND_BYE:
            printExitMessage();
            System.exit(0);
            // Fallthrough (If somehow cannot exit? LOL)
        default:
            return MESSAGE_INVALID_COMMAND;
        }
    }
    /** ==============================================================
     * Below are Outputs for PAPA to print.
     * Note string format placeholders, so ensure to use printf/format.
     * ==============================================================
     */
    public static final String MESSAGE_HELP = "PAPA is your personal task tracker. Options:\n" +
            "Add tasks: \n" +
            String.format("   %s <task_name>\n", COMMAND_TASK_TODO) +
            String.format("   %s <task_name> %s <due_date>\n",
                    COMMAND_TASK_DEADLINE, COMMAND_TASK_DEADLINE_DELIMITER) +
            String.format("   %s <task_name> %s <start> %s <end>\n",
                    COMMAND_TASK_EVENT, COMMAND_TASK_EVENT_DELIMITER1, COMMAND_TASK_EVENT_DELIMITER2) +
            String.format("%s        Show these tips.\n", COMMAND_HELP) +
            String.format("%s        List out existing tasks.\n", COMMAND_LIST) +
            String.format("%s         Exit PAPA.\n", COMMAND_BYE) +
            String.format("%s <n>    Mark the n-th task as done.\n", COMMAND_MARK) +
            String.format("%s <n>  Mark the n-th task as undone.\n", COMMAND_UNMARK);

    public static final String MESSAGE_LOGO =
            "██████╗  █████╗ ██████╗  █████╗ \n" +
            "██╔══██╗██╔══██╗██╔══██╗██╔══██╗\n" +
            "██████╔╝███████║██████╔╝███████║\n" +
            "██╔═══╝ ██╔══██║██╔═══╝ ██╔══██║\n" +
            "██║     ██║  ██║██║     ██║  ██║\n" +
            "╚═╝     ╚═╝  ╚═╝╚═╝     ╚═╝  ╚═╝";
    public static final String MESSAGE_INTRO =
            "Hello! I'm PAPA, your Personal Assistant, Personal Angel.\n" +
            "What can I do for you? Type 'help' for a list of commands.\n";
    public static final String MESSAGE_OUTRO = "Bye. Hope to see you again soon!\n";
    private static final String MESSAGE_TASK_ADDED =
            "Great! I've added the task for you: ";
    private static final String MESSAGE_TASK_COUNT =
            "Now you have %d tasks"; // Format at runtime.
    public static final String MESSAGE_TASKS_EMPTY =
            "Looks like you don't have anything to do. Nice!\n";
    public static final String MESSAGE_TASK_DONE =
            "Great! This task is now done: ";
    public static final String MESSAGE_TASK_UNDONE =
            "Okay, I've marked this task as not done yet: ";
    private static final String MESSAGE_INVALID_COMMAND =
            "Oops, I don't understand you. Type 'help' for commands you can use!\n";
    public static final String MESSAGE_INVALID_TASKNUMBER =
            "Erm, do make sure to give me the correct task number." +
            "Type 'task' to list out the existing tasks!\n";

    // Prints a horizontal line of 32 '=' characters.
    public static void printHLine() {
        System.out.println("================================");
    }

    // Greets the user.
    public static void printGreeting() {
        System.out.println(MESSAGE_LOGO);
        // Greet message
        printHLine();
        System.out.println(MESSAGE_INTRO);
        printHLine();
    }

    // Exit, goodbye message.
    public static void printExitMessage() {
        System.out.println(MESSAGE_OUTRO);
        printHLine();
    }

    // Announce the number of tasks.
    public static void printTaskAdded(Task task) {
        System.out.println(MESSAGE_TASK_ADDED);
        System.out.println(task);
        System.out.println("Total number of tasks: " + TaskList.getNumberOfTasks());
    }
}
