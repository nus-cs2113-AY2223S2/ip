package duke.ui;

import java.util.Scanner;

/**
 * Represents a Ui system that prints messages for the user
 */
public class Ui {
	private static final String LOGO =
			" ____        _        \n"
					+ "|  _ \\ _   _| | _____ \n"
					+ "| | | | | | | |/ / _ \\\n"
					+ "| |_| | |_| |   <  __/\n"
					+ "|____/ \\__,_|_|\\_\\___|\n";
	private static final String OPENING_LINE = "Hello! I'm Duke!"
			+ System.lineSeparator()
			+ "Type 'help' to see what you can do!";

	private static final String EXIT_LINE =
			"Bye. Hope to see you again soon! I will be missing you:(";
	private static final String DIVIDER =
			"______________________________";

	private final String TASK_ADDED =
			"Got it. I've added this task: ";
	private final String TASK_DELETED =
			"Got it. I've removed this task: ";
	private final String TASK_DONE =
			"Nice! I've marked this task as done:) ";
	private final String TASK_UNDONE =
			"Okay! I've unmarked this task:P ";
	private final String FIND_LINE = "Here are the matching tasks in your list: \n";
	private static final String HELP = "    todo {description} --add todo" + "\n"
			+ "    deadline {description} /by {deadline} --add deadline" + "\n"
			+ "    event {description} /from {startTime} /to {endTime}  --add event" + "\n"
			+ "    mark {task index number} --mark the task as done" + "\n"
			+ "    unmark {task index number} --mark the task as undone" + "\n"
			+ "    delete {task index number} --delete the task" + "\n"
			+ "    find {keywords}" + "\n"
			+ "    list --to show the current task list" + "\n"
			+ "    exit --to exit the application";

	/**
	 * Constructor
	 */
	public Ui() {
	}


	/** Displays the welcome message */
	public void showWelcome() {
		System.out.println("Hello from\n" + LOGO);
		printDivider();
		printOpeningLine();
	}

	/**
	 * Read the user command
	 * @return next line message
	 */
	public String readCommand() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	/**
	 * Print the opening message
	 */
	public void printOpeningLine() {
		System.out.println(OPENING_LINE);
	}

	/**
	 * Print the exit message
	 */
	public void printExitLine() {
		System.out.println(EXIT_LINE);
	}

	/**
	 * Print the divider line
	 */
	public void printDivider() {
		System.out.println(DIVIDER);
	}

	/**
	 * Print the error message
	 * @param message the error message
	 */
	public void showError(String message) {
		System.out.println(message);
	}

	/**
	 * Print help messages
	 */
	public void printHelp() {
		System.out.println(HELP);
	}

	/**
	 * Print add task message
	 */
	public void printAddingLine() {
		System.out.println(TASK_ADDED);
	}

	/**
	 * Print delete task message
	 */
	public void printDeletingLine() {
		System.out.println(TASK_DELETED);
	}

	/**
	 * Print mark task message
	 */
	public void printTaskDoneLine() {
		System.out.println(TASK_DONE);
	}

	/**
	 * Print unmark task message
	 */
	public void printTaskUndoneLine() {
		System.out.println(TASK_UNDONE);
	}

	/**
	 * Print find message
	 */
	public void printFindLine() {
		System.out.println(FIND_LINE);
	}
}
