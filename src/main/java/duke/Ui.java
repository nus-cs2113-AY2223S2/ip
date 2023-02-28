package duke;

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
            "     Enter \"list\" to obtain a list of all your tasks!.\n";

    public static final String LOGO =
            "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String BYE = "     Bye. Hope to see you again soon!\n";

    public static final String ERROR = "    Invalid command! :( Check your input and try again! \n" +
            "    Type 'help' to view list of command formats.\n";

    public static void showWelcome() {
        System.out.print(LINE + LOGO + GREET + HELP + LINE);
    }

    public static void showHelp() {
        System.out.print(LINE + HELP + LINE);
    }

    public static void sayBye(){
        System.out.print(LINE + BYE + LINE);
    }

    public static void printError(){
        System.out.print(LINE + ERROR + LINE);
    }
    public static void showAddedTask(ArrayList<Task> tasks){
        System.out.print(LINE + "    Got it. I've added this task:\n      " +
                tasks.get(tasks.size()-1).toString() + "\n    Now you have " + tasks.size() + " tasks in the list.\n" +
                LINE);
    }

    public static void printList(ArrayList<Task> tasks, String[] words) throws ExcessInputsException {
        if (words.length > 1) {
            throw new ExcessInputsException();
        }
        System.out.println(LINE + "Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("    " + i + "." + tasks.get(i-1).toString());
        }
        System.out.print(LINE);
    }

    public static String readInput(){
        return in.nextLine();
    }

    public static String initialiseScanner(){
        String line;
        in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }
}
