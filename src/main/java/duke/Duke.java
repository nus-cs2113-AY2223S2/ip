package duke;

import tasks.Task;
import utility.Storage;
import utility.Ui;
import utility.Parser;
import command.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

public class Duke {
    public static void main(String[] args) {
        // tasks for storing tasks + taskCounter to track next empty cell
        ArrayList<Task> tasks = new ArrayList<>();

        // Setting up input reading
        final Scanner INPUT = new Scanner(System.in);

        // Start up sequence
        Ui.startUp();
        Storage.startFileProcessing(tasks);
        Ui.promptInput();

        // Reads inputs until "bye" is sent
        while (true) {
            String userInput = INPUT.next();
            userInput += INPUT.nextLine();

            // Extracts first word in input
            // Used to check if tasks are to be marked/unmarked
            String firstWord = Parser.getCommand(userInput);

            switch (firstWord) {
            case ("bye"):
                // Shut down process
                Storage.endFileProcessing(tasks);
                Ui.shutDown();
                return;

            case ("find"):
                try {
                    find.executeFind(userInput, tasks);
                } catch (IndexOutOfBoundsException e) {
                    Ui.findError(userInput);
                }
                break;

            case ("list"):
                // Prints all contents of task Array
                list.printTasks(tasks);
                break;

            case ("mark"):
                try {
                    // Sets specified task to complete
                    mark.executeMark(userInput, tasks);
                } catch (IndexOutOfBoundsException e) {
                    // Invalid index
                    Ui.invalidIndex();
                } catch (NumberFormatException e) {
                    // Invalid input - a string or non-integer
                    Ui.requiresNumber();
                }
                break;

            case ("unmark"):
                try {
                    // Sets specified task to incomplete
                    unmark.executeUnmark(userInput, tasks);
                } catch (IndexOutOfBoundsException e) {
                    // Invalid index
                    Ui.invalidIndex();
                } catch (NumberFormatException e) {
                    // Invalid input - a string or non-integer
                    Ui.requiresNumber();
                }
                break;

            case ("delete"):
                try {
                    delete.executeDelete(userInput, tasks);
                } catch (IndexOutOfBoundsException e) {
                    // Invalid index
                    Ui.invalidIndex();
                } catch (NumberFormatException e) {
                    // Invalid input - a string or non-integer
                    Ui.requiresNumber();
                }
                break;

            case ("todo"):
                try {
                    todo.executeTodo(userInput, tasks);
                } catch (IndexOutOfBoundsException e) {
                    // Invalid input
                    Ui.todoError(userInput);
                }
                break;

            case ("deadline"):
                try {
                    deadline.executeDeadline(userInput, tasks);
                } catch (IndexOutOfBoundsException e) {
                    // Invalid input
                    Ui.deadlineError(userInput);
                } catch (DateTimeParseException e) {
                    // Invalid time input
                    Ui.dateTimeError();
                }
                break;

            case ("event"):
                try {
                    event.executeEvent(userInput, tasks);
                } catch (IndexOutOfBoundsException e) {
                    Ui.eventError(userInput);
                } catch (DateTimeParseException e) {
                    Ui.dateTimeError();
                }
                break;

            default:
                // If none of the above, invalid command - prints error informing user
                Ui.invalidCommand(userInput);
                break;
            }
            Ui.promptInput();
        }
    }
}
