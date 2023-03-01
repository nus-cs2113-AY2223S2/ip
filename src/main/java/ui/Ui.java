package ui;

/**
 * Text UI of the application
 */
public class Ui {
	public static final String DIVIDER = "____________________________________________________________\n";
	public static final String UNKNOWN_COMMAND_MESSAGE = "Error: Unknown command detected, please type help for the list of commands available";
	public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";
	public static final String GREET_MESSAGE = "Hello! I'm Bob\n" + "What can I do for you?\n";
	public static final String NO_MATCHING_TASK_MESSAGE = "Oops! There are no matching tasks in your list!";
	public static final String FIND_TASK_MESSAGE = "Here are the matching tasks in your list and its index:";
	public static final String BYE_COMMAND = "bye";
	public static final String ERROR_EMPTY_TODO_DESCRIPTION_MESSAGE = "Error: The description of Todo cannot be empty";
	public static final String ERROR_EMPTY_EVENT_DESCRIPTION_MESSAGE = "Error: The description, from or to cannot be empty";
	public static final String ERROR_EMPTY_DEADLINE_DESCRIPTION = "Error: The description or by cannot be empty";
	public static final String ERROR_TASK_NUMBER_OUT_OF_RANGE_MESSAGE = "Error: task number given out of range";
	public static final String ERROR_TASK_NUMBER_NOT_INT_MESSAGE = "Error: Task Number given empty/not a number!";
	public static final String ERROR_NO_KEYWORD_MESSAGE = "Error: keyword not inputted!";
	public static final String ERROR_UNABLE_TO_SAVE_CHANGES_MESSAGE = "Error: Unable to save changes";
	public static final String ERROR_FILE_NOT_DETECTED_MESSAGE = "Error: Nothing detected in save file";
	public static final String PRINT_HELP_INSTRUCTIONS_MESSAGE = "The following are the commands available and their arguments:";
	public static final String HELP_TODO_FORMAT = "Todo [description]";
	public static final String HELP_TODO_DESCRIPTION = "Creates Todo task. Requires a description text.";
	public static final String HELP_EVENT_FORMAT = "event [description] /from [from] /to [to]";
	public static final String HELP_EVENT_DESCRIPTION = "Creates Event task. Requires a description, from and to texts.";
	public static final String HELP_DEADLINE_FORMAT = "deadline [description] /by [by]";
	public static final String HELP_DEADLINE_DESCRIPTION = "Creates a Deadline task. Requires a Description, by text";
	public static final String HELP_MARK_FORMAT = "mark [task number]";
	public static final String HELP_MARK_DESCRIPTION = "Marks selected task indicating it is done. Requires a task number.";
	public static final String HELP_UNMARK_FORMAT = "unmark [task number]";
	public static final String HELP_UNMARK_DESCRIPTION = "Unmarks selected task indicating it is undone. Requires a task number.";
	public static final String HELP_LIST_FORMAT = "list";
	public static final String HELP_LIST_DESCRIPTION = "Lists the available commands.";
	public static final String HELP_BYE_FORMAT = "bye";
	public static final String HELP_BYE_DESCRIPTION = "Exits and closes the program";
	public static final String RUN_CLEAR_COMPLETE_MESSAGE = "List has been cleared";
	public static final String HELP_DELETE_FORMAT = "delete [task number]";
	public static final String HELP_DELETE_DESCRIPTION = "Deletes selected task from the list";
	public static final String HELP_CLEAR_FORMAT = "clear";
	public static final String HELP_CLEAR_DESCRIPTION = "Empties the list";
	
	/**
	 * Generates and prints greeting upon the opening of the program.
	 */
	public static void greet() {
		String greet = DIVIDER +
				GREET_MESSAGE +
				DIVIDER;
		System.out.println(greet);
	}
	
	/**
	 * Generates and prints help message
	 */
	public static void printHelp() {
		System.out.println(PRINT_HELP_INSTRUCTIONS_MESSAGE);
		printHelpTodo();
		System.out.println();
		printHelpEvent();
		System.out.println();
		printHelpDeadline();
		System.out.println();
		printHelpMark();
		System.out.println();
		printHelpUnmark();
		System.out.println();
		printHelpDelete();
		System.out.println();
		printHelpClear();
		System.out.println();
		printHelpList();
		System.out.println();
		printHelpBye();
	}
	
	/**
	 * Generates and prints help for adding todo.
	 */
	public static void printHelpTodo() {
		System.out.println(HELP_TODO_FORMAT);
		System.out.println(HELP_TODO_DESCRIPTION);
	}
	
	/**
	 * Generates and prints help for adding event.
	 */
	public static void printHelpEvent() {
		System.out.println(HELP_EVENT_FORMAT);
		System.out.println(HELP_EVENT_DESCRIPTION);
	}
	
	/**
	 * Generates and prints help for adding deadline.
	 */
	public static void printHelpDeadline() {
		System.out.println(HELP_DEADLINE_FORMAT);
		System.out.println(HELP_DEADLINE_DESCRIPTION);
	}
	
	/**
	 * Generates and prints help for marking tasks as done.
	 */
	public static void printHelpMark() {
		System.out.println(HELP_MARK_FORMAT);
		System.out.println(HELP_MARK_DESCRIPTION);
	}
	
	/**
	 * Generates and prints help for marking tasks as undone.
	 */
	public static void printHelpUnmark() {
		System.out.println(HELP_UNMARK_FORMAT);
		System.out.println(HELP_UNMARK_DESCRIPTION);
	}
	
	/**
	 * Generates and prints help for deleting task from list.
	 */
	public static void printHelpDelete() {
		System.out.println(HELP_DELETE_FORMAT);
		System.out.println(HELP_DELETE_DESCRIPTION);
	}
	
	/**
	 * Generates and prints help for clearing list.
	 */
	public static void printHelpClear() {
		System.out.println(HELP_CLEAR_FORMAT);
		System.out.println(HELP_CLEAR_DESCRIPTION);
	}
	
	/**
	 * Generates and prints help showing full list of tasks.
	 */
	public static void printHelpList() {
		System.out.println(HELP_LIST_FORMAT);
		System.out.println(HELP_LIST_DESCRIPTION);
	}
	
	/**
	 * Generates and prints help for exiting the program.
	 */
	public static void printHelpBye() {
		System.out.println(HELP_BYE_FORMAT);
		System.out.println(HELP_BYE_DESCRIPTION);
	}
	
	/**
	 * Generates and prints error message for unknown command.
	 */
	public static void printUnknownCommandMessage() {
		System.out.println(UNKNOWN_COMMAND_MESSAGE);
	}
	
	/**
	 * Generates and prints error message when program is unable to save changes into save.txt file.
	 */
	public static void printUnableToSaveChanges() {
		System.out.println(ERROR_UNABLE_TO_SAVE_CHANGES_MESSAGE);
	}
	
	/**
	 * Generates and prints error message when program is unable to find save.txt file after initialisation of program.
	 */
	public static void printNoFileDetected() {
		System.out.println(ERROR_FILE_NOT_DETECTED_MESSAGE);
	}
	
	/**
	 * Generates and prints exit message before closing the program.
	 */
	public static void exit() {
		String exit = DIVIDER + EXIT_MESSAGE + DIVIDER;
		System.out.println(exit);
	}
}
