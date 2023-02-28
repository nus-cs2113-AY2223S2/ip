package duke;
import java.util.ArrayList;

/**
 * Represents the user interface of the Duke program.
 */
public class Ui {

    /**
     * Prints the welcome message of the Duke program.
     */
    public static void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the bye message of the Duke program.
     */
    public static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a line to separate the user input and the output.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the message when a task is added.
     * @param task The task that is added.
     * @param taskCount The number of tasks in the list.
     */
    public static void printTaskAddedMessage(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints the message when a task is deleted.
     * @param index The index of the task that is deleted.
     * @param taskCount The number of tasks in the list.
     */
    public static void printTaskDeletedMessage(int index, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(TaskList.getTask(index));
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints the message when a task is marked as done.
     * @param task The task that is marked as done.
     */
    public static void printTaskDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints the list of tasks.
     * @param textList The list of tasks.
     */
    public static void printTaskList(ArrayList<Task> textList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < textList.size(); i++) {
            System.out.println((i + 1) + "." + textList.get(i));
        }
    }

    /**
     * Prints the message when a task is found.
     * @param foundList The list of tasks that are found.
     */
    public static void printFindMessage(ArrayList<Task> foundList) {
        if (foundList.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundList.size(); i++) {
                System.out.println((i + 1) + "." + foundList.get(i));
            }
        } else {
            System.out.println("No matching tasks found");
        }
    }

    /**
     * Prints the message when a task is marked as not done.
     * @param task The task that is marked as not done.
     */
    public static void printTaskUnDoneMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Prints the message when invalid index is typed in or accessed.
     */
    public static void printInvalidIndex() {
        System.out.println("Invalid index");
    }

    /**
     * Prints the message when the description of a task is empty.
     */
    public static void printEmptyDescription() {
        System.out.println("The description of a task cannot be empty.");
    }

    /**
     * Prints the message when the command is unknown or not recognised.
     */
    public static void printInvalidCommand() {
        System.out.println("Invalid command");
    }

    /**
     * Prints the message when the format of the date and time is invalid.
     */
    public static void printInvalidDateTime() {
        System.out.println("Invalid date and time format. Please use yyyy-mm-ddTHH:mm");
    }
}
