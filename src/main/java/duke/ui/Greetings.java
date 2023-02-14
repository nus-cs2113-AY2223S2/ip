package duke.ui;

public class Greetings {
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
	private static final String ERROR = "Hey! Error!";

	private static final String HELP = "    todo {description} --add todo" + System.lineSeparator()
			+ "    deadline {description} /by {deadline} --add deadline" + System.lineSeparator()
			+ "    event {description} /from {startTime} /to {endTime}  --add event" + System.lineSeparator()
			+ "    mark {task serial number} --mark the task as done" + System.lineSeparator()
			+ "    unmark {task serial number} --mark the task as undone" + System.lineSeparator()
			+ "    list --to show the current task list";

	public Greetings() {
	}

	public void printGreetings() {
		System.out.println("Hello from\n" + LOGO);
		printDivider();
	}

	public void printOpeningLine() {
		printDivider();
		System.out.println(OPENING_LINE);
		printDivider();
	}

	public void printExitLine() {
		printDivider();
		System.out.println(EXIT_LINE);
		printDivider();
	}

	public void printDivider() {
		System.lineSeparator();
		System.out.println(DIVIDER);
	}

	public void printErrorMessage() {
		printDivider();
		System.out.println(ERROR);

		printDivider();
	}

	public void printHelp() {
		printDivider();
		System.out.println(HELP);
		printDivider();
	}
}


