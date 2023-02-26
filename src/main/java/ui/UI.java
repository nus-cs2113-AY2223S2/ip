package ui;


import exception.IncompleteInputException;
import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private static final int LINE_LENGTH = 50;
    private  final Scanner scanner;
    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Gets the user command from the terminal
     * @return The complete input line
     */
    public String getCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a line with a length of LINE_LENGTH
     */
    public void printLine() {
        System.out.println("\t " + "_".repeat(LINE_LENGTH));
    }

    /**
     * Prints the greeting at the start of the program
     */
    public void printGreeting() {
        printLine();
        System.out.println("\t Hello I'm Duke, your personal chatbot.");
        System.out.println("\t Is there anything I can do for you");
        printLine();
    }

    /**
     * Prints the task that has been successfully added and the current total number of tasks
     * @param task The task that has been added
     * @param taskCount The total number of tasks
     */
    public void printAddTaskSuccess(Task task, int taskCount) {
        printLine();
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\t Now you have " + taskCount + " task(s) in your list.");
        printLine();
    }

    /**
     * Prints the task that has been successfully removed and the current total number of tasks
     * @param task The task that has been removed
     * @param taskCount The total number of tasks
     */
    public void printRemoveTaskSuccess(Task task, int taskCount) {
        printLine();
        System.out.println("\t Noted. I've removed this task: ");
        System.out.println("\t " + task);
        System.out.println("\t Now you have " + taskCount + " task(s) in your list");
        printLine();
    }

    /**
     * Prints all the tasks that is being specified
     * @param tasks The tasks to be printed
     */
    public void printList(ArrayList<Task> tasks) {
        printLine();
        if (tasks.size() ==0) {
            System.out.println("\t Seems like you do not have any task at the moment...");
        } else {
            System.out.println("\t Here are the tasks for your list: ");
            int i =1;
            for (Task t: tasks) {
                System.out.println("\t " + i++ + "." + t.toString());
            }
        }
        printLine();
    }

    /**
     * Prints all the found tasks specified
     * @param tasks The found tasks to be printed
     */
    public void printFoundTasks(ArrayList<Task> tasks) {
        printLine();
        if (tasks.size() ==0) {
            System.out.println("\t There is no task matching your description");
        } else {
            System.out.println("\t Here are the matching tasks for your list: ");
            int i =1;
            for (Task t: tasks) {
                System.out.println("\t " + i++ + "." + t.toString());
            }
        }
        printLine();
    }

    /**
     * Prints the task that has just been successfully marked.
     * @param task The marked task
     */
    public void printMarkSuccess (Task task) {
        printLine();
        System.out.println("\t Nice, I have marked this task as done: ");
        System.out.println("\t " + task);
        printLine();
    }

    /**
     * Prints the task that has just been successfully unmarked.
     * @param task The unmarked task
     */
    public void printUnmarkSuccess (Task task)  {
        printLine();
        System.out.println("\t Ouch, I have unmarked this task: ");
        System.out.println("\t " + task);
        printLine();
    }

    /**
     * Prints goodbye line when user exits the program
     */
    public void printBye() {
        printLine();
        System.out.println("\t Bye! Do let me know if you need any further assistance");
        printLine();
    }

    /**
     * Prints error associated with saving
     * @param ex The IOException to be printed
     */
    public void printSavingError(java.io.IOException ex) {
        printLine();
        System.out.println("Exception Occured: " + ex);
        System.out.println("Failed to save your file");
        printLine();
    }

    /**
     * Prints the error
     * @param ex The Exception to be printed
     */
    public void printError(Exception ex) {
        printLine();
        System.out.println("Exception Occured: " + ex);
        printLine();
    }



}
