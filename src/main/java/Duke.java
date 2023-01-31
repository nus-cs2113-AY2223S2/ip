import java.util.Scanner;

public class Duke {
    public static final String line = ("─".repeat(50));

    public static void main(String[] args) {

        Task[] tasks = new Task[100];

        printHelloMessage();

        Scanner myObj = new Scanner(System.in);
        int index = 0;

        while (true) {
            String userInput = myObj.nextLine();
            String[] userInputArray = userInput.split(" ");
            String commandWord = userInputArray[0];
            String commandDescriptor = userInput.substring(commandWord.length()).trim();

            switch (commandWord) {
            case "bye":
                printByeMessage();
                return;
            case "list":
                printTasks(tasks, index);
                break;
            case "todo":
                todoHandler(tasks, index, commandDescriptor);
                index++;
                break;
            case "deadline":
                deadlineHandler(tasks, index, commandDescriptor);
                index++;
                break;
            case "event":
                eventHandler(tasks, index, commandDescriptor);
                index++;
                break;
            case "mark":
            case "unmark":
                taskStatusHandler(tasks, commandWord, commandDescriptor);
                break;
            default:
                invalidCommandHandler();
            }
        }
    }

    private static void printHelloMessage() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);
    }

    private static void taskStatusHandler(Task[] tasks, String commandWord, String commandDescriptor) {
        System.out.println(line);
        int taskNumber = Integer.parseInt(commandDescriptor);
        if (commandWord.equals("mark")) {
            tasks[taskNumber - 1].setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[taskNumber - 1]);
        } else {
            tasks[taskNumber - 1].setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[taskNumber - 1]);
        }
        System.out.println(line);
    }

    private static void eventHandler(Task[] tasks, int index, String commandDescriptor) {
        System.out.println(line);
        String[] fromParts = commandDescriptor.split("/from");
        String taskDescription = fromParts[0].trim();
        String[] toParts = fromParts[1].split("/to");
        String startTime = toParts[0].trim();
        String endTime = toParts[1].trim();
        tasks[index] = new Event(taskDescription, startTime, endTime);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[index]);
        System.out.printf("Now you have %d tasks in the list.\n", index + 1);
        System.out.println(line);
    }

    private static void deadlineHandler(Task[] tasks, int index, String commandDescriptor) {
        System.out.println(line);
        String[] parts = commandDescriptor.split("/by");
        String taskDescription = parts[0];
        String deadline = parts[1];
        tasks[index] = new Deadline(taskDescription, deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[index]);
        System.out.printf("Now you have %d tasks in the list.\n", index + 1);
        System.out.println(line);
    }

    private static void todoHandler(Task[] tasks, int index, String commandDescriptor) {
        System.out.println(line);
        tasks[index] = new ToDo(commandDescriptor);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[index]);
        System.out.printf("Now you have %d tasks in the list.\n", index + 1);
        System.out.println(line);
    }

    private static void printTasks(Task[] tasks, int index) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks[i]);
        }
        System.out.println(line);
    }

    private static void invalidCommandHandler() {
        System.out.println(line);
        System.out.println("Please enter a valid command");
        System.out.println(line);
    }

    private static void printByeMessage() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
