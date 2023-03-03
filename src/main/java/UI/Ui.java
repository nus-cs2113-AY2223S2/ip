package UI;

import java.util.ArrayList;

import Task.Task;
import Task.TaskList;

public class Ui {
    final static String LINEBREAK = "______________________________________________________";
    final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    final static String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    final static String EXIT_MESSAGE = "Bye. Hope to see you again soon.";
    final static String UNRECOGNIZED_ACTION = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    final static String INCOMPLETE_ACTION = "Please provide the necessary information for the task.";

    final static String LIST = "list";
    final static String MARK = "mark";
    final static String UNMARK = "unmark";
    final static String TODO = "todo";
    final static String DEADLINE = "deadline";
    final static String EVENT = "event";
    final static String DELETE = "delete";

    final static String LIST_MESSAGE = "Here are the tasks in the list:";
    final static String MARK_MESSAGE = "Nice! I've marked this task as done:";
    final static String UNMARK_MESSAGE = "OK, I've marked this task as not done yet:";
    final static String ADD_TASK_MESSAGE = "Got it. I've added this task:";
    final static String DELETE_TASK_MESSAGE = "Noted. I've removed this task:";

    public final static void greet() {
        System.out.println(LOGO + "\n" + WELCOME_MESSAGE);
    }

    public final static void bye() {
        System.out.println(LINEBREAK + "\n" + EXIT_MESSAGE + "\n" + LINEBREAK);
    }

    public static void printList(ArrayList<Task> tasks) {
        String output = LIST_MESSAGE + System.lineSeparator()
                + TaskList.printTasksList(tasks);
        System.out.println(LINEBREAK);
        System.out.println(output);
        System.out.println(LINEBREAK);
    }

    public static void markMessage(ArrayList<Task> tasks, int index) {
        System.out.println(LINEBREAK);
        System.out.println(MARK_MESSAGE);
        System.out.println(tasks.get(index).toString());
        System.out.println(LINEBREAK);
    }

    public static void unmarkMessage(ArrayList<Task> tasks, int index) {
        System.out.println(LINEBREAK);
        System.out.println(UNMARK_MESSAGE);
        System.out.println(tasks.get(index).toString());
        System.out.println(LINEBREAK);
    }

    public static void printConfirmation(Task newTask, String action) {
        System.out.println(LINEBREAK);
        switch (action) {
            case "TODO":
            case "DEADLINE":
            case "EVENT":
                System.out.println(ADD_TASK_MESSAGE);
                System.out.println(newTask.toString());
                break;

            case "DELETE":
                System.out.println(DELETE_TASK_MESSAGE);
                System.out.println(newTask.toString());
                break;

            default:
                System.out.println(UNRECOGNIZED_ACTION);
        }
        System.out.println("Now you have " + Task.numberOfTasks + " tasks in the list");
        System.out.println(LINEBREAK);

    }

    public static void printIncompleteMessage() {
        System.out.println(INCOMPLETE_ACTION);
        System.out.println(LINEBREAK);
    }

}
