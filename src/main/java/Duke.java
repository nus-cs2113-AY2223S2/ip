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
    private static final String COMMAND_MARK = "MARK";
    private static final String COMMAND_UNMARK = "UNMARK";

    private static List<Task> userTasks = new ArrayList<>();

    private static boolean isRunning;
    private static Scanner scanner;

    // Overloaded default for printDivider
    private static void printDivider() {
        printDivider(60);
    }

    private static void printDivider(int width) {
        System.out.println("_".repeat(width));
    }

    private static void printUserTasks() {
        for (int index = 1; index <= userTasks.size(); index++) {
            System.out.println(index + ". " + userTasks.get(index - 1));
        }
    }

    private static void setUserTaskState(int userIndex, boolean isDone) {
        // Tasks are 0-indexed, user index is 1-indexed
        int index = userIndex - 1;
        if (index < 0 || index >= userTasks.size()) {
            System.out.println("Oops, not quite sure what task you're referring to...");
            return;
        }

        userTasks.get(index).setTaskState(isDone);
        if (isDone) {
            System.out.println("Nice! I've marked this task as done");
            System.out.println(userTasks.get(index));
        } else {
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(userTasks.get(index));
        }
    }

    private static String getUserCommand() {
        System.out.print(PROMPT_STR);
        return scanner.nextLine();
    }

    private static void handleUserInput(String userInput) {
        String[] splitInput = userInput.split("\\s+");
        String cmd = splitInput[0].trim().toUpperCase();

        if (cmd.equals(COMMAND_EXIT)) {
            isRunning = false;

        } else if (cmd.equals(COMMAND_LIST)) {
            printUserTasks();

        } else if (cmd.equals(COMMAND_MARK)) {
            int index = Integer.parseInt(splitInput[1]);
            setUserTaskState(index, true);

        } else if (cmd.equals(COMMAND_UNMARK)) {
            int index = Integer.parseInt(splitInput[1]);
            setUserTaskState(index, false);

        } else {
            Task task = new Task(userInput);
            userTasks.add(task);

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
