package duke;

import java.util.ArrayList;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private static String DIVIDER_LINE = "______________________________\n";

    /**
     * Prints a message to greet the user
     */
    public static void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greet = DIVIDER_LINE
                + "Hello! I'm Duke\n"
                + "What can i do for you\n"
                + DIVIDER_LINE;
        System.out.println(greet);
    }

    /**
     * Prints a message to indicate exiting the programme
     */
    public static void exitProgram() {
        System.out.println(DIVIDER_LINE + "Bye. Hope to see you again soon!\n" + DIVIDER_LINE);
    }

    /**
     * List all the tasks stored
     * @param tasks the Arraylist storing the tasks
     * @param taskCount total number of tasks
     */
    public static void listTasks(ArrayList<Task> tasks, int taskCount) {
        System.out.print(DIVIDER_LINE);
        for (int i = 0; i < taskCount; i += 1) {
            System.out.println(Integer.toString(i + 1) + ". " +tasks.get(i).toString());
        }
        System.out.println(DIVIDER_LINE);
    }

    /**
     * prints the results of finding the keyword
     * @param tasks the Arraylist storing the tasks
     * @param taskToFind the keyword to be found
     */
    public static void printFound(ArrayList<Task> tasks, String taskToFind) {
        System.out.println(DIVIDER_LINE + "Here are the matching tasks in your list:");
        for (Task t : tasks) {
            if (t.description.contains(taskToFind)) {
                System.out.println(t.toString());
            }
        }
        System.out.println(DIVIDER_LINE);
    }

    /**
     * prints the task just marked as done or undone
     * @param tasks the Arraylist storing the tasks
     * @param action the action to indicate mark or unmark
     */
    public static void printMarked(Task tasks, String action) {
        System.out.println(DIVIDER_LINE + "Nice! I've marked this task as "+ (action.equals("mark") ? "done:" : "undone:")
                + "\n" + tasks.toString() + "\n" + DIVIDER_LINE);
    }

    /**
     * prints the added task details
     * @param tasks the Arraylist storing the tasks
     * @param taskCount the total number of tasks
     */
    public static void printAdded(ArrayList<Task> tasks, int taskCount) {
        System.out.println(DIVIDER_LINE + "added:\n" + tasks.get(taskCount).toString() + "\n" + DIVIDER_LINE);
    }

    /**
     * prints the task just deleted
     * @param deletedTask the task that just got deleted
     */
    public static void printDeleted(Task deletedTask) {
        System.out.println(DIVIDER_LINE + "deleted:\n" + deletedTask.toString() + "\n" + DIVIDER_LINE);
    }

    /**
     * prints the total number of tasks
     * @param taskCount total number of tasks
     */
    public static void printNumTask(int taskCount) {
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

    /**
     * Print a message to indicate a wrong command word
     */
    public static void printWrongCommand() {
        System.out.println(DIVIDER_LINE + "Sorry! I don't know what that means.\n" + DIVIDER_LINE);
    }

    /**
     * prints a message to indicate empty content of a command
     */
    public static void printEmptyContent() {
        System.out.println(DIVIDER_LINE + "The content of the task cannot be empty!\n" + DIVIDER_LINE);
    }

    /**
     * prints a message to indicate wrong format for the command
     */
    public static void printWrongFormat() {
        System.out.println(DIVIDER_LINE + "Wrong format! Please try again\n" + DIVIDER_LINE);
    }

    /**
     * prints a message to indicate an invalid task number entered by the user
     */
    public static void printWrongNumber() {
        System.out.println(DIVIDER_LINE + "Please try again with a valid task number\n" + DIVIDER_LINE);
    }
}
