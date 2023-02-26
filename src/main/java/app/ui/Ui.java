package app.ui;

import app.exceptions.DukeException;
import app.tasks.Task;
import app.tasks.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to handle all interactions with the user.
 */
public class Ui {
    public static Scanner sc = new Scanner(System.in);

    /**
     * Method to take in the input from a user.
     * @return The input from the user.
     */
    public String readCommand() {
        String command = "";
        while (command.length() == 0) {
            command = sc.nextLine();
        }
        return command;
    }

    /**
     * Method to print welcome message after initialisation of Duke.
     */
    public void showWelcome() {
        Constants.printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Constants.printLine();
    }

    /**
     * Method to print divider.
     */
    public void showLine() {
        Constants.printLine();
    }

    /**
     * Method to inform user upon initialisation that there are no existing Tasks.
     */
    public void showLoadingError() {
        System.out.println("No saved tasks detected! Creating a new list.");
    }

    /**
     * Method to print exit message before exiting Duke.
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Method to print an error message in-case any Exception occurs during the running of Duke.
     * @param errorMessage The specific message to be displayed to the user.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Method to display successful adding of Task to the Task-list.
     * @param newTask The new Task that was just added.
     * @param tasksCount The current size of the Task-list.
     */
    public void newTaskAddedMessage(Task newTask, int tasksCount) {
        System.out.println("Got it. I've added this task:\n" + newTask.toString() +
                "\nYou now have " + tasksCount + " tasks in your list.");
    }

    /**
     * Method to display all the current Tasks with their attributes in a list format.
     * @param tasks The current Task-list.
     * @throws DukeException If there is an error in accessing a specific index.
     */
    public void printTasks(TaskList tasks) throws DukeException {
        if (tasks.getTasksCount() == 0){
            System.out.println("There are no tasks in your task list.");
        }
        for (int i = 0; i < tasks.getTasksCount(); i++) {
            String taskDescription = tasks.getTask(i).toString();
            System.out.println((i + 1) + ". " + taskDescription);
        }
    }

    /**
     * Method to display successful deletion of a Task from the Task-list.
     * @param deletedTask The Task that was just deleted.
     * @param tasksCount The current size of the Task-list.
     */
    public void taskDeletedMessage(Task deletedTask, int tasksCount) {
        System.out.println("Noted, I've removed this task.\n" + deletedTask.toString() +
                "\nYou now have " + tasksCount + " tasks left in your list.");
    }

    /**
     * Method to display successful marking of a Task as done.
     * @param markedTask The Task that was marked as done.
     */
    public void taskMarkedMessage(Task markedTask) {
        System.out.println("Nice! I've marked this task as done:\n" +
                markedTask.toString());
    }

    /**
     * Method to display successful unmarking of a Task as not done yet.
     * @param unmarkedTask The Task that was unmarked as not done yet.
     */
    public void taskUnmarkedMessage(Task unmarkedTask) {
        System.out.println("OK! I've marked this task as not done yet:\n" +
                unmarkedTask.toString());
    }

    public void printTasksWithKeyword(ArrayList<Task> tasksWithKeyword) {
        if (tasksWithKeyword.size() == 0){
            System.out.println("There are no tasks containing that keyword.");
        }
        else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasksWithKeyword.size(); i++) {
                String taskDescription = tasksWithKeyword.get(i).toString();
                System.out.println((i + 1) + ". " + taskDescription);
            }
        }
    }
}
