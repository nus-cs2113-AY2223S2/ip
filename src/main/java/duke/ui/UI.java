package duke.ui;

import duke.constants.Config;

import java.util.Scanner;

/**
 * Handles all UI outputs
 */
public class UI {
    public void printWelcomeMessage() {
        printString(Config.MESSAGE_WELCOME);
        printLine();
    }

    /**
     * Prints the given string with indentation.
     *
     * @param output String to be printed
     */
    public void printString(String output) {
        Scanner scan = new Scanner(output);
        while (scan.hasNextLine()) { // add indentation
            System.out.println(Config.INDENT + scan.nextLine());
        }
        scan.close();
    }

    public void printExit() {
        printString(Config.MESSAGE_EXIT);
        printLine();
        printString(System.lineSeparator());
    }

    public void printHelp() {
        printString(Config.MESSAGE_HELP);
        printLine();
    }

    public void printLine() {
        printString(Config.LINE + System.lineSeparator());
    }

    public void printLine(String text) {
        printString(text);
        printLine();
    }

    public void printLogo() {
        printString(Config.MESSAGE_LOGO + System.lineSeparator() + Config.LOGO);
        printLine();
    }

    public void printSaveFailed() {
        printString(Config.MESSAGE_SAVE_FAILED);
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
                + Config.INDENT + description + System.lineSeparator()
                + "Now you have " + numTasks + " tasks in the list";
        printString(output);
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
                + Config.INDENT + description + System.lineSeparator()
                + "Now you have " + numTasks + " tasks in the list.";
        printString(output);
        printLine();
    }

    /**
     * Prints the message when there are matches for the query by the user.
     *
     * @param query The keyword or regex queried by the user
     * @param tasks The matching tasks
     */
    public void printTasksFound(String query, String tasks) {
        printString(Config.MESSAGE_TASKS_FOUND + query + System.lineSeparator());
        printString(tasks);
        printLine();
    }
}
