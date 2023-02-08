package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

public class Duke {
    public static final int MAX_NUMBER_OF_TASKS = 100;
    public static int tasksIndex = 0;
    public static boolean isInUse = true;
    public static Task[] tasks;
    public static Scanner inputReader;

    public static void main(String[] args) {
        initDuke();
        greetUser();
        while (isInUse) {
            String userInput = getUserInput(inputReader);
            String[] informationNeededForPerformingUserRequest = processUserInput(userInput);
            performUserRequest(tasks, informationNeededForPerformingUserRequest);
        }
    }

    public static void initDuke() {
        tasks = new Task[MAX_NUMBER_OF_TASKS];
        inputReader = new Scanner(System.in);
    }

    private static void greetUser() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static String getUserInput(Scanner in) {
        return in.nextLine();
    }

    private static String[] processUserInput(String userInput) throws IndexOutOfBoundsException {
        String[] informationNeededForPerformingUserRequest = {"", "", "", ""};
        String taskInformation = "";

        // for all tasks: info...[0] is the command
        String command = userInput.split(" ", 2)[0];

        switch (command) {
        case "mark":
            taskInformation = userInput.split(" ", 2)[1];
            informationNeededForPerformingUserRequest[0] = command;
            // For "mark", "unmark": info...[1] is the task number (1-indexed).
            informationNeededForPerformingUserRequest[1] = taskInformation;
        case "unmark":
            taskInformation = userInput.split(" ", 2)[1];
            informationNeededForPerformingUserRequest[0] = command;
            // For "mark", "unmark": info...[1] is the task number (1-indexed).
            informationNeededForPerformingUserRequest[1] = taskInformation;
        case "todo":
            try {
                taskInformation = userInput.split(" ", 2)[1];
                informationNeededForPerformingUserRequest[0] = command;
                informationNeededForPerformingUserRequest[1] = taskInformation;
            } catch(IndexOutOfBoundsException e) {
                informationNeededForPerformingUserRequest[0] = "☹ OOPS!!! The description of a todo cannot be empty.";
            }
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
            inputReader.close();
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
        case "Invalid command": // earlier on we detected that such a command doesnt exist
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        default: // earlier on we detected correct command but invalid taskInformation
            System.out.println(informationNeededForPerformingUserRequest[0]);
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


