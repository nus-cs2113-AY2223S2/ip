package duke.classes;

import java.util.ArrayList;

/**
 * The Tasklist class represents a list of tasks.
 */
public class Tasklist {

    /** The list of tasks */
    protected static ArrayList<Task> listOfTask;

    /**
     * Constructs a new Tasklist object with the given list of tasks.
     *
     * @param listOfTask the list of tasks
     */
    public Tasklist(ArrayList<Task> listOfTask) {
        this.listOfTask = listOfTask;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task the task to be added
     */
    static void addTask(Task task) {
        listOfTask.add(task);
    }

    /**
     * Marks a task as done.
     *
     * @param task the task to be marked as done
     */
    static void markTask(Task task) {
        task.isDone = true;
    }

    /**
     * Marks a task as not done.
     *
     * @param task the task to be marked as not done
     */
    static void unmarkTask(Task task) {
        task.isDone = false;
    }

    /**
     * Prints all the tasks in the list of tasks.
     */
    static void printTasks() {
        int order = 1;
        for (int i = 0; i < listOfTask.size(); i++) {
            System.out.println(order + "." + listOfTask.get(i));
            order++;
        }
    }
}
