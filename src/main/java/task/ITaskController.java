package task;

import java.util.ArrayList;

import parser.Argument;

public interface ITaskController {
	/**
	 * Method call for obtaining ArrayList of Task user has.
	 * @return ArrayList<Task>
	 * @throws EmptyTaskListException
	 */
	public ArrayList<Task> getTask() throws EmptyTaskListException;
	/**
	 * Adds new task to controller. Task will be stored in ArrayList.
	 * @param description
	 */
	public void addTask(String description);
    /**
	 * 
	 * @param arg
	 * @return
	 * @throws IllegalArgumentException
	 */
	public String markTask(Argument arg) throws IllegalArgumentException;
}
