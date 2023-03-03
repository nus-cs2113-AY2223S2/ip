package duke;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int size = 0;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {

    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param idx The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to add to the task list.
     */
    public void add(Task task) {
        tasks.add(task);
        size++;
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param idx The index of the task to delete
     */
    public void delete(int idx) {
        tasks.remove(idx);
        size--;
    }

    /**
     * returns the size of the list tasks
     */
    public int getSize() {
        return size;
    }


}
