package luke.command;

import luke.icon.LukeLogo;
import luke.task.Task;

import java.util.ArrayList;

public class Response {
    /** Prints a line to indicate the start and end of a message */
    private void printLine() {
        System.out.println("____________________________________________________________");
    }

    /** Prints a string indicating that the messages are coming from LUKE */
    private void printSignature() {
        System.out.print("[Luke]: ");
    }

    /** Prints a hello message to the user */
    public void sayHi() {
        LukeLogo logo = new LukeLogo();
        printLine();
        printSignature();
        System.out.println("Hello I'm\n" + logo.getLogo(0));
        System.out.println("What can I do for you?");
        printLine();
    }

    /** Prints a goodbye message to the user */
    public void sayBye() {
        printLine();
        printSignature();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints a string for the user.
     *
     * @param toPrint The string to be printed.
     */
    public void printString(String toPrint) {
        printLine();
        printSignature();
        System.out.println(toPrint);
        printLine();
    }

    /**
     * Prints a confirmation to the user that a task have been successfully added to the TaskOrganizer.
     *
     * @param taskName The name of the task that has been added to the TaskOrganizer.
     */
    public void printAddTask(String taskName) {
        printLine();
        printSignature();
        System.out.println("Added: " + taskName);
        printLine();
    }

    /**
     * Prints a confirmation to the user that a task have been marked as complete.
     *
     * @param taskName The name of the task that is designated to be marked.
     */
    public void printMarkTask(String taskName) {
        printLine();
        printSignature();
        System.out.println(taskName + " has been marked as complete.");
        printLine();
    }

    /**
     * Prints a confirmation to the user that a task have been unmarked.
     *
     * @param taskName The name of the task that is designated to be unmarked.
     */
    public void printUnmarkTask(String taskName) {
        printLine();
        printSignature();
        System.out.println(taskName + " has been marked as incomplete.");
        printLine();
    }

    /**
     * Prints a confirmation to the user that a task have been deleted.
     *
     * @param taskName The name of the task to be deleted.
     */
    public void printDeleteTask(String taskName) {
        printLine();
        printSignature();
        System.out.println(taskName + " has been deleted.");
        printLine();
    }

    /**
     * Prints a list of all the task in the TaskOrganizer.
     *
     * @param tasks The list of all the tasks in the TaskOrganizer.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        printLine();
        printSignature();
        int serialNumber = 1;
        System.out.println("Printing Tasks...");
        for (Task task : tasks) {
            System.out.print(serialNumber + ". ");
            task.printTaskName();
            serialNumber += 1;
        }
        printLine();
    }

    /** Prints a message telling the user that the task organizer is empty */
    public void printEmptyList() {
        printLine();
        printSignature();
        System.out.println("The list is empty!");
        printLine();
    }

    /** Prints a message telling the user that he/she have entered an invalid command */
    public void printInvalidCommand() {
        printLine();
        printSignature();
        System.out.println("You have entered an invalid command!");
        printLine();
    }

    /** Prints a message telling the user that the index entered is out of bounds */
    public void printOutOfBounds() {
        printLine();
        printSignature();
        System.out.println("You have entered an index that does not exist!");
        printLine();
    }
}
