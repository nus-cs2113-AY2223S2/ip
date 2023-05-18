package tusky.tasks;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Collections;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Instantiates a TaskList object with an empty ArrayList of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Instantiates a TaskList object using an existing ArrayList of tasks
     * This is usually called when reading from the file at startup.
     * @param tasks ArrayList of tasks to be used in the TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = Objects.requireNonNullElseGet(tasks, ArrayList::new);
        // remove all null entries in the list
        this.tasks.removeAll(Collections.singleton(null));
    }


    /**
     * Returns all the tasks in the list.
     * @return ArrayList of Task objects
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the TaskList.
     * @param task Task to be added.
     * @return The task that was added.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Deletes a task from the TaskList.
     * @param index Index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        Task task = getTask(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Marks a task as done.
     * @param index Index of the task to be marked as done.
     * @return The task that was marked as done.
     */
    public Task markTask(int index) {
        Task task = tasks.get(index);
        task.setDone(true);
        return task;
    }

    /**
     * Marks a task as not done.
     * @param index Index of the task to be marked as not done.
     * @return The task that was marked as not done.
     */
    public Task unmarkTask(int index) {
        Task task = tasks.get(index);
        task.setDone(false);
        return task;
    }

    /**
     * Returns the size of the TaskList.
     * @return The size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     * @param index Index of the task to be returned.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Finds tasks that contain the specified keyword.
     * @param keyword Keyword to be searched for.
     * @return An ArrayList of tasks that contain the specified keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    @Override
    public String toString () {
        return "TaskList{" +
                "tasks=" + tasks +
                '}';
    }
}
