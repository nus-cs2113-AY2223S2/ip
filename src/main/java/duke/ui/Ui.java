package duke.ui;

import duke.task.TaskList;

/**
 * Class containing methods to display various messages.
 */
public abstract class Ui {
    /**
     * Returns the string displayed when adding/deleting tasks, which includes the current task count.
     *
     * @param taskListSize The size of the task list.
     * @return The string displayed when adding/deleting tasks.
     */
    private static String getTaskCountString(int taskListSize) {
        return String.format(Messages.TASK_COUNT.MESSAGE, taskListSize, taskListSize == 1 ? "" : "s");
    }

    /**
     * Prints out the given strings, each on a new line.
     *
     * @param strings The strings to be printed out, can be provided as multiple arguments or as an array.
     */
    public static void print(String... strings) {
        System.out.println(Messages.LINE.MESSAGE);
        for (String string : strings) {
            System.out.println(string);
        }
        System.out.println(Messages.LINE.MESSAGE);
    }

    /**
     * Displays the startup message.
     */
    public static void printStartMessage() {
        print(Messages.START.MESSAGE);
    }

    /**
     * Displays the shutdown message.
     */
    public static void printExitMessage() {
        print(Messages.EXIT.MESSAGE);
    }

    /**
     * Displays the task added message, the task that was just added, and the updated task count.
     *
     * @param taskString The string representation of the task that was just added to the task list.
     * @param taskList The task list that the task was added to, needed for the task count message.
     */
    public static void printAddTaskMessage(String taskString, TaskList taskList) {
        Ui.print(Messages.ADD_TASK.MESSAGE, taskString, getTaskCountString(taskList.size()));
    }

    /**
     * Displays the task deleted message, the task that was just deleted, and the updated task count.
     *
     * @param taskString The string representation of the task that was just added to the task list.
     * @param taskList The task list that the task was added to, needed for the task count message.
     */
    public static void printDeleteTaskMessage(String taskString, TaskList taskList) {
        Ui.print(Messages.DELETE_TASK.MESSAGE, taskString, getTaskCountString(taskList.size()));
    }

    /**
     * Displays the task marked or unmarked message depending on whether it was marked as done or not.
     *
     * @param taskString The string representation of the task whose completion status was just changed.
     * @param isDone Whether the task was marked as done or not.
     */
    public static void printMarkTaskMessage(String taskString, boolean isDone) {
        Ui.print(isDone ? Messages.MARK_TASK.MESSAGE : Messages.UNMARK_TASK.MESSAGE, taskString);
    }

    /**
     * Displays the error message and relevant help message when an error occurs.
     *
     * @param errorMessage The error message to be displayed.
     * @param command Type of command that caused the error, to identify which help message to display.
     */
    public static void printError(String errorMessage, String command) {
        switch (command) {
        case "todo":
            print(errorMessage, Messages.TODO_HELP.MESSAGE);
            return;
        case "deadline":
            print(errorMessage, Messages.DEADLINE_HELP.MESSAGE);
            return;
        case "event":
            print(errorMessage, Messages.EVENT_HELP.MESSAGE);
            return;
        case "mark":
            print(errorMessage, Messages.MARK_HELP.MESSAGE);
            return;
        case "unmark":
            print(errorMessage, Messages.UNMARK_HELP.MESSAGE);
            return;
        case "delete":
            print(errorMessage, Messages.DELETE_HELP.MESSAGE);
            return;
        case "save":
            print(errorMessage, Messages.SAVE_HELP.MESSAGE);
            return;
        case "find":
            print(errorMessage, Messages.FIND_HELP.MESSAGE);
            return;
        default:
            print(errorMessage, Messages.GENERIC_HELP.MESSAGE);
        }
    }
}
