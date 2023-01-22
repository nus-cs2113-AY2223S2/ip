import java.util.*;

public class Duke {
    // DUKE in ASCII art
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    // Horizontal Rule to act as a divider
    public static String HorizontalRule = "__________________________________________________";

    /* StartUp function
       Prints DUKE ASCII, and startup sentence
       Informs users that Duke is ready to accept commands */
    public static void StartUp(){
        System.out.println(logo);
        System.out.println(HorizontalRule);
        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");
        System.out.println(HorizontalRule);
    }

    /* ShutDown function
       Informs users that Duke has shut down and is not accepting commands */
    public static void ShutDown(){
        System.out.println(HorizontalRule);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(HorizontalRule);
    }

    public static void main(String[] args) {
        // taskArray for storing tasks + taskCounter to track next empty cell
        String[] taskArray = new String[100];
        int taskCounter = 0;

        Scanner s = new Scanner(System.in);

        StartUp();

        // Reads inputs until "bye" is sent
        while(true) {
            String userInput = s.next();
            userInput += s.nextLine();

            if (userInput.equals("bye")) {
                ShutDown();
                return;

            } else if (userInput.equals("list")) {
                // Prints all contents of task Array
                System.out.println(HorizontalRule);
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i + 1) + ". " + taskArray[i]);
                }
                System.out.println(HorizontalRule);

            } else {
                // Update taskArray and taskCounter
                taskArray[taskCounter] = userInput;
                taskCounter += 1;

                // Informs users that their task was successfully added
                System.out.println(HorizontalRule);
                System.out.println("Added: " + userInput);
                System.out.println("Total Number of Tasks: " + taskCounter);
                System.out.println(HorizontalRule);
            }
        }
    }
}
