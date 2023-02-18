package duke;

import task.Task;

import java.util.ArrayList;

/*
 * This class contains the methods responsible for printing all
 * the outputs of Duke.
 *
 **/
public class PrintOperations {

    static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void horizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void greet() {
        horizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        horizontalLine();
    }

    public static void bye() {
        horizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }

    public static void addTask(Task task) {
        System.out.println("Got it. I've added this task: \n" + task);
    }

    public static void list(ArrayList<Task> listOfInputs) {
        int counter = 0;

        if (listOfInputs.size() == 0) {
            System.out.println("Your search returned no results.");
            return;
        }

        for (Task inputs : listOfInputs) {
            System.out.println(counter + 1 + "." + inputs.toString());
            counter++;
        }
    }

    public static void bulletedList(ArrayList<Task> listOfFilteredInputs) {
        if (listOfFilteredInputs.size() == 0) {
            System.out.println("List empty!");
            return;
        }

        for (Task inputs : listOfFilteredInputs) {
            System.out.println("- " + inputs.toString());
        }
    }

    public static void numberOfTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    public static void taskRemoved(int taskIndex, ArrayList<Task> tasks) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskIndex + 1 + "." + tasks.get(taskIndex).toString());
    }

    public static void illegalCommand() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void alreadyMarked() {
        System.out.println("☹ OOPS!!! This task already has this marking :-(");
    }

    public static void illegalCharacter() {
        System.out.println("☹ OOPS!!! Please do not have '|' in your input :-(");
    }

    public static void illegalSyntax() {
        System.out.println("☹ OOPS!!! I'm sorry, You entered an incorrect syntax :-(");
    }

    public static void doesNotExist() {
        System.out.println("☹ OOPS!!! This task does not exist :-(");
    }

    public static void io() {
        System.out.println("☹ OOPS!!! Read/write file error :-(");
    }
}
