package taskList;

import task.Task;

import java.util.ArrayList;

/**
 * Represent a collection of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get the ArrayList of tasks in the TaskList object.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Append a task to TaskList.
     *
     * @param task The task to be appended to TaskList
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove a task from TaskList.
     *
     * @param i The index of the task in TaskList to be removed.
     */
    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Get the number of tasks in TaskList.
     *
     * @return The number of tasks in TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieve a specific task from TaskList.
     *
     * @param i The index of the task in TaskList to be retrieved.
     * @return The i-th task of TaskList(0-indexed).
     */
    public Task get(int i) {
        return tasks.get(i);
    }
}
