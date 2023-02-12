package app;

import app.exceptions.InvalidCommandException;
import app.tasks.Deadline;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String line = ("─".repeat(50));

    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();
        printHelloMessage();
        Scanner myObj = new Scanner(System.in);

        while (true) {
            String userInput = myObj.nextLine();
            String[] userInputArray = userInput.split(" ");
            String commandWord = userInputArray[0];
            String commandDescriptor = userInput.substring(commandWord.length()).trim();
            try {
                switch (commandWord) {
                case "bye":
                    printByeMessage();
                    return;
                case "list":
                    Task.printTasks(tasks);
                    break;
                case "delete":
                    Task.deleteTask(tasks, commandDescriptor);
                    break;
                case "todo":
                    ToDo.todoHandler(tasks, commandDescriptor);
                    break;
                case "deadline":
                    Deadline.deadlineHandler(tasks, commandDescriptor);
                    break;
                case "event":
                    Event.eventHandler(tasks, commandDescriptor);
                    break;
                case "mark":
                case "unmark":
                    Task.taskStatusHandler(tasks, commandWord, commandDescriptor);
                    break;
                default:
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                e.printErrorMessage(commandWord);
                System.out.println(line);
            }
        }
    }

    private static void printHelloMessage() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);
    }

    private static void printByeMessage() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
