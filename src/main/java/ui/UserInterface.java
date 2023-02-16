package ui;

import exceptions.InvalidSyntaxException;
import java.io.IOException;
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

    /*
     *  Task-related UI
     */

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

    public void printMarkedTask(Task markedTask) {
        System.out.println("Nice! I've marked this task as done");
        System.out.println(markedTask);
    }

    public void printUnmarkedTask(Task unmarkedTask) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(unmarkedTask);
    }

    /*
     *  Exception-handling UI
     */

    public void printUnrecognizedCommand() {
        System.out.println("Sorry, I don't recognize that command...");
    }

    public void printInvalidSyntax(InvalidSyntaxException ex) {
        System.out.println("That doesn't look quite right, try: " + ex.getExpectedSyntax());
    }

    public void printUnknownTask() {
        System.out.println("Oops, not quite sure what task you're referring to...");
    }

    public void printSaveFailure(IOException ex) {
        System.out.println("Couldn't save your files!");
        ex.printStackTrace();
        printDivider();
    }

    public void printLoadFailure(Exception ex) {
        System.out.println("Something went wrong! I couldn't load your tasks :(");
        ex.printStackTrace();
        printDivider();
    }

    /*
     *  Miscellaneous UI
     */

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
