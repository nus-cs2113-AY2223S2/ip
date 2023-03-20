package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private static final Scanner in = new Scanner(System.in);
    public static void printWelcomeMessage() {
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Elzi, your personal pet dog!\n" +
                "____________________________________________________________";
        System.out.println(welcomeMessage);
    }

    public static String getCommand() {
        System.out.println("What is my task, master?");
        return in.nextLine();
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("Oops! I encountered this error! "+ errorMessage);
    }

    public static void printLine() {
        System.out.println("-------------------------------------------------");
    }

    public static void printByeMessage() {
        System.out.println("Good bye! See you soon!");
        System.out.println("______________________******_____________________");
    }

    public static void printAddTask(Task task) {
        System.out.println("Got it. I've added this task:\n     " + task.toString());
    }
    public static void printDeleteTask(Task task) {
        System.out.println("I have removed this item\n      " + task.toString());
    }
    public static void printSetAsDone(Task task) {
        System.out.println("I have marked this task as done\n" + task.toString());
        printLine();
    }
    public static void printSetAsNotDone(Task task) {
        System.out.println("I have unmarked this task\n" + task.toString());
        printLine();
    }
    public static void printTaskLeft(TaskList taskList) {
        System.out.println("Now you have " + taskList.getSize() + " task in the list");
        printLine();
    }
    public static void printList(TaskList taskList) {
        String list = taskList.listMessages();
        System.out.println("Your current tasks are as follows:");
        System.out.println(list);
        printLine();
    }
}
