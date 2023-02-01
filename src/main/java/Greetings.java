public class Greetings {
	private static final String LOGO =
			" ____        _        \n"
					+ "|  _ \\ _   _| | _____ \n"
					+ "| | | | | | | |/ / _ \\\n"
					+ "| |_| | |_| |   <  __/\n"
					+ "|____/ \\__,_|_|\\_\\___|\n";
	private static final String OPENING_LINE = "Hello! I'm Duke"
					+ System.lineSeparator()
					+ "What can I do for you?";

	private static final String EXIT_LINE =
			"Bye. Hope to see you again soon!";
	private static final String DIVIDER =
			"______________________________";
	private static final String ERROR = "Hey! Error!";

	private static final String COMMAND = "todo / deadline / event";

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

}


