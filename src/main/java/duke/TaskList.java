package duke;

import java.util.ArrayList;

/**
 * Encapsulates an ArrayList of Tasks. Contains methods to add, remove, and read from the TaskList.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes TaskList as an empty ArrayList of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added.

     */
    public void addTask (Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task found at the index.
     *
     * @param index Index to retrieve task from.
     * @return Task at the corresponding index.
     * @throws IncorrectIndexException If index is out of bounds.
     */
    public Task getTask (int index) throws IncorrectIndexException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new IncorrectIndexException();
        }
    }

    /**
     * Removes the task found at the index.
     *
     * @param index Index of task to remove.
     * @throws IncorrectIndexException If index is out of bounds.
     */
    public void removeTask (int index) throws IncorrectIndexException {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            throw new IncorrectIndexException();
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Length of tasks in the list.
     */
    public int length () {
        return tasks.size();
    }
}
