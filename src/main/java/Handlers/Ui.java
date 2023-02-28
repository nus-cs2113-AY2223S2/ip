package Handlers;

import java.util.Scanner;
import Tasks.Task;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

public interface Ui {

    /**
     * Takes in user inputs, extracts the command and calls the relevant methods in
     * TaskManager
     */
    public static void takeUserInputs() {

        boolean isComplete = false;
        Scanner in = new Scanner(System.in);

        printHello();
        printLineBreak();

        while (!isComplete) {

            String line = in.nextLine();
            String firstWord = Parser.getFirstWord(line);

            switch (firstWord) {
            case "mark":
                TaskManager.markTask(Parser.getTaskNumber(line));
                break;
            case "unmark":
                TaskManager.unmarkTask(Parser.getTaskNumber(line));
                break;
            case "delete":
                int taskNumberDelete = Parser.getTaskNumber(line);
                TaskManager.deleteTask(taskNumberDelete);
                break;
            case "list":
                TaskManager.listTask();
                break;
            case "todo":
                Parser.addTodoTask(line);
                break;
            case "deadline":
                Parser.addDeadlineTask(line);
                break;
            case "event":
                Parser.addEventTask(line);
                break;
            case "find":
                TaskManager.findTask(Parser.getTaskDescription(line));
                break;
            case "bye":
                isComplete = true;
                break;
            default:
                System.out.println("Unrecognized command. Please try again.");
                break;
            }

            Ui.printLineBreak();
        }

        printBye();
        in.close();
    }

    /**
     * Prints the welcome message
     */
    private static void printHello() {
        System.out.println("________         __         __________");
        System.out.println("\\______ \\  __ __|  | __ ____\\______   \\__ __  ____   ____   ___________ ");
        System.out.println(" |    |  \\|  |  \\  |/ // __ \\|       _/  |  \\/    \\ /    \\_/ __ \\_  __ \\ ");
        System.out.println(" |    `   \\  |  /    <\\  ___/|    |   \\  |  /   |  \\   |  \\  ___/|  | \\/ ");
        System.out.println("/_______  /____/|__|_ \\\\___  >____|_  /____/|___|  /___|  /\\___  >__| ");
        System.out.println("        \\/           \\/    \\/       \\/           \\/     \\/     \\/ ");
        System.out.println("Hello! I'm DukeRunner\nWhat can I do for you?\n");
    }

    /**
     * Prints the goodbye message
     */
    private static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a line break
     */
    private static void printLineBreak() {
        System.out.println("==============================\n");
    }

    public static void addedTodoMessage(Todo todoTask) {
        System.out.println("Got it. I've added this task:\n" + todoTask.describeTask());
        System.out.println("\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.\n");
    }

    public static void addedDeadlineMessage(Deadline deadlineTask) {
        System.out.println("Got it. I've added this task:\n" + deadlineTask.describeTask());
        System.out.println("\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.\n");
    }

    public static void addedEventMessage(Event eventTask) {
        System.out.println("Got it. I've added this task:\n" + eventTask.describeTask());
        System.out.println("\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.\n");
    }

    public static void deletedTaskMessage(int index) {
        System.out.println(
                "Noted. I've removed this task:\n" + TaskManager.getTaskList().get(index - 1).describeTask() + "\n");
    }

    public static void taskCountMessage() {
        System.out.println("\nYou have " + TaskManager.getTaskCount() + " tasks in the list.\n");
    }

    public static void markTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.describeTask() + "\n");
    }

    public static void unmarkTaskMessage(Task task) {
        System.out.println("OK, Ive marked this task as not done yet:\n" + task.describeTask() + "\n");
    }

    public static void describeTaskMessage(int existingTaskCount, Task item) {
        System.out.println(existingTaskCount + ". " + item.describeTask());
    }

    public static void fileErrorMessage() {
        System.out.println("Error reading file");
    }

    public static void taskNumberOutofBoundsMessage() {
        System.out.println("task number does not exist.");
    }

    public static void taskNumberInvalidTypeMessage() {
        System.out.println("task number must be a number.");
    }

    public static void taskNumberMissingMessage() {
        System.out.println("task number must be stated.");
    }

    public static void taskDescriptionEmptyMessage() {
        System.out.println("task description for find function cannot be empty.");
    }

    public static void todoDescriptionMissingMessage() {
        System.out.println("description for todo cannot be empty.");
    }

    public static void deadlineDescriptionMissingMessage() {
        System.out.println("description for deadline cannot be empty.");
    }

    public static void eventDescriptionMissingMessage() {
        System.out.println("description for event cannot be empty.");
    }

    public static void emptyTaskListMessage() {
        System.out.println("You have no tasks in your list yet!");
    }

    public static void createFileErrorMessage() {
        System.out.println("Error creating file");
    }

    public static void fileNotFoundMessage() {
        System.out.println("File not found");
    }

    public static void writeToFileErrorMessage() {
        System.out.println("Error writing to file");
    }

}
