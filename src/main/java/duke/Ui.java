package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Deals with interactions with the user. Reads the user's input from standard input and prints the output to the
 * standard output. Output consists of expected output and error messages.
 */
public class Ui {
    public static final String LINE_SEPARATOR = "____________________________________________________________";
    private static Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public static void printSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    public static String getInput() {
        String input = in.nextLine();
        return input;
    }

    public static void greet() {
        printSeparator();
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?");
        printSeparator();
        System.out.println("");
    }

    public static void farewell() {
        printSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        printSeparator();
    }

    /**
     * Displays the list in a readable format with all the necessary information.
     * Indicates index of <code>Task</code>.
     * Indicates the type of <code>Task</code>, whether it is a <code>Todo</code>, <code>Deadline</code>
     * or <code>Event</code>.
     * Indicates if the <code>Task</code> is marked as done.
     *
     * @param tasks The list of <code>Task</code>s to display.
     */
    public static void displayList(ArrayList<Task> tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.print(" " + (i + 1) + ".");
            System.out.println(tasks.get(i));
        }
    }


    public void printSuccessfulAddMessage(Task currTask, int numTasks) {
        System.out.println(" Got it. I've added this task: ");
        System.out.println("  " + currTask);
        System.out.println(" Now you have " + numTasks + " tasks in the list.");

    }

    public static void printValidSave() {
        System.out.println("Valid save file detected.");
    }

    public static void printSuccessfulSave(String filepath) {
        System.out.println("File successfully saved to: " + filepath);
    }

    public static void printSuccessfulMark(Task markedTask) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + markedTask.taskTypeBoxFormat() + markedTask.markedBoxFormat() + " "
                + markedTask.getTaskName());
    }

    public static void printSuccessfulUnmark(Task unmarkedTask) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + unmarkedTask.taskTypeBoxFormat() + unmarkedTask.markedBoxFormat() + " "
                + unmarkedTask.getTaskName());
    }

    public static void printSuccessfulDelete(Task deletedTask, int numTasks) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + deletedTask.taskTypeBoxFormat() + deletedTask.markedBoxFormat() +
                " " + deletedTask.getTaskName());
        System.out.println(" Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Displays the filtered list in a readable format with all the necessary information.
     * Indicates index of <code>Task</code>.
     * Indicates the type of <code>Task</code>, whether it is a <code>Todo</code>, <code>Deadline</code>
     * or <code>Event</code>.
     * Indicates if the <code>Task</code> is marked as done.
     *
     * @param filteredList The list of <code>Task</code>s to display.
     */
    public static void printFilteredList(ArrayList<Task> filteredList) {
        System.out.println(" Here are the matching tasks in your list:");

        for (int i = 0; i < filteredList.size(); i += 1) {
            System.out.print(" " + (i + 1) + ".");
            System.out.println(filteredList.get(i));
        }
    }

    public static void printFilteredListEmpty() {
        System.out.println(" There are no matching tasks!");
    }

    public static void printInvalidIndex() {
        System.out.println("Given index is invalid!");
    }

    public static void printIndexNotNumber() {
        System.out.println("Given index is not a number!");
    }

    public static void printNoValidSave() {
        System.out.println("No valid save file detected. Starting with " +
                "empty list.");
    }

    public static void printNoValidSaveClassNotFound() {
        System.out.println("ClassNotFoundException caught, no valid save file detected. Starting with " +
                "empty list.");
    }

    public static void printInvalidCommandException() {
        System.out.println("Invalid command!");
    }

    public static void printFailedToSave() {
        System.out.println("IOException caught, failed to save.");
    }

    public static void printTodoEmptyDescription() {
        System.out.println("Invalid todo command! Task description cannot be empty");
    }

    public static void printDeadlineEmptyDescription() {
        System.out.println("Invalid deadline command! Task description cannot be empty");
    }

    public static void printInvalidDeadline() {
        System.out.println("Invalid deadline command!");
    }

    public static void printEventEmptyDescription() {
        System.out.println("Invalid event command! Task description cannot be empty");
    }

    public static void printInvalidEvent() {
        System.out.println("Invalid event command!");
    }

}
