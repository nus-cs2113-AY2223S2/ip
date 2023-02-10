package task;

import java.util.ArrayList;

public interface ITaskController {
	/**
	 * Method call for obtaining ArrayList of Task user has.
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
     * Marks task based on task index specified
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
     * Count of number of task user has created
     * @return int count of task
     */
	public int getCount();
    public String deleteTask(int taskIndex) throws TaskIndexOutOfRangeException;
}