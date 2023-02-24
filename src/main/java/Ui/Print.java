package Ui;

import duke.Task;
import java.util.ArrayList;

public class Print {
    protected String description;
    public Print (String description){
        this.description=description;
    }
    public void List (ArrayList<Task> tasks , int i) {

            System.out.println("____________________________________________________________\n" +
                    "Here are the tasks in your list:");

            for (int m = 0; m < i; m += 1) {
                int index = m + 1;

                System.out.println(index + "." + tasks.get(m));

            }
            System.out.println("____________________________________________________________");
    }

    public void Greeting() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        String greeting = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(greeting);

    }
    public void Bye () {

        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n");

    }

    public void printEmpty () {

        System.out.println("____________________________________________________________\n"
                + "OOPS!!! The description of a todo cannot be empty.\n"
                + "____________________________________________________________\n");

    }

    public void printException () {
        System.out.println("Something went wrong\n"
                + "____________________________________________________________");
    }

    public void printTaskAdded (ArrayList<Task> tasks , int i) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n" +
                "  " + tasks.get(i) + "\n" +
                "Now you have " + (i + 1) + " tasks in the list.\n"
                + "____________________________________________________________\n");
    }

    public void printUnMark(ArrayList<Task> tasks , int index_for_mark) {
        System.out.println("____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n"
                + tasks.get(index_for_mark - 1) + "\n"
                + "____________________________________________________________\n");
    }

    public void printMark (ArrayList<Task> tasks , int index_for_mark) {
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + tasks.get(index_for_mark - 1) + "\n"
                + "____________________________________________________________\n");
    }
    public void printDelete (ArrayList<Task> tasks , int index_for_delete, int i) {
        System.out.println("____________________________________________________________\n"
                + " Noted. I've removed this task:\n" +
                "  " + tasks.get(index_for_delete - 1) + "\n" +
                "Now you have " + (i - 1) + " tasks in the list.\n"
                + "____________________________________________________________\n");
    }

}







