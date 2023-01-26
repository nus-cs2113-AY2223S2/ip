import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import task.DeadlineTask;
import task.EventTask;
import task.Task;

public class Duke {

    /**
     * User commands
     */
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final Set<String> COMMANDS_TASK = new HashSet<>(Arrays.asList("todo", "deadline", "event"));

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

    private static void handleAddUserTask(String cmd, String[] splitInput) {
        Task task = null;

        if (cmd.equals("todo")) {
            // Syntax: todo <desc>
            task = new Task(splitInput[1]);

        } else if (cmd.equals("deadline")) {
            // Syntax: deadline <desc> /by <deadline>
            String[] params = splitInput[1].split("/by");
            task = new DeadlineTask(params[0].trim(), params[1].trim());

        } else if (cmd.equals("event")) {
            // Syntax: event <desc> /from <start> /to <end>
            String[] params = splitInput[1].split("(/from|/to)");
            task = new EventTask(params[0].trim(), params[1].trim(), params[2].trim());

        }

        userTasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(userTasks.get(userTasks.size() - 1));
        System.out.println("Now you have " + userTasks.size() + " task(s) in the list.");
    }

    private static String getUserCommand() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    private static void handleUserInput(String userInput) {
        String[] splitInput = userInput.split("\\s+", 2);
        // Case-insensitive to user input
        String cmd = splitInput[0].trim().toLowerCase();

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

        } else if (COMMANDS_TASK.contains(cmd)) {
            handleAddUserTask(cmd, splitInput);

        } else {
            System.out.println("Sorry, I don't recognize that command...");

        }
    }

    public static void main(String[] args) {
        printDivider();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

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
        System.out.println("Bye. Hope to see you again soon!");
    }
}
