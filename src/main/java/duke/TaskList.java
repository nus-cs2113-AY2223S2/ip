package duke;


import java.util.ArrayList;
import duke.data.Task;

/**
 * Represents the entire TaskList. Contains the data of the TaskList.
 */
public class TaskList {
    final private static ArrayList<Task> tasks = new ArrayList<>();
    private static int tasksLength = 0;

    /**
     * Adds a task to the TaskList.
     * @param newTask the new task to be added
     */
    public static void addTask(Task newTask) {
        tasks.add(newTask);
        tasksLength++;
    }

    /**
     * Removes the equivalent task from the TaskList
     * @param index the task at which position you want to remove
     */
    public static void deleteTask(int index) {
        tasks.remove(index);
        tasksLength--;
    }

    /**
     * Returns the task at a certain position
     * @param index the position at which the task needs to be returned
     * @return
     */
    public static Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * get the number of tasks in the task list
     * @return the size
     */
    public static int getSize() {
        return tasksLength;
    }

    /**
     * Format the way the task is printed.
     */
    public static void printSize() {
        if (tasksLength == 1) {
            System.out.println("Now you have 1 task in the list.");
        }
        else if (tasksLength == 0) {
            System.out.println("Now you have 0 task in the list.");
        }
        else {
            System.out.println("Now you have "+ tasksLength +" tasks in the list.");
        }
    }
}
