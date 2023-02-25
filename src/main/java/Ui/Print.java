package Ui;

import duke.Task;
import java.util.ArrayList;

public class Print {
    protected String description;
    public final static String lineBreak = "____________________________________________________________\n";

    public Print (String description){
        this.description=description;
    }
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

    public void printEmpty () {

        System.out.println(lineBreak
                + "OOPS!!! The description of a todo cannot be empty.\n"
                + lineBreak);

    }

    public void printException () {
        System.out.println("Something went wrong\n"
                + lineBreak);
    }

    public void printTaskAdded (ArrayList<Task> tasks , int i) {
        System.out.println(lineBreak
                + "Got it. I've added this task:\n" +
                "  " + tasks.get(i) + "\n" +
                "Now you have " + (i + 1) + " tasks in the list.\n"
                + lineBreak);
    }

    public void printUnMark(ArrayList<Task> tasks , int index_for_mark) {
        System.out.println(lineBreak
                + "OK, I've marked this task as not done yet:\n"
                + tasks.get(index_for_mark - 1) + "\n"
                + lineBreak);
    }

    public void printMark (ArrayList<Task> tasks , int index_for_mark) {
        System.out.println(lineBreak
                + "Nice! I've marked this task as done:\n"
                + tasks.get(index_for_mark - 1) + "\n"
                + lineBreak);
    }
    public void printDelete (ArrayList<Task> tasks , int index_for_delete, int i) {
        System.out.println(lineBreak
                + " Noted. I've removed this task:\n" +
                "  " + tasks.get(index_for_delete - 1) + "\n" +
                "Now you have " + (i - 1) + " tasks in the list.\n"
                + lineBreak);
    }
    public void printFind (ArrayList<Task> foundTasks,int count) {
        System.out.println(lineBreak
                + " Here are the matching tasks in your list: " );

        for (int m = 0; m < count; m += 1) {
            int index = m + 1;

            System.out.println(index + "." + foundTasks.get(m));

        }
        System.out.println(lineBreak);
    }
}









