import java.util.Scanner;

public class Duke {
    private static final String LINE = "_".repeat(60);
    private static final String START_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private static void print(String s) {
        System.out.printf("%s%n%s%n%s%n", LINE, s, LINE);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;

        print(START_MESSAGE);
        while (isRunning) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                isRunning = false;
            } else {
                print(command);
            }
        }
        print(EXIT_MESSAGE);
    }
}
