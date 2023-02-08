import java.util.Scanner;

public class Duke {
    public static final int MAX_NUMBER_OF_TASKS = 100;
    public static int tasksIndex = 0;
    public static boolean isInUse = true;
    public static Task[] tasks;
    public static Scanner in;

    public static void main(String[] args) {
        initDuke();
        greetUser();
        while (isInUse) {
            String userInput = getUserInput(in);
            String[] informationNeededForPerformingUserRequest = processUserInput(userInput);
            performUserRequest(tasks, informationNeededForPerformingUserRequest);
        }
    }

    public static void initDuke() {
        tasks = new Task[MAX_NUMBER_OF_TASKS];
        in = new Scanner(System.in);
    }

    private static void greetUser() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static String getUserInput(Scanner in) {
        return in.nextLine();
    }

    private static String[] processUserInput(String userInput) {
        String[] informationNeededForPerformingUserRequest = {"", "", "", ""};
        String taskInformation = "";

        // for all tasks: info...[0] is the command
        String command = userInput.split(" ", 2)[0];

        switch (command) {
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "todo":
            taskInformation = userInput.split(" ", 2)[1];
            informationNeededForPerformingUserRequest[0] = command;
            // For "mark", "unmark": info...[1] is the task number (1-indexed). For "todo": info...[1] is the task name.
            informationNeededForPerformingUserRequest[1] = taskInformation;
            break;
        case "deadline":
            taskInformation = userInput.split(" ", 2)[1];
            informationNeededForPerformingUserRequest[0] = command;
            // For "deadline": info...[1] is the name, info...[2] is the deadline.
            informationNeededForPerformingUserRequest[1] = taskInformation.split(" /by ")[0];
            informationNeededForPerformingUserRequest[2] = taskInformation.split(" /by ")[1];
            break;
        case "event":
            taskInformation = userInput.split(" ", 2)[1];
            informationNeededForPerformingUserRequest[0] = command;
            // For "event": info...[1] is the name, info...[2] is when the task starts, info...[3] is when the task ends
            informationNeededForPerformingUserRequest[1] = taskInformation.substring(0, taskInformation.indexOf(" /from "));
            informationNeededForPerformingUserRequest[2] = taskInformation.substring(taskInformation.indexOf(" /from ") + 7, taskInformation.indexOf(" /to "));
            informationNeededForPerformingUserRequest[3] = taskInformation.substring(taskInformation.indexOf(" /to ") + 5);
            break;
        case "bye":
            // Fallthrough
        case "list":
            informationNeededForPerformingUserRequest[0] = command;
            break;
        default: // not a valid command
            // ensure that the next function can tell that something went wrong with the command - flag it somehow
            informationNeededForPerformingUserRequest[0] = "Invalid command";
            break;
        }
        return informationNeededForPerformingUserRequest;
    }


    public static void performUserRequest(Task[] tasks, String[] informationNeededForPerformingUserRequest) {
        switch (informationNeededForPerformingUserRequest[0]) {
        case "bye":
            isInUse = false;
            printExitMessage();
            break;
        case "list":
            listTasks(tasks);
            break;
        case "mark":
            int indexToBeMarked = Integer.parseInt(informationNeededForPerformingUserRequest[1]) - 1; // 0-indexed
            tasks[indexToBeMarked].setDone(true);
            printNotification(tasks[indexToBeMarked], "mark", tasksIndex + 1);
            break;
        case "unmark":
            int indexToBeUnmarked = Integer.parseInt(informationNeededForPerformingUserRequest[1]) - 1; // 0-indexed
            tasks[indexToBeUnmarked].setDone(false);
            printNotification(tasks[indexToBeUnmarked], "unmark", tasksIndex + 1);
            break;
        case "todo":
            tasks[tasksIndex] = new ToDo(informationNeededForPerformingUserRequest[1]);
            printNotification(tasks[tasksIndex], "todo", tasksIndex + 1);
            tasksIndex++;
            break;
        case "deadline":
            tasks[tasksIndex] = new Deadline(informationNeededForPerformingUserRequest[1],informationNeededForPerformingUserRequest[2]);
            printNotification(tasks[tasksIndex], "deadline", tasksIndex + 1);
            tasksIndex++;
            break;
        case "event":
            tasks[tasksIndex] = new Event(informationNeededForPerformingUserRequest[1],informationNeededForPerformingUserRequest[2],informationNeededForPerformingUserRequest[3]);
            printNotification(tasks[tasksIndex], "event", tasksIndex + 1);
            tasksIndex++;
            break;
        default: // something went wrong, it is none of the cases -> must have been detected by processsInput() earlier
            // print error message & user guide
            System.out.println("Invalid input");
            System.out.println("The input can be as follows:");
            System.out.println("todo taskname (for example: todo eat)");
            System.out.println("deadline taskname /by deadline (for example: deadline homework /by Wednesday)");
            System.out.println("event taskname /from starttime /to endtime (for example: event party /from 7pm /to 11pm");
            break;
        }
    }

    private static void printNotification(Task task, String action, int numberOfTasks) {
        switch (action) {
        case "unmark":
            System.out.println("OK, I've marked this task as not done yet:");
            task.printTask();
            break;
        case "mark":
            System.out.println("Nice! I've marked this task as done:");
            task.printTask();
            break;
        case "deadline":
            // Fallthrough
        case "todo":
            // Fallthrough
        case "event":
            System.out.print("Got it. I've added this task:\n" + "  ");
            task.printTask();
            System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
            break;
        }
    }

    private static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void listTasks(Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksIndex; i++) {
            System.out.print(i + 1);
            System.out.print(".");
            tasks[i].printTask();
        }
    }
}


