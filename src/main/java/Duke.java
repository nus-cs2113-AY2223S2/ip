import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
	public static final String DIVIDER = "____________________________________________________________\n";
	public static final String UNKNOWN_COMMAND_MESSAGE = "Error: Unknown command detected, please type help for the list of commands available";
	public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";
	public static final String GREET_MESSAGE = "Hello! I'm Bob\n" + "What can I do for you?\n";
	public static final String ERROR_EMPTY_TODO_DESCRIPTION_MESSAGE = "Error: The description of Todo cannot be empty";
	public static final String ERROR_EMPTY_EVENT_DESCRIPTION_MESSAGE = "Error: The description, from or to cannot be empty";
	public static final String ERROR_EMPTY_DEADLINE_DESCRIPTION = "Error: The description or by cannot be empty";
	public static final String ERROR_TASK_NUMBER_OUT_OF_RANGE_MESSAGE = "Error: task number given out of range";
	public static final String ERROR_TASK_NUMBER_NOT_INT_MESSAGE = "Error: Task Number given is a text and not a number!";
	public static final String PRINT_HELP_INSTRUCTIONS_MESSAGE = "The following are the commands available and their arguments: ";
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
	
	public static void main(String[] args) {
		Tasks list = new Tasks();
		File saveDir = new File("./data");
		File saveFile = new File("data/save.txt");
		
		list.initTasks(saveDir, saveFile);
		greet();
		readCommandLine(list);
		exit();
	}
	
	public static void greet() {
		String greet = DIVIDER +
				GREET_MESSAGE +
				DIVIDER;
		System.out.println(greet);
	}
	
	private static void readCommandLine(Tasks list) {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		
		// continuously reads input from command line until command 'bye' is inputted
		while (!line.equalsIgnoreCase("bye")) {
			System.out.print(DIVIDER);
			runCommand(list, line);
			System.out.println(DIVIDER);
			
			line = in.nextLine();
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
		printHelpList();
		System.out.println();
		printHelpBye();
		System.out.println();
	}
	
	private static void printHelpTodo() {
		System.out.println(HELP_TODO_FORMAT);
		System.out.println(HELP_TODO_DESCRIPTION);
	}
	
	private static void printHelpEvent() {
		System.out.println(HELP_EVENT_FORMAT);
		System.out.println(HELP_EVENT_DESCRIPTION);
	}
	
	private static void printHelpDeadline() {
		System.out.println(HELP_DEADLINE_FORMAT);
		System.out.println(HELP_DEADLINE_DESCRIPTION);
	}
	
	private static void printHelpMark() {
		System.out.println(HELP_MARK_FORMAT);
		System.out.println(HELP_MARK_DESCRIPTION);
	}
	
	private static void printHelpUnmark() {
		System.out.println(HELP_UNMARK_FORMAT);
		System.out.println(HELP_UNMARK_DESCRIPTION);
	}
	
	private static void printHelpList() {
		System.out.println(HELP_LIST_FORMAT);
		System.out.println(HELP_LIST_DESCRIPTION);
	}
	
	private static void printHelpBye() {
		System.out.println(HELP_BYE_FORMAT);
		System.out.println(HELP_BYE_DESCRIPTION);
	}
	private static void runMark(Tasks list, String arg) {
		try {
			int taskNumber = Integer.parseInt(arg);
			if (taskNumber <= list.getTasksCount()) {
				list.markTaskDone(taskNumber);
			} else {
				System.out.println(ERROR_TASK_NUMBER_OUT_OF_RANGE_MESSAGE);
			}
		} catch (NumberFormatException e) {
			System.out.println(ERROR_TASK_NUMBER_NOT_INT_MESSAGE);
		}
	}
	
	private static void runUnmark(Tasks list, String arg) {
		try {
			int taskNumber = Integer.parseInt(arg);
			if (taskNumber <= list.getTasksCount()) {
				list.markTaskUndone(taskNumber);
			} else {
				System.out.println(ERROR_TASK_NUMBER_OUT_OF_RANGE_MESSAGE);
			}
		} catch (NumberFormatException e) {
			System.out.println(ERROR_TASK_NUMBER_NOT_INT_MESSAGE);
		}
	}
	
	private static void addTodo(Tasks list, String arg) {
		if (arg.isBlank()) {
			System.out.println(ERROR_EMPTY_TODO_DESCRIPTION_MESSAGE);
			printHelpTodo();
		} else {
			Todo newTodo = new Todo(arg);
			list.addTask(newTodo);
		}
	}
	
	private static void addEvent(Tasks list, String arg) {
		String[] descriptionFromAndTo = splitEventArg(arg);
		String description = descriptionFromAndTo[0];
		String from = descriptionFromAndTo[1];
		String to = descriptionFromAndTo[2];
		
		if (description.isBlank() || from.isBlank() || to.isBlank()) {
			System.out.println(ERROR_EMPTY_EVENT_DESCRIPTION_MESSAGE);
			printHelpEvent();
		} else {
			Event newEvent = new Event(description, from, to);
			list.addTask(newEvent);
		}
	}
	
	private static String[] splitEventArg(String arg) {
		String[] splitDescription = arg.split("/from", 2); // separate the argument into description and fromAndTo
		String[] splitFromAndTo = splitDescription[1].split("/to", 2); // separate fromAndTo into from and to
		return new String[]{splitDescription[0].trim(), splitFromAndTo[0].trim(), splitFromAndTo[1].trim()};
	}
	
	private static void addDeadline(Tasks list, String arg) {
		String[] descriptionAndBy = splitDeadlineArg(arg);
		String description = descriptionAndBy[0];
		String by = descriptionAndBy[1];
		
		if (description.isBlank() || by.isBlank()) {
			System.out.println(ERROR_EMPTY_DEADLINE_DESCRIPTION);
			printHelpDeadline();
		} else {
			Deadline newDeadline = new Deadline(description, by);
			list.addTask(newDeadline);
		}
	}
	
	private static String[] splitDeadlineArg(String arg) {
		String[] splitDescriptionAndBy = arg.split("/by", 2);
		return new String[]{splitDescriptionAndBy[0].trim(), splitDescriptionAndBy[1].trim()};
	}
	
	private static void printUnknownCommandMessage() {
		System.out.println(UNKNOWN_COMMAND_MESSAGE);
	}
	
	public static void exit() {
		String exit = DIVIDER + EXIT_MESSAGE + DIVIDER;
		System.out.println(exit);
	}
}
