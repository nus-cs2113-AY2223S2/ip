package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static void printGreet() {
        System.out.println("Hello! I'm duke.");
        System.out.println("What can I do for you?");
    }

    public static void printBye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public static String[] getInput() {
        Scanner input = new Scanner(System.in);
        return Parser.parse(input);
    }

    public static void printNumberOfTasks(ArrayList<Task> tasks) {
        System.out.println("You currently have " + tasks.size() + " tasks in your list.");
    }
}
