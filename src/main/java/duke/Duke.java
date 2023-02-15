package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import duke.Pair;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static boolean isInUse = true;

    public static Scanner inputReader;

    public static void main(String[] args) throws FileNotFoundException {
        initDuke();
        Storage.loadData(tasks,"./data.txt");
        greetUser();
        while (isInUse) {
            String userInput = getUserInput(inputReader);
            String[] informationNeededForPerformingUserRequest = processUserInput(userInput);
            performUserRequest(tasks, informationNeededForPerformingUserRequest);
            Storage.saveData("./data.txt", tasks);
        }
    }

    public static void initDuke() {
        inputReader = new Scanner(System.in);

    }

    private static void greetUser() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        listTasks(tasks);
    }

    private static String getUserInput(Scanner in) {
        return in.nextLine();
    }

    private static String[] processUserInput(String userInput) throws IndexOutOfBoundsException {
        String[] informationNeededForPerformingUserRequest = {"", "", "", ""};
        String taskInformation;

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
        case "delete":
            taskInformation = userInput.split(" ", 2)[1];
            informationNeededForPerformingUserRequest[0] = command;
            // For "delete": info...[1] is the task number (1-indexed).
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


    public static void performUserRequest(ArrayList<Task> tasks, String[] informationNeededForPerformingUserRequest) {
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
            tasks.get(indexToBeMarked).setDone(true);
            printNotification(tasks.get(indexToBeMarked), "mark", tasks.size());
            break;
        case "unmark":
            int indexToBeUnmarked = Integer.parseInt(informationNeededForPerformingUserRequest[1]) - 1; // 0-indexed
            tasks.get(indexToBeUnmarked).setDone(false);
            printNotification(tasks.get(indexToBeUnmarked), "unmark", tasks.size());
            break;
        case "delete": // new functionality to delete tasks
            int indexToRemove = Integer.parseInt(informationNeededForPerformingUserRequest[1]) - 1; // 0-indexed
            printNotification(tasks.get(indexToRemove), "delete", tasks.size() - 1);
            tasks.remove(indexToRemove);
            break;
        case "todo":
            tasks.add(new ToDo(informationNeededForPerformingUserRequest[1]));
            printNotification(tasks.get(tasks.size() - 1), "todo", tasks.size());
            break;
        case "deadline":
            tasks.add(new Deadline(informationNeededForPerformingUserRequest[1],informationNeededForPerformingUserRequest[2]));
            printNotification(tasks.get(tasks.size() - 1), "deadline", tasks.size());
            break;
        case "event":
            tasks.add(new Event(informationNeededForPerformingUserRequest[1],informationNeededForPerformingUserRequest[2],informationNeededForPerformingUserRequest[3]));
            printNotification(tasks.get(tasks.size() - 1), "event", tasks.size());
            break;
        case "Invalid command": // earlier on we detected that such a command doesn't exist
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
        case "delete":
            System.out.println("Noted. I've removed this task:");
            task.printTask();
            System.out.println("Now you have " + numberOfTasks + " task(s) in the list.");
            break;
        case "deadline":
            // Fallthrough
        case "todo":
            // Fallthrough
        case "event":
            System.out.print("Got it. I've added this task:\n" + "  ");
            task.printTask();
            System.out.println("Now you have " + numberOfTasks + " task(s) in the list.");
            break;
        }
    }

    private static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void listTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1);
            System.out.print(".");
            tasks.get(i).printTask();
        }
    }
}


