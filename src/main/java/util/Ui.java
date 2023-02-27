package util;

import java.util.Scanner;
import java.util.ArrayList;
import util.Task;

/**
 * 
 * The Ui class handles all user interactions and outputs.
 * It contains methods to display the command list, ask for user input, print
 * dash lines,
 * print welcome and bye messages, print specific task, and print the length of
 * the task list.
 * 
 */
public class Ui {
    public Ui() {
    }

    /**
     * Displays the list of tasks.
     * 
     * @param commands the list of tasks to be displayed
     */
    public void displayCommandsList(ArrayList<Task> commands) {
        int i = 1;
        System.out.println("\t_____________________________________________________");
        for (Task task : commands) {
            System.out.println("\t" + i + "." + task);
            i++;
        }
        System.out.println("\t_____________________________________________________");
    }

    /**
     * Asks for user input.
     * 
     * @return the user's input
     */
    public String ask() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public void printDashLine() {
        System.out.println("\t_____________________________________________________");
    }

    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" Hello! I'm Duke ");
        System.out.println(" What can I do for you? \n");
        System.out.println("____________________________________________________________\n");
    }

    public void printByeMessage() {
        System.out.println("____________________________________________________________\n");
        System.out.println("\t Bye. Hope to see you again soon!\n");
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints a specified message and a specific task.
     * 
     * @param index    the index of the task to be printed
     * @param commands the list of tasks
     * @param message  a message to be displayed before the task
     */
    public void printSpecificTask(int index, ArrayList<Task> commands, String message) {
        if (!message.equals("")) {
            System.out.println("\t " + message);
        }
        System.out.println("\t" + index + "." + commands.get(index - 1));
    }

    public void printLenghtOfTaskList(ArrayList<Task> commands) {
        System.out.println("\t Now you have " + commands.size() + " tasks in the list.");
    }
}
