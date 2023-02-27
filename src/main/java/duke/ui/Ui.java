package duke.ui;

import java.util.Scanner;

public class Ui {
	private static final String LOGO =
			" ____        _        \n"
					+ "|  _ \\ _   _| | _____ \n"
					+ "| | | | | | | |/ / _ \\\n"
					+ "| |_| | |_| |   <  __/\n"
					+ "|____/ \\__,_|_|\\_\\___|\n";
	private static final String OPENING_LINE = "Hello! I'm duke.Duke"
			+ System.lineSeparator()
			+ "What can I do for you? Type 'help' for the command list";

	private static final String EXIT_LINE =
			"Bye. Hope to see you again soon!";
	private static final String DIVIDER =
			"______________________________";

	private final String TASK_ADDED =
			"Got it. I've added this task: ";
	private final String TASK_DELETED =
			"Got it. I've removed this task: ";
	private final String TASK_DONE =
			"Nice! I've marked this task as done: ";
	private final String TASK_UNDONE =
			"Okay! I've unmarked this task: ";

	private static final String HELP = "    todo {description} --add todo" + System.lineSeparator()
			+ "    deadline {description} /by {deadline} --add deadline" + System.lineSeparator()
			+ "    event {description} /from {startTime} /to {endTime}  --add event" + System.lineSeparator()
			+ "    mark {task serial number} --mark the task as done" + System.lineSeparator()
			+ "    unmark {task serial number} --mark the task as undone" + System.lineSeparator()
			+ "    list --to show the current task list";

	public Ui() {
	}

	public void showWelcome() {
		System.out.println("Hello from\n" + LOGO);
		printDivider();
		printOpeningLine();
	}

	public String readCommand() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	public void printOpeningLine() {
		System.out.println(OPENING_LINE);
	}

	public void printExitLine() {
		System.out.println(EXIT_LINE);
	}

	public void printDivider() {
		System.out.println(DIVIDER);
	}

	public void showError(String message) {
		System.out.println(message);
	}

	public void printHelp() {
		System.out.println(HELP);
	}

	public void printAddingLine() {
		System.out.println(TASK_ADDED);
	}

	public void printDeletingLine() {
		System.out.println(TASK_DELETED);
	}


	public void printTaskDoneLine() {
		System.out.println(TASK_DONE);
	}

	public void printTaskUndoneLine() {
		System.out.println(TASK_UNDONE);
	}
}
