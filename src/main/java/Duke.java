import java.util.ArrayList;
import java.util.List;
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
    private static final String COMMAND_LIST = "LIST";
    private static List<String> storedUserInputs = new ArrayList<>();

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
        } else if (cmd.equals(COMMAND_LIST)) {
            for (int index = 1; index <= storedUserInputs.size(); index++) {
                System.out.println(index + ". " + storedUserInputs.get(index - 1));
            }
        } else {
            storedUserInputs.add(userInput);
            System.out.println("added: " + userInput);
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
