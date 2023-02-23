package ui;

/**
 * Text UI of the application
 */
public class Ui {
	public static final String DIVIDER = "____________________________________________________________\n";
	public static final String UNKNOWN_COMMAND_MESSAGE = "Error: Unknown command detected, please type help for the list of commands available";
	public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";
	public static final String GREET_MESSAGE = "Hello! I'm Bob\n" + "What can I do for you?\n";
	public static final String ERROR_EMPTY_TODO_DESCRIPTION_MESSAGE = "Error: The description of Todo cannot be empty";
	public static final String ERROR_EMPTY_EVENT_DESCRIPTION_MESSAGE = "Error: The description, from or to cannot be empty";
	public static final String ERROR_EMPTY_DEADLINE_DESCRIPTION = "Error: The description or by cannot be empty";
	public static final String ERROR_TASK_NUMBER_OUT_OF_RANGE_MESSAGE = "Error: task number given out of range";
	public static final String ERROR_TASK_NUMBER_NOT_INT_MESSAGE = "Error: Task Number given empty/not a number!";
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
	public static final int INDEX_DESCRIPTION = 0;
	public static final int INDEX_FROM = 1;
	public static final int INDEX_TO = 2;
	public static final String FROM_DEMARCATION = "/from";
	public static final String TO_DEMARCATION = "/to";
	public static final int BY_DESCRIPTION = 1;
	public static final String BY_DEMARCATION = "/by";
	
	
	}
