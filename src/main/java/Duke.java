import java.util.Scanner;

public class Duke {
    // DUKE in ASCII art
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    // Horizontal Rule to act as a divider
    private static final String HORIZONTAL_RULE = "__________________________________________________";

    /**
     * Start up function.
     * Prints ASCII art and prompts users for input
     */
    public static void startUp() {
        System.out.println(LOGO);
        System.out.println(HORIZONTAL_RULE);
        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");
        System.out.println(HORIZONTAL_RULE);
    }

    /**
     * Shut down function.
     * Informs users that Duke is no longer accepting commands
     */
    public static void shutDown() {
        System.out.println(HORIZONTAL_RULE);
        System.out.println("Shutting Down! Hope to see you again soon!");
        System.out.println(HORIZONTAL_RULE);
    }

    public static void main(String[] args) {
        // tasks for storing tasks + taskCounter to track next empty cell
        Task[] tasks = new Task[100];
        int taskCounter = 0;

        // Setting up input reading
        Scanner s = new Scanner(System.in);

        startUp();

        // Reads inputs until "bye" is sent
        while (true) {
            String userInput = s.next();
            userInput += s.nextLine();

            // Extracts first word in input
            // Used to check if tasks are to be marked/unmarked
            String firstWord = userInput.split(" ", 2)[0].toLowerCase();

            if ((userInput).equalsIgnoreCase("bye")) {
                shutDown();
                return;

            } else if ((userInput).equalsIgnoreCase("list")) {
                // Prints all contents of task Array
                System.out.println(HORIZONTAL_RULE);
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i + 1) + ". " + "[" + tasks[i].getStatus() + "] " + tasks[i].getTask());
                }

                // Prints total number
                System.out.println("Total number of tasks: " + taskCounter);
                System.out.println(HORIZONTAL_RULE);

            } else if (firstWord.equals("mark")) {
                // Sets specified task to complete
                int taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]);
                tasks[taskIndex - 1].setComplete();

                // Prints acknowledgement
                System.out.println(HORIZONTAL_RULE);
                System.out.println("Marking task \"" + tasks[taskIndex - 1].getTask() + "\" as complete!");
                System.out.println(HORIZONTAL_RULE);

            } else if (firstWord.equals("unmark")) {
                // Sets specified task to incomplete
                int taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]);
                tasks[taskIndex - 1].setIncomplete();

                // Prints acknowledgement
                System.out.println(HORIZONTAL_RULE);
                System.out.println("Marking task \"" + tasks[taskIndex - 1].getTask() + "\" as incomplete!");
                System.out.println(HORIZONTAL_RULE);

            } else {
                // If none of the above, insert new task
                // Update tasks and taskCounter
                Task newTask = new Task(userInput);
                tasks[taskCounter] = newTask;
                taskCounter += 1;

                // Prints acknowledgement
                System.out.println(HORIZONTAL_RULE);
                System.out.println("Added task: " + userInput);
                System.out.println("Total Number of Tasks: " + taskCounter);
                System.out.println(HORIZONTAL_RULE);
            }
        }
    }
}