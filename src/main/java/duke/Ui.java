package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;


public class Ui {
    public static final String LINESEPARATOR = "____________________________________________________________";
    private static Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public static void printSeparator() {
        System.out.println(LINESEPARATOR);
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


    public static void printValidSave() {
        System.out.println("Valid save file detected.");
    }
    public static void printSuccessfulSave(String filepath) {
        System.out.println("File successfully saved to: " + filepath);
    }
    public static void printNoValidSave() {
        System.out.println("No valid save file detected. Starting with empty list.");
    }

    public static void printInvalidCommandException() {
        System.out.println("Invalid task type!");
    }
    public static void printClassNotFoundException() {
        System.out.println("ClassNotFoundException caught");
    }
    public static void printIOException() {
        System.out.println("IOException caught");
    }

    public static void displayList(ArrayList<Task> taskList) {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i += 1) {
                // print index of task
                System.out.print(" " + (i + 1) + ".");

                // list the details about the task. Based on whether the task is ToDo, Deadline or Event.
                System.out.println(taskList.get(i));
            }
        }



}
