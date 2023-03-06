package Gilbert.tasks;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Adds a task to the tasklist.
     *
     * @param task      Task to be added.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Gets the size of the tasklist.
     *
     */
    public int sizeTaskList() {
        return list.size();
    }

    /**
     * Deletes the task at the specified index of the tasklist.
     *
     * @param index     Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        list.remove(index);
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index     Index of the task to be returned.
     */
    public Task getTask(int index) {
        return list.get(index);
    }

}
