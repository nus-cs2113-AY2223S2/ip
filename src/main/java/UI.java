import java.util.ArrayList;

/**
 * Represents all the outputs that the ChatBot can display to the user so the user understands the current state of
 * the ChatBot.
 */
public class UI {
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printHelloMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
    }

    public static void printUpdateStatusMessage(boolean isDone, Task currentTask) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + currentTask);
    }

    public static void printAddTaskMessage(Task task, ArrayList<Task> listOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + listOfTasks.size() + " task in the list.");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printDeleteTaskMessage(int taskNumber, ArrayList<Task> listOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + listOfTasks.get(taskNumber));
        System.out.println("Now you have " + (listOfTasks.size() - 1) + " task in the list.");
    }

    public static void printFindTaskMessage(int index, Task task) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(index + ". " + task);
    }

    public static void printLoadSavedDataMessage (int numberOfSavedMessages) {
        if (numberOfSavedMessages == 0) {
            System.out.println("There are no saved tasks. Welcome new user!");
        } else {
            System.out.println("Welcome back! " + numberOfSavedMessages + " saved tasks have been loaded");
        }
    }
}
