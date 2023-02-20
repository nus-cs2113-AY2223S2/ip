package duke.task;

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * A list of Task objects representing the current list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns the number of tasks in this task list.
     *
     * @return The number of tasks in this task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index of the task list.
     *
     * @param index The index of the task to return.
     *              Must be between 1 and the size of the task list.
     * @return The task at the given index of the task list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Appends the given task to the task list.
     *
     * @param task The task to be added to the task list.
     * @return String representation of the task that was added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return task.toString();
    }

    /**
     * Sets the completion status of the task at the given index of the task list.
     *
     * @param index The index of the task whose completion status should be set.
     *              Must be between 1 and the size of the task list.
     * @param isDone Whether the task should be marked as completed.
     * @return String representation of the task whose completion status was set.
     */
    public String setDone(int index, boolean isDone) {
        tasks.get(index).setDone(isDone);
        return tasks.get(index).toString();
    }

    /**
     * Deletes the task at the given index of the task list.
     *
     * @param index The index of the task to be deleted.
     *              Must be between 1 and the size of the task list.
     * @return String representation of the task that was deleted.
     */
    public String deleteTask(int index) {
        String taskString = tasks.get(index).toString();
        tasks.remove(index);
        return taskString;
    }

    /**
     * Converts the task list into its string representation.
     *
     * @return String representation of the task list.
     */
    public String toString() {
        StringJoiner taskListString = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < size(); i++) {
            taskListString.add((i + 1) + "." + getTask(i).toString());
        }
        return taskListString.toString();
    }

    /**
     * Filters the task list before converting it into its string representation.
     *
     * @param filter The string that task descriptions should contain to be filtered.
     * @return A string representation of the filtered task list.
     */
    public String toString(String filter) {
        StringJoiner taskListString = new StringJoiner(System.lineSeparator());
        for (int i = 0, index = 0; i < size(); i++) {
            if (getTask(i).containsFilter(filter)) {
                taskListString.add((++index) + "." + getTask(i).toString());
            }
        }
        return taskListString.toString();
    }

    /**
     * Converts the task list into its string representation for saving purposes,
     * which contains delimiter characters for easier parsing when loading.
     *
     * @return String representation of the task list meant for saving purposes only.
     */
    public String toSaveString() {
        StringJoiner taskListString = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < size(); i++) {
            taskListString.add(getTask(i).toSaveString());
        }
        return taskListString.toString();
    }
}
