package app;

import app.exceptions.InvalidCommandException;
import app.save.FileManager;
import app.tasks.Deadline;
import app.tasks.Event;
import app.tasks.Task;
import app.tasks.ToDo;
import app.ui.Greetings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String line = ("─".repeat(50));
    public static void main(String[] args) {

        try {
            FileManager.loadTasks();
        } catch (IOException | InvalidCommandException e) {
            throw new RuntimeException(e);
        }

        Greetings.printHelloMessage();
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner myObj = new Scanner(System.in);

        while (true) {
            String userInput = myObj.nextLine();
            String[] userInputArray = userInput.split(" ");
            String commandWord = userInputArray[0];
            String commandDescriptor = userInput.substring(commandWord.length()).trim();
            try {
                switch (commandWord) {
                case "bye":
                    Greetings.printByeMessage();
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
}
