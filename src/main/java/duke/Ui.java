package duke;

import java.util.ArrayList;

/**
 * <code>Ui</code> deals with interactions with the user by printing messages to display to the user
 */
public class Ui {

    public void showLine() {
        System.out.println("    _________________________________________");
    }

    public void showGreetingMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    Hello from\n" + logo);
        showLine();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        showLine();
        System.out.println("     ");
    }

    public void showExitMessage() {
        System.out.println("    Bye. Hope to see you again soon!");
        showLine();
        System.out.println("     ");
        System.exit(0);
    }

    public void printListContents(ArrayList<Todo> tasks, int counter) {
        for (int i = 0; i < counter; ++i) {
            if (tasks.get(i).isDone) {
                System.out.print("    " + (i + 1) + ".");
                tasks.get(i).printInList();
            } else {
                System.out.print("    " + (i + 1) + ".");
                tasks.get(i).printInList();
            }
        }
        showLine();
        System.out.println("     ");
    }

    public void printUnmarkedAcknowledgement(ArrayList<Todo> tasks, int taskNumber) {
        System.out.println("    _________________________________________");
        System.out.println("    " + taskNumber + "." + "[ ] " + tasks.get(taskNumber - 1).getDescription());
        System.out.println("    _________________________________________");
    }

    public void printMarkedAcknowledgement(ArrayList<Todo> tasks, int taskNumber) {
        System.out.println("    _________________________________________");
        System.out.println("    " + taskNumber + "." + "[X] " + tasks.get(taskNumber - 1).getDescription());
        System.out.println("    _________________________________________");
    }

    public void printAddedAcknowledgment(int counter) {
        System.out.println("    Now you have " + counter + " tasks in your list!");
    }
}

