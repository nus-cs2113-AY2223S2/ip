import storage.ListEncoder;
import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Tasks;
import tasks.Todo;
import ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
	// TODO DELETE

	// TODO DELETE
	
	public static void main(String[] args) {
		Tasks list = Storage.loadSave();
		greet();
		readCommandLine(list);
		exit();
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
		while (!line.equalsIgnoreCase("bye")) {
			System.out.print(Ui.DIVIDER);
			runCommand(list, line);
			System.out.println(Ui.DIVIDER);
			line = in.nextLine();
			try {
				Storage.saveTasks(list);
			} catch (IOException e) {
				System.out.println("Error: Unable to save changes");
			} catch (NullPointerException e) {
				System.out.println("Error: Nothing detected in save file");
			}
		}
	}
	
	private static void runCommand(Tasks list, String line) {
		String[] commandAndArg = splitCommandAndArgs(line);
		String command = commandAndArg[0];
		String arg = commandAndArg[1];
		
		switch (command) {
		case "list":
			list.printList();
			break;
		case "help":
			printHelp();
			break;
		case "mark":
			runMark(list, arg);
			break;
		case "unmark":
			runUnmark(list, arg);
			break;
		case "todo":
			addTodo(list, arg);
			break;
		case "event":
			addEvent(list, arg);
			break;
		case "deadline":
			addDeadline(list, arg);
			break;
		case "delete":
			runDelete(list, arg);
			break;
		case "clear":
			runClear(list);
			break;
		default:
			printUnknownCommandMessage();
			break;
		}
	}
	
	private static String[] splitCommandAndArgs(String line) {
		final String[] splitStrings = line.split(" ", 2);
		return splitStrings.length == 2 ? splitStrings : new String[]{splitStrings[0], ""};
	}
	
	private static void printHelp() {
		//  TODO
		System.out.println(Ui.PRINT_HELP_INSTRUCTIONS_MESSAGE);
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
	
	private static void printHelpTodo() {
		System.out.println(Ui.HELP_TODO_FORMAT);
		System.out.println(Ui.HELP_TODO_DESCRIPTION);
	}
	
	private static void printHelpEvent() {
		System.out.println(Ui.HELP_EVENT_FORMAT);
		System.out.println(Ui.HELP_EVENT_DESCRIPTION);
	}
	
	private static void printHelpDeadline() {
		System.out.println(Ui.HELP_DEADLINE_FORMAT);
		System.out.println(Ui.HELP_DEADLINE_DESCRIPTION);
	}
	
	private static void printHelpMark() {
		System.out.println(Ui.HELP_MARK_FORMAT);
		System.out.println(Ui.HELP_MARK_DESCRIPTION);
	}
	
	private static void printHelpUnmark() {
		System.out.println(Ui.HELP_UNMARK_FORMAT);
		System.out.println(Ui.HELP_UNMARK_DESCRIPTION);
	}
	
	private static void printHelpDelete() {
		System.out.println(Ui.HELP_DELETE_FORMAT);
		System.out.println(Ui.HELP_DELETE_DESCRIPTION);
	}
	
	private static void printHelpClear() {
		System.out.println(Ui.HELP_CLEAR_FORMAT);
		System.out.println(Ui.HELP_CLEAR_DESCRIPTION);
	}
	
	private static void printHelpList() {
		System.out.println(Ui.HELP_LIST_FORMAT);
		System.out.println(Ui.HELP_LIST_DESCRIPTION);
	}
	
	private static void printHelpBye() {
		System.out.println(Ui.HELP_BYE_FORMAT);
		System.out.println(Ui.HELP_BYE_DESCRIPTION);
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
			printHelpTodo();
		} else {
			Todo newTodo = new Todo(arg);
			list.addTask(newTodo);
		}
	}
	
	private static void addEvent(Tasks list, String arg) {
		String[] descriptionFromAndTo = splitEventArg(arg);
		String description = descriptionFromAndTo[Ui.INDEX_DESCRIPTION];
		String from = descriptionFromAndTo[Ui.INDEX_FROM];
		String to = descriptionFromAndTo[Ui.INDEX_TO];
		
		if (description.isBlank() || from.isBlank() || to.isBlank()) {
			System.out.println(Ui.ERROR_EMPTY_EVENT_DESCRIPTION_MESSAGE);
			printHelpEvent();
		} else {
			Event newEvent = new Event(description, from, to);
			list.addTask(newEvent);
		}
	}
	
	private static String[] splitEventArg(String arg) {
		String[] splitDescription = arg.split(Ui.FROM_DEMARCATION, 2); // separate the argument into description and fromAndTo
		String[] splitFromAndTo = splitDescription[1].split(Ui.TO_DEMARCATION, 2); // separate fromAndTo into from and to
		return new String[]{splitDescription[0].trim(), splitFromAndTo[0].trim(), splitFromAndTo[1].trim()};
	}
	
	private static void addDeadline(Tasks list, String arg) {
		String[] descriptionAndBy = splitDeadlineArg(arg);
		String description = descriptionAndBy[Ui.INDEX_DESCRIPTION];
		String by = descriptionAndBy[Ui.BY_DESCRIPTION];
		
		if (description.isBlank() || by.isBlank()) {
			System.out.println(Ui.ERROR_EMPTY_DEADLINE_DESCRIPTION);
			printHelpDeadline();
		} else {
			Deadline newDeadline = new Deadline(description, by);
			list.addTask(newDeadline);
		}
	}
	
	private static String[] splitDeadlineArg(String arg) {
		String[] splitDescriptionAndBy = arg.split(Ui.BY_DEMARCATION, 2);
		return new String[]{splitDescriptionAndBy[0].trim(), splitDescriptionAndBy[1].trim()};
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
	
	private static void printUnknownCommandMessage() {
		System.out.println(Ui.UNKNOWN_COMMAND_MESSAGE);
	}
	
	public static void exit() {
		String exit = Ui.DIVIDER + Ui.EXIT_MESSAGE + Ui.DIVIDER;
		System.out.println(exit);
	}
}
