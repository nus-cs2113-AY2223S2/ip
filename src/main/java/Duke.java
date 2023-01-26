import java.util.Scanner;

public class Duke {
	public static void startDuke() {

		Greetings dukeGreeting = new Greetings();
		dukeGreeting.printGreetings();
		dukeGreeting.printOpeningLine();
		Scanner sc = new Scanner(System.in);

		String[] tasks = new String[100];
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
				if (taskCount == 0) {
					System.out.println("No task！");
				} else {
					for (int i = 0; i < taskCount; i += 1) {
						System.out.println((i + 1) + ". " + tasks[i]);
					}
				}
				dukeGreeting.printDivider();
			} else {
				tasks[taskCount] = userMessage;
				dukeGreeting.printDivider();
				System.out.println("Added: " + userMessage);
				dukeGreeting.printDivider();
		}
		taskCount += 1;
	}

}

	public static void main(String[] args) {
		startDuke();
	}
}
