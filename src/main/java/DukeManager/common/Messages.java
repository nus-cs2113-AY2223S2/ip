package DukeManager.common;

/**
 * Container for user visible messages.
 */
public class Messages {
	public static final String LINE_PARTITION = "\t____________________________________________________________\n";
	public static final String FAREWELL = "Bye. Hope to see you again soon!\n";
	public static final String[] GREET = {"Hello! I'm kimo \n","What can I do for you?\n"};
	public static final String FILE_PATH = "taskList.txt";
	public static final String ERROR_FACE = " (ಠ_ಠ) ";
	public static final String MSG_INVALID_ARGS_MSG =
			ERROR_FACE + "You did not enter an integer.\n";
	public static final String MSG_CMD_FORMAT_ERROR =
			ERROR_FACE + "You have entered the wrong format for this command.\n" +
			"This is the correct format for this command: \n%s";
	public static final String MSG_INVALID_CMD =
			ERROR_FACE + "You have entered an invalid command.\n" +
			"Please type \"help\" to see the available list of commands and the relevant formats\n";
	public static final String MSG_EMPTY_LIST_ERROR =
			ERROR_FACE + "Your list is empty. Please add items first.\n";

	public static final String MSG_INVALID_TASK_DISPLAYED_INDEX = "The index provided is invalid";
	public static final String MSG_TASKS_LISTED_OVERVIEW =
			"%1$d task(s) listed!";

	//HELP MESSAGE
	public static final String HELP_LIST =
			"These are the valid commands and the relevant formats for each:\n" +
			"list : displays the current tasklist of tasks you have entered, with each tasks' statuses shown." +
			"mark (no.) : marks task number (no.) as completed.\n" +
			"unmark (no.) : marks task number (no.) as incomplete.\n" +
			"todo (task) : adds task with description (task) to the tasklist.\n" +
			"deadline (task) /by (date) : adds task with description (task) and date (date) to the tasklist.\n" +
			"event (task) /from (date1) /to (date2) : adds task with description (task) " +
			"and start date (date1) and end date (date2)\n" +
			"help : shows list of available commands\n" +
			LINE_PARTITION;
	public static final String MSG_TASK_NOT_IN_TASKLIST =
			"This task does not exist. Use command \"list\" to see the current list of tasks.";

}
