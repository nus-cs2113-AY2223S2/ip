package components;

import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class UI {
    public UI() {
    }

    private static final String LOGO =
            " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
    }

    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void greet() {
        printWelcomeMessage();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        printHorizontalLine();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void showLoadingError() {
        printHorizontalLine();
        System.out.println("Could not find any Duke.txt but don't worry. One will be created later");
        printHorizontalLine();
    }

    public String readCommand(Scanner in) {
        return in.nextLine();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void taskAdded(ArrayList<Task> tasks) {
        boolean isLessThanOne = (tasks.size() <= 1);
        System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1) +
                "\nNow you have " + tasks.size() + (isLessThanOne ? " task" : " tasks") + " in the list.");
    }

    public void taskRemoved(ArrayList<Task> tasks, Task t) {
        boolean isLessThanOne = (tasks.size() <= 1);
        System.out.println("Got it. I've removed this task:\n" + t.toString() +
                "\nNow you have " + tasks.size() + (isLessThanOne ? " task" : " tasks") + " in the list.");
    }

    public void taskMarked() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public void taskUnmarked() {
        System.out.println("Ok, I've marked this task as not done yet:");
    }

    public void printTask(TaskList tasks, int taskNumber) {
        System.out.println(tasks.tasks.get(taskNumber - 1));
    }

    public void printTaskInList(TaskList tasks, Task task) {
        System.out.println((tasks.tasks.indexOf(task) + 1) + "." + task);
    }
}
