package duke.ui;

import java.util.Scanner;

/**
 * Handles all UI outputs
 */
public class UI {
    private static final String INDENT = "    ";
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_GREET = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_HELP = "Welcome to Duke." + System.lineSeparator()
            + "Duke is a ";
    private static final String MESSAGE_LOGO = "Hello from";

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
    }

    public void printLine() {
        print(LINE + "\n");
    }

    public void printLine(String text) {
        print(text);
        printLine();
    }

    public void printLogo() {
        print(MESSAGE_LOGO + System.lineSeparator() + LOGO);
        printLine();
    }

    /**
     * Prints the message after a new task is created.
     *
     * @param description Description of the task added
     * @param numTasks    Number of tasks in the list
     */
    public void printTaskAdded(String description, int numTasks) {
        String output = "Got it. I've added this task:\n"
                + INDENT + description + "\n"
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
        String output = "Noted. I have removed this task:\n"
                + INDENT + description + "\n"
                + "Now you have " + numTasks + " tasks in the list.";
        print(output);
        printLine();
    }

    /**
     * Prints the message when there are matches for the query by the user.
     * @param query The keyword or regex queried by the user
     * @param tasks The matching tasks
     */
    public void printTasksFound(String query, String tasks) {
        print("Showing matches for query: " + query + "\n");
        print(tasks);
        printLine();
    }
}
