package ui;

import java.util.Scanner;
import task.Task;
import task.TaskList;

public class UserInterface {

    private static final String USER_PROMPT = "> ";
    private static final int DIVIDER_WIDTH = 60;

    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public String getUserCommand() {
        printDivider();
        System.out.print(USER_PROMPT);

        return scanner.nextLine();
    }

    public void printTasks(TaskList tasks) {
        for (int index = 1; index <= tasks.getTaskCount(); index++) {
            System.out.println(index + ". " + tasks.getTask(index - 1));
        }
    }

    public void printTaskCount(TaskList tasks) {
        System.out.println("Now you have " + tasks.getTaskCount() + " task(s) in the list.");
    }

    public void printAddedTask(Task addedTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(addedTask);
    }

    public void printRemovedTask(Task removedTask) {
        System.out.println("Alrighty, I've removed this task:");
        System.out.println(removedTask);
    }

    public void printDivider() {
        System.out.println("_".repeat(DIVIDER_WIDTH));
    }

    public void printGreeting() {
        printDivider();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void printGoodbye() {
        printDivider();
        System.out.println("Bye. Hope to see you again soon!");
    }

}
