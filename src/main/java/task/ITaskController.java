package task;

import java.util.ArrayList;

public interface ITaskController {
	/**
	 * Returns list of tasks in duke.
	 * @return ArrayList<Task>
	 * @throws EmptyTaskListException
	 */
	public ArrayList<Task> getTasks() throws EmptyTaskListException;
	/**
	 * Adds new task to controller. Task will be stored in ArrayList.
	 * @param description task specified by user
	 * @return Task object
	 */
	public String addTask(Task newTask);
	/**
     * Marks task based on task index specified.
     * @param taskIndex
     * @return String message displayed by Duke for user
     * @throws TaskIndexOutOfRangeException
     * @throws TaskMarkException
     */
	public String markTask(int taskIndex) throws TaskIndexOutOfRangeException, TaskMarkException;
    /**
     * Marks task based on task index specified
     * @param taskIndex
     * @return String message displayed by Duke for user
     * @throws TaskIndexOutOfRangeException
     * @throws TaskMarkException
     */
	public String unmarkTask(int taskIndex) throws TaskIndexOutOfRangeException, TaskMarkException;
    /**
     * Counts the number of task user has created
     * @return int count of task
     */
	public int getCount();
    /**
     * Deletes a task in the list given the index of the task
     * @param taskIndex
     * @return
     * @throws TaskIndexOutOfRangeException
     */
    public String deleteTask(int taskIndex) throws TaskIndexOutOfRangeException;
    /**
     * Checks if there exist any task
     * @return
     */
    public boolean isEmpty();
    /**
     * Removes the first task in the list. Method is called by serialiser when saving the different task in duke
     * @return
     */
    public Task removeTaskForStorage();
    /**
     * Finds tasks containing keyword given in the description
     * @param keyword
     * @return
     */
    public String findTask(String keyword);
}