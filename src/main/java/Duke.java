import java.util.Scanner;

public class Duke {

    static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void runProgram(){

        // Variables needed
        Scanner scanner = new Scanner(System.in);
        String userInput;
        String command;
        Task[] tasks = new Task[100];
        int inputCounter = 0;


        // Get first task
        userInput = scanner.nextLine();
        command = userInput.split(" ")[0];

        // Loop for next tasks to add
        while (!userInput.equals("bye")) {

            System.out.println(HORIZONTAL_LINE);

            // Print list upon user request
            if (userInput.equals("list")) {
                printList(tasks, inputCounter);
            }

            // Mark as done
            else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.substring(userInput.length() - 1));
                tasks[taskIndex - 1].markAsDone();
            }

            // Mark as undone
            else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.substring(userInput.length() - 1));
                tasks[taskIndex - 1].markAsNotDone();
            }

            // Add other tasks into list
            else {

                Task newTask;

                // Depending on the type of command, the input gets parsed into the different handlers
                switch (command) {
                case "deadline":
                    String[] deadlineArgs = deadlineHandler(userInput);
                    newTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                    break;
                case "event":
                    String[] eventArgs = eventHandler(userInput);
                    newTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
                    break;
                default:
                    String todoCommand = todoHandler(userInput);
                    newTask = new Todo(todoCommand);
                    break;
                }

                tasks[inputCounter] = newTask;
                inputCounter++;
                System.out.println("Got it. I've added this task: \n" + newTask.toString());

                if (inputCounter == 1) {
                    System.out.println("Now you have " + inputCounter + " task in the list.");
                } else {
                    System.out.println("Now you have " + inputCounter + " tasks in the list.");
                }

            }

            // Print trailing horizontal line and take in next input
            System.out.println(HORIZONTAL_LINE + "\n");
            userInput = scanner.nextLine();
            command = userInput.split(" ")[0];

        }

    }

    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
        System.exit(0);
    }

    public static void printList(Task[] listOfInputs, int inputCounter) {
        for (int i = 0; i < inputCounter; i++) {
            System.out.println(i + 1 + "." + listOfInputs[i].toString());
        }
    }

    // Returns a string containing taskName
    public static String todoHandler(String userInput) {

        // Format of userInput: <command> <task_name>
        int numberOfWords = userInput.split(" ").length;
        String[] userInputArray = new String[numberOfWords - 1];
        String taskName = "";

        userInputArray = userInput.split(" ");

        // Remove to_do command from the finalString
        for (int i = 1; i < numberOfWords; i++) {
            taskName += userInputArray[i] + " ";
        }

        // Remove trailing space at the end of taskName
        taskName = taskName.trim();

        return taskName;

    }

    // Returns an array containing [taskName, deadline]
    public static String[] deadlineHandler(String userInput) {

        // Format of userInput: <command> <taskName> /by <deadline>
        int numberOfWords = userInput.split(" ").length;
        int endOfTaskNameIndex = 2;
        String taskName = "";
        String deadline = "";
        String[] userInputArray = new String[numberOfWords];
        String[] cleanedUserInputArray = new String[numberOfWords - 1];
        String[] outputArray = new String[2];
        boolean isByPassed = false;

        userInputArray = userInput.split(" ");

        // Remove "/by" and put each word into cleanedUserInputArray
        for (int i = 0; i < numberOfWords; i++) {
            if (userInputArray[i].equals("/by")) {
                endOfTaskNameIndex = i;
                isByPassed = true;
                continue;
            }
            if (!isByPassed) {
                cleanedUserInputArray[i] = userInputArray[i];
            } else {
                cleanedUserInputArray[i - 1] = userInputArray[i];
            }
        }

        // Collate words for the taskName
        for (int i = 1; i < endOfTaskNameIndex; i++) {
            taskName += cleanedUserInputArray[i] + " ";
        }

        // Remove trailing space at the end of taskName
        taskName = taskName.trim();
        outputArray[0] = taskName;

        // Collate words for the deadline
        for (int i = endOfTaskNameIndex + 1; i < numberOfWords; i++) {
            deadline += userInputArray[i] + " ";
        }

        // Remove trailing space at the end of deadline
        deadline = deadline.trim();
        outputArray[1] = deadline;

        return outputArray;
    }

    // Returns an array containing [taskName, from, to]
    public static String[] eventHandler(String userInput) {

        // Format of userInput: <command> <taskName> /from <from> /to <to>
        int numberOfWords = userInput.split(" ").length;
        String taskName = "";
        String from = "";
        String to = "";
        String[] userInputArray = new String[numberOfWords];
        String[] cleanedUserInputArray = new String[numberOfWords - 2];
        String[] outputArray = new String[3];
        boolean isFromPassed = false;
        boolean isToPassed = false;
        int endOfTaskNameIndex = 2;
        int endOfFromIndex = 3;

        userInputArray = userInput.split(" ");

        // Remove "/from", "/to" and put each word into cleanedUserInputArray
        for (int i = 0; i < numberOfWords; i++) {
            if (userInputArray[i].equals("/from")) {
                endOfTaskNameIndex = i;
                isFromPassed = true;
                continue;
            }

            if (userInputArray[i].equals("/to")) {
                endOfFromIndex = i;
                isToPassed = true;
                continue;
            }

            if (!isFromPassed && !isToPassed) {
                cleanedUserInputArray[i] = userInputArray[i];
            } else if (isFromPassed && !isToPassed) {
                cleanedUserInputArray[i - 1] = userInputArray[i];
            } else {
                cleanedUserInputArray[i - 2] = userInputArray[i];
            }
        }

        // Collate words for the taskName
        for (int i = 1; i < endOfTaskNameIndex; i++) {
            taskName += cleanedUserInputArray[i] + " ";
        }

        // Remove trailing space at the end of taskName
        taskName = taskName.trim();
        outputArray[0] = taskName;

        // Collate words for the `from` field
        for (int i = endOfTaskNameIndex + 1; i < endOfFromIndex; i++) {
            from += userInputArray[i] + " ";
        }

        // Remove trailing space at the end of the `from` field
        from = from.trim();
        outputArray[1] = from;

        // Collate words for the `to` field
        for (int i = endOfFromIndex + 1; i < numberOfWords; i++) {
            to += userInputArray[i] + " ";
        }

        // Remove trailing space at the end of the `to` field
        to = to.trim();
        outputArray[2] = to;

        return outputArray;

    }

    public static void main(String[] args) {

        // Start the program with a lovely greeting
        greet();

        // Run the program
        runProgram();

        // Exit the program
        exit();

    }

}