package duke.ui;

import duke.constants.textImage;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;

/**
 * UI which deals with interactions with the user
 */
public interface UI {

    /**
     * Prints the task that had been added recently, and the updated number
     * of tasks in the current task-list
     *
     * @param task the newly added task
     * @param tasks the current task-list
     */
    public static void printAddedTask(Task task, TaskList tasks){
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.sizeOfList() + " tasks in the list.");
    }

    /**
     * Prints all tasks stored in the task-list
     *
     * @param tasks the current task-list
     */
    public static void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());    }
    }
    /**
     * Prints the status of task (marked as done or unmarked)
     *
     * @param tasks the current task-list
     * @param taskNum the index of the task to be marked/unmarked
     */
    public static void printMarkMessage(TaskList tasks, int taskNum) {
        System.out.println("  " + tasks.getStatus(taskNum) + " " + tasks.getDescription(taskNum));
    }
    /**
     * Prints the task that had been deleted recently and the updated number
     * of tasks in the current task-list
     *
     * @param tasks the current task-list
     * @param taskNum the index of the task that has been deleted
     */
    public static void printDeleteMessage(TaskList tasks, int taskNum) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + tasks.getString(taskNum));
        System.out.println("Now you have " + tasks.sizeOfList() + " tasks in the list.");
    }
    /**
     * Print welcome message when the user runs program
     */
    public static void showWelcomeMessage() {
        System.out.println(textImage.WELCOME_TEXT_IMAGE);
        System.out.println(textImage.HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(textImage.HORIZONTAL_LINE);
    }
    /**
     * Print exit message when user terminates the program
     */
    public static void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}



