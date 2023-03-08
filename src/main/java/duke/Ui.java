package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    /**
     * Prints a greeting to the user.
     */
    public static void greetUser() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a list of tasks in the task list, according to the order in which they were added.
     *
     * @param tasks   The list of tasks to be printed on the screen.
     * @param context The context in which the tasks will be listed, which will affect the first line printed on the
     *                screen. There are 2 contexts - 'list' where the user wants to list all the tasks in the task list
     *                and 'find' where the user wants to list the tasks containing the keyword specified by the user.
     */
    public static void listTasks(ArrayList<Task> tasks, String context) {
        if (context.equals("find")) {
            System.out.println("Here are the matching tasks in your list:");
        } else if (context.equals("list")) {
            System.out.println("Here are the tasks in your list:");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1);
            System.out.print(".");
            tasks.get(i).printTask();
        }
    }

    /**
     * Prints a message to notify the user of what has just been changed in the task list.
     *
     * @param task          The task which has just been modified in the task list or added to the task list.
     * @param action        The modification or addition which has just been done to the task list.
     * @param numberOfTasks The total number of tasks in the task list.
     */
    public static void printNotification(Task task, String action, int numberOfTasks) {
        switch (action) {
        case "unmark":
            System.out.println("OK, I've marked this task as not done yet:");
            task.printTask();
            break;
        case "mark":
            System.out.println("Nice! I've marked this task as done:");
            task.printTask();
            break;
        case "delete":
            System.out.println("Noted. I've removed this task:");
            task.printTask();
            System.out.println("Now you have " + numberOfTasks + " task(s) in the list.");
            break;
        case "deadline":
            // Fallthrough
        case "todo":
            // Fallthrough
        case "event":
            System.out.print("Got it. I've added this task:\n" + "  ");
            task.printTask();
            System.out.println("Now you have " + numberOfTasks + " task(s) in the list.");
            break;
        default:
            System.out.println("Something went wrong!");
            break;
        }
    }

    /**
     * Prints a message to notify the user of the error that has just occurred.
     *
     * @param errorType The type of error that has just occurred.
     */
    public static void printErrorMessage(String errorType) {
        switch (errorType) {
        case "conversion":
            System.out.println("OOPS! Something went wrong while converting your data!");
            break;
        case "file not found":
            System.out.println("File not found");
            break;
        case "saving":
            System.out.println("OOPS! Something went wrong with saving your data!");
            break;
        case "invalid command":
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        case "error with information provided":
            System.out.println("OOPS! I'm sorry, but the information you provided contains errors."); // fill in
            break;
        default:
            System.out.println("Encountered an unknown error");
            break;
        }
    }

    /**
     * Prints an exit message to the user.
     */
    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
