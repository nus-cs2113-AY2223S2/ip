package nano.ui;

import nano.data.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String HORIZONTAL_LINE = "_______________________________________________________________________";
    private static final String NANO_LOGO = "| \\  ||   / \\   | \\  |||  __  |\n"
            + "||\\\\ ||  / _ \\  ||\\\\ ||| |  | |\n"
            + "|| \\\\|| //   \\\\ || \\\\||| |__| |\n"
            + "||  \\_|//     \\\\||  \\_||______|\n";
    private static final String MESSAGE_TASK_NOT_FOUND = "Task not found!";
    private static final String MESSAGE_TASK_MARKED = "Great job completing ";
    private static final String MESSAGE_TASK_ALREADY_DONE = " is already done.";
    private static final String MESSAGE_TASK_ALREADY_UNDONE = " is already not done.";
    private static final String MESSAGE_TASK_UNMARKED = " set to undone!";
    private static final String MESSAGE_HELP_COMMAND = "Enter \"/help\" for full command list";
    private static final String MESSAGE_EXIT = "Sleep mode activated.";
    private static final String MESSAGE_HELP_LIST_COMMAND = "/list : Displays list of tasks";
    private static final String MESSAGE_HELP_ADD_EVENT = "/add <Event_Name> from/<Start_Date> to/<End_Date> : Add new event";
    private static final String MESSAGE_HELP_ADD_DEADLINE = "/add <Deadline_Name> by/<Deadline> : Add new deadline";
    private static final String MESSAGE_HELP_ADD_TODO = "/add <Todo_Name> : Add new Todo";
    private static final String MESSAGE_HELP_MARK_TASK = "/mark <task> : set task as completed";
    private static final String MESSAGE_HELP_UNMARK_TASK = "/unmark <task> : set task as undone";
    private static final String MESSAGE_HELP_HELP = "/help : Displays list of all commands";
    private static final String MESSAGE_HELP_EXIT = "/exit : Exit chatbot";

    private final Scanner in;
    public Ui() {
        this.in = new Scanner(System.in);
        displayWelcomeMessage();
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public static void displayWelcomeMessage() {
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
        System.out.println(HORIZONTAL_LINE);
    }

    public static void displayTaskList(ArrayList<Task> tasks) {
        System.out.println("You have completed " + Task.getCompletedTaskCount() + " tasks. " +
                Task.getUncompletedTaskCount() + " more to go!");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }

    public static void displayExitMessage() {
        System.out.println(MESSAGE_EXIT);
    }

    public static void displayCommandList() {
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

    public static void displayHelpMessage() {
        System.out.println(MESSAGE_HELP_COMMAND);
    }

    public static void displayTaskNotFoundMessage() {
        System.out.println(MESSAGE_TASK_NOT_FOUND);
    }

    public static void displayUnmarkTaskMessage(String taskName) {
        System.out.println(taskName + MESSAGE_TASK_UNMARKED);
    }

    public static void displayTaskAlreadyUndoneMessage(String taskName) {
        System.out.println(taskName + MESSAGE_TASK_ALREADY_UNDONE);
    }

    public static void displayMarkTaskMessage(String taskName) {
        System.out.println(MESSAGE_TASK_MARKED + taskName + '!');
    }

    public static void displayTaskAlreadyDoneMessage(String taskName) {
        System.out.println(taskName + MESSAGE_TASK_ALREADY_DONE);
    }

    public static void displayInputErrorMessage() {
        System.out.println("Input error!");
        displayHelpMessage();
    }

    public static void displayUnknownCommandMessage() {
        System.out.println("Unknown command!");
        displayHelpMessage();
    }
}