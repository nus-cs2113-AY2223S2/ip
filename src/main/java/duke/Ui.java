package duke;
import java.util.ArrayList;

public class Ui {
    public static void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printTaskAddedMessage(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void printTaskDeletedMessage(int index, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(TaskList.getTask(index));
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void printTaskDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public static void printTaskList(ArrayList<Task> textList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < textList.size(); i++) {
            System.out.println((i + 1) + "." + textList.get(i));
        }
    }

    public static void printFindMessage(ArrayList<Task> foundList) {
        if (foundList.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundList.size(); i++) {
                System.out.println((i + 1) + "." + foundList.get(i));
            }
        } else {
            System.out.println("No matching tasks found");
        }
    }

    public static void printTaskUnDoneMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public static void showLoadingError() {
        System.out.println("File not found");
    }

    public static void printInvalidIndex() {
        System.out.println("Invalid index");
    }

    public static void printEmptyDescription() {
        System.out.println("The description of a todo cannot be empty.");
    }

    public static void printInvalidCommand() {
        System.out.println("Invalid command");
    }

    public static void printInvalidDateTime() {
        System.out.println("Invalid date and time format. Please use yyyy-mm-ddTHH:mm");
    }
}
