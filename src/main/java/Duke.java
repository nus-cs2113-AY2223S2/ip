import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String HELLO_MESSAGE = " Hello! I'm Duke\n" + "     What can I do for you?\n";
    private static final String BYE_MESSAGE = " Bye. Hope to see you again soon!";
    private static final String SEPARATOR = "________________________________________________________." +
            "____";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String LINE_PREFIX = "    ";

    public static void main(String[] args) {
        // reused from contacts Contacts1.java with modification
        showWelcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            String feedback = executeCommand(userCommand);
            showResultToUser(feedback);
        }
    }

    private static void showWelcomeMessage() {
        showToUser("Hello from\n" + LOGO);
        showToUser(SEPARATOR);
        showToUser(HELLO_MESSAGE);
        showToUser(SEPARATOR);
    }

    private static String getUserInput() {
        return SCANNER.nextLine();
    }

    private static String executeCommand(String userInputString) {
        switch (userInputString) {
        case COMMAND_EXIT_WORD:
            exitProgram();
        default:
            return userInputString;
        }
    }

    private static void showResultToUser(String result) {
        showToUser(SEPARATOR, result, SEPARATOR);
    }

    private static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m);
        }
    }

    private static void exitProgram() {
        showToUser(SEPARATOR, BYE_MESSAGE, SEPARATOR);
        System.exit(0);
    }
}
