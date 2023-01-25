import java.util.Scanner;

public class Duke {
	public static void main(String[] args) {

		//level-2
		System.out.println("    ____________________________________");
		System.out.println("    Hello! I'm Duke");
		System.out.println("    What can I do for you?\n");
		System.out.println("    ____________________________________");

		String[] userInputs = new String[100];
		int index = 0;

		Scanner userInput = new Scanner(System.in);
		while (true) {
			String text = userInput.nextLine();
			if ("bye".equalsIgnoreCase(text)) {
				break;
			}

			if ("list".equalsIgnoreCase(text)) {
				System.out.println("    ____________________________________");
				for (int i = 0; i < userInputs.length; i += 1) {
					if (userInputs[i] == null) {
						break;
					}
					System.out.println("     " + (i + 1) + ". " + userInputs[i]);
				}
				System.out.println("    ____________________________________");
			} else {
				userInputs[index] = text;
				index += 1;

				System.out.println("    ____________________________________");
				System.out.println("     added: " + text);
				System.out.println("    ____________________________________");
			}
		}

		System.out.println("    ____________________________________");
		System.out.println("    Bye. Hope to see you again soon!");
		System.out.println("    ____________________________________");
	}
}