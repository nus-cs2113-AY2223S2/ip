package duke.ui;

import duke.data.TaskList;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents UI object that handles all interactions with the user
 */
public class UI {
    /**
     * Gets the input commands from the user
     *
     * @return the full input command
     */
    public String getInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    public void printDottedLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints all current tasks in task list
     *
     * @param tasks the task list to be printed
     */
    public void printAllTasks(TaskList tasks) {
        printDottedLine();
        int counter = 1;
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> taskList = tasks.getTaskList();
        for (Task task : taskList) {
            if (task != null) {
                System.out.println(counter + "." + task);
                counter++;
            }
        }
        printDottedLine();
    }

    /**
     * Print hello statement when user starts up programme
     */
    public void printHelloStatement() {
        printDottedLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printDottedLine();
    }

    /**
     * Print bye statement when user ends programme
     */
    public void printByeStatement() {
        printDottedLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printDottedLine();
    }

    /**
     * Prints the task that has been marked or unmarked by user
     *
     * @param curTask the task to be marked or unmarked
     * @param status  the current status of the task
     */
    public void printTaskStatusStatement(Task curTask, String status) {
        printDottedLine();
        if (status.equals("mark")) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println(curTask);
        printDottedLine();
    }

    /**
     * Prints the task that had been most recently added by the user, and the current number
     * of tasks in the task list
     *
     * @param tasks the current task list
     * @param task  the task that had been added
     */
    public void printTaskAddedStatement(TaskList tasks, Task task) {
        printDottedLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + task);
        if (tasks.sizeOfTaskList() == 1) {
            System.out.println(" Now you have " + tasks.sizeOfTaskList() + " task in the list.");
        } else {
            System.out.println(" Now you have " + tasks.sizeOfTaskList() + " tasks in the list.");
        }
        printDottedLine();
    }

    /**
     * Prints the task that had been most recently deleted by the user, and the current number
     * of tasks in the task list
     *
     * @param tasks the current task list
     * @param task  the task that had been deleted
     */
    public void printTaskDeletedStatement(TaskList tasks, Task task) {
        printDottedLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println(" " + task);
        if (tasks.sizeOfTaskList() == 1) {
            System.out.println(" Now you have " + (tasks.sizeOfTaskList() - 1) + " task in the list.");
        } else {
            System.out.println(" Now you have " + (tasks.sizeOfTaskList() - 1) + " tasks in the list.");
        }
        printDottedLine();
    }

    public void findTask(TaskList tasks, String input) {
        printDottedLine();
        ArrayList<Task> taskList = tasks.getTaskList();
        int index = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : taskList) {
            if (task.getDescription().contains(input)){
                System.out.println(index + "." + task);
                index++;
            }
        }
        printDottedLine();
    }
}

