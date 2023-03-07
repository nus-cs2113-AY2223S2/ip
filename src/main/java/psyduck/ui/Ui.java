package psyduck.ui;

import psyduck.command.Command;
import psyduck.task.*;
import psyduck.tasklist.TaskList;

import java.util.Scanner;

public class Ui {

    private final Scanner in = new Scanner(System.in);

    /**
     * Prints a dashed line.
     */
    public static void linePrint() {
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Reads in the input from the stream.
     *
     * @return a string containing the user's input.
     */
    public String readInput() {
        String input = in.nextLine();
        return input;
    }

    /**
     * Prints the message at the start of the program.
     */
    public void greet() {
        Ui.linePrint();
        System.out.println("Hi I am Psyduck! How can I help you?");
        Ui.linePrint();
    }

    /**
     * Prints the message before the program terminates.
     */
    public void sayBye() {
        linePrint();
        System.out.println("Bye see you soon! :) ");
        linePrint();
    }

    /**
     * Prints the message about the newly added task.
     * @param tasks The task list containing the task that was recently added.
     */
    public static void printTaskAdded(TaskList tasks) {
        linePrint();
        System.out.println("Psyduck has added the task: " + TaskList.getNewestTask());
        System.out.print("You now have: " + TaskList.getTaskCount());
        if (TaskList.getTaskCount() == 1) {
            System.out.println(" task");
        } else {
            System.out.println(" tasks");
        }
        linePrint();
    }

    /**
     * Prints a message on the task that was removed.
     *
     * @param task the task that was removed.
     * @param tasks the task list containing the task that was removed.
     */
    public static void printTaskRemoved(Task task, TaskList tasks) {
        linePrint();
        System.out.println("Psyduck has removed the task: " + task);
        linePrint();
    }

    /**
     * Prints a message on the task that was marked.
     *
     * @param task the task that was marked.
     * @param tasks the task list containing the task that was marked.
     */
    public static void printTaskMarked(Task task, TaskList tasks) {
        linePrint();
        System.out.println("Psyduck has marked the task: " + task);
        linePrint();
    }

    /**
     * Prints a message on the task that was unmarked.
     *
     * @param task the task that was unmarked.
     * @param tasks the task list containing the task that was unmarked.
     */
    public static void printTaskUnmarked(Task task, TaskList tasks) {
        linePrint();
        System.out.println("Psyduck has unmarked the task: " + task);
        linePrint();
    }

    /**
     * Prints out the header details for the list.
     */
    public static void printListDetails() {
        System.out.println("Here are the tasks currently in your list: ");
    }
}
