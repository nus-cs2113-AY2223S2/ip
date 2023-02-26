package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {
    public Ui() {
    }

    public static final String LINE_BREAK = "---------------------------------------------";

    public static void printLine() {
        System.out.println(LINE_BREAK);
    }

    public static void printAddTask(Task item) {
        System.out.println("Got it. I've added this task: " + item.getTypeIcon()
                + item.getStatusIcon() + item.description);
    }

    public static void printRemoveTask(Task item) {
        System.out.println("Got it. I've added this task: " + item.getTypeIcon()
                + item.getStatusIcon() + item.description);
    }

    public static void printListSize(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void printIntro() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void printExiting() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printError() {
        System.out.println("You chose a task that does not exist!");
    }

    public static void printLoadingError() {
        System.out.println("New directory has been made. Please provide some data to be written to the file.");
    }

    public static void printSearchResults(ArrayList<Task> searchResults) {
        int itemNumber = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task item : searchResults) {
            System.out.println(itemNumber + ". " + item.getTypeIcon()
                    + item.getStatusIcon() + item.description);
            itemNumber++;
        }
        printLine();
    }

    public static String getInput() throws NoSuchElementException {
        Scanner in = new Scanner(System.in);
        String line = "";
        try {
            line = in.nextLine();
        } catch (NoSuchElementException e) {
            printExiting();
        }
        return line;
    }
}

