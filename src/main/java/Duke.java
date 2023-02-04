import java.util.Scanner;

public class Duke {
    //String constants
    private static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String HELLO_MESSAGE = " Hello! I'm Duke\n" + "     What can I do for you?\n";
    private static final String BYE_MESSAGE = " Bye. Hope to see you again soon!";
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_MARK_WORD = "mark";
    private static final String COMMAND_UNMARK_WORD = "unmark";
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String LINE_PREFIX = "    ";
    //Scanner for user input
    private static final Scanner SCANNER = new Scanner(System.in);
    //Task list
    private static final Task[] TASK_LIST = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        // reused from contacts Contacts1.java with modification
        showWelcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            executeCommand(userCommand);
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

    private static void executeCommand(String userInputString) {
        showToUser(SEPARATOR);
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
        case COMMAND_LIST_WORD:
            listTask();
            break;
        case COMMAND_MARK_WORD:
            markAsDone(commandArgs);
            break;
        case COMMAND_UNMARK_WORD:
            markAsNotDone(commandArgs);
            break;
        case COMMAND_EXIT_WORD:
            exitProgram();
        default:
            addTask(userInputString);
        }
        showToUser(SEPARATOR);
    }

    private static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m);
        }
    }

    private static void addTask(String newTaskDescription) {
        TASK_LIST[taskCount] = new Task(newTaskDescription);
        taskCount += 1;
        showToUser("added: " + newTaskDescription);
    }

    private static void markAsDone(String taskNumber) {
        Task taskToBeMarked = TASK_LIST[Integer.parseInt(taskNumber) - 1];
        taskToBeMarked.isDone = true;
        showToUser("Nice! I've marked this task as done:");
        showToUser(taskToBeMarked.getStatusIcon() + " " + taskToBeMarked.description);
    }

    private static void markAsNotDone(String taskNumber) {
        Task taskToBeUnmarked = TASK_LIST[Integer.parseInt(taskNumber) - 1];
        taskToBeUnmarked.isDone = false;
        showToUser("OK, I've marked this task as not done yet:");
        showToUser(taskToBeUnmarked.getStatusIcon() + " " + taskToBeUnmarked.description);
    }

    private static void listTask() {
        for (int i = 0; i < taskCount; i++) {
            showToUser((i + 1) + ". " + TASK_LIST[i].getStatusIcon() + " " + TASK_LIST[i].description);
        }
    }

    private static void exitProgram() {
        showToUser(BYE_MESSAGE, SEPARATOR);
        System.exit(0);
    }

    //reused from Contacts1.java
    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[]{split[0], ""}; // else case: no parameters
    }
}
