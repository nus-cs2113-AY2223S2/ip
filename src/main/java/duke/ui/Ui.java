package duke.ui;

import duke.tasklist.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;

/**
 * Represents the class that deals with interactions with the user.
 */
public class Ui {
    public static final String LINE = "_______________________________________________________________________";


    /**
     * This documents the types of user commands
     * or possible interactions with the user.
     */
    public enum CommandType {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, INDEXOUTOFBOUNDS, FIND, DUKEEXCEPTION,
        NULLPOINTER, IOEXCEPTION, FILENOTFOUND, RUNTIMEEXCEPTION, NUMBERFORMAT
    }

    /**
     * Prints message related to a task, depending on the action done by user.
     * This includes adding task-to-do, deadline, event, delete task,
     * mark and unmark task.
     * @param task The task specified by user.
     * @param commandType The type of command.
     */
    public static void printMessage(Task task, CommandType commandType) {
        System.out.println(LINE);
        switch(commandType) {
        case TODO:
            System.out.println("Gotcha, this task has been added to the list: ");
            System.out.println(task.toString());
            System.out.println("There are " + (TaskList.getNumTasks()+1) + " tasks in your list :)");
            break;
        case DEADLINE:
            System.out.println("Gotcha, this deadline has been added to the list: ");
            System.out.println(task.toString());
            System.out.println("There are " + (TaskList.getNumTasks()+1) + " tasks in your list :)");
            break;
        case EVENT:
            System.out.println("Gotcha, this event has been added to the list: ");
            System.out.println(task.toString());
            System.out.println("There are " + (TaskList.getNumTasks()+1) + " tasks in your list :)");
            break;
        case DELETE:
            System.out.println("Okay, I have deleted this task: ");
            System.out.println(task.toString());
            System.out.println("There are " + (TaskList.getNumTasks()) + " tasks in your list :)");
            break;
        case MARK:
            System.out.println("Gotcha, this task has been marked done: ");
            System.out.println(task.toString());
            break;
        case UNMARK:
            System.out.println("Gotcha, this task has been unmarked. Remember to complete it: ");
            System.out.println(task.toString());
            break;
        }
        System.out.println(LINE);
    }

    /**
     * Prints message related to finding a task using keywords.
     * @param query The keywords that user wants to search.
     * @param commandType The type of command.
     */
    public static void printMessage(String query, CommandType commandType) {
        System.out.println(LINE);
        switch(commandType) {
        case FIND:
            System.out.println("Here are the matching tasks in your list:");
            TaskList.findTasks(query);
            break;
        default:
            System.out.println("Oops.. something went wrong.");
        }
        System.out.println(LINE);
    }

    /**
     * Prints message related to other general commands.
     * This includes list, and IndexOutOfBoundsException.
     * @param commandType The type of command.
     */
    public static void printMessage(CommandType commandType) {
        System.out.println(LINE);
        switch(commandType) {
        case LIST:
            if (TaskList.getTasks().isEmpty()) {
                System.out.println("Your task list is empty. Time to add some tasks!");
            } else {
                System.out.println("Here are the tasks in your list: ");
                printList(TaskList.getTasks());
            }
            break;
        case INDEXOUTOFBOUNDS:
            System.out.println("Oops.. I don't know what this means :(");
            break;
        case DUKEEXCEPTION:
            System.out.println("Invalid input format... try again. The description cannot be empty.");
            break;
        case NULLPOINTER:
            System.out.println("Can't remember what was saved :(");
            break;
        case IOEXCEPTION:
            System.out.println("IOException here.. Process interrupted and I don't have the ability to execute :(");
            break;
        case FILENOTFOUND:
            System.out.println("I can't seem to find an existing list :( Creating a new one now.");
            break;
        case RUNTIMEEXCEPTION:
            System.out.println("I can't find what you want :( try something else");
            break;
        case NUMBERFORMAT:
            System.out.println("Missing a number.. Key in the index of the task!!");
            break;
        }
        System.out.println(LINE);
    }

    /**
     * Prints hello message to greet user at the start of the program.
     */
    public static void printGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE + '\n' + logo);
        System.out.println("Hello! i'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Prints the list of task.
     * @param tasks The list of tasks to be printed.
     */
    public static void printList(ArrayList<Task> tasks) {
        tasks.trimToSize();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            } else {
                System.out.println((i+1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Prints bye message to the user.
     */
    public static void printBye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE + '\n');
    }
}
