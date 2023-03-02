package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public static final Scanner in = new Scanner(System.in);

    public void printAllTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("No tasks in the list.");
            return;
        }

        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(count + "." + task);
            count += 1;
        }
    }

    public void printFoundTasks(ArrayList<Task> foundTasks) {
        if (foundTasks.size() == 0) {
            System.out.println("No tasks in the list.");
            return;
        }

        int count = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : foundTasks) {
            System.out.println(count + "." + task);
            count += 1;
        }
    }

    public void printTask(Task task) {
        System.out.println(task);
    }

    public void printAddTask() {
        System.out.println("Got it. I've added this task:");
    }

    public void printMarkDone() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public void printMarkNotDone() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public void printDeleteTask() {
        System.out.println("Noted. I've removed this task:");
    }

    public void printNoOfTasks(int size) {
        System.out.println("Now you have " + size + " tasks in the list");
    }

    public void printInvalidMessage() {
        System.out.println("I don't know what that means :-(");
    }

    public void printInvalidFileRead() {
        System.out.println("Unable to read from file");
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
}
