package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    public static void greetUser() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    public static void listTasks(ArrayList<Task> tasks, String purpose) {
        if (purpose.equals("find")) {
            System.out.println("Here are the matching tasks in your list:");
        } else if (purpose.equals("list")){
            System.out.println("Here are the tasks in your list:");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1);
            System.out.print(".");
            tasks.get(i).printTask();
        }
    }

    public static void printNotification(Task task, String action, int numberOfTasks) {
        switch (action) {
        case "unmark":
            System.out.println("OK, I've marked this task as not done yet:");
            task.printTask();
            break;
        case "mark":
            System.out.println("Nice! I've marked this task as done:");
            task.printTask();
            break;
        case "delete":
            System.out.println("Noted. I've removed this task:");
            task.printTask();
            System.out.println("Now you have " + numberOfTasks + " task(s) in the list.");
            break;
        case "deadline":
            // Fallthrough
        case "todo":
            // Fallthrough
        case "event":
            System.out.print("Got it. I've added this task:\n" + "  ");
            task.printTask();
            System.out.println("Now you have " + numberOfTasks + " task(s) in the list.");
            break;
        default:
            System.out.println("Something went wrong!");
            break;
        }
    }

    public static void printErrorMessage(String errorType) {
        switch(errorType) {
        case "conversion":
            System.out.println("OOPS! Something went wrong while converting your data!");
            break;
        case "file not found":
            System.out.println("File not found");
            break;
        case "saving":
            System.out.println("OOPS! Something went wrong with saving your data!");
            break;
        case "invalid command":
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        case "error with information provided":
            System.out.println("OOPS! I'm sorry, but the information you provided contains errors."); // fill in
            break;
        default:
            System.out.println("Encountered an unknown error");
            break;
        }
    }
    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
