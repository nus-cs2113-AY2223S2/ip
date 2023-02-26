package duke;

public class Ui {
    public static final String LINE = "____________________________________________________________\n";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!\n";

    /**
     * Prints greeting message when program is ran.
     */
    public static void printGreetMessage() {
        System.out.println(LINE + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + LINE);
    }

    /**
     * Prints message after adding To-Do/Deadline/Event task.
     *
     * @param tasks the current list of tasks
     */
    public static void printTaskMessage(TaskList tasks) {
        System.out.println(LINE
                + "Got it. I've added this duke.task:\n"
                + tasks.getTask(tasks.getTaskListSize() - 1) + "\n"
                + "Now you have " + tasks.getTaskListSize() + " in the list.\n"
                + LINE);
    }

    /**
     * Prints exiting message before program stops
     */
    public static void printByeMessage() {
        System.out.println(BYE_MESSAGE);
    }

    /**
     * Prints message if user input was not understandable by program.
     */
    public static void printIllegalInputMessage() {
        System.out.println(LINE
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + LINE);
    }

    /**
     * Prints message if user input has an empty description.
     *
     * @param task the task that was inputted by user
     */
    public static void printEmptyInputMessage(String task) {
        System.out.println(LINE
                + "☹ OOPS!!! The description of " + task + " cannot be empty.\n"
                + LINE);
    }

    /**
     * Prints message if user has inputted an index not found in list.
     */
    public static void printIllegalIndexMessage() {
        System.out.println("Sorry unable to locate such a task.\n");
    }

    /**
     * Prints all the items in the list
     *
     * @param tasks the current list of tasks
     */
    public static void printList(TaskList tasks) {
        System.out.println(LINE + "Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.getTaskListSize(); ++i) {
            System.out.println(i + 1 + ". " + tasks.getTask(i));
        }
        System.out.println(LINE);
    }

    /**
     * Prints message after user deletes specific task in the task list.
     *
     * @param tasks the current list of tasks
     * @param index the index of the task to be removed
     */
    public static void printDeletedMessage(TaskList tasks, int index) {
        System.out.println(LINE + "Noted. I've removed this task:\n" + tasks.getTask(index)
                + "\nNow you have " + (tasks.getTaskListSize() - 1) + " task(s) in the list\n" + LINE);
    }

    /**
     * Prints message if error has occurred when syncing with txt file.
     */
    public static void printInitialiseErrorMessage() {
        System.out.println("Unable to initialise database\n");
    }

    /**
     * Prints message if error has occurred while syncing with txt file when deleting task.
     */
    public static void printDeletingErrorMessage() {
        System.out.println("Error encountered when deleting task in memory");
    }

    /**
     * Prints message after user has marked/un-marked a specific task in the task list.
     *
     * @param tasks         the current list of tasks
     * @param command       the input command given by user
     * @param indexToChange the index to be marked/un-marked
     */
    public static void printMarkOrUnmarkMessage(TaskList tasks, String command, int indexToChange) {
        if (command.equals("mark")) {
            tasks.getTask(indexToChange).setDone();
            System.out.println(LINE
                    + "Nice! I've marked this duke.task as done:\n"
                    + tasks.getTask(indexToChange) + "\n"
                    + LINE);
        } else {
            tasks.getTask(indexToChange).setUndone();
            System.out.println(LINE
                    + "OK, I've marked this duke.task as not done yet:\n"
                    + tasks.getTask(indexToChange) + "\n"
                    + LINE);
        }
    }

    public static void printFindMessage() {
        System.out.println(LINE + "Here are the matching tasks in your list:\n");
    }
    public static void printFoundNothingMessage() {
        System.out.println("Unable to find any matching task.");
    }

    public static void printLine() {
        System.out.println(LINE);
    }
}
