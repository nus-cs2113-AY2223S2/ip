import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Tasks;
import tasks.Todo;
import ui.Ui;
import java.io.IOException;
import java.util.Scanner;

/**
 * Entry point of the Duke program.
 * Initialises the application and
 * starts the interaction with the user via the Command Line Interface (CLI).
 */
public class Duke {
	public static final String LIST_COMMAND = "list";
	public static final String HELP_COMMAND = "help";
	public static final String MARK_COMMAND = "mark";
	public static final String UNMARK_COMMAND = "unmark";
	public static final String TODO_COMMAND = "todo";
	public static final String EVENT_COMMAND = "event";
	public static final String DEADLINE_COMMAND = "deadline";
	public static final String DELETE_COMMAND = "delete";
	public static final String CLEAR_COMMAND = "clear";
	public static final int INDEX_DESCRIPTION = 0;
	public static final int INDEX_FROM = 1;
	public static final int INDEX_TO = 2;
	public static final String FROM_DEMARCATION = "/from";
	public static final String TO_DEMARCATION = "/to";
	public static final int BY_DESCRIPTION = 1;
	public static final String BY_DEMARCATION = "/by";
	public static final int START_INDEX = 1;
	private Ui ui;
	private Storage storage;
	private Tasks list;
	
	public Duke() {
		ui = new Ui();
		storage = new Storage();
		list = Storage.loadSave();
	}
	
	/**
	 * Runs program till it is exited.
	 */
	public void run() {
		Ui.greet();
		readCommandLine(list);
		Ui.exit();
	}
	
	public static void main(String[] args) {
		new Duke().run();
	}
	
	/**
	 * Reads in the input from the command line and executes the command accordingly.
	 * Each time a command is executed, save.txt file is updated with the latest task list.
	 *
	 * @param list List of tasks for the user
	 */
	private static void readCommandLine(Tasks list) {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		
		// continuously reads input from command line until command 'bye' is inputted
		while (!line.equalsIgnoreCase(Ui.BYE_COMMAND)) {
			System.out.print(Ui.DIVIDER);
			runCommand(list, line);
			System.out.println(Ui.DIVIDER);
			line = in.nextLine();
			
			// save the list into the save.txt file
			try {
				Storage.saveTasks(list);
			} catch (IOException e) {
				Ui.printUnableToSaveChanges();
			} catch (NullPointerException e) {
				Ui.printNoFileDetected();
			}
		}
	}
	
	/**
	 * Process the line and executes the correct command based on extracting the line.
	 * @param list List of tasks for the user.
	 * @param line Command line read fom the Command Line, contains the command and arguments to execute in the program.
	 */
	private static void runCommand(Tasks list, String line) {
		String[] commandAndArg = splitCommandAndArgs(line);
		String command = commandAndArg[0];
		String arg = commandAndArg[1];
		
		switch (command) {
		case LIST_COMMAND:
			list.printList();
			break;
		case HELP_COMMAND:
			Ui.printHelp();
			break;
		case MARK_COMMAND:
			runMark(list, arg);
			break;
		case UNMARK_COMMAND:
			runUnmark(list, arg);
			break;
		case TODO_COMMAND:
			addTodo(list, arg);
			break;
		case EVENT_COMMAND:
			addEvent(list, arg);
			break;
		case DEADLINE_COMMAND:
			addDeadline(list, arg);
			break;
		case DELETE_COMMAND:
			runDelete(list, arg);
			break;
		case CLEAR_COMMAND:
			runClear(list);
			break;
		default:
			Ui.printUnknownCommandMessage();
			break;
		}
	}
	
	/**
	 * Splits line into 2 strings: command and combined string of its arguments (if any).
	 * @param line String to be split.
	 * @return commandAndArgs An array of size 2 with: command and arguments (if any).
	 * Should there be no arguments, empty string in index 1.
	 */
	private static String[] splitCommandAndArgs(String line) {
		final String[] splitStrings = line.split(" ", 2);
		String[] commandAndArgs = splitStrings.length == 2 ? splitStrings : new String[]{splitStrings[0], ""};
		return commandAndArgs;
	}
	
	/**
	 * Extracts the arguments required to mark the selected task as done.
	 * Also checks if the argument placed is valid.
	 * @param list List of tasks of the user.
	 * @param arg String of arguments inputted by the user.
	 */
	private static void runMark(Tasks list, String arg) {
		try {
			int taskNumber = Integer.parseInt(arg);
			if (taskNumber >= START_INDEX && taskNumber <= list.getTasksCount()) { // check if taskNumber within the range of list
				list.markTaskDone(taskNumber);
			} else {
				System.out.println(Ui.ERROR_TASK_NUMBER_OUT_OF_RANGE_MESSAGE);
			}
		} catch (NumberFormatException e) {
			System.out.println(Ui.ERROR_TASK_NUMBER_NOT_INT_MESSAGE);
		}
	}
	
