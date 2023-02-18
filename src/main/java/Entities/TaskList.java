package Entities;

import java.util.ArrayList;

import Exceptions.DukeException;

import java.lang.IndexOutOfBoundsException;

/**
 * Class which holds the list of currently added tasks
 * Has Methods to modify and filter the tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Empty Constructor for TaskList
     * Initialises an empty ArrayList of Tasks 
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList
     * @param tasks Currently added tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        setTasks(tasks);
    }

    /**
     * Adds new task to task list
     * @param task new task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task using its index
     * @param taskIndex index of task
     * @return deleted task
     * @throws DukeException
     */
    public Task deleteTask(int taskIndex) throws DukeException {
        getTask(taskIndex);                 // Throws an exception if the taskIndex is not valid 
        return tasks.remove(taskIndex);
    }

    /**
     * Marks task as completed
     * @param taskIndex index of task
     * @return completed task
     * @throws DukeException
     */
    public Task markTask(int taskIndex) throws DukeException {
        Task t = getTask(taskIndex);
        t.setIsDone(true);
        return t;
    }

    /**
     * Marks task as not completed
     * @param taskIndex index of task
     * @return not completed task
     * @throws DukeException
     */
    public Task unmarkTask(int taskIndex) throws DukeException {
        Task t = getTask(taskIndex);
        t.setIsDone(false);
        return t;
    }
    
    /**
     * Gets all currently added tasks
     * @return all tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Sets currently added tasks
     * @param tasks new list of tasks
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get number of currently added tasks
     * @return number of added tasks
     */
    public int getTasksCount() {
        return tasks.size();
    }

    /**
     * Returns a task based on its index in the list
     * @param taskIndex
     * @return Found Task
     * @throws DukeException
     */
    public Task getTask(int taskIndex) throws DukeException {
        try {
            return getTasks().get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Uh oh, the index you have inputted is out of range!");
        }
    }
}
