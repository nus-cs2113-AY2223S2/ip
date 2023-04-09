package task;

import java.util.ArrayList;

/**
 * model a class to handle the collection of the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Build constructor for TaskList class.
     * @param tasks the list of the task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Build empty/new list of the task.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Method to add task into the list.
     * @param task the task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Method to compute the size of the list.
     * @return the size of the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Method to get the element of the list.
     * @param index of the list.
     * @return the element of the list.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Method to remove element from the list.
     * @param index of the list to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }
}
