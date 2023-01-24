import java.util.*;

public class Duke {
    // DUKE in ASCII art
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    // Horizontal Rule to act as a divider
    public static String horizontalRule = "__________________________________________________";

    /**
    * Start up function.
    * Prints ASCII art and prompts users for input
    */
    public static void startUp(){
        System.out.println(logo);
        System.out.println(horizontalRule);
        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");
        System.out.println(horizontalRule);
    }

    /**
    * Shut down function.
    * Informs users that Duke is no longer accepting commands
    */
    public static void shutDown(){
        System.out.println(horizontalRule);
        System.out.println("Shutting Down! Hope to see you again soon!");
        System.out.println(horizontalRule);
    }

    public static void main(String[] args) {
        // taskArray for storing tasks + taskCounter to track next empty cell
        Task[] taskArray = new Task[100];
        int taskCounter = 0;

        // Setting up input reading
        Scanner s = new Scanner(System.in);

        startUp();

        // Reads inputs until "bye" is sent
        while(true) {
            String userInput = s.next();
            userInput += s.nextLine();

            // Extracts first word in input
            // Used to check if tasks are to be marked/unmarked
            String firstWord = userInput.split(" ", 2)[0];

            if (userInput.equals("bye")) {
                shutDown();
                return;

            } else if (userInput.equals("list")) {
                // Prints all contents of task Array
                System.out.println(horizontalRule);
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i + 1) + ". " + "[" + taskArray[i].getStatus() + "] " + taskArray[i].getTask());
                }

                // Prints total number
                System.out.println("Total number of tasks: " + taskCounter);
                System.out.println(horizontalRule);

            } else if (firstWord.equals("mark")) {
                // Sets specified task to complete
                int taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]);
                taskArray[taskIndex-1].setComplete();

                // Prints acknowledgement
                System.out.println(horizontalRule);
                System.out.println("Marking task \"" + taskArray[taskIndex].getTask() + "\" as complete!");
                System.out.println(horizontalRule);

            } else if (firstWord.equals("unmark")) {
                // Sets specified task to incomplete
                int taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]);
                taskArray[taskIndex-1].setIncomplete();

                // Prints acknowledgement
                System.out.println(horizontalRule);
                System.out.println("Marking task \"" + taskArray[taskIndex].getTask() + "\" as incomplete!");
                System.out.println(horizontalRule);

            } else {
                // If none of the above, insert new task
                // Update taskArray and taskCounter
                Task newTask = new Task(userInput);
                taskArray[taskCounter] = newTask;
                taskCounter += 1;

                // Prints acknowledgement
                System.out.println(horizontalRule);
                System.out.println("Added task: " + userInput);
                System.out.println("Total Number of Tasks: " + taskCounter);
                System.out.println(horizontalRule);
            }
        }
    }
}
