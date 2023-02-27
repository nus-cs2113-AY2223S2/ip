package DukeManager;// packages import
import DukeManager.Commands.Cmd;
import DukeManager.Ui.TextUi;
import DukeManager.data.DukeErrors.BlankListException;
import DukeManager.data.TaskList;
import DukeManager.data.Tasks.Deadline;
import DukeManager.data.Tasks.Event;
import DukeManager.data.Tasks.Task;
import DukeManager.data.Tasks.Todo;
import org.w3c.dom.Text;

// java library import
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

	/*private static final Scanner in = new Scanner(System.in);
	private static final ArrayList<Task> taskList = new ArrayList<>();


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
				String[] parts = line.split(" ", 2);
				String taskType = parts[0];
				boolean status = parts[1].charAt(1) == 'X';
				String taskDesc = parts[1].substring(4);
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
			System.out.println("\t  Saved list loaded. Welcome back!");
		} catch (FileNotFoundException e) {
			System.out.println("\t  Welcome, new user. How may I help you?");
		}
	}

	public static void takeUserInput() {
		String userInput = in.nextLine();
		String[] cmdTypeAndParams = splitUserInput(userInput);
		String cmd = cmdTypeAndParams[0];
		String args = cmdTypeAndParams[1];

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
		case "delete":
			tryDelete(args);
			break;
		case "bye":
			executeBye();
		default:
			invalidCmd();
		}
	}

	private static void printHelp() {
		System.out.print(HELP_LIST);
	}

	private static String[] splitUserInput(String rawUserInput) {
		String[] split = rawUserInput.trim().split("\\s+", 2);

		// else case: no parameters for cmd
		return split.length == 2 ? split : new String[]{split[0], ""};
	}

	private static void executeList() throws BlankListException {
		if (taskList.size() == 0) {
			throw new BlankListException();
		}
		System.out.println(LINE_PARTITION +
				"\t  These are the task(s) in your list: ");

		for (int i = 1; i < taskList.size() + 1; i++) {
			System.out.printf("\t  %d.%s%n", i, taskList.get(i - 1));
		}
		System.out.print(LINE_PARTITION);
	}

	private static void saveTaskListToFile() {
		try {
			writeToFile();
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}

	private static void writeToFile() throws IOException {
		FileWriter fw = new FileWriter(FILE_PATH);
		for (Task task : taskList) {
			fw.write(task + "\n");
		}
		fw.close();
	}

	private static void executeMark(String args, boolean hasCompleted) {
		try {
			markInitiate(args, hasCompleted);
		} catch (NumberFormatException e) {
			// non-convertable / no input
			invalidMarkArgs();

		} catch (NullPointerException | IndexOutOfBoundsException e) {
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
		System.out.printf(LINE_PARTITION +
				"\t  Great job! I will mark this task as done: \n" +
				"\t  %s\n", task +
				LINE_PARTITION);
	}

	private static void printUnmark(Task task) {
		System.out.printf(LINE_PARTITION +
				"\t  Alright, I have marked this task as undone: \n" +
				"\t  %s\n", task +
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
			printTaskAdd();
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
				"\t  There are %d item(s) in your list.\n", taskList.size()) +
				"\t  Please enter a number in the appropriate range." + ERROR_FACE + "\n" +
				LINE_PARTITION);
	}

	private static void invalidMarkArgs() {
		System.out.print(INVALID_ARGS_MSG);
	}

	private static void emptyListError() {
		System.out.print(EMPTY_LIST_ERROR_MSG);
	}

	private static void tryDelete(String args) {
		try {
			executeDelete(args);

		} catch (NumberFormatException e) {
			// non-convertable / no input
			invalidMarkArgs();

		} catch (NullPointerException | IndexOutOfBoundsException e) {
			// prompt > listLength / non-positive
			OutOfBoundArgs();
		}
	}

	private static void executeDelete (String args) {
		Task task = taskList.remove(Integer.parseInt(args) - 1);
		System.out.println(LINE_PARTITION +
				"\t  Okies, I have removed this task: \n\t  " + task + "\n" +
				LINE_PARTITION);
	}

	private static void executeBye() {
		System.out.println(FAREWELL);
		System.exit(0);
	}

	private static void cmdFormatError() {
		System.out.print(CMD_FORMAT_ERROR);
	}

	private static void printTaskAdd() {
		System.out.printf(LINE_PARTITION +
				"\t  added:%s\n" +
				"\t  You now have %d task(s) in your list.\n" +
				LINE_PARTITION, taskList.get(taskList.size() - 1), taskList.size());
	}

	private static void invalidCmd() {
		System.out.print(INVALID_CMD);
	}*/
	private Storage storage;
	private TaskList tasks;
	private Ui ui;

	public Duke(String filePath) {
		ui = new TextUi();
		storage = new Storage(filePath);
		try {
			tasks = new TaskList(storage.load());
		} catch (DukeException e) {
			ui.showLoadingError();
			tasks = new TaskList();
		}
	}

	public void run() {
		ui.showWelcome();
		boolean isExit = false;
		while (!isExit) {
			try {
				String fullCommand = ui.readCommand();
				ui.showLine(); // show the divider line ("_______")
				Cmd c = Parser.parse(fullCommand);
				c.execute(tasks, ui, storage);
				isExit = c.isExit();
			} catch (DukeException e) {
				ui.showError(e.getMessage());
			} finally {
				ui.showLine();
			}
		}
	}

	public static void main(String[] args) {
		new Duke("data/tasks.txt").run();
	}
}