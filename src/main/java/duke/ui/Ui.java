package duke.ui;

import duke.task.Task;

import java.util.Scanner;


/**
 * Ui class handles all interactions with user, printing messages etc
 */
public class Ui {

    public static final String LINE = "\t____________________________________________________________";

    public static void printLine() {
        System.out.println(LINE);
    }

    public static void printInvalidNumber(String taskType) {
        printLine();
        System.out.println("\tError! Invalid input.");
        System.out.println("\tPlease provide a task number for \"" + taskType + "\" command.");
        System.out.println("\tPlease use \"list\" command to see your task numbers.");
        printLine();
    }

    public static void printEmptyCommand(String taskType) {
        printLine();
        System.out.println("\tError! \"" + taskType + "\" command cannot be empty.");
        System.out.println("\tPlease provide more details");
        printLine();
    }

    public static void printInvalidFormat(String taskType) {
        printLine();
        System.out.println("\tError! Invalid format for \"" + taskType + "\" command.");
        printLine();
    }

    public static void printEmptyFile() {
        printLine();
        System.out.println("\tSaved file is empty.");
        printLine();
    }

    public static void printIncorrectTaskFormat() {
        printLine();
        System.out.println("\tError! Tasks saved in incorrect format.");
        printLine();
    }

    public static void printFileCreated(boolean fileCreated) {
        if (fileCreated) {
            printLine();
            System.out.println("\tFile \"duke.txt\" created!");
            printLine();
        } else {
            printLine();
            System.out.println("\tError! Failed to create file.");
            printLine();
        }
    }

    public static void printGreetMessage() {
        System.out.println("\tHello! I'm Duke.");
        System.out.println("\tHow can I help you today?\n");
        printLine();
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("\tBye! Remember to finish your tasks.\n");
        printLine();
    }

    public static void printInvalidCommand() {
        printLine();
        System.out.println("\tError! Please input a valid command!");
        printLine();
    }

    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }


    /**
     * Function loops through given file and prints out each line
     * Loops through as long as there is an available line
     *
     * @param s a file scanner to scan through the file
     */
    public static void printFileContents(Scanner s) {
        printLine();
        System.out.println("\tHere are your stored tasks!");
        int index = 1;
        while (s.hasNext()) {
            System.out.println("\t" + index + ". " + s.nextLine());
            index++;
        }
        printLine();
    }

    public static void printNoMatchingTasks() {
        System.out.println("\tNo matching task found in stored data.");
    }

    public static void printTaskDetails(int index, Task task) {
        if (index == 1) {
            System.out.println("\tHere are the matching tasks in your list:");
        }
        System.out.print("\t " + index + ".");
        System.out.println(task.returnCommand());
    }
}
