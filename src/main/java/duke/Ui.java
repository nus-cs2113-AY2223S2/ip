package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to handle printing messages to the user
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructor for a new Ui object
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Shows the welcome message
     */
    public void showWelcome() {
        showLine();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        showLine();
    }

    /**
     * Shows the error when loading the program
     * @param e the error
     */
    public void showLoadingError(Exception e) {
        showLine();
        System.out.println("    Loading error!");
        System.out.println("    " + e);
        showLine();
    }

    /**
     * Reads an input from the user
     * @return the string that was inputted
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a line separator
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows an error caught during the execution of commands
     * @param e the error caught
     */
    public void showError(Exception e) {
        switch (e.getClass().getName()) {
            case "duke.IllegalCommandException":
                IllegalCommandException commandError = (IllegalCommandException) e;
                if (commandError.type == IllegalCommandExceptionType.COMMAND_DOES_NOT_EXIST) {
                    showLine();
                    System.out.println("    Error: The command '" + commandError.value + "' does not exist!");
                    showLine();
                } else if (commandError.type == IllegalCommandExceptionType.MISSING_VALUE) {
                    showLine();
                    System.out.println("    Error: Missing value after '" + commandError.value + "' command!");
                    showLine();
                } else if (commandError.type == IllegalCommandExceptionType.MISSING_PARAMETER) {
                    showLine();
                    System.out.println("    Error: This command requires the '" + commandError.value + "' parameter!");
                    showLine();
                }
                break;
            case "duke.IllegalParameterException":
                IllegalParameterException parameterError = (IllegalParameterException) e;
                if (parameterError.type == IllegalParameterExceptionType.PARAMETER_DOES_NOT_EXIST) {
                    showLine();
                    System.out.println("    Error: The parameter '" + parameterError.value + "' does not exist!");
                    showLine();
                } else if (parameterError.type == IllegalParameterExceptionType.MISSING_VALUE) {
                    showLine();
                    System.out.println("    Error: Missing value after '" + parameterError.value + "' parameter!");
                    showLine();
                }
                break;
            default:
                showLine();
                System.out.println("    Error: " + e);
                showLine();
                break;
        }
    }

    /**
     * Displays all tasks
     * @param taskList TaskList object containing the tasks to be shown
     */
    public void showAllTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String prefix = (i + 1) + ".";
            System.out.println("    " + prefix + tasks.get(i).getLabel());
        }
    }

    /**
     * Displays message to acknowledge task addition
     * @param taskList the TaskList object
     * @param task the added task
     */
    public void showAddedTask(TaskList taskList, Task task) {
        ArrayList<Task> tasks = taskList.getAllTasks();
        showLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + task.getLabel());
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        showLine();
    }

    /**
     * Displays acknowledgement for task removal
     * @param task the removed task
     */
    public void showRemovedTask(Task task) {
        showLine();
        System.out.println("    Noted. I've removed this task:");
        System.out.println(task.getLabel());
        showLine();
    }

    /**
     * Displays acknowledgement for marking task as done
     * @param task the marked task
     */
    public void showMarkedTask(Task task) {
        showLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println(task.getLabel());
        showLine();
    }

    /**
     * Displays acknowledgement for marking task as not done
     * @param task the unmarked task
     */
    public void showUnmarkedTask(Task task) {
        showLine();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println(task.getLabel());
        showLine();
    }

    /**
     * Displays exit message
     */
    public void showExitMessage() {
        showLine();
        System.out.println("    Bye. Hope to see you again soon!");
        showLine();
    }
}
