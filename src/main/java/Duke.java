import java.util.Scanner;

public class Duke {

    /**
     * Chatbot interface strings
     */
    private static final String GREET_STR = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String PROMPT_STR = "> ";
    private static final String EXIT_STR = "Bye. Hope to see you again soon!";

    /**
     * User commands
     */
    private static final String COMMAND_EXIT = "BYE";

    private static boolean isRunning;
    private static Scanner scanner;

    // Overloaded default for printDivider
    private static void printDivider() {
        printDivider(60);
    }

    private static void printDivider(int width) {
        System.out.println("_".repeat(width));
    }

    private static String getUserCommand() {
        System.out.print(PROMPT_STR);
        return scanner.nextLine();
    }

    private static void handleUserInput(String userInput) {
        String cmd = userInput.trim().toUpperCase();
        if (cmd.equals(COMMAND_EXIT)) {
            isRunning = false;
        } else {
            // Just print out user input as is
            System.out.println(userInput);
        }
    }

    public static void main(String[] args) {
        printDivider();
        System.out.println(GREET_STR);

        // Initializations
        isRunning = true;
        scanner = new Scanner(System.in);

        // Main user interaction loop
        while (isRunning) {
            printDivider();
            String userInput = getUserCommand();
            handleUserInput(userInput);
        }

        printDivider();
        System.out.println(EXIT_STR);
    }
}
