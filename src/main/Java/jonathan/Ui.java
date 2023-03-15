package jonathan;

import task.Task;
import task.TaskList;

import java.util.Scanner;

public class Ui {

    public String readCommand() {
        Scanner input = new Scanner((System.in));
        String fullCommand = input.nextLine();
        return fullCommand;
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("    Hello! I'm Jonathan");
        System.out.println("    What can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public void showAdded(TaskList tasks, Task task) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showDelete(TaskList tasks, Task task) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    " + task);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showAllTasks(TaskList tasks) {
        if (tasks.size() > 0) {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i+1) + ". "  + tasks.get(i));
            }
        } else {
            System.out.println("    You don't have any tasks!");
        }
    }

    public void showMarked(Task task) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public void showUnmarked(Task task) {
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("    " + task);
    }

    public void showMatchedTask(TaskList tasks) {
        if (tasks.size() > 0) {
            System.out.println("    Here are the matching tasks in your list:");

            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i+1) + ". "  + tasks.get(i));
            }
        } else {
            System.out.println("    No mathcing tasks for those keyword");
        }

    }

    public void showWrongFormat() {
        System.out.println("    Please type with he following format:\n");
        System.out.println("    - todo <description>");
        System.out.println("    - deadline <description> /by <time>");
        System.out.println("    - event <descripton> /from <time> /to <time>");
        System.out.println("    - delete <index>\n");
        System.out.println("    note: without the angle bracket");
    }

    public void showError(String message) {
        System.out.println("    " + message);
    }
}
