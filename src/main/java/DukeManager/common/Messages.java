package DukeManager.common;

/**
 * Container for user visible messages.
 */
public class Messages {
	public static final String LINE_PARTITION = "\t____________________________________________________________\n";
	public static final String FAREWELL = LINE_PARTITION +
			"\t  Bye. Hope to see you again soon!\n" +
			LINE_PARTITION;
	public static final String GREET = LINE_PARTITION +
			"\t  Hello! I'm kimo\n" +
			"\t  What can I do for you?\n" +
			LINE_PARTITION;
	public static final String FILE_PATH = "taskList.txt";
	public static final String ERROR_FACE = " (ಠ_ಠ) ";
	public static final String MSG_INVALID_ARGS_MSG = LINE_PARTITION +
			"\t  " + ERROR_FACE + "You did not enter an integer.\n" +
			"\t  Try again.\n" +
			LINE_PARTITION;
	public static final String MSG_CMD_FORMAT_ERROR = LINE_PARTITION +
			"\t  " + ERROR_FACE + "You have entered the wrong format for this command.\n" +
			"\t  This is the correct format for this command: \n%s" +
			LINE_PARTITION;
	public static final String MSG_INVALID_CMD = LINE_PARTITION +
			"\t  " + ERROR_FACE + "You have entered an invalid command.\n" +
			"\t  Please type \"help\" to see the available list of commands and the relevant formats\n" +
			LINE_PARTITION;
	public static final String MSG_EMPTY_LIST_ERROR = LINE_PARTITION +
			"\t  " + ERROR_FACE + "Your list is empty. Please add items first.\n" +
			LINE_PARTITION;

	public static final String MSG_INVALID_TASK_DISPLAYED_INDEX = "The index provided is invalid";
	public static final String MSG_TASKS_LISTED_OVERVIEW = LINE_PARTITION +
			"\t  %1$d task(s) listed!" +
			LINE_PARTITION;

	//HELP MESSAGE
	public static final String HELP_LIST = LINE_PARTITION +
			"\t  These are the valid commands and the relevant formats for each:\n" +
			"\t  list : displays the current tasklist of tasks you have entered, with each tasks' statuses shown." +
			"\t  mark (no.) : marks task number (no.) as completed.\n" +
			"\t  unmark (no.) : marks task number (no.) as incomplete.\n" +
			"\t  todo (task) : adds task with description (task) to the tasklist.\n" +
			"\t  deadline (task) /by (date) : adds task with description (task) and date (date) to the tasklist.\n" +
			"\t  event (task) /from (date1) /to (date2) : adds task with description (task) " +
			"and start date (date1) and end date (date2)\n" +
			"\t  help : shows list of available commands\n" +
			LINE_PARTITION;
	public static final String MSG_TASK_NOT_IN_TASKLIST = LINE_PARTITION +
			"\t This task does not exist. Use command \"list\" to see the current list of tasks." +
			LINE_PARTITION;

}
