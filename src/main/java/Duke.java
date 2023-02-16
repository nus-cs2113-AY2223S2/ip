import exceptions.InvalidSyntaxException;
import exceptions.UnrecognizedInputException;
import java.util.List;
import java.util.Scanner;
import storage.TaskStorageManager;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import ui.Command;

public class Duke {

    private static final TaskStorageManager taskStorageManager = new TaskStorageManager();
    private static final List<Task> userTasks = taskStorageManager.loadTasks();

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

    private static void printUserTasksCount() {
        System.out.println("Now you have " + userTasks.size() + " task(s) in the list.");
    }

    private static void printAddedUserTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println(userTasks.get(userTasks.size() - 1));
    }

    private static void printRemovedUserTask(Task removedTask) {
        System.out.println("Alrighty, I've removed this task:");
        System.out.println(removedTask);
    }

    private static void setUserTaskState(int index, boolean isDone) {
        userTasks.get(index).setIsDone(isDone);

        if (isDone) {
            System.out.println("Nice! I've marked this task as done");
            System.out.println(userTasks.get(index));
        } else {
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(userTasks.get(index));
        }
    }

    private static void handleAddUserTask(String cmd, String[] splitInput) throws InvalidSyntaxException {
        Task task = null;
        if (cmd.equals(Command.TODO.label)) {
            task = Task.createFromInput(splitInput);
        } else if (cmd.equals(Command.DEADLINE.label)) {
            task = DeadlineTask.createFromInput(splitInput);
        } else if (cmd.equals(Command.EVENT.label)) {
            task = EventTask.createFromInput(splitInput);
        }

        userTasks.add(task);

        printAddedUserTask();
        printUserTasksCount();

        taskStorageManager.saveTasks(userTasks);
    }

    private static void handleModifyUserTask(String cmd, String[] splitInput) throws InvalidSyntaxException {
        try {
            // Tasks are 0-indexed, user index is 1-indexed
            int userIndex = Integer.parseInt(splitInput[1]);
            int index = userIndex - 1;

            if (cmd.equals(Command.MARK.label)) {
                setUserTaskState(index, true);
            } else if (cmd.equals(Command.UNMARK.label)) {
                setUserTaskState(index, false);
            } else if (cmd.equals(Command.DELETE.label)) {
                Task removedTask = userTasks.remove(index);

                printRemovedUserTask(removedTask);
                printUserTasksCount();
            }

            taskStorageManager.saveTasks(userTasks);

        } catch (NumberFormatException ex) {
            if (cmd.equals(Command.MARK.label)) {
                throw new InvalidSyntaxException(Command.MARK.expectedSyntax);
            } else if (cmd.equals(Command.UNMARK.label)) {
                throw new InvalidSyntaxException(Command.UNMARK.expectedSyntax);
            } else if (cmd.equals(Command.DELETE.label)) {
                throw new InvalidSyntaxException(Command.DELETE.expectedSyntax);
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Oops, not quite sure what task you're referring to...");
        }
    }

    private static String getUserCommand() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    private static void handleUserInput(String userInput) throws UnrecognizedInputException, InvalidSyntaxException {
        String[] splitInput = userInput.split("\\s+", 2);
        // Case-insensitive to user input
        String cmd = splitInput[0].trim().toLowerCase();

        if (cmd.equals(Command.EXIT.label)) {
            isRunning = false;
        } else if (cmd.equals(Command.LIST.label)) {
            printUserTasks();
        } else if (Command.MODIFY_TASK_COMMANDS.contains(cmd)) {
            handleModifyUserTask(cmd, splitInput);
        } else if (Command.ADD_TASK_COMMANDS.contains(cmd)) {
            handleAddUserTask(cmd, splitInput);
        } else {
            throw new UnrecognizedInputException();
        }
    }

    private static void printGreeting() {
        printDivider();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void printGoodbye() {
        printDivider();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        printGreeting();

        // Initializations
        isRunning = true;
        scanner = new Scanner(System.in);

        // Main user interaction loop
        while (isRunning) {
            printDivider();
            String userInput = getUserCommand();

            try {
                handleUserInput(userInput);
            } catch (UnrecognizedInputException ex) {
                System.out.println("Sorry, I don't recognize that command...");
            } catch (InvalidSyntaxException ex) {
                System.out.println("That doesn't look quite right, try: " + ex.getExpectedSyntax());
            }
        }

        printGoodbye();
    }
}
