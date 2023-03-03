package duke;

import duke.task.Task;

import java.util.Iterator;
import java.util.ArrayList;

public class TaskList implements Iterable<Task> {
    private static ArrayList<Task> tasks = new ArrayList<>();

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Adds task to task list
     * @param task The task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list
     * @param taskNumber The (zero-based) index of the task to be removed
     * @return The task to be deleted
     */
    public Task removeTask(int taskNumber) {
        return tasks.remove(taskNumber - 1);
    }

    /**
     * Gets a task from the list
     * @param taskNumber The (zero-based) index of the task to be removed
     * @return The task with the index of (taskNumber - 1)
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    /**
     * Gets the size of the task list.
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Finds a list of tasks that contain a certain keyword.
     * @param keyword The word to be searched for in the task list
     * @return List of tasks containing the keyword to be searched for
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();

        for (Task task : tasks) {
            if (task.isFound(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