	/**
	 * Extracts the arguments required to mark the selected task as not done.
	 * Also checks if the argument placed is valid.
	 * @param list List of tasks of the user.
	 * @param arg String of arguments inputted by the user.
	 */
	private static void runUnmark(Tasks list, String arg) {
		try {
			int taskNumber = Integer.parseInt(arg);
			if (taskNumber >= START_INDEX && taskNumber <= list.getTasksCount()) { // check if taskNumber within the range of list
				list.markTaskUndone(taskNumber);
			} else {
				System.out.println(Ui.ERROR_TASK_NUMBER_OUT_OF_RANGE_MESSAGE);
			}
		} catch (NumberFormatException e) {
			System.out.println(Ui.ERROR_TASK_NUMBER_NOT_INT_MESSAGE);
		}
	}
	
	/**
	 * Extracts the arguments required to add a todo task into list.
	 * Also checks if the argument placed is valid.
	 * @param list List of tasks of the user.
	 * @param arg String of arguments inputted by the user.
	 */
	private static void addTodo(Tasks list, String arg) {
		String description = arg;
		if (description.isBlank()) { // check if there is a description
			System.out.println(Ui.ERROR_EMPTY_TODO_DESCRIPTION_MESSAGE);
			Ui.printHelpTodo();
		} else {
			Todo newTodo = new Todo(arg);
			list.addTask(newTodo);
		}
	}
	
	/**
	 * Extracts the arguments required to add an event into list.
	 * Also checks if the argument placed is valid.
	 * @param list List of tasks of the user.
	 * @param arg String of arguments inputted by the user.
	 */
	private static void addEvent(Tasks list, String arg) {
		String[] descriptionFromAndTo = splitEventArg(arg);
		String description = descriptionFromAndTo[INDEX_DESCRIPTION];
		String from = descriptionFromAndTo[INDEX_FROM];
		String to = descriptionFromAndTo[INDEX_TO];
		
		if (description.isBlank() || from.isBlank() || to.isBlank()) { // check if there is a description, from and to
			System.out.println(Ui.ERROR_EMPTY_EVENT_DESCRIPTION_MESSAGE);
			Ui.printHelpEvent();
		} else {
			Event newEvent = new Event(description, from, to);
			list.addTask(newEvent);
		}
	}
	
	/**
	 * Splits the arg string into an array of strings of: description, from and to in that order.
	 * Used specifically to split the argument of addEvent.
	 * @param arg String which contains the combined arguments of description, from and to from the user.
	 * @return descriptionFromAndTo Array of String of size 3 containing=
	 * description, from and to respectively.
	 * should any be missing, index would be blank.
	 */
	private static String[] splitEventArg(String arg) {
		String[] splitDescription = arg.split(FROM_DEMARCATION, 2); // separate the argument into description and fromAndTo
		String[] splitFromAndTo = splitDescription[1].split(TO_DEMARCATION, 2); // separate fromAndTo into from and to
		String[] descriptionFromAndTo = new String[]{splitDescription[0].trim(), splitFromAndTo[0].trim(), splitFromAndTo[1].trim()};
		return descriptionFromAndTo;
	}
	
	/**
	 * Extracts the arguments required to add deadline into list.
	 * Also checks if the argument placed is valid.
	 * @param list List of tasks of the user.
	 * @param arg String of arguments inputted by the user.
	 */
	private static void addDeadline(Tasks list, String arg) {
		String[] descriptionAndBy = splitDeadlineArg(arg);
		String description = descriptionAndBy[INDEX_DESCRIPTION];
		String by = descriptionAndBy[BY_DESCRIPTION];
		
		if (description.isBlank() || by.isBlank()) { // check if there is a description and by
			System.out.println(Ui.ERROR_EMPTY_DEADLINE_DESCRIPTION);
			Ui.printHelpDeadline();
		} else {
			Deadline newDeadline = new Deadline(description, by);
			list.addTask(newDeadline);
		}
	}
	
	/**
	 * Splits the arg string into an array of strings of: description and by in that order.
	 * Used specifically to split the argument of addDeadline.
	 * @param arg String which contains the combined arguments of description and by from the user.
	 * @return descriptionAndBy Array of String of size 2 containing
	 * description, from and to respectively.
	 * should any be missing, index would be blank.
	 */
	private static String[] splitDeadlineArg(String arg) {
		String[] splitDescriptionAndBy = arg.split(BY_DEMARCATION, 2);
		String[] descriptionAndBy = new String[]{splitDescriptionAndBy[0].trim(), splitDescriptionAndBy[1].trim()};
		return descriptionAndBy;
	}
	
	/**
	 * Extracts the arguments required to delete the selected task from list.
	 * Also checks if the argument placed is valid.
	 * @param list List of tasks of the user.
	 * @param arg String of arguments inputted by the user.
	 */
	private static void runDelete(Tasks list, String arg) {
		try {
			int taskNumber = Integer.parseInt(arg);
			if (taskNumber >= START_INDEX && taskNumber <= list.getTasksCount()) { // check if it is within the range of list
				list.deleteTask(taskNumber);
			} else {
				System.out.println(Ui.ERROR_TASK_NUMBER_OUT_OF_RANGE_MESSAGE);
			}
		} catch (NumberFormatException e) {
			System.out.println(Ui.ERROR_TASK_NUMBER_NOT_INT_MESSAGE);
		}
	}
	
	/**
	 * Executes clear on list and notifies after list has been cleared.
	 * @param list List of tasks of the user.
	 */
	private static void runClear(Tasks list) {
		list.clear();
		System.out.println(Ui.RUN_CLEAR_COMPLETE_MESSAGE);
	}
}
