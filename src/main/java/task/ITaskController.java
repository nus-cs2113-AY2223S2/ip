package task;

import java.util.ArrayList;

import parser.Argument;

public interface ITaskController {
	/**
	 * Method call for obtaining ArrayList of Task user has.
	 * @return ArrayList<Task>
	 * @throws EmptyTaskListException
	 */
	public ArrayList<Task> getTasks() throws EmptyTaskListException;
	/**
	 * Adds new task to controller. Task will be stored in ArrayList.
	 * @param description
	 * @return Task object
	 */
	public Task addTask(Argument arg) throws EmptyDescriptionException;
    public Task addTask(Task newTask);
	/**
	 * 
	 * @param arg
	 * @return
	 * @throws IllegalArgumentException
	 */
	public String markTask(Argument arg) throws TaskIndexOutOfRangeException;
	public int getCount();
}