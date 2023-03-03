package duke.ui;

import duke.tasklist.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String TOP_DIVIDER = "==================================================================\n";
    private static final String BOTTOM_DIVIDER
            = "\n==================================================================\n\n";
    private static final String LIST_TASK_BOTTOM_DIVIDER
            = "==================================================================\n\n";
    private static final String WELCOME_MESSAGE = " Hello! I'm Chatty\n How can I help you?";
    private static final String EXIT_MESSAGE = " Goodbye. Hope to see you again soon!";
    private static final String ADD_TASK_DESC = " Roger. I've added this task:\n  ";
    private static final String CUR_NO_OF_TASK_START_DESC = "\n You currently have ";
    private static final String CUR_NO_OF_TASK_END_DESC = " task(s) in the list.";
    private static final String DELETE_TASK_DESC = " Roger. I've removed this task:\n  ";
    private static final String MARK_TASK_DESC = " Good Job! I've marked this task as completed:\n  ";
    private static final String UNMARK_TASK_DESC = " Noted, I have marked this task as incomplete:\n  ";
    private static final String LIST_TASK_DESC = " Here are the tasks in your list:\n";
    private static final String UNKNOWN_CMD_ERR = " WHOOPS! I'm sorry, but I don't know what that means :(";
    private static final String EMPTY_TODO_DESC_ERR = " WHOOPS! The description of a todo cannot be empty.";
    private static final String EMPTY_DEADLINE_DESC_ERR =
            " WHOOPS! The description/date/time of a deadline cannot be empty.";
    private static final String EMPTY_EVENT_DESC_ERR =
            " WHOOPS! The description/dates/times of an event cannot be empty.";
    private static final String EMPTY_TASK_NO_ERR = " WHOOPS! Task number cannot be empty.";
    private static final String WRONG_TASK_NO_FORMAT_ERR = " WHOOPS! Task number must be an integer.";
    private static final String TASK_NO_OUT_OF_RANGE_ERR = " WHOOPS! There is no such task number.";
    private static final String WRITE_FILE_ERR = "Error writing to file\n";
    private static final String LOAD_FILE_ERR = "File not found/empty file. Creating new empty task list...\n";
    private static final String LIST_FOUND_TASK_DESC = " Here are the matching tasks in your list:\n";
    private static final String EMPTY_KEYWORD_ERR = " WHOOPS! The keyword of find cannot be empty.";

    private Scanner in;

    /**
     * Initialises an instance of Ui.
     * Create and store a new input stream into the instance of Ui.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Gets input from the user via CLI.
     *
     * @return Input entered by user.
     */
    public String getInput() {
        String line = in.nextLine();
        return line;
    }

    /**
     * Prints the welcome message of Duke chatbot to CLI by calling showToUser().
     */
    public void showWelcomeMessage() {
        showToUser(WELCOME_MESSAGE);
    }

    /**
     * Prints the exit message of Duke chatbot to CLI by calling showToUser().
     */
    public void showExitMessage() {
        showToUser(EXIT_MESSAGE);
    }

    /**
     * Prints the task added message of Duke chatbot to CLI by calling showToUser().
     * Called when a task is added to the list successfully.
     *
     * @param tasks Task list of Duke chatbot.
     */
    public void showTaskAdded(ArrayList<Task> tasks) {
        String taskAdded = ADD_TASK_DESC + tasks.get(tasks.size() - 1)
                + CUR_NO_OF_TASK_START_DESC + tasks.size() + CUR_NO_OF_TASK_END_DESC;
        showToUser(taskAdded);
    }

    /**
     * Prints the task deleted message of Duke chatbot to CLI by calling showToUser().
     * Called when a task is deleted from the list successfully.
     *
     * @param tasks Task list of Duke chatbot.
     * @param taskNo Task number of a task in the task list.
     */
    public void showTaskDeleted(ArrayList<Task> tasks, int taskNo) {
        String taskDeleted = DELETE_TASK_DESC + tasks.get(taskNo)
                + CUR_NO_OF_TASK_START_DESC + (tasks.size() - 1) + CUR_NO_OF_TASK_END_DESC;
        showToUser(taskDeleted);
    }

    /**
     * Prints the task completed message of Duke chatbot to CLI by calling showToUser().
     * Called when a task in the list is marked as completed successfully.
     *
     * @param tasks Task list of Duke chatbot.
     * @param taskNo Task number of a task in the task list.
     */
    public void showTaskCompleted(ArrayList<Task> tasks, int taskNo) {
        String taskCompleted = MARK_TASK_DESC + tasks.get(taskNo);
        showToUser(taskCompleted);
    }

    /**
     * Prints the task incomplete message of Duke chatbot to CLI by calling showToUser().
     * Called when a task in the list is marked as incomplete successfully.
     *
     * @param tasks Task list of Duke chatbot.
     * @param taskNo Task number of a task in the task list.
     */
    public void showTaskIncomplete(ArrayList<Task> tasks, int taskNo) {
        String taskIncomplete = UNMARK_TASK_DESC + tasks.get(taskNo);
        showToUser(taskIncomplete);
    }

    /**
     * Prints the task list of Duke chatbot in indexed format to CLI.
     * Called when a user enters the list command in CLI.
     *
     * @param tasks Task list of Duke chatbot.
     */
    public void listTask(ArrayList<Task> tasks) {
        System.out.print(TOP_DIVIDER + LIST_TASK_DESC);

        for (int itemNo = 0; itemNo < tasks.size(); itemNo++) {
            String printItem = " " + (itemNo + 1) + "." + tasks.get(itemNo) + System.lineSeparator();
            System.out.print(printItem);
        }

        System.out.print(LIST_TASK_BOTTOM_DIVIDER);
    }

    /**
     * Prints the task list based on a keyword entered via user input to CLI.
     * Called when a user enters the find command in CLI.
     *
     * @param tasks Task list of Duke chatbot.
     */
    public void listFoundTask(ArrayList<Task> tasks) {
        System.out.print(TOP_DIVIDER + LIST_FOUND_TASK_DESC);

        for (int itemNo = 0; itemNo < tasks.size(); itemNo++) {
            String printItem = " " + (itemNo + 1) + "." + tasks.get(itemNo) + System.lineSeparator();
            System.out.print(printItem);
        }

        System.out.print(LIST_TASK_BOTTOM_DIVIDER);
    }

    /**
     * Prints the unknown command error message of Duke chatbot to CLI by calling showToUser().
     * Called when a user enters a command unknown to Duke.
     */
    public void showUnknownCmdErr() {
        showToUser(UNKNOWN_CMD_ERR);
    }

    /**
     * Prints the empty to do description error message of Duke chatbot to CLI by calling showToUser().
     * Called when a user enters an incomplete to do command.
     */
    public void showEmptyTodoDescErr() {
        showToUser(EMPTY_TODO_DESC_ERR);
    }

    /**
     * Prints the empty deadline description error message of Duke chatbot to CLI by calling showToUser().
     * Called when a user enters an incomplete deadline command.
     */
    public void showEmptyDeadlineDescErr() {
        showToUser(EMPTY_DEADLINE_DESC_ERR);
    }

    /**
     * Prints the empty event description error message of Duke chatbot to CLI by calling showToUser().
     * Called when a user enters an incomplete event command.
     */
    public void showEmptyEventDescErr() {
        showToUser(EMPTY_EVENT_DESC_ERR);
    }

    /**
     * Prints the empty task no error message of Duke chatbot to CLI by calling showToUser().
     * Called when a user did not enter a task no when using mark/unmark/delete command.
     */
    public void showEmptyTaskNoErr() {
        showToUser(EMPTY_TASK_NO_ERR);
    }

    /**
     * Prints the wrong task format error message of Duke chatbot to CLI by calling showToUser().
     * Called when a user entered a non integer task no when using mark/unmark/delete command.
     */
    public void showWrongTaskNoFormatErr() {
        showToUser(WRONG_TASK_NO_FORMAT_ERR);
    }

    /**
     * Prints the task no out of range error message of Duke chatbot to CLI by calling showToUser().
     * Called when a user entered a task no outside the range of the task list when using mark/unmark/delete
     * command.
     */
    public void showTaskNoOutOfRangeErr() {
        showToUser(TASK_NO_OUT_OF_RANGE_ERR);
    }

    /**
     * Prints the load file error message of Duke chatbot to CLI by calling showToUser().
     * Called when duke.txt is empty or not found.
     */
    public void showLoadFileErr() {
        showToUser(LOAD_FILE_ERR);
    }

    /**
     * Prints the write file error message of Duke chatbot to CLI by calling showToUser().
     * Called when there is an error creating ./data/duke.txt or writing to duke.txt.
     */
    public void showWriteFileErr() {
        showToUser(WRITE_FILE_ERR);
    }

    /**
     * Prints the empty keyword error message of Duke chatbot to CLI by calling showToUser().
     * Called when a user did not enter a keyword when using find command.
     */
    public void showEmptyKeywordErr() {
        showToUser(EMPTY_KEYWORD_ERR);
    }

    /**
     * Prints a formatted message to CLI.
     *
     * @param message Message to be printed.
     */
    private void showToUser(String message) {
        System.out.print(TOP_DIVIDER + message + BOTTOM_DIVIDER);
    }
}
