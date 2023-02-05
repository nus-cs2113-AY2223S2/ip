package dev.joulev.archduke.tasks;

import java.util.ArrayList;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.io.Out;
import dev.joulev.archduke.storage.Storage;

/**
 * This class represents the store of tasks, aka a list of tasks. It also
 * handles reading and saving tasks to the file system.
 */
public class Store {
    private ArrayList<Task> tasks;

    public Store() {
        this.tasks = Storage.readSavedTasks();
    }

    private void onMutation() throws ArchdukeException {
        Storage.saveTasks(tasks);
    }

    /**
     * Adds a task to the store.
     * 
     * @param task The task to add.
     * @throws ArchdukeException If the operation fails.
     */
    public void addTask(Task task) throws ArchdukeException {
        this.tasks.add(task);
        onMutation();
    }

    /**
     * Toggles the {@code isComplete} status of a task.
     * 
     * @param index The index of the task to toggle.
     * @throws ArchdukeException If the operation fails.
     */
    public void toggleTaskCompleteness(int index) throws ArchdukeException {
        Task task = getTask(index);
        task.toggleCompleted();
        onMutation();
    }

    /**
     * Deletes a task from the store.
     * 
     * @param index The index of the task to delete.
     * @throws ArchdukeException If the operation fails.
     */
    public void deleteTask(int index) throws ArchdukeException {
        tasks.remove(index);
        onMutation();
    }

    /**
     * Lists all tasks in the store, in a prettified format.
     * 
     * @throws ArchdukeException If the operation fails.
     */
    public void listTasks() throws ArchdukeException {
        for (Task task : tasks) {
            Out.printf("  %s", task.toString());
        }
        Out.printf("You have %d task(s) in the list.", getTaskCount());
    }

    /**
     * Gets a task from the store.
     * 
     * @param index The index of the task to get.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the store.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }
}
