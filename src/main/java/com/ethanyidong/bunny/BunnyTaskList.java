package com.ethanyidong.bunny;

import com.ethanyidong.bunny.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of <code>Task</code>s
 */
public class BunnyTaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates a new empty list of <code>Task</code>s
     */
    public BunnyTaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list
     * @param task the task to add to the list
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * @param index the index of the task to return
     * @return a task from the list at a specified index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Delete a task at a specified index
     * @param index the index of the task to delete
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * @return the number of tasks currently in the list
     */
    public int numTasks() {
        return this.tasks.size();
    }
}
