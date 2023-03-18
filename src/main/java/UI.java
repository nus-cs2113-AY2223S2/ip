import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    protected final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    protected void printDivider() {
        final String DIVIDER = "____________________________________________________";
        System.out.println(DIVIDER);
    }

    public void printHelloMessage() {
        System.out.println("Hello from\n" + LOGO);
        printDivider();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        printDivider();
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    public static String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printTaskMarked(Task description) {
        System.out.println("Nice! I've marked this task as done:\n" + description);
    }

    public void printTaskUnmarked(Task description) {
        System.out.println("Nice! I've marked this task as not done yet:\n" + description);
    }
    public void printTaskAdded(Task description, int size) {
        System.out.println("Got it. I've added this tasks:\n" + description);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void printTaskDeleted(Task description, int size) {
        System.out.println("Noted. I've removed this task:\n" + description);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Checks if there is any task present before printing the list of tasks
     * or a message that tells the user that there is no task in the list.
     *
     * @param tasks List of task that has been stored in the array to be printed.
     * @param size Size of the array to determine if there is any task to be printed.
     */
    public void printList(ArrayList<Task> tasks, int size) {
        if (size > 0) {
            System.out.println("Here are the tasks in your list:");
            int count = 1;
            for (Task output : tasks) {
                System.out.println(count + "." + output);
                count++;
            }
        } else {
            System.out.println("There are no task in the list.");
        }
    }

    /**
     * Checks if there is any task in the list before printing the list of tasks
     * or a message that tells the user that there is no task containing that keyword.
     *
     * @param matchingTasks List of tasks that has been stored if it contains the keyword
     * @param size Size of the task list.
     */
    public void printTaskFound(ArrayList<Task> matchingTasks, int size) {
        if (size > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int count = 0; count < size; count++) {
                System.out.println(count + 1 + "." + matchingTasks.get(count));
            }
        } else {
            System.out.println("There are no task in the list containing the keyword.");
        }
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
