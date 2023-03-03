package duke;

import duke.exceptions.EmptyFindException;
import duke.exceptions.EmptyListException;
import duke.exceptions.ExcessInputsException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static Scanner in;
    public static final String LINE = "    ____________________________________________________________\n";
    public static final String GREET = "     Hello! I'm Duke\n" +
            "     Welcome to Your To-do List!\n";
    public static final String HELP = "     Enter \"todo 'task-name'\" to add a task.\n" +
            "     Enter \"deadline 'task-name' /by 'deadline'\" to add a task with a deadline.\n" +
            "     Enter \"event 'task-name' /from 'start-date' /to 'end-date'\" to add a task with start and end dates.\n" +
            "     Enter \"mark 'task-index'\" to mark a task as done.\n" +
            "     Enter \"unmark 'task-index'\" to mark a task as not done yet.\n" +
            "     Enter \"delete 'task-index'\" to delete a task from the list.\n" +
            "     Enter \"list\" to obtain a list of all your tasks!.\n" +
            "     Enter \"find 'string'\" to obtain a list of tasks containing the string you input.\n" +
            "     Enter \"help\" to view this list of instruction again.\n" +
            "     Enter \"bye\" to exit the program. (Your program will also be saved whenever a change is made.)\n";

    public static final String LOGO =
            "     |  _ \\ _   _| | _____ \n"
                    + "     | | | | | | | |/ / _ \\\n"
                    + "     | |_| | |_| |   <  __/\n"
                    + "     |____/ \\__,_|_|\\_\\___|\n";
    public static final String BYE = "     Bye. Hope to see you again soon!\n";

    public static final String ERROR = "    Invalid command! :( Check your input and try again! \n" +
            "    Type 'help' to view list of command formats.\n";

    /**
     * Prints the logo of Duke, greetings as well as the command list.
     */
    public static void showWelcome() {
        System.out.print(LINE + LOGO + GREET + HELP + LINE);
    }

    /**
     * Print the command list.
     */
    public static void showHelp() {
        System.out.print(LINE + HELP + LINE);
    }

    /**
     * Prints the exit message.
     */
    public static void sayBye(){
        System.out.print(LINE + BYE + LINE);
    }

    /**
     * Prints invalid command error message.
     */
    public static void printError(){
        System.out.print(LINE + ERROR + LINE);
    }

    /**
     * Prints a message showing that the current task has been added.
     * @param tasks the task list.
     */
    public static void showAddedTask(ArrayList<Task> tasks){
        System.out.print(LINE + "    Got it. I've added this task:\n      " +
                tasks.get(tasks.size()-1).toString() + "\n    Now you have " + tasks.size() + " tasks in the list.\n" +
                LINE);
    }

    /**
     * Print the task list.
     * @param tasks the task list.
     * @param words task description from user input.
     * @throws ExcessInputsException if an excess param was input with the list command.
     * @throws EmptyListException if current task list is empty.
     */
    public static void printList(ArrayList<Task> tasks, String[] words) throws ExcessInputsException, EmptyListException {
        if (words.length > 1) {
            throw new ExcessInputsException();
        }
        if (tasks.size() < 1) {
            throw new EmptyListException();
        }
        System.out.println(LINE + "    Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("    " + i + "." + tasks.get(i-1).toString());
        }
        System.out.print(LINE);
    }

    /**
     * Prints the list of tasks with description that contains the keyword.
     * @param tasks list of task with description that contains the keyword
     * @throws EmptyFindException there is no matching task.
     */
    public static void printMatchingList(ArrayList<Task> tasks) throws EmptyFindException {
        if (tasks.size() < 1) {
            throw new EmptyFindException();
        }
        System.out.println(LINE + "    Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("    " + i + "." + tasks.get(i-1).toString());
        }
        System.out.print(LINE);
    }

    /**
     * Returns the line of user input.
     * @return String of user input.
     */
    public static String readInput(){
        return in.nextLine();
    }

    /**
     * Starts a scanner that reads user input. Returns the user input.
     * @return String of user input.
     */
    public static String initialiseScanner(){
        String line;
        in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }
}
