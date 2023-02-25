package Ui;

import duke.Task;
import java.util.ArrayList;

public class Print {

    public final static String lineBreak = "____________________________________________________________\n";

    public Print () {

    }

    /**
     * Displays the list of tasks
     * @param tasks is the task list
     * @param i is the number of tasks in the task list currently
     */

    public void List (ArrayList<Task> tasks , int i) {

            System.out.println(lineBreak+ "Here are the tasks in your list:");

            for (int m = 0; m < i; m += 1) {
                int index = m + 1;

                System.out.println(index + "." + tasks.get(m));
            }
            System.out.println(lineBreak);
    }

    public void Greeting() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        String greeting = lineBreak
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                +lineBreak;

        System.out.println(greeting);

    }
    public void Bye () {

        System.out.println(lineBreak
                + " Bye. Hope to see you again soon!\n"
                + lineBreak);

    }

    // If the description of the task is empty, it will display this error message
    public void printEmpty () {

        System.out.println(lineBreak
                + "OOPS!!! The description of a todo cannot be empty.\n"
                + lineBreak);

    }

    // If there is an exception, it will display this error message
    public void printException () {
        System.out.println("Something went wrong\n"
                + lineBreak);
    }

    /**
     * Displays a message to show the task added.
     * @param tasks is the task list
     * @param i is the number of tasks in the task list currently
     */

    public void printTaskAdded (ArrayList<Task> tasks , int i) {
        System.out.println(lineBreak
                + "Got it. I've added this task:\n" +
                "  " + tasks.get(i) + "\n" +
                "Now you have " + (i + 1) + " tasks in the list.\n"
                + lineBreak);
    }

    /**
     * Displays the tasks that has been chosen to be unmarked
     * @param tasks is the task list
     * @param indexToMark is the number of tasks in the task list currently
     */

    public void printUnMark(ArrayList<Task> tasks , int indexToMark) {
        System.out.println(lineBreak
                + "OK, I've marked this task as not done yet:\n"
                + tasks.get(indexToMark - 1) + "\n"
                + lineBreak);
    }

    /**
     * Displays the tasks that has been chosen to be marked
     * @param tasks is the task list
     * @param indexToMark is the number of tasks in the task list currently
     */

    public void printMark (ArrayList<Task> tasks , int indexToMark) {
        System.out.println(lineBreak
                + "Nice! I've marked this task as done:\n"
                + tasks.get(indexToMark - 1) + "\n"
                + lineBreak);
    }

    /**
     * Displays the tasks that has been chosen to be deleted
     * @param tasks is the task list
     * @param indexToDelete is the number of tasks in the task list currently
     * @param i is the number of tasks in the task list currently
     */

    public void printDelete (ArrayList<Task> tasks , int indexToDelete, int i) {
        System.out.println(lineBreak
                + " Noted. I've removed this task:\n" +
                "  " + tasks.get(indexToDelete - 1) + "\n" +
                "Now you have " + (i - 1) + " tasks in the list.\n"
                + lineBreak);
    }

    /**
     * Displays the tasks that match the keyword given
     * @param foundTasks is the list of tasks that match the keyword given
     * @param count is the number of tasks that match the keyword given
     */
    public void printFind (ArrayList<Task> foundTasks, int count) {

        System.out.println(lineBreak
                + " Here are the matching tasks in your list: " );

        for (int m = 0; m < count; m += 1) {
            int index = m + 1;
            System.out.println(index + "." + foundTasks.get(m));
        }

        System.out.println(lineBreak);
    }
}









