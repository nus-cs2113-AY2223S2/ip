package luke;

import luke.icon.LukeLogo;
import luke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A <code>Ui</code> object is created to handle interaction with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String SIGNATURE = "[Luke]: ";

    /** A scanner object to read in user input */
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void closeScanner() {
        this.scanner.close();
    }

    public String readUserInput() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    /** Prints a line to indicate the start and end of a message */
    private void printLine() {
        System.out.println(LINE);
    }

    /** Prints a string indicating that the messages are coming from LUKE */
    private void printSignature() {
        System.out.print(SIGNATURE);
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
     * Prints a confirmation to the user that a task have been successfully added to the TaskList.
     *
     * @param taskName The name of the task that has been added to the TaskList.
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
     * Prints a list of all the task in the TaskList.
     *
     * @param tasks The list of all the tasks in the TaskList.
     */
    public void printTaskList(ArrayList<Task> tasks, String firstLine) {
        printLine();
        printSignature();
        System.out.println(firstLine);
        for (Task task : tasks) {
            task.printTaskName();
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

    public void printNoSimilarTasks() {
        printLine();
        printSignature();
        System.out.println("Unable to find matching tasks");
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

    /** Prints a message telling the user that there is an error when loading saved data */
    public void printSaveError() {
        printLine();
        printSignature();
        System.out.println("There is an error when loading saved data.");
        printLine();
    }

    /** Prints a message telling the user that there is an error when creating a file */
    public void printFileCreationError() {
        printLine();
        printSignature();
        System.out.println("There has been an error creating the files. Please delete and try again.");
        printLine();
    }

    /** Prints a message telling the user that there is an error when loading a file */
    public void printFileLoadingError() {
        printLine();
        printSignature();
        System.out.println("There has been an error loading the files. Please delete and try again.");
        printLine();
    }

    /** Indicating testing mode is enabled for automated testing. */
    public void printTestingMode() {
        printLine();
        printSignature();
        System.out.println("Testing Mode Enabled.");
        printLine();
    }
}
