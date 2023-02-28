package psyduck.ui;

import psyduck.command.Command;

public class ErrorMessage {
    public static void printFileNotFoundMessage() {
        Ui.linePrint();
        System.out.println("No past file record is made, will create a " +
                "new save file after terminating the program.");
        Ui.linePrint();
    }

    public static void printTaskEmptyMessage() {
        Ui.linePrint();
        System.out.println("Task description cannot be empty! >:(");
        Ui.linePrint();
    }

    public static void printInvalidEventFormatMessage() {
        Ui.linePrint();
        System.out.println("Psyduck needs a start or end date.");
        Ui.linePrint();
    }

    public static void printInvalidDeadlineFormatMessage() {
        Ui.linePrint();
        System.out.println("Psyduck needs a deadline.");
        Ui.linePrint();
    }

    public static void printRemoveTaskErrorMessage() {
        Ui.linePrint();
        System.out.println("Please remove a valid task.");
        Ui.linePrint();
    }

    public static void printMarkTaskErrorMessage() {
        Ui.linePrint();
        System.out.println("Please mark a valid task.");
        Ui.linePrint();
    }

    public static void printInvalidCommandMessage() {
        Ui.linePrint();
        System.out.println("Invalid command, Psyduck does not understand. :-(");
        System.out.println("Please enter a valid command.");
        Ui.linePrint();
    }
}
