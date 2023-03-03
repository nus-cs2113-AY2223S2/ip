package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;

/**
 * TaskList class represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList object with an existing list of tasks.
     *
     * @param tasks the existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list based on the specified index.
     *
     * @param indexTask the index of the task to be deleted.
     * @return the deleted task.
     */
    public Task deleteTask(int indexTask) {
        Task deletedTask = tasks.get(indexTask);
        tasks.remove(indexTask);
        return deletedTask;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return the number of tasks in the task list.
     */
    public int getNumTask() {
        return tasks.size();
    }

    /**
     * Gets the list of tasks in the task list.
     *
     * @return the list of tasks in the task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the task in the task list based on the specified index.
     *
     * @param indexTask the index of the task to be retrieved.
     * @return the task at the specified index.
     */
    public Task getTaskByIndex(int indexTask) {
        return tasks.get(indexTask);
    }


}
