package Duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class contains methods that involve a task number and command (mark,unmark,delete)
 */

public class numericMethods {

    /**
     * Changes the completion status of a task from completed to uncompleted
     * @param tasks array of tasks
     * @param taskNum task number of the task to change the completion status of
     */
    public static void unmarkTask(ArrayList<Task> tasks, int taskNum) {
        Task target = tasks.get(taskNum - 1);
        target.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(target.getStatusIcon() + "   " + target.taskName);
    }

    /**
     * Changes the completion status of a task from uncompleted to completed
     * @param tasks array of tasks
     * @param taskNum task number of the task to change the completion status of
     */
    public static void markTask(ArrayList<Task> tasks, int taskNum) {
        Task target = tasks.get(taskNum - 1);
        target.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(target.getStatusIcon() + "   " + target.taskName);
    }

    /**
     * Deletes task from the list of tasks
     * @param tasks array of tasks
     * @param taskNum task number of the task to delete
     */
    public static void deleteTask(ArrayList<Task> tasks, int taskNum) {
        System.out.println("Noted. I've removed this task:");
        Task task = tasks.get(taskNum - 1);
        System.out.print(taskNum);
        task.printTask();
        tasks.remove(taskNum - 1);

    }
}










