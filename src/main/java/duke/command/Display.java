package duke.command;

import duke.Duke;
import duke.task.Task;

import java.util.ArrayList;

/**
 * The Display class prints out various
 * messages for the user
 */
public class Display {
    public static final String LOGO =
            "\t __   __\n"
                    + "\t|  | |  |   ____     _  _   _    _\n"
                    + "\t|  |_|  |  / _  \\   | |/_\\ | |  | |\n"
                    + "\t|   _   | | |_|  \\  |  /   | \\_/  |\n"
                    + "\t|__| |__|  \\___/\\_\\ |_|     \\__/|_|\n";
    public static final String DIVIDER = "\t____________________________________________________________";
    public static final String SPACER = "\t";

    /**
     * This method prints the welcome message
     */
    public static void printWelcome() {
        System.out.println(DIVIDER);
        System.out.println(LOGO);
        System.out.println(SPACER + "Hello! I'm Haru");
        System.out.println(SPACER + "What can I do for you?");
        System.out.println(DIVIDER);
    }

    /**
     * This method prints the goodbye message
     */
    public static void printGoodbye() {
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    /**
     * This method prints the number of tasks listed
     */
    public static void printNumberOfTasks() {
        System.out.println(SPACER+"Now you have " + Duke.userTextCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * This method prints the welcome message
     */
    public static void printInvalidInput() {
        System.out.println(DIVIDER);
        System.out.println(SPACER+"☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(DIVIDER);
    }

    /**
     * This method prints out all the tasks listed
     */
    public static void listTasks(int userTextCount, ArrayList<Task> storedUserTasks) {
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Here are the tasks in your lists:");
        for(int i=0;i<userTextCount;i++){
            System.out.println(SPACER+(i+1)+"."+storedUserTasks.get(i).toString());
        }
        System.out.println(DIVIDER);
    }

    /**
     * This method prints the task progress
     */
    public static void displayTaskProgress(String checkType, int taskIndex,ArrayList<Task> storedUserTasks) {
        System.out.println(DIVIDER);
        if(checkType.equals("mark")) {
            System.out.println(SPACER+"Nice! I've marked this task as done:");
        } else {
            System.out.println(SPACER+"OK, I've marked this task as not done yet:");
        }
        System.out.println(SPACER+storedUserTasks.get(taskIndex).toString());
        System.out.println(DIVIDER);
    }

    /**
     * This method prints a deleted task
     */
    public static void displayDeleteTask(int taskIndex,ArrayList<Task> storedUserTasks) {
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Noted. I've removed this task:");
        System.out.println(SPACER+SPACER+storedUserTasks.remove(taskIndex).toString());
    }

    /**
     * This method prints a created task
     */
    public static void displayCreateTask(ArrayList<Task> storedUserTasks) {
        System.out.println(DIVIDER);
        System.out.println(SPACER+"Got it. I've added this task:");
        System.out.println(SPACER+storedUserTasks.get(Duke.userTextCount).toString());
    }

}
