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
    private static final String MESSAGE_HELP_ADD_EVENT = "/add <Event_Name> from/<Start_Date> to/<End_Date>" +
            " : Add new event";
    private static final String MESSAGE_HELP_ADD_DEADLINE = "/add <Deadline_Name> by/<Deadline> : Add new deadline";
    private static final String MESSAGE_HELP_ADD_TODO = "/add <Todo_Name> : Add new Todo";
    private static final String MESSAGE_HELP_DELETE_TASK = "/delete <Task_Name> : Delete task \"Task_Name\"";
    private static final String MESSAGE_HELP_MARK_TASK = "/mark <Task_Name> : Set \"Task_Name\" as completed";
    private static final String MESSAGE_HELP_UNMARK_TASK = "/unmark <Task_Name> : Set \"Task_Name\" as " +
            "undone";
    private static final String MESSAGE_HELP_FIND_TASK = "/find <keyword> : Find and displays all tasks " +
            "with keyword";
    private static final String MESSAGE_HELP_HELP = "/help : Displays list of all commands";
    private static final String MESSAGE_HELP_EXIT = "/exit : Exit chatbot";
    public static final String MESSAGE_INVALID_KEYWORD = "Invalid keyword entered!";

    private final Scanner in;
    public Ui() {
        this.in = new Scanner(System.in);
        displayWelcomeMessage();
    }

    /**
     * Displays header message when displaying list of tasks found using find command.
     *
     * @param numOfTask number of tasks in list.
     * @param keyword keyword used in search.
     */
    public static void displayFindTaskMessage(int numOfTask, String keyword) {
        System.out.println("There are " + numOfTask + " tasks with \"" + keyword +"\":");
    }

    /**
     * Displays error message when an invalid keyword is used for find command.
     */
    public static void displayKeywordError() {
        System.out.println(MESSAGE_INVALID_KEYWORD);
    }

    /**
     * Returns input entered by user.
     *
     * @return Input by user.
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Displays the startup message of NANO chatbot.
     */
    public static void displayWelcomeMessage() {
        printHorizontalLine();
        System.out.println(NANO_LOGO);
        System.out.println("Serial number: 034-4532-5893.....");
        System.out.println("Activating the 7th generation Nano Machine of the Chan Corporation.....");
        printHorizontalLine();
        System.out.println("How may I assist you?");
        printHorizontalLine();
    }

    /**
     * Display a horizontal line.
     * This improves visual readability of output by separating chunks of text.
     */
    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a list of tasks with numbering.
     *
     * @param tasks List of tasks.
     */
    public static void displayTaskList(ArrayList<Task> tasks) {
        int counter = 1;
        for (Task task : tasks) {
            System.out.println(counter + ". " +task.toString());
            counter += 1;
        }
    }

    /**
     * Displays lists of tasks with list command.
     * It also informs user how many tasks are completed and uncompleted.
     *
     * @param tasks List of tasks.
     */
    public static void displayTaskListMessage(ArrayList<Task> tasks) {
        System.out.println("You have completed " + Task.getCompletedTaskCount() + " tasks. " +
                Task.getUncompletedTaskCount() + " more to go!");
        displayTaskList(tasks);
    }

    /**
     * Displays exit message after exit command.
     */
    public static void displayExitMessage() {
        System.out.println(MESSAGE_EXIT);
    }

    /**
     * Displays details and formats for all commands.
     */
    public static void displayCommandList() {
        // list,add,mark,unmark,help,exit
        printHorizontalLine();
        System.out.println(MESSAGE_HELP_LIST_COMMAND);
        System.out.println(MESSAGE_HELP_ADD_EVENT);
        System.out.println(MESSAGE_HELP_ADD_DEADLINE);
        System.out.println(MESSAGE_HELP_ADD_TODO);
        System.out.println(MESSAGE_HELP_DELETE_TASK);
        System.out.println(MESSAGE_HELP_MARK_TASK);
        System.out.println(MESSAGE_HELP_UNMARK_TASK);
        System.out.println(MESSAGE_HELP_FIND_TASK);
        System.out.println(MESSAGE_HELP_HELP);
        System.out.println(MESSAGE_HELP_EXIT);
    }

    /**
     * Displays how to use help command when user enters an invalid input.
     */
    public static void displayHelpMessage() {
        System.out.println(MESSAGE_HELP_COMMAND);
    }

    /**
     * Displays message when task does not exists in the list.
     */
    public static void displayTaskNotFoundMessage() {
        System.out.println(MESSAGE_TASK_NOT_FOUND);
    }

    /**
     * Displays message when task has been unmarked.
     *
     * @param taskName Name of task unmarked.
     */
    public static void displayUnmarkTaskMessage(String taskName) {
        System.out.println(taskName + MESSAGE_TASK_UNMARKED);
    }

    /**
     * Displays message when task is already undone.
     *
     * @param taskName Name of task being unmarked.
     */
    public static void displayTaskAlreadyUndoneMessage(String taskName) {
        System.out.println(taskName + MESSAGE_TASK_ALREADY_UNDONE);
    }

    /**
     * Displays message when task is marked.
     *
     * @param taskName Name of task marked.
     */
    public static void displayMarkTaskMessage(String taskName) {
        System.out.println(MESSAGE_TASK_MARKED + taskName + '!');
    }

    /**
     * Displays message when task is already done.
     *
     * @param taskName Name of task being marked.
     */
    public static void displayTaskAlreadyDoneMessage(String taskName) {
        System.out.println(taskName + MESSAGE_TASK_ALREADY_DONE);
    }

    /**
     * Displays message when user enters an invalid input.
     */
    public static void displayInputErrorMessage() {
        System.out.println("Input error!");
        displayHelpMessage();
    }

    /**
     * Displays message when user enters an unknown command.
     */
    public static void displayUnknownCommandMessage() {
        System.out.println("Unknown command!");
        displayHelpMessage();
    }
}