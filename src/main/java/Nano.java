import java.util.Scanner;

public class Nano {

    private final static int HORIZONTAL_LINE_LENGTH = 100;
    private final static int MAX_TASK_COUNT = 100;
    private final static String NANO_LOGO = "| \\  ||   / \\   | \\  |||  __  |\n"
            + "||\\\\ ||  / _ \\  ||\\\\ ||| |  | |\n"
            + "|| \\\\|| //   \\\\ || \\\\||| |__| |\n"
            + "||  \\_|//     \\\\||  \\_||______|\n";
    private final static int COMMAND_INDEX = 0;
    private final static int TASK_NAME_INDEX = 1;
    public static final String TASK_COMPLETED_MARK = "[x] ";
    public static final String TASK_UNCOMPLETED_MARK = "[] ";
    public static final String MESSAGE_TASK_NOT_FOUND = "Task not found.";
    public static final String MESSAGE_TASK_MARKED = "Great job completing ";
    public static final String MESSAGE_TASK_ALREADY_DONE = " is already done";
    public static final String MESSAGE_TASK_UNMARKED = " is marked as undone";
    private static Task[] tasks;
    public static void main(String[] args) {
        //chatbot startup
        displayWelcomeMessage();
        initialiseTaskList();

        //receive commands
        while (true) {
            String userInput = getUserInput();
            executeCommand(userInput);
        }
    }

    private static void executeCommand(String userInput) {
        String[] userInputs = processInput(userInput);

        switch (userInputs[COMMAND_INDEX]) {
        case "list":
            displayTaskList();
            break;
        case "add":
            addTask(userInputs[TASK_NAME_INDEX]);
            break;
        case "mark":
            markTask(userInputs[TASK_NAME_INDEX]);
            break;
        case "unmark":
            unmarkTask(userInputs[TASK_NAME_INDEX]);
            break;
        case "exit":
            displayExitMessage();
            System.exit(0);
        default:
            displayHelpMessage();
            break;
        }
    }

    private static void unmarkTask(String taskName) {
        int taskIndex = getTaskIndex(taskName);
        if (taskIndex == -1) {
            System.out.println(MESSAGE_TASK_NOT_FOUND);
        } else {
            if (tasks[taskIndex].getTaskCompletionStatus()) {
                tasks[taskIndex].incompleteTask();
                System.out.println(taskName + MESSAGE_TASK_UNMARKED);
            } else {
                System.out.println(taskName + MESSAGE_TASK_ALREADY_DONE);
            }
        }
    }
    private static void markTask(String taskName) {
        int taskIndex = getTaskIndex(taskName);
        if (taskIndex == -1) {
            System.out.println(MESSAGE_TASK_NOT_FOUND);
        } else {
            if (!tasks[taskIndex].getTaskCompletionStatus()) {
                tasks[taskIndex].completeTask();
                System.out.println(MESSAGE_TASK_MARKED + taskName);
            } else {
                System.out.println(taskName + MESSAGE_TASK_ALREADY_DONE);
            }
        }
    }
    private static int getTaskIndex(String taskName) {
        for (int i = 1; i <= Task.getTaskCount(); i += 1) {
            if (tasks[i].getTaskName().equals(taskName)) {
                return i;
            }
        }
        return -1;
    }
    private static void addTask(String taskName) {
        if (isInList(taskName)) {
            System.out.println(taskName + " is already in the list!");
        } else {
            Task newTask = new Task(taskName);
            tasks[Task.getTaskCount()] = newTask;
            System.out.println("Added " + taskName);
        }
    }

    private static boolean isInList(String taskName) {
        for (int i = 1; i <= Task.getTaskCount(); i += 1) {
            if (tasks[i].getTaskName().equals(taskName)) {
                return true;
            }
        }
        return false;
    }

    private static void displayHelpMessage() {
        //TODO to be improved
        System.out.println("Invalid input!");
    }

    private static String[] processInput(String userInput) {
        userInput = userInput.replace("/", "").trim();
        String[] userInputs = new String[2];
        userInputs = userInput.split("\\s+", 2);
        return userInputs;
    }

    private static void displayTaskList() {
        System.out.println("You have completed " + Task.getCompletedTaskCount() + " tasks. " + Task.getUncompletedTaskCount() + " more to go!");
        for (int i = 1; i <= Task.getTaskCount(); i += 1) {
            System.out.print(i + ".");
            printTodoMark(i);
            System.out.println(tasks[i].getTaskName());
        }
    }

    private static void printTodoMark(int i) {
        if (tasks[i].getTaskCompletionStatus()) {
            System.out.print(TASK_COMPLETED_MARK);
        } else {
            System.out.print(TASK_UNCOMPLETED_MARK);
        }
    }

    private static boolean isValidCommand(String command) {
        return command.startsWith("/");
    }

    private static void displayExitMessage() {
        System.out.println("Sleep mode activated.");
    }

    //creates a list of task (1-index)
    private static void initialiseTaskList() {
        tasks = new Task[MAX_TASK_COUNT + 1];
    }

    private static String getUserInput() {
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        return userInput;
    }

    private static void displayWelcomeMessage() {
        printHorizontalLine();
        System.out.println(NANO_LOGO);
        System.out.println("Serial number: 034-4532-5893.....");
        System.out.println("Activating the 7th generation Nano Machine of the Chan Corporation.....");
        printHorizontalLine();
        System.out.println("How may I assist you?");
        printHorizontalLine();
    }

    //print horizontal line
    public static void printHorizontalLine() {
        for (int i = 0; i < HORIZONTAL_LINE_LENGTH / 10; i += 1) {
            System.out.print("__________");
        }
        System.out.println();
    }
}


