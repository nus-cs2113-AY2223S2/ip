package duke.ui;

import duke.file.TaskList;
import duke.outputs.Messages;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI which deals with interactions with the user
 */
public class UI {
    private static final String MARGIN = "*----------------------------*";
    public static void showLine(){
        System.out.println(MARGIN);
    }

    /**
     * Reads in the user input into a string
     *
     * @return user input as a string
     */
    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        return userInput;
    }
    /**
     * Print welcome message when the user runs program
     */
    public static void showWelcomeMessage() {
        String logo = " __________________ ##\n" +
                "_________________###*\n" +
                "______________.*#####\n" +
                "_____________*######\n" +
                "___________*#######\n" +
                "__________*########.\n" +
                "_________*#########.\n" +
                "_________*#######*##*\n" +
                "________*#########*###\n" +
                "_______*##########*__*##\n" +
                "_____*###########_____*\n" +
                "____############\n" +
                "___*##*#########\n" +
                "___*_____########\n" +
                "__________#######\n" +
                "___________*######\n" +
                "____________*#####*\n" +
                "______________*####*\n" +
                "________________*####\n" +
                "__________________*##*\n" +
                "____________________*##\n" +
                "_____________________*##.\n" +
                "____________________.#####.\n" +
                "_________________.##########\n" +
                "________________.####*__*####\n" +
                "\n";

        System.out.println(MARGIN);
        System.out.println(logo);
        System.out.println(Messages.WELCOME_MESSAGE_1);
        System.out.println(Messages.WELCOME_MESSAGE_2);
        System.out.println(MARGIN);
    }

    /**
     * Print exit message when user terminates the program
     */
    public static void endProgram() {
        System.out.println(MARGIN);
        System.out.println(Messages.EXIT_MESSAGE);
        System.out.println(MARGIN);
    }
    /**
     * Prints all tasks stored in the tasklist
     *
     * @param tasks the entire tasklist
     */
    public void printTasksArray(TaskList tasks) {
        showLine();
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> listOfTasks = tasks.getTasksArray();
        for (Task task : listOfTasks) {
            if (task != null) {
                System.out.println(count + "." + task);
                count++;
            }
        }
        showLine();
    }

    /**
     * Prints the status of task (marked as done or unmarked)
     *
     * @param task task to be marked as done or unmarked
     * @param taskStatus  the status of the task
     */
    public void printTaskStatusMessage(Task task, String taskStatus) {
        showLine();
        if (taskStatus.equals("mark")) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println(task);
        showLine();
    }

    /**
     * Prints the task that had been added recently , as well as the number
     * of tasks in the current tasklist
     *
     * @param tasks the current tasklist
     * @param task  the new task added
     */
    public void printTaskAddedMessage(TaskList tasks, Task task) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + task);
        if (tasks.sizeOfTasksArray() == 1) {
            System.out.println(" Now you have " + tasks.sizeOfTasksArray() + " task in the list.");
        } else {
            System.out.println(" Now you have " + tasks.sizeOfTasksArray() + " tasks in the list.");
        }
        showLine();
    }

    /**
     * Prints the task that had been deleted recently , as well as the number
     * of tasks in the current tasklist
     *
     * @param tasks the current tasklist
     * @param task  the task deleted
     */
    public void printTaskDeletedMessage(TaskList tasks, Task task) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println(" " + task);
        if (tasks.sizeOfTasksArray() == 1) {
            System.out.println(" Now you have " + (tasks.sizeOfTasksArray() - 1) + " task in the list.");
        } else {
            System.out.println(" Now you have " + (tasks.sizeOfTasksArray() - 1) + " tasks in the list.");
        }
        showLine();
    }



}





