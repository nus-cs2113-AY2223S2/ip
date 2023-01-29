package task;

import java.util.ArrayList;

public interface ITaskController {
	/**
	 * Method call for obtaining ArrayList of Task user has.
	 * @return ArrayList<Task>
	 * @throws EmptyTaskListException
	 */
	public ArrayList<Task> getTasks() throws EmptyTaskListException;
    public Task addTask(Task newTask);
	public String markTask(int taskIndex) throws TaskIndexOutOfRangeException, TaskMarkException;
	public String unmarkTask(int taskIndex) throws TaskIndexOutOfRangeException, TaskMarkException;
	public int getCount();
}