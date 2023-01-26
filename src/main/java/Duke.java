import java.util.Scanner;

public class Duke {
	public static void startDuke(){

		Greetings dukeGreeting = new Greetings();
		dukeGreeting.printGreetings();
		dukeGreeting.printOpeningLine();
		Scanner sc = new Scanner(System.in);

		String userMessage;

		boolean shouldExit = false;

		while(!shouldExit) {
			userMessage = sc.nextLine();
			if (userMessage.equals("bye") || userMessage.equals("exit")) {
				shouldExit = true;
				dukeGreeting.printExitLine();
			} else {
				dukeGreeting.printDivider();
				System.out.println(userMessage);
				dukeGreeting.printDivider();
			}
		}
	}
	public static void main(String[] args) {
		startDuke();
	}
}
