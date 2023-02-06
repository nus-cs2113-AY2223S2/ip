import java.util.Scanner;

public class Duke {
    /**
     * DUKE in ASCII art
     * Made it a long string so IntelliJ would stop highlighting this as a warning
     */
    private static final String LOGO = " ____        _        \n|  _ \\ _   _| | _____ \n| | | | | | | |/ / _ \\\n| |_| | |_| |   <  __/\n|____/ \\__,_|_|\\_\\___|\n";

    // Horizontal Rule to act as a divider
    private static final String HORIZONTAL_RULE = "__________________________________________________";

    public static void printHorizontalRule() {
        System.out.println(HORIZONTAL_RULE);
    }

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
    public static void printAcknowledgement(String taskType, String description, int taskCounter) {
        printHorizontalRule();
        System.out.println("Added " + taskType + ": " + description);
        System.out.println("Total Number of Tasks: " + taskCounter);
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

    public static void main(String[] args) {
        // tasks for storing tasks + taskCounter to track next empty cell
        final int MAX_TASKS = 100;
        Task[] tasks = new Task[MAX_TASKS];
        int taskCounter = 0;

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
                case ("bye"):
                    shutDown();
                    return;

                case ("list"):
                    // Prints all contents of task Array
                    printHorizontalRule();
                    for (int i = 0; i < taskCounter; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                    // Prints total number
                    System.out.println("Total number of tasks: " + taskCounter);
                    printHorizontalRule();
                    break;

                case ("mark"):
                    try {
                        // Sets specified task to complete
                        taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]);
                        tasks[taskIndex - 1].setComplete();

                        // Prints acknowledgement
                        printHorizontalRule();
                        System.out.println("Marking task \"" + tasks[taskIndex - 1].getTask() + "\" as complete!");
                        printHorizontalRule();
                    } catch (IndexOutOfBoundsException e) {
                        printHorizontalRule();
                        System.out.println("Please provide a valid index!");
                        printHorizontalRule();
                    }

                    break;

                case ("unmark"):
                    try {
                        // Sets specified task to incomplete
                        taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]);
                        tasks[taskIndex - 1].setIncomplete();

                        // Prints acknowledgement
                        printHorizontalRule();
                        System.out.println("Marking task \"" + tasks[taskIndex - 1].getTask() + "\" as incomplete!");
                        printHorizontalRule();
                    } catch (IndexOutOfBoundsException e) {
                        printHorizontalRule();
                        System.out.println("Please provide a valid index!");
                        printHorizontalRule();
                    }

                    break;

                case ("todo"):
                /*
                  For to do tasks
                  Removes the command word, then adds a new to do with the description to the array
                 */
                    withoutCommand = removeCommandWord(userInput);
                    Todo newTodo = new Todo(withoutCommand);
                    tasks[taskCounter] = newTodo;
                    taskCounter += 1;

                    // Prints acknowledgement
                    printAcknowledgement("Todo", withoutCommand, taskCounter);

                    break;

                case ("deadline"):
                /*
                  For deadline tasks
                  Removes the command word and extracts the description
                  Also extracts the deadline to the by variable
                  Adds a new deadline to the array
                 */

                    withoutCommand = removeCommandWord(userInput);
                    description = getDescriptionForDeadline(withoutCommand);
                    String by = getDeadline(withoutCommand);

                    Deadline deadline = new Deadline(description, by);
                    tasks[taskCounter] = deadline;
                    taskCounter += 1;

                    // Prints acknowledgement
                    printAcknowledgement("Deadline", description, taskCounter);

                    break;

                case ("event"):
                /*
                  For event tasks
                  Removes the command word and extracts the description
                  Also extracts the start and end to the from and to variable
                  Adds a new event to the array
                 */
                    withoutCommand = removeCommandWord(userInput);
                    description = getDescriptionForEvent(withoutCommand);

                    // Returns in the format [from, to]
                    String[] timings = getTimings(withoutCommand);

                    Event event = new Event(description, timings[0], timings[1]);
                    tasks[taskCounter] = event;
                    taskCounter += 1;

                    // Prints acknowledgement
                    printAcknowledgement("Deadline", description, taskCounter);

                    break;

                default:
                    // If none of the above, invalid command - prints error informing user
                    printHorizontalRule();
                    System.out.println(userInput + " is an invalid command. Please try again!");
                    printHorizontalRule();
            }
        }
    }
}
