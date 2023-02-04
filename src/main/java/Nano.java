import java.util.Scanner;

public class Nano {
    private static final int HORIZONTAL_LINE_LENGTH = 100;
    private static final int MAX_TASK_COUNT = 100;
    private static final int COMMAND_INDEX = 0;
    private static final int TASK_NAME_INDEX = 1;
    private static final int TASK_TYPE_INDEX = 2;
    private static final int TASK_START_DATE_INDEX = 3;
    private static final int TASK_DUE_DATE_INDEX = 3;
    private static final int TASK_END_DATE_INDEX = 4;
    public static final int TASK_INDEX_ERROR = -1;
    public static final int USER_INPUT_MAX_ARG_COUNT = 4;
    private static final String NANO_LOGO = "| \\  ||   / \\   | \\  |||  __  |\n"
            + "||\\\\ ||  / _ \\  ||\\\\ ||| |  | |\n"
            + "|| \\\\|| //   \\\\ || \\\\||| |__| |\n"
            + "||  \\_|//     \\\\||  \\_||______|\n";
    public static final String MESSAGE_TASK_NOT_FOUND = "Task not found.";
    public static final String MESSAGE_TASK_MARKED = "Great job completing ";
    public static final String MESSAGE_TASK_ALREADY_DONE = " is already done";
    public static final String MESSAGE_TASK_ALREADY_UNDONE = " is already not done";
    public static final String MESSAGE_TASK_UNMARKED = " set to undone";

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
            addTask(userInputs);
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
        printHorizontalLine();
    }

    private static void unmarkTask(String taskName) {
        int taskIndex = getTaskIndex(taskName);
        if (taskIndex == TASK_INDEX_ERROR) {
            System.out.println(MESSAGE_TASK_NOT_FOUND);
            return;
        }

        if (tasks[taskIndex].isCompleted()) {
            tasks[taskIndex].setUndone();
            System.out.println(taskName + MESSAGE_TASK_UNMARKED);
        } else {
            System.out.println(taskName + MESSAGE_TASK_ALREADY_UNDONE);
        }
    }

    private static void markTask(String taskName) {
        int taskIndex = getTaskIndex(taskName);
        if (taskIndex == TASK_INDEX_ERROR) {
            System.out.println(MESSAGE_TASK_NOT_FOUND);
            return;
        }

        if (!tasks[taskIndex].isCompleted()) {
            tasks[taskIndex].setDone();
            System.out.println(taskName + MESSAGE_TASK_MARKED);
        } else {
            System.out.println(taskName + MESSAGE_TASK_ALREADY_DONE);
        }
    }

    private static int getTaskIndex(String taskName) {
        for (int i = 1; i <= Task.getTaskCount(); i += 1) {
            if (tasks[i].getTaskName().equals(taskName)) {
                return i;
            }
        }
        return TASK_INDEX_ERROR;
    }

    private static void addTask(String[] taskDetails) {
        if (isInList(taskDetails[TASK_NAME_INDEX])) {
            System.out.println(taskDetails[TASK_NAME_INDEX] + " is already in the list!");
            return;
        }

        Task newTask;
        switch (taskDetails[TASK_TYPE_INDEX]) {
        case "deadline":
            newTask = new Deadline(taskDetails[TASK_NAME_INDEX], taskDetails[TASK_DUE_DATE_INDEX]);
            break;
        case "event":
            newTask = new Event(taskDetails[TASK_NAME_INDEX], taskDetails[TASK_START_DATE_INDEX], taskDetails[TASK_END_DATE_INDEX]);
            break;
        default:
            newTask = new Todo(taskDetails[TASK_NAME_INDEX]);
            break;
        }
        tasks[Task.getTaskCount()] = newTask;
        System.out.println("Added " + taskDetails[TASK_NAME_INDEX]);
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
        String[] userInputs = new String[USER_INPUT_MAX_ARG_COUNT + 1];
        String[] commandAndName = userInput.split("\\s+", 2);
        System.arraycopy(commandAndName, 0, userInputs, 0, commandAndName.length);

        if (commandAndName.length == 2) {
            processTaskDetails(userInput, userInputs);
        }
        return userInputs;
    }

    private static void processTaskDetails(String userInput, String[] userInputs) {
        if (isDeadline(userInput)) {
            userInputs[TASK_TYPE_INDEX] = "deadline";
            userInputs[TASK_NAME_INDEX] = userInput.substring(userInput.indexOf(" "), userInput.indexOf("by:")).trim();
            userInputs[TASK_DUE_DATE_INDEX] = userInput.substring(userInput.indexOf("by:")).trim();
        } else if (isEvent(userInput)) {
            userInputs[TASK_TYPE_INDEX] = "event";
            userInputs[TASK_NAME_INDEX] = userInput.substring(userInput.indexOf(" "), userInput.indexOf("from:")).trim();
            userInputs[TASK_START_DATE_INDEX] = userInput.substring(userInput.indexOf("from:"), userInput.indexOf("to:")).trim();
            userInputs[TASK_END_DATE_INDEX] = userInput.substring(userInput.indexOf("to")).trim();
        } else {
            userInputs[TASK_TYPE_INDEX] = "todo";
        }
    }

    private static boolean isDeadline(String task) {
        return task.contains("by:");
    }

    private static boolean isEvent(String task) {
        return task.contains("from:");
    }

    private static void displayTaskList() {
        System.out.println("You have completed " + Task.getCompletedTaskCount() + " tasks. " + Task.getUncompletedTaskCount() + " more to go!");
        for (int i = 1; i <= Task.getTaskCount(); i += 1) {
            System.out.println(tasks[i].toString());
        }
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


