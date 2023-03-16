package utility;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ArrayList;

import tasks.Task;

public class Ui {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    // Print on start
    private static final String LOGO = " ____        _        \n|  _ / _   _| | _____ \n| | | | | | | |/ / _ /\n| |_| | |_| |   <  __/\n|____/ /__,_|_|/_/___|\n";

    // Horizontal Rule to act as a divider
    private static final String HORIZONTAL_RULE = "________________________________________________________________________________";

    public static void printHorizontalRule() {
        System.out.println(HORIZONTAL_RULE);
    }

    /**
     * Prints an acknowledgement, based on the type of task.
     * Also prints the description of the task and the current number of tasks
     */
    public static void printAcknowledgement(String taskType, String description, String size) {
        printHorizontalRule();
        System.out.println("Added " + taskType + ": " + description);
        System.out.println("Total Number of Tasks: " + size);
        printHorizontalRule();
    }

    public static void startUp() {
        System.out.println(LOGO);
        printHorizontalRule();
        System.out.println("Hello! I'm Duke.");
    }

    public static void promptInput() {
        printHorizontalRule();
        System.out.println("What can I do for you?");
        printHorizontalRule();
    }

    public static void invalidCommand(String userInput) {
        printHorizontalRule();
        System.out.println(userInput + " is an invalid command. Please try again!");
        printHorizontalRule();
    }

    public static void dateTimeError() {
        printHorizontalRule();
        System.out.println("Wrong date and time format used! The required format is YYYY-MM-DD HH:MM");
        printHorizontalRule();
    }

    public static void eventError(String userInput) {
        printHorizontalRule();
        System.out.println("Wrong usage of event. Format is: event DESCRIPTION /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM");
        System.out.println("You entered: " + userInput);
        printHorizontalRule();
    }

    public static void deadlineError(String userInput) {
        printHorizontalRule();
        System.out.println("Wrong usage of deadline. Format is: deadline DESCRIPTION /by YYYY-MM-DD HH:MM");
        System.out.println("You entered: " + userInput);
        printHorizontalRule();
    }

    public static void todoError(String userInput) {
        printHorizontalRule();
        System.out.println("Invalid command for todo. Cannot have a blank description!");
        printHorizontalRule();
    }

    public static void invalidIndex() {
        printHorizontalRule();
        System.out.println("Please provide a valid index!");
        printHorizontalRule();
    }

    public static void requiresNumber() {
        printHorizontalRule();
        System.out.println("Please give an integer!");
        printHorizontalRule();
    }

    public static void markTask(String taskName, String status) {
        printHorizontalRule();
        System.out.println("Marking task \"" + taskName + "\" as " + status + "!");
        printHorizontalRule();
    }

    public static void deleteTask(String taskName) {
        printHorizontalRule();
        System.out.println("Deleting task: \"" + taskName + "\"");
        printHorizontalRule();
    }

    public static void printTasks(ArrayList<Task> tasks) {
        printHorizontalRule();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        // Prints total number
        System.out.println("Total number of tasks: " + tasks.size());
        printHorizontalRule();
    }

    public static void findError(String userInput) {
        printHorizontalRule();
        System.out.println("Wrong usage of find. Format is: find DESCRIPTION");
        System.out.println("You entered: " + userInput);
        printHorizontalRule();
    }

    public static void findHeader(String description) {
        printHorizontalRule();
        System.out.println("Here are all tasks matching " + description);
        System.out.println("The index given here is not reflective of the task's actual index.\nHere are all tasks which contain " + description + " in your list:");
    }

    public static void findEnd() {
        printHorizontalRule();
    }

    public static void findMatching(int counter, Task task) {
        System.out.println((counter) + ". " + task);
    }

    public static void shutDown() {
        printHorizontalRule();
        System.out.println("Shutting Down! Hope to see you again soon!");
        printHorizontalRule();
    }

    public static void cannotBeNull() {
        printHorizontalRule();
        System.out.println("Input cannot be empty!");
        printHorizontalRule();
    }
}
