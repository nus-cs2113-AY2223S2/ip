package duke;

import java.util.ArrayList;

/**
 * Class representing a list of tasks and its associated functions
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for a task list if there is already a list of tasks.
     * @param tasks ArrayList of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for a new task list, will be empty.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Getter for all tasks in the task list
     * @return ArrayList containing all tasks
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Adds a new task to the task list
     * @param task the task to be added
     * @return the task that was just added
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Remove a task by its displayed index
     * @param displayedIndex the displayed index of the task
     * @return the task that was just removed
     */
    public Task removeTaskByDisplayedIndex(int displayedIndex) {
        Task task = tasks.get(displayedIndex-1);
        tasks.remove(displayedIndex-1);
        return task;
    }

    /**
     * Marks a task as done by its displayed index
     * @param displayedIndex the displayed index of the task
     * @return the task that was just marked
     */
    public Task markTaskByDisplayedIndex(int displayedIndex) {
        tasks.get(displayedIndex-1).setIsDone(true);
        return tasks.get(displayedIndex-1);
    }

    /**
     * Marks a task as not done by its displayed index
     * @param displayedIndex the displayed index of the task
     * @return the task that was just unmarked
     */
    public Task unmarkTaskByDisplayedIndex(int displayedIndex) {
        tasks.get(displayedIndex-1).setIsDone(false);
        return tasks.get(displayedIndex-1);
    }
}
