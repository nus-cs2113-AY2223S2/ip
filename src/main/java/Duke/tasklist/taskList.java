package Duke.tasklist;

import Duke.exception.DukeException;
import Duke.parser.parse;
import Duke.storage.fileIO;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;

import java.util.ArrayList;

/**
 * contains the task list and all the operations related
 */
public class taskList {
	private static ArrayList<Task> tasks;

	/**
	 * When instantiated, read data from stored file and add lists to task list
	 */
	public taskList () {

		tasks = fileIO.readFile ();
	}

	/**
	 * Get task index
	 *
	 * @param index task index
	 * @return specified task
	 */
	public static Task get (int index) {

		return tasks.get (index);
	}

	/**
	 * get task list size
	 *
	 * @return size
	 */
	public static int size () {

		return tasks.size ();
	}

	/**
	 * Add todo task to the task list
	 *
	 * @param description task description
	 */
	public static void addTodo (String description) {
		tasks.add (new Task (description));
		fileIO.writeFile (tasks);
	}

	/**
	 * Add deadline task to the task list
	 *
	 * @param description task description
	 */
	public static void addDeadline (String description) {
		String[] arrInput = parse.parseDeadline (description);
		tasks.add (new Deadline (arrInput[0], arrInput[1]));
		fileIO.writeFile (tasks);
	}

	/**
	 * Add event task to the task list
	 *
	 * @param description task description
	 */
	public static void addEvent (String description) {
		String[] arrInput = parse.parseEventFrom (description);
		String[] eventInput = parse.parseEventTo (arrInput[1]);
		tasks.add (new Event (arrInput[0], eventInput[0], eventInput[1]));
		fileIO.writeFile (tasks);
	}

	/**
	 * Mark specific task as complete
	 *
	 * @param index task number
	 */
	public static void markTask (int index) {
		tasks.get (index).setIsDone (true);
		fileIO.writeFile (tasks);
	}

	/**
	 * Mark specific task as incomplete
	 *
	 * @param index task number
	 */
	public static void unmarkTask (int index) {
		tasks.get (index).setIsDone (false);
		fileIO.writeFile (tasks);
	}

	/**
	 * Delete task from task list
	 *
	 * @param index task number
	 */
	public static void deleteTask (int index) {
		tasks.remove (index);
		fileIO.writeFile (tasks);
	}

}
