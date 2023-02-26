package task;
import exception.InvalidIndexException;

import java.util.ArrayList;

/**
 * Represents a TaskList class that is mainly responsible for the operation of the ArrayList<Task>,
 * which includes adding,removing, and getting.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return All the existing task in the format of ArrayList<Task>
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     *
     * @param taskIdx The index of the task to be returned (0-based)
     * @return The task of the index specified
     * @throws InvalidIndexException If the user enters an invalid index (less than 0 or out-of-bounds)
     */
    public Task getTask(int taskIdx) throws InvalidIndexException {
        if (taskIdx >= tasks.size() || taskIdx < 0) {
            throw new InvalidIndexException("Please ensure you enter the correct task number");
        }
        return tasks.get(taskIdx);
    }

    /**
     * @return The total number of tasks in the ArrayList<Task> currently.
     */
    public int getTasksCount() {
        return tasks.size();
    }

    /**
     * Adds a task to the ArrayList<Task>
     * @param taskToAdd The {@link task.Task} to be added
     */
    public void addTask (Task taskToAdd) {
        tasks.add(taskToAdd);
    }


    /**
     * @param taskIdx The index of the task to be removed (0-based)
     * @return The task that has been removed
     * @throws InvalidIndexException
     */
    public Task removeTask(int taskIdx) throws InvalidIndexException {
        if (taskIdx >= tasks.size() || taskIdx < 0) {
            throw new InvalidIndexException("Please ensure you enter the correct task number");
        }
        return tasks.remove(taskIdx);
    }

}
