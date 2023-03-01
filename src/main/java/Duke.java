import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Tasks;
import tasks.Todo;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
	public static final String FIND_COMMAND = "find";

	private Ui ui;
	private Storage storage;
	private Tasks list;
	
	public Duke() {
		ui = new Ui();
		storage = new Storage();
		list = Storage.loadSave();
	}
	
	public void run() {
		Ui.greet();
		readCommandLine(list);
		Ui.exit();
	}
	public static void main(String[] args) {
		new Duke().run();
	}
	
	public static void greet() {
		String greet = Ui.DIVIDER +
				Ui.GREET_MESSAGE +
				Ui.DIVIDER;
		System.out.println(greet);
	}
	
	private static void readCommandLine(Tasks list) {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		
		// continuously reads input from command line until command 'bye' is inputted
		while (!line.equalsIgnoreCase(Ui.BYE_COMMAND)) {
			System.out.print(Ui.DIVIDER);
			runCommand(list, line);
			System.out.println(Ui.DIVIDER);
			line = in.nextLine();
			try {
				Storage.saveTasks(list);
			} catch (IOException e) {
				Ui.printUnableToSaveChanges();
			} catch (NullPointerException e) {
				Ui.printNoFileDetected();
			}
		}
	}
	
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
		case FIND_COMMAND:
			runFind(list, arg);
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
	
	private static String[] splitCommandAndArgs(String line) {
		final String[] splitStrings = line.split(" ", 2);
		return splitStrings.length == 2 ? splitStrings : new String[]{splitStrings[0], ""};
	}
	
	private static void runMark(Tasks list, String arg) {
		try {
			int taskNumber = Integer.parseInt(arg);
			if (taskNumber <= list.getTasksCount()) {
				list.markTaskDone(taskNumber);
			} else {
				System.out.println(Ui.ERROR_TASK_NUMBER_OUT_OF_RANGE_MESSAGE);
			}
		} catch (NumberFormatException e) {
			System.out.println(Ui.ERROR_TASK_NUMBER_NOT_INT_MESSAGE);
		}
	}
	
	private static void runUnmark(Tasks list, String arg) {
		try {
			int taskNumber = Integer.parseInt(arg);
			if (taskNumber <= list.getTasksCount()) {
				list.markTaskUndone(taskNumber);
			} else {
				System.out.println(Ui.ERROR_TASK_NUMBER_OUT_OF_RANGE_MESSAGE);
			}
		} catch (NumberFormatException e) {
			System.out.println(Ui.ERROR_TASK_NUMBER_NOT_INT_MESSAGE);
		}
	}
	
	private static void addTodo(Tasks list, String arg) {
		if (arg.isBlank()) {
			System.out.println(Ui.ERROR_EMPTY_TODO_DESCRIPTION_MESSAGE);
			Ui.printHelpTodo();
		} else {
			Todo newTodo = new Todo(arg);
			list.addTask(newTodo);
		}
	}
	
	private static void addEvent(Tasks list, String arg) {
		String[] descriptionFromAndTo = splitEventArg(arg);
		String description = descriptionFromAndTo[INDEX_DESCRIPTION];
		String from = descriptionFromAndTo[INDEX_FROM];
		String to = descriptionFromAndTo[INDEX_TO];
		
		if (description.isBlank() || from.isBlank() || to.isBlank()) {
			System.out.println(Ui.ERROR_EMPTY_EVENT_DESCRIPTION_MESSAGE);
			Ui.printHelpEvent();
		} else {
			Event newEvent = new Event(description, from, to);
			list.addTask(newEvent);
		}
	}
	
	private static String[] splitEventArg(String arg) {
		String[] splitDescription = arg.split(FROM_DEMARCATION, 2); // separate the argument into description and fromAndTo
		String[] splitFromAndTo = splitDescription[1].split(TO_DEMARCATION, 2); // separate fromAndTo into from and to
		return new String[]{splitDescription[0].trim(), splitFromAndTo[0].trim(), splitFromAndTo[1].trim()};
	}
	
	private static void addDeadline(Tasks list, String arg) {
		String[] descriptionAndBy = splitDeadlineArg(arg);
		String description = descriptionAndBy[INDEX_DESCRIPTION];
		String by = descriptionAndBy[BY_DESCRIPTION];
		
		if (description.isBlank() || by.isBlank()) {
			System.out.println(Ui.ERROR_EMPTY_DEADLINE_DESCRIPTION);
			Ui.printHelpDeadline();
		} else {
			Deadline newDeadline = new Deadline(description, by);
			list.addTask(newDeadline);
		}
	}
	
	private static String[] splitDeadlineArg(String arg) {
		String[] splitDescriptionAndBy = arg.split(BY_DEMARCATION, 2);
		return new String[]{splitDescriptionAndBy[0].trim(), splitDescriptionAndBy[1].trim()};
	}
	
	/**
	 * Extracts the argument required to find for the keyword within the  list of tasks.
	 * Should there be no matching tasks containing the keyword there would be a message informing the user.
	 * @param list List of tasks of user.
	 * @param arg String of argument inputted by the user.
	 */
	private static void runFind(Tasks list, String arg) {
		String keyword = arg;
		
		// check if keyword is empty
		if (keyword.isBlank()) {
			System.out.println(Ui.ERROR_NO_KEYWORD_MESSAGE);
			return;
		}
		
		ArrayList<Integer> findTasksIndex = list.findTasks(keyword);
		int findTasksCount = findTasksIndex.size();
		if (findTasksCount == 0) {
			System.out.println(Ui.NO_MATCHING_TASK_MESSAGE);
		} else {
			System.out.println(Ui.FIND_TASK_MESSAGE);
			findTasksIndex.forEach((i) -> list.printTask(i));
		}
	}
	
	private static void runDelete(Tasks list, String arg) {
		try {
			int taskNumber = Integer.parseInt(arg);
			if (taskNumber <= list.getTasksCount()) {
				list.deleteTask(taskNumber);
			} else {
				System.out.println(Ui.ERROR_TASK_NUMBER_OUT_OF_RANGE_MESSAGE);
			}
		} catch (NumberFormatException e) {
			System.out.println(Ui.ERROR_TASK_NUMBER_NOT_INT_MESSAGE);
		}
	}
	
	private static void runClear(Tasks list) {
		list.clear();
		System.out.println(Ui.RUN_CLEAR_COMPLETE_MESSAGE);
	}
	
	
	
}
