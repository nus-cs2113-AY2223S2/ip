import java.util.Scanner;

public class Duke {
	public static void main(String[] args) {
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

		Task[] userInputs = new Task[100];
		int index = 0;

		Scanner userInput = new Scanner(System.in);
		while (true) {
			String text = userInput.nextLine();

			if ("bye".equalsIgnoreCase(text)) {
				break;
			}

			String[] commands = text.split(" ", 2);

			switch (commands[0]) {
			case "list":
				System.out.println("    ____________________________________");
				System.out.println("     Here are the tasks in your list:");

				for (int i = 0; i < userInputs.length; i += 1) {
					if (userInputs[i] == null) {
						break;
					}

					System.out.print("     " + (i + 1) + ".");
					System.out.println("[" + userInputs[i].getStatusIcon() + "] " + userInputs[i].description);
				}
				System.out.println("    ____________________________________");
				break;

			//brace added for different scopes
			case "mark": {
				int taskNumber = Integer.parseInt(commands[1]);
				taskNumber -= 1;

				userInputs[taskNumber].setDone();

				System.out.println("    ____________________________________");
				System.out.println("     Nice! I've marked this task as done:");
				System.out.print("       [" + userInputs[taskNumber].getStatusIcon() + "] ");
				System.out.println(userInputs[taskNumber].description);
				System.out.println("    ____________________________________");
				break;
			}

			case "unmark": {
				int taskNumber = Integer.parseInt(commands[1]);
				taskNumber -= 1;

				userInputs[taskNumber].setNotDone();

				System.out.println("    ____________________________________");
				System.out.println("     OK, I've marked this task as not done yet:");
				System.out.print("       [" + userInputs[taskNumber].getStatusIcon() + "] ");
				System.out.println(userInputs[taskNumber].description);
				System.out.println("    ____________________________________");
				break;
			}

			default:
				Task t = new Task(text);

				userInputs[index] = t;
				index += 1;

				System.out.println("    ____________________________________");
				System.out.println("     added: " + text);
				System.out.println("    ____________________________________");
				break;
			}
		}

		System.out.println("    ____________________________________");
		System.out.println("    Bye. Hope to see you again soon!");
		System.out.println("    ____________________________________");
	}
}