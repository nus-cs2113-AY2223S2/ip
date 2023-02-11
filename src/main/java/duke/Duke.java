package duke;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    /* --- GLOBAL VARIABLES --- */
    private static final String LOGO = " ____        _        \n|  _ \\ _   _| | _____ \n| | | | | | | |/ / _ \\\n| |_| | |_| |   <  __/\n|____/ \\__,_|_|\\_\\___|\n";

    // Horizontal Rule to act as a divider
    private static final String HORIZONTAL_RULE = "___________________________________________________________________";

    public static void printHorizontalRule() {
        System.out.println(HORIZONTAL_RULE);
    }



    /* --- FUNCTIONS --- */

    /**
     * Start up function.
     * Prints ASCII art and prompts users for input
     */
    public static void startUp() {
        System.out.println(LOGO);
        printHorizontalRule();
        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");
        printHorizontalRule();
    }

    /**
     * Shut down function.
     * Informs users that Duke is no longer accepting commands
     */
    public static void shutDown() {
        printHorizontalRule();
        System.out.println("Shutting Down! Hope to see you again soon!");
        printHorizontalRule();
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

    /**
     * Removes the command word for creating tasks, ie. to do, deadline or event
     */
    public static String removeCommandWord(String userInput) {
        return userInput.split(" ", 2)[1];
    }

    /**
     * Extracts the description given by the user for deadline tasks
     */
    public static String getDescriptionForDeadline(String input) {
        return input.split(" /by ", 2)[0];
    }

    /**
     * Extracts the deadline for deadline tasks
     */
    public static String getDeadline(String input) {
        return input.split(" /by ", 2)[1];
    }

    /**
     * Extracts the description given by the user for event tasks
     */
    public static String getDescriptionForEvent(String input) {
        return input.split(" /from ", 2)[0];
    }

    /**
     * Extracts the start and end timing for event tasks
     */
    public static String[] getTimings(String input) {

        String[] timings = new String[2];
        String timing = input.split(" /from ", 2)[1];

        timings[0] = timing.split(" /to ", 2)[0];
        timings[1] = timing.split(" /to ", 2)[1];

        return timings;
    }


    /* --- MAIN FUNCTION --- */
    public static void main(String[] args) {
        // tasks for storing tasks + taskCounter to track next empty cell
        ArrayList<Task> tasks = new ArrayList<Task>();

        // Setting up input reading
        final Scanner INPUT = new Scanner(System.in);

        startUp();

        // Reads inputs until "bye" is sent
        while (true) {
            String userInput = INPUT.next();
            userInput += INPUT.nextLine();

            // Extracts first word in input
            // Used to check if tasks are to be marked/unmarked
            String firstWord = userInput.split(" ", 2)[0].toLowerCase();

            // initializing variables
            int taskIndex;
            String withoutCommand = "", description = "";

            switch (firstWord) {
                case ("bye"): {
                    shutDown();
                    return;
                }

                case ("list"): {
                    // Prints all contents of task Array
                    printHorizontalRule();
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    // Prints total number
                    System.out.println("Total number of tasks: " + tasks.size());
                    printHorizontalRule();
                    break;
                }

                case ("mark"): {
                    try {
                        // Sets specified task to complete
                        taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]);
                        (tasks.get(taskIndex - 1)).setComplete();

                        // Prints acknowledgement
                        printHorizontalRule();
                        System.out.println("Marking task \"" + (tasks.get(taskIndex - 1)).getTask() + "\" as complete!");
                        printHorizontalRule();
                    } catch (IndexOutOfBoundsException e) {
                        printHorizontalRule();
                        System.out.println("Please provide a valid index!");
                        printHorizontalRule();
                    }
                    break;
                }

                case ("unmark"): {
                    try {
                        // Sets specified task to incomplete
                        taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]);
                        (tasks.get(taskIndex - 1)).setIncomplete();

                        // Prints acknowledgement
                        printHorizontalRule();
                        System.out.println("Marking task \"" + (tasks.get(taskIndex - 1)).getTask() + "\" as incomplete!");
                        printHorizontalRule();
                    } catch (IndexOutOfBoundsException e) {
                        printHorizontalRule();
                        System.out.println("Please provide a valid index!");
                        printHorizontalRule();
                    }
                    break;
                }

                case ("delete"): {
                    try {
                        // Sets specified task to incomplete
                        taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;

                        // Prints acknowledgement
                        printHorizontalRule();
                        System.out.println("Deleting task: \"" + (tasks.get(taskIndex)).getTask() + "\"");
                        printHorizontalRule();

                        tasks.remove(taskIndex);

                    } catch (IndexOutOfBoundsException e) {
                        printHorizontalRule();
                        System.out.println("Please provide a valid index!");
                        printHorizontalRule();
                    }
                    break;
                }

                case ("todo"): {
                /*
                  For to do tasks
                  Removes the command word, then adds a new to do with the description to the array
                 */
                    try {
                        String todoDescription = removeCommandWord(userInput);
                        Todo newTodo = new Todo(todoDescription);
                        tasks.add(newTodo);

                        // Prints acknowledgement
                        printAcknowledgement("Todo", todoDescription, String.valueOf(tasks.size()));
                    } catch (IndexOutOfBoundsException e) {
                        printHorizontalRule();
                        System.out.println("Invalid command for todo. Cannot have a blank description!");
                        printHorizontalRule();
                    }
                    break;
                }

                case ("deadline"): {
                /*
                  For deadline tasks
                  Removes the command word and extracts the description
                  Also extracts the deadline to the by variable
                  Adds a new deadline to the array
                 */
                    try {
                        withoutCommand = removeCommandWord(userInput);
                        description = getDescriptionForDeadline(withoutCommand);
                        String by = getDeadline(withoutCommand);

                        Deadline deadline = new Deadline(description, by);
                        tasks.add(deadline);

                        // Prints acknowledgement
                        printAcknowledgement("Deadline", description, String.valueOf(tasks.size()));
                    } catch (IndexOutOfBoundsException e) {
                        printHorizontalRule();
                        System.out.println("Wrong usage of deadline. Format is: deadline {description} /by {date}");
                        System.out.println("You entered: " + userInput);
                        printHorizontalRule();
                    }
                    break;
                }
                case ("event"): {
                /*
                  For event tasks
                  Removes the command word and extracts the description
                  Also extracts the start and end to the from and to variable
                  Adds a new event to the array
                 */
                    try {
                        withoutCommand = removeCommandWord(userInput);
                        description = getDescriptionForEvent(withoutCommand);

                        // Returns in the format [from, to]
                        String[] timings = getTimings(withoutCommand);

                        Event event = new Event(description, timings[0], timings[1]);
                        tasks.add(event);

                        // Prints acknowledgement
                        printAcknowledgement("Event", description, String.valueOf(tasks.size()));
                    } catch (IndexOutOfBoundsException e) {
                        printHorizontalRule();
                        System.out.println("Wrong usage of event. Format is: event {description} /from {date} /to {date}");
                        System.out.println("You entered: " + userInput);
                        printHorizontalRule();
                    }
                    break;
                }
                default: {
                    // If none of the above, invalid command - prints error informing user
                    printHorizontalRule();
                    System.out.println(userInput + " is an invalid command. Please try again!");
                    printHorizontalRule();
                    break;
                }
            }
        }
    }
}
