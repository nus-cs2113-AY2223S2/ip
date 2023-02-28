package io;

import tasks.Task;

import java.util.ArrayList;

/**
 * A tool to print messages to the terminal. Used by Duke chatbot.
 */
public class DukeNUSPrinter {
    public static final String LOGO = ""
            + "     ____        _          __    _ _   _ ______\n"
            + "    |  _ \\ _   _| | _____  |   \\ | | | | | _____|\n"
            + "    | | | | | | | |/ / _ \\ | |\\ \\| | | | |_____ |\n"
            + "    | |_| | |_| |   <  __/ | | \\ \\ | |_| |____| | \n"
            + "    |____/ \\__,_|_|\\_\\___| |_|  \\__|_____|______|\n";
    public static final String INDENT = "    ";

    /**
     * Prints a horizontal line made of underscores to the terminal.
     */
    public static void printHorizontalLine() {
        System.out.println(INDENT + "____________________________________________________________");
    }

    /**
     * Prints a DukeNUS message bound by horizontal lines above and below to the console. Only single lines should be used.
     *
     * @param message The single line message to be bound by horizontal lines
     */
    public static void printMessage(String message) {
        printHorizontalLine();
        System.out.println(INDENT + message);
        printHorizontalLine();
    }

    /**
     * Prints a message to the console whenever a task is added to the tasks array.
     *
     * @param taskDisplayDescription The formatted description of the task with indications of its isDone and Task object type.
     */
    public static void printAddedTask(String taskDisplayDescription, int totalTaskCount) {
        printHorizontalLine();
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + taskDisplayDescription);
        System.out.println(INDENT + "You now have " + totalTaskCount + " tasks in the list.");
        printHorizontalLine();
    }

    /**
     * @param taskDisplayDescription The description of the task, its type and status.
     */
    public static void printDeletedTask(String taskDisplayDescription) {
        printHorizontalLine();
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + taskDisplayDescription);
        printHorizontalLine();
    }

    /**
     * Prints a message to the console whenever a task is marked.
     *
     * @param taskDisplayDescription The formatted description of the task with indications of its isDone and Task object type.
     */
    public static void printMarkedTask(String taskDisplayDescription) {
        printHorizontalLine();
        System.out.println(INDENT + "Congratulations! You have finished this task:");
        System.out.println(INDENT + taskDisplayDescription);
        printHorizontalLine();
    }

    /**
     * Prints a message to the console whenever a task is unmarked.
     *
     * @param taskDisplayDescription The formatted description of the task with indications of its isDone and Task object type
     */
    public static void printUnmarkedTask(String taskDisplayDescription) {
        printHorizontalLine();
        System.out.println(INDENT + "The following task is now marked as undone:");
        System.out.println(INDENT + taskDisplayDescription);
        printHorizontalLine();
    }

    /**
     * Prints all tasks in the tasks array that is not null. To be used by the list command.
     *
     * @param tasks     The array of tasks. The array used in practice is of fixed size 100.
     */
    public static void listTasks(ArrayList<Task> tasks) {
        printHorizontalLine();
        System.out.println(INDENT + "Here are the tasks in your list:");
        printTasks(tasks);
        printHorizontalLine();
    }
    public static void showSearchResults(ArrayList<Task> tasks) {
        printHorizontalLine();
        System.out.println(INDENT + "Here are the matching tasks in your list:");
        printTasks(tasks);
        printHorizontalLine();
    }
    private static void printTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println(INDENT + (i + 1) + '.' + tasks.get(i).getTaskString());
        }
    }

    public static void printWelcomeMessage() {
        printMessage("Hello from\n" + LOGO + INDENT + "Hello! I'm Duke-NUS Medical School. \n" + INDENT + "What can I do for you?");
    }

    public static void printFarewellMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }


}
