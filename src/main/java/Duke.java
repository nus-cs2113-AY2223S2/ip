import javax.swing.event.DocumentEvent;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

	private static final Scanner in = new Scanner(System.in);

	// max no. of tasks in taskList
	private static final int MAX_TASKS = 100;
	// array of class Task
	private static final Task[] taskList = new Task[MAX_TASKS];
	;
	private static int listLength = 0;


	private static final String LINE_PARTITION = "    ____________________________________________________________\n";
	private static final String FAREWELL = LINE_PARTITION +
			"\t  Bye. Hope to see you again soon!\n" +
			LINE_PARTITION;
	private static final String GREET = LINE_PARTITION +
			"\t  Hello! I'm kimo\n" +
			"\t  What can I do for you?\n" +
			LINE_PARTITION;

	private static final String NO_ARGS_TASK_ERROR = LINE_PARTITION +
			"\t  Please enter your task details with your command.\n" +
			LINE_PARTITION;
	private static final String CMD_FORMAT_ERROR = LINE_PARTITION +
			"\t  You have entered the wrong format for this command.\n" +
			"\t  Please type \"help\" to see the available list of commands and the relevant formats\n" +
			LINE_PARTITION;
	private static final String INVALID_CMD = LINE_PARTITION +
			"\t  You have entered an invalid command.\n" +
			"\t  Please type \"help\" to see the available list of commands and the relevant formats\n" +
			LINE_PARTITION;

	private static final String HELPLIST = LINE_PARTITION +
			"\t  These are the valid commands and the relevant formats for each:\n" +
			"\t  list : displays the current tasklist of tasks you have entered, with each tasks' statuses shown." +
			"\t  mark (no.) : marks task number (no.) as completed.\n" +
			"\t  unmark (no.) : marks task number (no.) as incomplete.\n" +
			"\t  todo (task) : adds task with description (task) to the tasklist.\n" +
			"\t  deadline (task) /by (date) : adds task with description (task) and due date (date) to the tasklist" +
			".\n" +
			"\t  event (task) /from (date1) /to (date2) : adds task with description (task) " +
			"and start date (date1) and end date (date2)\n" +
			"\t  help : shows list of available commands\n" +
			LINE_PARTITION;

	public static void main(String[] args) {
		System.out.print(GREET);
		while (true) {
			takeUserInput();
		}
	}

	public static void takeUserInput() {
		final String userInput = in.nextLine();
		final String[] cmdTypeAndParams = splitUserInput(userInput);
		final String cmd = cmdTypeAndParams[0];
		final String args = cmdTypeAndParams[1];

		switch (cmd) {
		case "help":
			printHelp();
			break;
		case "list":
			executeList();
			break;
		case "mark":
			executeMark(args, true);
			break;
		case "unmark":
			executeMark(args, false);
			break;
		case "todo":
			executeTaskAdd(args, "todo");
			break;
		case "deadline":
			executeTaskAdd(args, "deadline");
			break;
		case "event":
			executeTaskAdd(args, "event");
			break;
		case "bye":
			executeBye();
		default:
			invalidCmd();
		}
	}

	private static void printHelp() {
		System.out.print(HELPLIST);
	}

	private static String[] splitUserInput(String rawUserInput) {
		final String[] split = rawUserInput.trim().split("\\s+", 2);
		return split.length == 2 ? split : new String[]{split[0], ""}; // else case: no parameters
	}

	private static void executeList() {
		System.out.println(LINE_PARTITION +
				"\t  These are the tasks in your list: ");
		int counter = 1;
		for (Task task : Arrays.copyOf(taskList, listLength)) {
			System.out.println(String.format("\t  %d.%s", counter, task));
			counter++;
		}
		System.out.print(LINE_PARTITION);
	}

	private static void executeMark(String args, boolean hasCompleted) {
		switch (markCommandVerify(args)) {
		case "isValid":
			Task task = taskList[Integer.parseInt(args) - 1];
			task.setDone(hasCompleted);
			if (hasCompleted) {
				printMark(task);
			} else {
				printUnmark(task);
			}
			break;
		case "isOutOfBounds":
			invalidMarkArgs(args, true);
			break;
		case "isNotNum":
			invalidMarkArgs(args, false);
			break;
		case "noInput":
			noMarkArgs();
			break;
		default:
			System.out.print(LINE_PARTITION +
					"Invalid input for \"mark\" command. Please try again." +
					LINE_PARTITION);
		}
	}

	private static String markCommandVerify(String details) {
		if (details.matches("\\d+")) {
			int listNum = Integer.parseInt(details);
			return (listLength >= listNum && listNum > 0) ? "isValid" : "isOutOfBounds";
		}
		return "isNotNum";
	}

	private static void invalidMarkArgs(String args, boolean isNum) {
		if (isNum) {
			System.out.print(LINE_PARTITION +
					String.format("\t  Task number %s does not exist.\n", args));
		} else {
			System.out.print(LINE_PARTITION +
					String.format("\t  %s is not a number.\n", args));
		}
		System.out.print(String.format("\t  Please use a number between 1 and %d for \"mark\" and \"unmark\" " +
				"commands\n", listLength) +
				LINE_PARTITION);
	}

	private static void noMarkArgs() {
		System.out.print("You did not enter any number.\n" +
				String.format("Please use a number between 1 and %d for \"mark\" commands\n", listLength) +
				LINE_PARTITION);
	}

	private static void printMark(Task task) {
		System.out.print(LINE_PARTITION +
				String.format("\t  Great job! I will mark this task as done: \n\t  %s\n", task) +
				LINE_PARTITION);
	}

	private static void printUnmark(Task task) {
		System.out.print(LINE_PARTITION +
				String.format("\t  Alright, I have marked this task as undone: \n\t  %s\n", task) +
				LINE_PARTITION);
	}

	private static void executeTaskAdd(String args, String taskType) {
		if (args == null) {
			System.out.print(NO_ARGS_TASK_ERROR);
		} else if (args.matches("(.*) /by (.*)") && taskType.equals("deadline")) {
			deadlineAdd(args);
		} else if (args.matches("(.*) /from (.*) /to (.*)") && taskType.equals("event")) {
			eventAdd(args);
		} else if (taskType.equals("todo") && !args.isEmpty()) {
			todoAdd(args);
		} else {
			cmdFormatError();
		}
	}

	private static void deadlineAdd(String args) {
		String[] taskArgs = args.trim().split(" /by ", 2);
		Deadline task = new Deadline(taskArgs[0], taskArgs[1]);
		taskList[listLength] = task;
		listLength++;
		printTaskAdd();
	}

	private static void eventAdd(String args) {
		String[] taskArgs = args.trim().split(" /from ", 2);
		String[] taskDates = taskArgs[1].split(" /to ", 2);
		Event task = new Event(taskArgs[0], taskDates[0], taskDates[1]);
		taskList[listLength] = task;
		listLength++;
		printTaskAdd();
	}

	private static void todoAdd(String args) {
		Todo task = new Todo(args);
		taskList[listLength] = task;
		listLength++;
		printTaskAdd();
	}

	private static void cmdFormatError() {
		System.out.print(CMD_FORMAT_ERROR);
	}

	private static void printTaskAdd() {
		System.out.print(LINE_PARTITION +
				String.format("\t  added:%s\n" +
						"\t  You now have %d task(s) in your list.\n", taskList[listLength - 1], listLength) +
				LINE_PARTITION);
	}

	private static void invalidCmd() {
		System.out.print(INVALID_CMD);
	}

	private static void executeBye() {
		System.out.println(FAREWELL);
		System.exit(0);
	}
}

/*
String logo =
        " ____        _        \n" +
        "|  _ \\ _   _| | _____ \n" +
        "| | | | | | | |/ / _ \\\n" +
        "| |_| | |_| |   <  __/\n" +
        "|____/ \\__,_|_|\\_\\___|\n";
System.out.println("Hello from\n" + logo);
*/
