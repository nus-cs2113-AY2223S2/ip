import DukeErrors.BlankListException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

	private static final Scanner in = new Scanner(System.in);

	// array of class Task

	private static final String LINE_PARTITION = "    ____________________________________________________________\n";
	private static final String FAREWELL = LINE_PARTITION +
			"\t  Bye. Hope to see you again soon!\n" +
			LINE_PARTITION;
	private static final String GREET = LINE_PARTITION +
			"\t  Hello! I'm kimo\n" +
			"\t  What can I do for you?\n" +
			LINE_PARTITION;
	private static final String FILE_PATH = "taskList.txt";
	private static ArrayList<Task> taskList = new ArrayList<>();

	//ERROR MESSAGE FINAL STRINGS
	private static final String ERROR_FACE = "(ಠ_ಠ)";
	private static final String INVALID_ARGS_MSG = LINE_PARTITION +
			"\t  You did not enter a number.\n" +
			"\t  Try again." + ERROR_FACE + "\n" +
			LINE_PARTITION;
	private static final String CMD_FORMAT_ERROR = LINE_PARTITION +
			"\t  You have entered the wrong format for this command.\n" +
			"\t  Please type \"help\" to see the list of commands and the relevant formats " + ERROR_FACE + "\n" +
			LINE_PARTITION;
	private static final String INVALID_CMD = LINE_PARTITION +
			"\t  You have entered an invalid command.\n" +
			"\t  Please type \"help\" to see the available list of commands and the relevant formats\n" +
			LINE_PARTITION;


	//HELP MESSAGE
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

	//MAIN CODE
	public static void main(String[] args) {
		System.out.print(GREET);
		loadListFromFile();
		while (true) {
			takeUserInput();
			saveTaskListToFile();
		}
	}

	private static void loadListFromFile() {
		try (Scanner reader = new Scanner(new File(FILE_PATH))) {
			String line;
			while (reader.hasNext()) {
				line = reader.nextLine();
				String[] parts = line.split(" ", 3);
				String taskType = parts[0];
				boolean status = parts[1].equals("[X]");
				String taskDesc = parts[2];
				switch (taskType) {
				case ("[D]"):
					deadlineAdd(taskDesc);
					break;
				case ("[E]"):
					eventAdd(taskDesc);
					break;
				case ("[T]"):
					todoAdd(taskDesc);
					break;
				default:
				}
				Task task = taskList.get(taskList.size() - 1);
				task.setDone(status);
			}
			System.out.println("List loaded from file.");
		} catch (FileNotFoundException e) {
			System.out.println("List file not found. Starting with an empty list.");
			taskList = new ArrayList<>();
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
			try {
				executeList();
			} catch (BlankListException e) {
				emptyListError();
			}
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

		// else case: no parameters for cmd
		return split.length == 2 ? split : new String[]{split[0], ""};
	}

	private static void executeList() throws BlankListException {
		if (taskList.size() == 0) {
			throw new BlankListException();
		}
		System.out.println(LINE_PARTITION +
				"\t  These are the tasks in your list: ");
		for (int i = 1; i < taskList.size() + 1; i++) {
			System.out.println(String.format("\t  %d.%s", i, taskList.get(i - 1)));
		}
		System.out.print(LINE_PARTITION);
	}

	private static void saveTaskListToFile() {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
			outputStream.writeObject(taskList);
		} catch (IOException e) {
			System.out.println("Error writing to file: " + FILE_PATH);
		}
	}

	private static void executeMark(String args, boolean hasCompleted) {
		try {
			markInitiate(args, hasCompleted);

		} catch (NumberFormatException e) {
			// non-convertable / no input
			invalidMarkArgs();

		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			// prompt > listLength / non-positive
			OutOfBoundArgs();

		} catch (BlankListException e) {
			emptyListError();
		}
	}
	private static void markInitiate(String args, boolean hasCompleted) throws BlankListException {
		if (taskList.size() == 0) {
			throw new BlankListException();
		}
		Task task = taskList.get(Integer.parseInt(args) - 1);
		task.setDone(hasCompleted);
		if (hasCompleted) {
			printMark(task);
		} else {
			printUnmark(task);
		}

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
		try {
			switch (taskType) {
			case ("deadline"):
				deadlineAdd(args);
				break;
			case ("event"):
				eventAdd(args);
				break;
			case ("todo"):
				todoAdd(args);
				break;
			default:
			}
			printTaskAdd(taskList.get(taskList.size() - 1));
		} catch (ArrayIndexOutOfBoundsException e) {
			cmdFormatError();
		}
	}

	private static void deadlineAdd(String args) {
		String[] taskArgs = args.trim().split(" /by ", 2);
		Deadline task = new Deadline(taskArgs[0], taskArgs[1]);
		taskList.add(task);
	}

	private static void eventAdd(String args) {
		String[] taskArgs = args.trim().split(" /from ", 2);
		String[] taskDates = taskArgs[1].split(" /to ", 2);
		Event task = new Event(taskArgs[0], taskDates[0], taskDates[1]);
		taskList.add(task);
	}

	private static void todoAdd(String args) {
		Todo task = new Todo(args);
		taskList.add(task);
	}
	private static void OutOfBoundArgs() {
		System.out.print(String.format(LINE_PARTITION +
				"\t  There are %d items in your list.\n", taskList.size()) +
				"\t  Please enter a number in the appropriate range." + ERROR_FACE + "\n" +
				LINE_PARTITION);
	}

	private static void invalidMarkArgs() {
		System.out.print(INVALID_ARGS_MSG);
	}

	private static void emptyListError() {
		System.out.print(LINE_PARTITION +
				"\t  Your list is empty. Please add items before marking them." + ERROR_FACE + "\n" +
				LINE_PARTITION);
	}

	private static void cmdFormatError() {
		System.out.print(CMD_FORMAT_ERROR);
	}

	private static void printTaskAdd(Task task) {
		System.out.print(LINE_PARTITION +
				String.format("\t  added:%s\n" +
						"\t  You now have %d task(s) in your list.\n", task, taskList.size()) +
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