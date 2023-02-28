package storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Tasks;
import tasks.Todo;

/**
 * Decodes string line from the save text file,
 * and updates tasks list accordingly.
 */
public class ListDecoder {
	public static final int COMMAND_INDEX = 0;
	public static final int MARK_INDEX = 1;
	public static final int DESCRIPTION_INDEX = 2;
	public static final String MARK_INDICATOR = "1";
	public static final String TASK_COMMAND = "T";
	public static final String DEADLINE_COMMAND = "D";
	public static final String EVENT_COMMAND = "E";
	public static final String SAVE_FILE_PARTITION = " / ";
	public static final int BY_INDEX = 3;
	public static final int FROM_INDEX = 3;
	
	/**
	 * Reads the string and updates task list accordingly.
	 * {@code @pre} taskString is correctly formatted.
	 * @param taskString String to be read to update the task list.
	 */
	public static void readTask(String taskString, Tasks list) {
		String[] commandMarkAndArg = splitCommandAndArg(taskString);
		String command = commandMarkAndArg[COMMAND_INDEX];
		String mark = commandMarkAndArg[MARK_INDEX];
		String description = commandMarkAndArg[DESCRIPTION_INDEX];
		boolean isMark = isMark(mark);
		
		switch (command) {
		case TASK_COMMAND:
			loadTodo(isMark, description, list);
			break;
		case DEADLINE_COMMAND:
			String by = commandMarkAndArg[BY_INDEX];
			loadDeadline(isMark, description, by, list);
			break;
		case EVENT_COMMAND:
			String from = commandMarkAndArg[FROM_INDEX];
			String to = commandMarkAndArg[4];
			loadEvent(isMark, description, from, to, list);
			break;
		default:
			break;
		}
	}
	
	/**
	 * returns an array of strings containing the individual commands and arguments from the line.
	 * @param line String to be split up.
	 * @return array of String that is organised into its command and arguments.
	 */
	private static String[] splitCommandAndArg(String line) {
		String[] splitStrings = line.split(SAVE_FILE_PARTITION);
		return splitStrings; // assumed that there when used there is no exception where it cannot be split
	}
	
	/**
	 * Checks if task is marked as done or not,
	 * if "1", means marked, otherwise it is unmarked.
	 * @param mark String that indicates if task is marked or unmarked.
	 * @return True if task being checked is marked, false otherwise.
	 */
	private static boolean isMark(String mark) {
		return mark.contains(MARK_INDICATOR);
	}
	
	/**
	 * Loads todo task onto list.
	 * @param isMark True is todo is marked done, false if otherwise.
	 * @param description Description of todo.
	 * @param list List of tasks of user.
	 */
	public static void loadTodo(boolean isMark, String description, Tasks list) {
		Todo newTodo = new Todo(description);
		int tasksCount = list.getTasksCount();
		
		// create new Todo task
		list.setTasksCount(tasksCount++);
		list.addSavedTask(newTodo);
		if (isMark) {
			list.getTask(tasksCount).markTask(true);
		}
	}
	
	/**
	 * Loads deadline task onto list.
	 * @param isMark True is deadline is marked done, false if otherwise.
	 * @param description Description of deadline.
	 * @param by By of deadline.
	 * @param list List of tasks of user.
	 */
	public static void loadDeadline(boolean isMark, String description, String by, Tasks list) {
		Deadline newDeadline = new Deadline(description, by);
		int tasksCount = list.getTasksCount();
		
		// create new Deadline task
		list.setTasksCount(tasksCount++);
		list.addSavedTask(newDeadline);
		if (isMark) {
			list.getTask(tasksCount).markTask(true);
		}
	}
	
	/**
	 * Loads event task onto list.
	 * @param isMark True is event is marked done, false if otherwise.
	 * @param description Description of event.
	 * @param from From of event.
	 * @param to To of event.
	 * @param list List of tasks of user.
	 */
	public static void loadEvent(boolean isMark, String description, String from, String to, Tasks list) {
		Event newEvent = new Event(description, from, to);
		int tasksCount = list.getTasksCount();
		
		// create new Event task
		list.setTasksCount(tasksCount++);
		list.addSavedTask(newEvent);
		if (isMark) {
			list.getTask(tasksCount).markTask(true);
		}
	}
}
