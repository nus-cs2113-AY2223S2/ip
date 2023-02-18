package Output;

import java.util.Scanner;

import Entities.Task;
import Entities.TaskList;
import Exceptions.DukeException;

public class UI {
    public static Scanner sc = new Scanner(System.in);

    public String readCommand() {
        String command = "";
        while (command.length() == 0) {
            command = sc.nextLine();
        }
        return command;
    }

    public void printIntroduction() {
        printLine();
        System.out.println("\tHello! I'm Duke. Hope you are having a wonderful day");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    public void printGoodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public void printLine() {
        System.out.println("\t------------------------------------------------------------");
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void taskAddedMessage(Task t, int numTasks) {
        System.out.println(
                "\tGot it. I have added this task to the list!\n" + 
                "\t   " + t.toString() + "\n" + 
                "\tNow you have " + numTasks + 
                " task" + (numTasks == 1 ? "" : "s") + 
                " in the list.\n"
        );
    }

    public void printTasks(TaskList tasks) throws DukeException {
        String output = "";

        if (tasks.getTasksCount() == 0) {
            output += "\t No tasks found!\n";
        } else {
            output += "\t Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.getTasksCount(); i++) {
                output += "\t" + (i + 1) + "." + tasks.getTask(i).toString() + "\n";
            }
        }

        System.out.printf(output);
    }

    public void taskDeletedMessage(Task t, int numTasks) {
        System.out.println(
                "\tNoted. I've removed this task:\n" + 
                "\t   " + t.toString() + "\n" + 
                "\tNow you have " + numTasks + 
                " task" + (numTasks == 1 ? "" : "s") + 
                " in the list.\n"
        );
    }

    public void printMarkedTask(Task t) {
        System.out.println( 
                "\tNice! I've marked this task as done:\n" + 
                "\t   " + t.toString() + "\n"
        );
    }

    public void printUnmarkedTask(Task t) {
        System.out.println( 
                "\tOK, I've marked this task as not done yet:\n" + 
                "\t   " + t.toString() + "\n"
        );
    }
}
