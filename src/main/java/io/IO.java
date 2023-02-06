package io;

import task.Task;
import task.TaskList;

/*
* Use this class for INPUT and OUTPUT (IO) related stuff
* Got some inspiration from Contacts (Week 4)
*/
public final class IO {
    /**
     * ==============================================================
     * This section will cover Inputs,
     * Input Validations,
     * and Input Processing Methods
     * ==============================================================
     */
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";

    /** Add task commands. Sample usage as below. not delimited by spaces.
     *  todo: "todo task_name"
     *  deadline: "deadline task_name /by due_date"
     *  event: "event task_name /from start_time /to end_time"
     */
    public static final String COMMAND_TASK_TODO = "todo";
    public static final String COMMAND_TASK_DEADLINE = "deadline";
    public static final String COMMAND_TASK_DEADLINE_DELIMITER = "/by";
    public static final String COMMAND_TASK_DEADLINE_DELIMITER_REGEX =
            "\\s+\\" + COMMAND_TASK_DEADLINE_DELIMITER + "\\s+";
    public static final String COMMAND_TASK_EVENT = "event";
    public static final String COMMAND_TASK_EVENT_DELIMITER1 = "/from";
    public static final String COMMAND_TASK_EVENT_DELIMITER2 = "/to";
    public static final String COMMAND_TASK_EVENT_DELIMITER_REGEX =
            "\\s+\\" + COMMAND_TASK_EVENT_DELIMITER1 +
            "\\s+|\\s+\\" + COMMAND_TASK_EVENT_DELIMITER2 + "\\s+";

    /**
     * Split input into 1st arg and subsequent line.
     * @param inputLine A line of input direct from user
     */
    public static String[] splitCommandAndArgs(String inputLine) {
        // Trim trailing and beginning whitespace, then split once. Output is [command, args].
        final String[] split = inputLine.trim().split("\\s+", 2);
        // Else case: no parameters
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    /**
     * Input validation for Todo Task. Check if input empty.
     * @param commandArgs Line input, should simply be the task name
     * @return the same input, unless blank
     * @throws DukeException only if blank
     */
    public static String processTaskTodo(String commandArgs) throws DukeException {
        if (commandArgs.isBlank()) {
            throw new DukeException();
        }
        return commandArgs;
    }

    public static String[] processTaskDeadline(String commandArgs) throws DukeException {
        String[] deadlineArgs = commandArgs.split(COMMAND_TASK_DEADLINE_DELIMITER_REGEX);

        // Needs to be specifically 2, i.e. [task_name, deadline]
        // Is this a magic number?
        if (deadlineArgs.length != 2) {
            throw new DukeException();
        }
        return deadlineArgs;
    }

    public static String[] processTaskEvent(String commandArgs) throws DukeException {
        // ensure it contains both.
        if (!commandArgs.contains(COMMAND_TASK_EVENT_DELIMITER1) ||
                !commandArgs.contains(COMMAND_TASK_EVENT_DELIMITER2)) {
            throw new DukeException();
        }

        // TODO check for inputs like /from abc /from abc /to
        String[] eventArgs = commandArgs.split(COMMAND_TASK_EVENT_DELIMITER_REGEX);

        // Needs to be specifically 3, i.e. [task_name, from, to]
        if (eventArgs.length != 3) {
            throw new DukeException();
        }
        return eventArgs;
    }

    /**
     * ==============================================================
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
            String.format("%s <n>  Mark the n-th task as undone.", COMMAND_UNMARK);

    public static final String MESSAGE_LOGO =
            "██████╗  █████╗ ██████╗  █████╗ \n" +
            "██╔══██╗██╔══██╗██╔══██╗██╔══██╗\n" +
            "██████╔╝███████║██████╔╝███████║\n" +
            "██╔═══╝ ██╔══██║██╔═══╝ ██╔══██║\n" +
            "██║     ██║  ██║██║     ██║  ██║\n" +
            "╚═╝     ╚═╝  ╚═╝╚═╝     ╚═╝  ╚═╝";
    public static final String MESSAGE_INTRO =
            "Hello! I'm PAPA, your Personal Assistant, Personal Angel." +
            "What can I do for you? Type 'help' for a list of commands.";
    public static final String MESSAGE_OUTRO = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_TASK_ADDED =
            "Great! I've added the task for you: ";
    public static final String MESSAGE_TASK_COUNT =
            "Now you have %d tasks"; // Format at runtime.
    public static final String MESSAGE_TASK_DONE =
            "Great! This task is now done: ";
    public static final String MESSAGE_TASK_UNDONE =
            "Okay, I've marked this task as not done yet: ";
    public static final String ERROR_TASKS_EMPTY =
            "Looks like you don't have anything to do. Nice!";
    public static final String ERROR_MESSAGE_HELP =
            String.format("Type %s for information on commands!", COMMAND_HELP);
    public static final String ERROR_MESSAGE_INVALID_COMMAND =
            "Oops, I don't understand you.\n" + ERROR_MESSAGE_HELP;
    public static final String ERROR_MESSAGE_ARGUMENT_MISSING =
            "I think you might be missing something.\n" + ERROR_MESSAGE_HELP;
    public static final String ERROR_MESSAGE_ARGUMENT_NUMBER =
            "You might be missing some arguments, or have too many.\n" + ERROR_MESSAGE_HELP;
    public static final String ERROR_MESSAGE_TASK_INDEX =
            "Erm, do make sure to give me the correct task number." +
            "Type 'task' to list out the existing tasks!";


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
    public static String feedbackTaskAdded(Task task) {
        String output = MESSAGE_TASK_ADDED + '\n' + task + '\n';
        output += "Total number of tasks: " + TaskList.getNumberOfTasks();
        return output;
    }
}
