package duke.ui;

import java.util.Scanner;

/**
 * Handles all UI outputs
 */
public class UI {
    private static final String INDENT = "    ";
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO = " ____        _        " + System.lineSeparator()
            + "|  _ \\ _   _| | _____ " + System.lineSeparator()
            + "| | | | | | | |/ / _ \\" + System.lineSeparator()
            + "| |_| | |_| |   <  __/" + System.lineSeparator()
            + "|____/ \\__,_|_|\\_\\___|" + System.lineSeparator();
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_GREET = "Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?";
    private static final String MESSAGE_HELP = "Welcome to Duke." + System.lineSeparator()
            + "Duke is a task managing application optimised for the Command-Line Interface." + System.lineSeparator()
            + "Commands: bye, deadline, event, exit, find, help, list, mark, todo, unmark" + System.lineSeparator()
            + "To learn more about each command and view the full user guide, "
            + "visit https://jinxuan-owyong.github.io/ip/" + System.lineSeparator();
    private static final String MESSAGE_LOGO = "Hello from";
    private static final String MESSAGE_SAVE_FAILED = "Warning: Save operation failed." + System.lineSeparator()
            + "Any data added from the most recent command will not be saved.";

    public void greet() {
        print(MESSAGE_GREET);
        printLine();
    }

    /**
     * Prints the given string with indentation.
     *
     * @param output String to be printed
     */
    public void print(String output) {
        Scanner scan = new Scanner(output);
        while (scan.hasNextLine()) { // add indentation
            System.out.println(INDENT + scan.nextLine());
        }
        scan.close();
    }

    public void printExit() {
        print(MESSAGE_EXIT);
        printLine();
        print(System.lineSeparator());
    }

    public void printHelp() {
        print(MESSAGE_HELP);
        printLine();
    }

    public void printLine() {
        print(LINE + System.lineSeparator());
    }

    public void printLine(String text) {
        print(text);
        printLine();
    }

    public void printLogo() {
        print(MESSAGE_LOGO + System.lineSeparator() + LOGO);
        printLine();
    }

    public void printSaveFailed() {
        print(MESSAGE_SAVE_FAILED);
        printLine();
    }

    /**
     * Prints the message after a new task is created.
     *
     * @param description Description of the task added
     * @param numTasks    Number of tasks in the list
     */
    public void printTaskAdded(String description, int numTasks) {
        String output = "Got it. I've added this task:" + System.lineSeparator()
                + INDENT + description + System.lineSeparator()
                + "Now you have " + numTasks + " tasks in the list";
        print(output);
        printLine();
    }

    /**
     * Prints the message after a task is deleted.
     *
     * @param description Description of the task deleted
     * @param numTasks    Number of tasks in the list
     */
    public void printTaskDeleted(String description, int numTasks) {
        String output = "Noted. I have removed this task:" + System.lineSeparator()
                + INDENT + description + System.lineSeparator()
                + "Now you have " + numTasks + " tasks in the list.";
        print(output);
        printLine();
    }

    /**
     * Prints the message when there are matches for the query by the user.
     *
     * @param query The keyword or regex queried by the user
     * @param tasks The matching tasks
     */
    public void printTasksFound(String query, String tasks) {
        print("Showing matches for query: " + query + System.lineSeparator());
        print(tasks);
        printLine();
    }
}
