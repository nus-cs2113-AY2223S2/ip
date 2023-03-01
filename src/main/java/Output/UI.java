package Output;

import java.util.Scanner;

import Entities.Task;
import Entities.TaskList;
import Exceptions.DukeException;

/**
 * Class responsible for outputting to CLI as interface between user and Duke
 */
public class UI {
    public static Scanner sc = new Scanner(System.in);

    /**
     * Reads user input
     * @return user input
     */
    public String readCommand() {
        String command = "";
        while (command.length() == 0) {
            command = sc.nextLine();
        }
        return command;
    }

    /**
     * Prints Welcome message when Duke first starts
     */
    public void printIntroduction() {
        printLine();
        System.out.println("\tHello! I'm Duke. Hope you are having a wonderful day");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    /**
     * Prints Goodbye message when Duke exits
     */
    public void printGoodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Helper function to print divider
     */
    public void printLine() {
        System.out.println("\t------------------------------------------------------------");
    }

    /**
     * Prints error message
     * @param errorMessage error message
     */
    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints message when a new task is added to task list
     * @param t new task
     * @param numTasks new number of tasks
     */
    public void taskAddedMessage(Task t, int numTasks) {
        System.out.println(
                "\tGot it. I have added this task to the list!\n" + 
                "\t   " + t.toString() + "\n" + 
                "\tNow you have " + numTasks + 
                " task" + (numTasks == 1 ? "" : "s") + 
                " in the list.\n"
        );
    }

    /**
     * Prints all tasks on task list
     * @param tasks Task List
     * @throws DukeException
     */
    public void printTasks(TaskList tasks) throws DukeException {
        String output = "";

        if (tasks.getTasksCount() == 0) {
            output += "\t No tasks found!\n";
        } else {
            output += "\t Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.getTasksCount(); i++) {
                output += "\t" + (i + 1) + "." + tasks.getTask(i).toString() + "\n";
            }
        }

        System.out.printf(output);
    }

    /**
     * Prints all tasks with custom message
     * @param tasks
     * @throws DukeException
     */
    public void printTasks(TaskList tasks, String header) throws DukeException {
        String output = "";

        if (tasks.getTasksCount() == 0) {
            output += "\t No tasks found!\n";
        } else {
            output += "\t" + header + "\n";
            for (int i = 0; i < tasks.getTasksCount(); i++) {
                output += "\t" + (i + 1) + "." + tasks.getTask(i).toString() + "\n";
            }
        }

        System.out.printf(output);
    }

    /**
     * Prints message when a task is deleted to task list
     * @param t deleted task
     * @param numTasks new number of tasks
     */
    public void taskDeletedMessage(Task t, int numTasks) {
        System.out.println(
                "\tNoted. I've removed this task:\n" + 
                "\t   " + t.toString() + "\n" + 
                "\tNow you have " + numTasks + 
                " task" + (numTasks == 1 ? "" : "s") + 
                " in the list.\n"
        );
    }

    /**
     * Prints message when a task is marked as completed
     * @param t task that is marked as completed
     */
    public void printMarkedTask(Task t) {
        System.out.println( 
                "\tNice! I've marked this task as done:\n" + 
                "\t   " + t.toString() + "\n"
        );
    }

    /**
     * Prints message when a task is marked as not completed
     * @param t task that is marked as not completed
     */
    public void printUnmarkedTask(Task t) {
        System.out.println( 
                "\tOK, I've marked this task as not done yet:\n" + 
                "\t   " + t.toString() + "\n"
        );
    }
}
