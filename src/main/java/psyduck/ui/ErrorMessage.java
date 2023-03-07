package psyduck.ui;

import psyduck.command.Command;

/**
 * Represents all the error messages that is used in the program.
 */
public class ErrorMessage {

    /**
     * Prints out the error message when FileNotFoundException is thrown.
     */
    public static void printFileNotFoundMessage() {
        Ui.linePrint();
        System.out.println("No past file record is made, will create a " +
                "new save file after terminating the program.");
        Ui.linePrint();
    }

    /**
     * Prints out the error message when TaskEmptyException is thrown.
     */
    public static void printTaskEmptyMessage() {
        Ui.linePrint();
        System.out.println("Task description cannot be empty! >:(");
        Ui.linePrint();
    }

    /**
     * Prints out the error message when InvalidEventFormatException is thrown.
     */
    public static void printInvalidEventFormatMessage() {
        Ui.linePrint();
        System.out.println("Psyduck needs a start or end date.");
        Ui.linePrint();
    }

    /**
     * Prints out the error message when InvalidDeadlineFormatException is thrown.
     */
    public static void printInvalidDeadlineFormatMessage() {
        Ui.linePrint();
        System.out.println("Psyduck needs a deadline.");
        Ui.linePrint();
    }

    /**
     * Prints out the error message when user is trying to remove a task at a
     * specified position out of bounds of the array list.
     */
    public static void printRemoveTaskErrorMessage() {
        Ui.linePrint();
        System.out.println("Please remove a valid task.");
        Ui.linePrint();
    }

    /**
     * Prints out the error message when user is trying to mark a task at a
     * specified position out of bounds of the array list.
     */
    public static void printMarkTaskErrorMessage() {
        Ui.linePrint();
        System.out.println("Please mark a valid task.");
        Ui.linePrint();
    }

    /**
     * Prints out the error message when user is trying to unmark a task at a
     * specified position out of bounds of the array list.
     */
    public static void printUnmarkTaskErrorMessage() {
        Ui.linePrint();
        System.out.println("Please mark a valid task.");
        Ui.linePrint();
    }

    /**
     * Prints out the error message when user inputs an unknown command.
     */
    public static void printInvalidCommandMessage() {
        Ui.linePrint();
        System.out.println("Invalid command, Psyduck does not understand. :-(");
        System.out.println("Please enter a valid command.");
        Ui.linePrint();
    }

    /**
     * Prints out the error message when EmptyFindException is thrown.
     */
    public static void printEmptyFindMessage() {
        Ui.linePrint();
        System.out.println("Your description is empty.");
        Ui.linePrint();
    }
}
