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

    /**
     * Prints horizontal rule
     */
    public static void printHorizontalRule() {
        System.out.println(HORIZONTAL_RULE);
    }

    /**
     * Prints start up text
     */
    public static void startUp() {
        System.out.println(LOGO);
        printHorizontalRule();
        System.out.println("Hello! I'm Duke.");
    }

    /**
     * Prints shut down text
     */
    public static void shutDown() {
        printHorizontalRule();
        System.out.println("Saving current tasks...");
        System.out.println("Shutting Down! Hope to see you again soon!");
        printHorizontalRule();
    }

    /**
     * Prints an acknowledgement, based on the type of task.
     * @param taskType Type of task, T/D/E
     * @param description Description of the task
     * @param size Current size of task list
     */
    public static void printAcknowledgement(String taskType, String description, String size) {
        printHorizontalRule();
        System.out.println("Added " + taskType + ": " + description);
        System.out.println("Total Number of Tasks: " + size);
        printHorizontalRule();
    }

    /**
     * Prompts user for input
     */
    public static void promptInput() {
        printHorizontalRule();
        System.out.println("What can I do for you?");
        printHorizontalRule();
    }

    /**
     * Prints an acknowledgement on marking or unmarking tasks
     * @param taskName Name of task modified
     * @param status Either complete or incomplete
     */
    public static void markTask(String taskName, String status) {
        printHorizontalRule();
        System.out.println("Marking task \"" + taskName + "\" as " + status + "!");
        printHorizontalRule();
    }

    /**
     * Prints an acknowledgement of deleted task
     * @param taskName Name of task deleted
     */
    public static void deleteTask(String taskName) {
        printHorizontalRule();
        System.out.println("Deleting task: \"" + taskName + "\"");
        printHorizontalRule();
    }

    /**
     * Prints list of tasks
     * @param tasks Array list of all tasks
     */
    public static void printTasks(ArrayList<Task> tasks) {
        printHorizontalRule();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        // Prints total number
        System.out.println("Total number of tasks: " + tasks.size());
        printHorizontalRule();
    }

    /**
     * Prints start of find function's results
     * @param description The task the user is searching for
     */
    public static void findHeader(String description) {
        printHorizontalRule();
        System.out.println("Here are all tasks matching " + description);
        System.out.println("The index given here is not reflective of the task's actual index.\nHere are all tasks which contain " + description + " in your list:");
    }

    /**
     * Prints horizontal rule
     */
    public static void findEnd() {
        printHorizontalRule();
    }

    /**
     * Prints matching task
     * @param counter Current number of matching tasks
     * @param task Name of task
     */

    public static void findMatching(int counter, Task task) {
        System.out.println((counter) + ". " + task);
    }

    /*** ERROR MESSAGES ***/
    // COMMON ERRORS

    /**
     * Prints error message on invalid command
     * @param userInput User's input
     */
    public static void invalidCommand(String userInput) {
        printHorizontalRule();
        System.out.println(userInput + " is an invalid command. Please try again!");
        printHorizontalRule();
    }

    /**
     * Prints error message that input cannot be null
     */
    public static void cannotBeNull() {
        printHorizontalRule();
        System.out.println("Input cannot be empty!");
        printHorizontalRule();
    }

    /**
     * Prints error if date and time given is invalid
     */
    public static void dateTimeError() {
        printHorizontalRule();
        System.out.println("Wrong date and time format used! The required format is YYYY-MM-DD HH:MM");
        printHorizontalRule();
    }

    /**
     * Prints error if index is invalid
     */
    public static void invalidIndex() {
        printHorizontalRule();
        System.out.println("Please provide a valid index!");
        printHorizontalRule();
    }

    /**
     * Prints error is non-integers are given
     */
    public static void requiresNumber() {
        printHorizontalRule();
        System.out.println("Please give an integer!");
        printHorizontalRule();
    }

    /**
     * Prints error if given wrong to do format
     * @param userInput User's input
     */
    // TO DO ERROR
    public static void todoError(String userInput) {
        printHorizontalRule();
        System.out.println("Invalid command for todo. Cannot have a blank description!");
        printHorizontalRule();
    }

    /**
     * Prints error if invalid deadline format
     * @param userInput User's input
     */
    // DEADLINE ERROR
    public static void deadlineError(String userInput) {
        printHorizontalRule();
        System.out.println("Wrong usage of deadline. Format is: deadline DESCRIPTION /by YYYY-MM-DD HH:MM");
        System.out.println("You entered: " + userInput);
        printHorizontalRule();
    }

    /**
     * Prints error if invalid event format
     * @param userInput User's input
     */
    // EVENT ERROR
    public static void eventError(String userInput) {
        printHorizontalRule();
        System.out.println("Wrong usage of event. Format is: event DESCRIPTION /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM");
        System.out.println("You entered: " + userInput);
        printHorizontalRule();
    }

    /**
     * Prints error if invalid find format
     * @param userInput User's input
     */
    // FIND ERROR
    public static void findError(String userInput) {
        printHorizontalRule();
        System.out.println("Wrong usage of find. Format is: find DESCRIPTION");
        System.out.println("You entered: " + userInput);
        printHorizontalRule();
    }
}
