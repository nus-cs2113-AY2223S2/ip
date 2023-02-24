package duke;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * User Interface class that deals with inputs from and outputs to the user
 */
public class Ui {

    // Scanner to read user inputs on CLI
    private static final Scanner in = new Scanner(System.in);

    /**
     * Get user input from CLI.
     *
     * @return String containing the CLI input
     */
    public String readCommand() {
        return in.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void printWelcomeMessage() {
        showLine();
        System.out.println("Hello from\n" +
                " ____        _\n" +
                "|  _ \\ _   _| | _____\n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n" +
                "Enter \"help\" to see a list of commands.");
        showLine();
    }

    /**
     * For <code>help</code> command.
     * Prints out a list of all available commands.
     */
    public void printHelpMessage() {
        System.out.println(" Enter \"list\" to see all tasks\n" +
                " Enter \"todo [task]\" to add a task\n" +
                " Enter \"deadline [task] /by [date]\" to add a deadline\n" +
                " Enter \"event [task] /from [date] /to [date]\" to add an event\n" +
                " Enter \"mark [idx]\" to mark task as done\n" +
                " Enter \"unmark [idx]\" to mark task as not done\n" +
                " Enter \"delete [idx]\" to remove task from list\n" +
                " Enter \"find [keyword]\" to see all tasks containing [keyword]\n" +
                " Enter \"date [yyyy-MM-dd]\" to see all tasks occurring on that date\n" +
                " Enter \"bye\" to exit the program\n\n" +
                " ***NOTE***\n" +
                " The \"date\" command only considers tasks when [date] is input in the format:\n" +
                "     \"yyyy-MM-ddThh:mm\"\n" +
                " eg. \"2023-10-30T23:59\" represents Oct 20 2023, 11:59PM");
    }

    /**
     * For <code>list</code> command.
     * Prints all Tasks within the ArrayList given.
     *
     * @param allTasks ArrayList of Tasks
     */
    public void printList(ArrayList<Task> allTasks) {
        if (allTasks.size() == 0) {
            System.out.println("There are no tasks in your list!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println(i + 1 + "." + allTasks.get(i));
        }
    }

    /**
     * For <code>todo</code>, <code>deadline</code>, and <code>event</code> commands.
     * Prints out message for successful adding of Task.
     *
     * @param newTask Task that has just been added
     */
    public void printAddMessage(Task newTask) {
        System.out.println("Got it. I've added this " + newTask.getType() + ":\n" +
                "  " + newTask);
    }

    /**
     * For <code>mark</code> command.
     * Prints out message for successful marking of Task as done.
     *
     * @param doneTask Task that has just been marked as done
     */
    public void printMarkDone(Task doneTask) {
        System.out.println("Nice!, I've marked this task as done:\n" +
                "  " + doneTask);
    }

    /**
     * For <code>unmark</code> command.
     * Prints out message for successful marking of Task as not done.
     *
     * @param notDoneTask Task that has just been marked as not done
     */
    public void printMarkNotDone(Task notDoneTask) {
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "  " + notDoneTask);
    }

    /**
     * For <code>delete</code> command.
     * Prints out message for successful deletion of Task.
     *
     * @param deletedTask Task that will be deleted
     * @param size        Number of tasks left in the list after deletion
     */
    public void printDeleted(Task deletedTask, int size) {
        System.out.println("Noted, I've removed this task:\n" +
                "  " + deletedTask + "\n" +
                "Now you have " + (size - 1) + " tasks in the list");
    }

    /**
     * For <code>find</code> command.
     * Prints all Tasks within the ArrayList given, all containing a certain keyword.
     *
     * @param foundTasks ArrayList of Tasks containing a keyword
     */
    public void printFoundList(ArrayList<Task> foundTasks) {
        if (foundTasks.size() == 0) {
            System.out.println("There are no matching tasks!");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println(i + 1 + "." + foundTasks.get(i));
        }
    }

    /**
     * For <code>date</code> command.
     * Prints all Tasks within the ArrayList given, all happening on a certain date.
     *
     * @param happeningTasks ArrayList of Tasks happening on a date
     */
    public void printDateList(ArrayList<Task> happeningTasks, LocalDate date) {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        if (happeningTasks.size() == 0) {
            System.out.println("There are no tasks on " + dateString + "!");
            return;
        }
        System.out.println("Here are the tasks happening on " + dateString + ":");
        for (int i = 0; i < happeningTasks.size(); i++) {
            System.out.println(i + 1 + "." + happeningTasks.get(i));
        }
    }

    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    // error messages
    public void printErrorForIdx(int size) {
        if (size != 0) {
            System.out.println("Please enter [idx] in the form of an integer from 1 to " + size);
        } else {
            System.out.println("There are no tasks in your list!");
        }
    }

    public void printErrorForIO() {
        System.out.println("Something went wrong with the hard disk :(");
    }

    public void printErrorFileNotFound() {
        System.out.println("Save file not found, initialising empty list...");
    }

    public void printInvalidDeadline() {
        System.out.println("Please enter deadline as \"deadline [task] /by [date]\".");
    }

    public void printInvalidEvent() {
        System.out.println("Please enter event as \"event [task] /from [date] /to [date]\".");
    }

    public void printInvalidDateTime() {
        System.out.println("Please enter date in the format of yyyy-MM-dd.");
    }

    public void printInvalidCommand() {
        System.out.println("Sorry, but I don't know what that means :(");
    }

    public void printInvalidSaveFile(int counter, String filePath) {
        showLine();
        System.out.println("There is an error in save.txt at line " + (counter + 1) + "\n" +
                "Task " + (counter + 1) + " has been excluded. You can edit the save file at:\n" +
                filePath);
        showLine();
    }

    public void printEmptyDescription() {
        System.out.println("Oops! The description cannot be empty.");
    }

    public void printDateOrderException() {
        System.out.println("Oops, the start date for your event occurs after the end date!");
    }

}
