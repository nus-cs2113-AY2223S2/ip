import java.util.Scanner;

public class Duke {

	public static void main(String[] args) {
		showWelcomeMessage();

		Task[] taskList = new Task[100];
		int taskIndex = 0;

		Scanner input = new Scanner(System.in);

		while (true) {
			String userInput = input.nextLine();

			if ("bye".equalsIgnoreCase(userInput)) {
				break;
			}

			String[] commands = userInput.split(" ");

			switch (commands[0]) {
			case "list":
				printTaskList(taskList);
				break;

			case "mark":
				markSelectedTask(taskList, commands);
				break;

			case "unmark": {
				unmarkSelectedTask(taskList, commands);
				break;
			}

			default:
				Task t = new Task(userInput);

				taskList[taskIndex] = t;
				taskIndex += 1;

				System.out.println("    ____________________________________");
				System.out.println("     added: " + userInput);
				System.out.println("    ____________________________________");
				break;
			}
		}

		showExitMessage();
	}

	private static void unmarkSelectedTask(Task[] taskList, String[] commands) {
		int taskNumber = Integer.parseInt(commands[1]);
		taskNumber -= 1;

		taskList[taskNumber].setNotDone();

		System.out.println("    ____________________________________");
		System.out.println("     OK, I've marked this task as not done yet:");
		System.out.print("       [" + taskList[taskNumber].getStatusIcon() + "] ");
		System.out.println(taskList[taskNumber].description);
		System.out.println("    ____________________________________");
	}

	private static void markSelectedTask(Task[] taskList, String[] commands) {
		int taskNumber = Integer.parseInt(commands[1]);
		taskNumber -= 1;

		taskList[taskNumber].setDone();

		System.out.println("    ____________________________________");
		System.out.println("     Nice! I've marked this task as done:");
		System.out.print("       [" + taskList[taskNumber].getStatusIcon() + "] ");
		System.out.println(taskList[taskNumber].description);
		System.out.println("    ____________________________________");
	}

	private static void printTaskList(Task[] taskList) {
		System.out.println("    ____________________________________");
		System.out.println("     Here are the tasks in your list:");

		for (int i = 0; i < taskList.length; i += 1) {
			if (taskList[i] == null) {
				break;
			}

			System.out.print("     " + (i + 1) + ".");
			System.out.println("[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
		}
		System.out.println("    ____________________________________");
	}

	private static void showWelcomeMessage() {
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);

		System.out.println("    ____________________________________");
		System.out.println("    Hello! I'm Duke");
		System.out.println("    What can I do for you?");
		System.out.println("    ____________________________________");
	}

	private static void showExitMessage() {
		System.out.println("    ____________________________________");
		System.out.println("    Bye. Hope to see you again soon!");
		System.out.println("    ____________________________________");
	}
}