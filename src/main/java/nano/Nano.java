package nano;

import nano.exception.NanoCommandException;
import nano.exception.NanoInputFormatException;
import nano.task.Deadline;
import nano.task.Event;
import nano.task.Task;
import nano.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;
public class Nano {
    private static final int HORIZONTAL_LINE_LENGTH = 100;
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
    public static final String MESSAGE_HELP_COMMAND = "Enter \"/help\" for full command list";
    public static final String MESSAGE_EXIT = "Sleep mode activated.";
    public static final String MESSAGE_HELP_LIST_COMMAND = "/list : Displays list of tasks";
    public static final String MESSAGE_HELP_ADD_EVENT = "/add <Event_Name> from/<Start_Date> to/<End_Date> : Add new event";
    public static final String MESSAGE_HELP_ADD_DEADLINE = "/add <Deadline_Name> by/<Deadline> : Add new deadline";
    public static final String MESSAGE_HELP_ADD_TODO = "/add <Todo_Name> : Add new Todo";
    public static final String MESSAGE_HELP_MARK_TASK = "/mark <task> : set task as completed";
    public static final String MESSAGE_HELP_UNMARK_TASK = "/unmark <task> : set task as undone";
    public static final String MESSAGE_HELP_HELP = "/help : Displays list of all commands";
    public static final String MESSAGE_HELP_EXIT = "/exit : Exit chatbot";
    private static ArrayList<Task> tasks;

    public static void main(String[] args) {
        //chatbot startup
        displayWelcomeMessage();
        initialiseTaskList();

        //receive commands
        while (true) {
            String userInput = getUserInput();
            try {
                executeCommand(userInput);
            } catch (NanoCommandException commandException) {
                System.out.println("unknown command");
                displayHelpMessage();
            }
        }
    }

    private static void executeCommand(String userInput) throws NanoCommandException {
        String[] userInputs;
        try {
            userInputs = processInput(userInput);
        } catch (NanoInputFormatException inputException) {
            System.out.println("input error");
            displayHelpMessage();
            return;
        }

        switch (userInputs[COMMAND_INDEX]) {
        case "list":
            displayTaskList();
            break;
        case "add":
            addTask(userInputs);
            break;
        case "delete":
            deleteTask(userInputs[TASK_NAME_INDEX]);
            break;
        case "mark":
            markTask(userInputs[TASK_NAME_INDEX]);
            break;
        case "unmark":
            unmarkTask(userInputs[TASK_NAME_INDEX]);
            break;
        case "help":
            displayCommandList();
            break;
        case "exit":
            displayExitMessage();
            System.exit(0);
        default:
            throw new NanoCommandException();
        }
        printHorizontalLine();
    }

