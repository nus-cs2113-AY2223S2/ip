import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String HELLO_MESSAGE = " Hello! I'm Duke\n" + "     What can I do for you?\n";
    private static final String BYE_MESSAGE = " Bye. Hope to see you again soon!";
    private static final String SEPARATOR = "____________________________________________________________";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String LINE_PREFIX = "    ";

    private static final Task[] taskList = new Task[100];
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
        switch (userInputString) {
        case COMMAND_LIST_WORD:
            listTask();
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
        taskList[taskCount] = new Task(newTaskDescription);
        taskCount += 1;
        showToUser("added: " + newTaskDescription);
    }

    private static void listTask() {
        for (int i = 0; i < taskCount; i++) {
            showToUser((i+1) + ". " + taskList[i].description);
        }
    }

    private static void exitProgram() {
        showToUser(BYE_MESSAGE, SEPARATOR);
        System.exit(0);
    }
}
