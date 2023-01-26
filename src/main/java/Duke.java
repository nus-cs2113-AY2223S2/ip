import java.util.Scanner;

public class Duke {

	public static int findTaskNumber(String input) {
		String number = input.replaceAll("[^0-9]", "");
		int taskNumber = Integer.parseInt(number);
		return taskNumber;
	}

	public static void printTaskList() {

	}

	public static void startDuke() {

		Greetings dukeGreeting = new Greetings();
		dukeGreeting.printGreetings();
		dukeGreeting.printOpeningLine();
		Scanner sc = new Scanner(System.in);

		Task[] tasks = new Task[100];
		int taskCount = 0;

		String userMessage;

		boolean shouldExit = false;

		while (!shouldExit) {
			userMessage = sc.nextLine();
			if (userMessage.equals("bye")) {
				shouldExit = true;
				dukeGreeting.printExitLine();
			} else if (userMessage.equals("hello") || userMessage.equals("echo")) {
				dukeGreeting.printDivider();
				System.out.println(userMessage);
				dukeGreeting.printDivider();
			} else if (userMessage.equals("list")) {
				dukeGreeting.printDivider();
				if (taskCount == 0 || taskCount < 0) {
					System.out.println("No task！");
				} else {
					System.out.println("Here are the tasks in your list: ");
					for (int i = 0; i < taskCount; i += 1) {
						int serialNumber = i + 1;
						System.out.print(serialNumber + ".");
						tasks[i].printTask();
					}
				}
				dukeGreeting.printDivider();
			} else if (userMessage.startsWith("mark")) {
				int no = findTaskNumber(userMessage);
				if (no == 0 || no < 0) {
					dukeGreeting.printErrorMessage();
				} else {
					dukeGreeting.printDivider();
					tasks[no - 1].markAsDone();
					tasks[no - 1].printTask();
					dukeGreeting.printDivider();
				}
			} else if (userMessage.startsWith("unmark")) {
				int no = findTaskNumber(userMessage);
				if (no == 0 || no < 0) {
					dukeGreeting.printErrorMessage();
				} else {
					dukeGreeting.printDivider();
					tasks[no - 1].unmarkAsUndone();
					tasks[no - 1].printTask();
					dukeGreeting.printDivider();
				}
			} else {
				Task t = new Task(userMessage);
				tasks[taskCount] = t;
				dukeGreeting.printDivider();
				System.out.println("Added: " + tasks[taskCount].getDescription());
				taskCount += 1;
				dukeGreeting.printDivider();
			}
		}
	}

	public static void main(String[] args) {
		startDuke();
	}
}