    private static void deleteTask(String taskName) {
        int taskIndex = getTaskIndex(taskName);
        if (taskIndex == TASK_INDEX_ERROR) {
            System.out.println(MESSAGE_TASK_NOT_FOUND);
            return;
        }

        tasks.remove(taskIndex);
        Task.deleteTask();
        System.out.println("Deleted " + taskName);
    }
    private static void unmarkTask(String taskName) {
        int taskIndex = getTaskIndex(taskName);
        if (taskIndex == TASK_INDEX_ERROR) {
            System.out.println(MESSAGE_TASK_NOT_FOUND);
            return;
        }

        if (tasks.get(taskIndex).isCompleted()) {
            tasks.get(taskIndex).setUndone();
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

        if (!tasks.get(taskIndex).isCompleted()) {
            tasks.get(taskIndex).setDone();
            System.out.println(MESSAGE_TASK_MARKED + taskName);
        } else {
            System.out.println(taskName + MESSAGE_TASK_ALREADY_DONE);
        }
    }

    private static int getTaskIndex(String taskName) {
        for (Task t : tasks) {
            if (t.getTaskName().equals(taskName)) {
                return tasks.indexOf(t);
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
            newTask = new Event(taskDetails[TASK_NAME_INDEX], taskDetails[TASK_START_DATE_INDEX],
                    taskDetails[TASK_END_DATE_INDEX]);
            break;
        default:
            newTask = new Todo(taskDetails[TASK_NAME_INDEX]);
            break;
        }
        tasks.add(newTask);
        System.out.println("Added " + taskDetails[TASK_NAME_INDEX]);
    }

    private static boolean isInList(String taskName) {
        for (Task t : tasks) {
            if (t.getTaskName().equals(taskName)) {
                return true;
            }
        }
        return false;
    }

    private static void displayCommandList() {
        // list,add,mark,unmark,help,exit
        printHorizontalLine();
        System.out.println(MESSAGE_HELP_LIST_COMMAND);
        System.out.println(MESSAGE_HELP_ADD_EVENT);
        System.out.println(MESSAGE_HELP_ADD_DEADLINE);
        System.out.println(MESSAGE_HELP_ADD_TODO);
        System.out.println(MESSAGE_HELP_MARK_TASK);
        System.out.println(MESSAGE_HELP_UNMARK_TASK);
        System.out.println(MESSAGE_HELP_HELP);
        System.out.println(MESSAGE_HELP_EXIT);
    }
    private static void displayHelpMessage() {
        System.out.println(MESSAGE_HELP_COMMAND);
    }

    private static String[] processInput(String userInput) throws NanoInputFormatException {
        userInput = userInput.trim();
        if (!userInput.startsWith("/")) {
            throw new NanoInputFormatException();
        }
        userInput = userInput.replaceFirst("/", "");
        String[] userInputs = new String[USER_INPUT_MAX_ARG_COUNT + 1];
        String[] commandAndName = userInput.split("\\s+", 2);
        System.arraycopy(commandAndName, 0, userInputs, 0, commandAndName.length);

        if (commandAndName.length == 2) {
            processTaskDetails(userInput, userInputs);
        }
        return userInputs;
    }

    private static void processTaskDetails(String userInput, String[] userInputs) throws NanoInputFormatException {
        if (isDeadline(userInput)) {
            getDeadline(userInput, userInputs);
        } else if (isEvent(userInput)) {
            getEvent(userInput, userInputs);
        } else if (userInput.contains("/")) {
            throw new NanoInputFormatException();
        } else {
            getTodo(userInputs);
        }
    }

    private static void getTodo(String[] userInputs) {
        userInputs[TASK_TYPE_INDEX] = "todo";
    }

    private static void getEvent(String userInput, String[] userInputs) {
        userInputs[TASK_TYPE_INDEX] = "event";
        userInputs[TASK_NAME_INDEX] = userInput.substring(userInput.indexOf(" "), userInput.indexOf("from/")).trim();
        userInputs[TASK_START_DATE_INDEX] = userInput.substring(userInput.indexOf("from/"),
                userInput.indexOf("to/")).replaceFirst("/", "").trim();
        userInputs[TASK_END_DATE_INDEX] = userInput.substring(userInput.indexOf("to/")).replaceFirst("/", "");
    }

    private static void getDeadline(String userInput, String[] userInputs) {
        userInputs[TASK_TYPE_INDEX] = "deadline";
        userInputs[TASK_NAME_INDEX] = userInput.substring(userInput.indexOf(" "), userInput.indexOf("by/")).trim();
        userInputs[TASK_DUE_DATE_INDEX] = userInput.substring(userInput.indexOf("by/"));
    }

    private static boolean isDeadline(String task) {
        return task.contains("by/");
    }

    private static boolean isEvent(String task) {
        return task.contains("from/") && task.contains("to/");
    }

    private static void displayTaskList() {
        System.out.println("You have completed " + Task.getCompletedTaskCount() + " tasks. " +
                Task.getUncompletedTaskCount() + " more to go!");
        for (Task t : tasks) {
            System.out.println(t.toString());
        }
    }

    private static void displayExitMessage() {
        System.out.println(MESSAGE_EXIT);
    }

    //creates a list of task (1-index)
    private static void initialiseTaskList() {
        tasks = new ArrayList<>();
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