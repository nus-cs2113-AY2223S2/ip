package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;

import error.DukeAlreadyMarkedException;
import error.DukeIllegalCommandException;
import error.DukeIllegalSyntaxException;
import error.DukeTaskDoesNotExistException;
import error.DukeIllegalCharacterException;

public class Duke {

    static ArrayList<Task> tasks = FileOperations.loadArrayListFromFile();

    private static void runProgram() {

        // Variables needed
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        // Loop for next tasks to add
        while (!userInput.equals("bye")) {

            try {
                manageInput(userInput, userInput.split(" ")[0]);
            }

            // This runs when the user enters an invalid command
            catch (DukeIllegalCommandException exception) {
                PrintOperations.illegalCommand();
            }

            // This runs when a user enters the wrong syntax for a command
            catch (DukeIllegalSyntaxException exception) {
                PrintOperations.illegalSyntax();
            }

            // This runs when a user tries to mark or unmark a task that already has that marking
            catch (DukeAlreadyMarkedException exception) {
                PrintOperations.alreadyMarked();
            }

            // This runs when a user tries to delete a non-existent task index number
            catch (DukeTaskDoesNotExistException exception) {
                PrintOperations.doesNotExist();
            }

            // This runs when a user enters `|` in their input
            catch (DukeIllegalCharacterException exception) {
                PrintOperations.illegalCharacter();
            }

            // This runs when there is an unexpected error with file reading/writing
            catch (IOException exception) {
                PrintOperations.io();
            }

            // Enter the next input and continue the loop
            finally {
                PrintOperations.horizontalLine();
                userInput = scanner.nextLine();
            }
        }

    }

    private static void manageInput(String userInput, String command)
            throws DukeIllegalCharacterException, DukeTaskDoesNotExistException,
            DukeAlreadyMarkedException, DukeIllegalSyntaxException,
            DukeIllegalCommandException, IOException {

        PrintOperations.horizontalLine();

        if (userInput.contains("|")) {
            throw new DukeIllegalCharacterException();
        }

        // Print list upon user request
        if (userInput.equals("list")) {
            PrintOperations.list(tasks);
        }

        // Handle mark, unmark, and delete cases
        else if (userInput.startsWith("mark") || userInput.startsWith("unmark")
                || userInput.startsWith("delete")) {
            handleMarkAndDelete(userInput);
        }

        // Add other tasks into list
        else {
            addNewTask(userInput, command);
        }

        // Save task ArrayList information into tasks.txt
        FileOperations.saveArrayListToFile(tasks);

    }

    private static void handleMarkAndDelete(String userInput)
            throws DukeTaskDoesNotExistException, DukeAlreadyMarkedException {
        int taskIndex = Integer.parseInt(userInput.substring(userInput.length() - 1)) - 1;

        // If task does not exist, throw exception
        if ((taskIndex + 1) > tasks.size() || taskIndex < 0) {
            throw new DukeTaskDoesNotExistException();
        }

        // Mark as done
        if (userInput.startsWith("mark") && userInput.charAt(4) == ' ') {
            tasks.get(taskIndex).markAsDone();
        }

        // Mark as undone
        else if (userInput.startsWith("unmark") && userInput.charAt(6) == ' ') {
            tasks.get(taskIndex).markAsNotDone();
        }

        // Delete task from array
        else if (userInput.startsWith("delete")) {
            PrintOperations.taskRemoved(taskIndex, tasks);
            tasks.remove(taskIndex);
            PrintOperations.numberOfTasks(tasks);
        }
    }

    private static void addNewTask(String userInput, String command)
            throws DukeIllegalSyntaxException, DukeIllegalCommandException {

        Task newTask;

        // Depending on the type of command, the input gets parsed into the different handlers
        switch (command) {
        case "deadline":
            String[] deadlineArgs = Deadline.handler(userInput);
            newTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
            break;
        case "event":
            String[] eventArgs = Event.handler(userInput);
            newTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
            break;
        case "todo":
            String todoCommand = Todo.handler(userInput);
            newTask = new Todo(todoCommand);
            break;
        default:
            throw new DukeIllegalCommandException();
        }

        tasks.add(newTask);
        PrintOperations.addTask(newTask);
        PrintOperations.numberOfTasks(tasks);
    }

    private static void exit() {
        PrintOperations.bye();
        System.exit(0);
    }
    
    public static void main(String[] args) {

        // Start the program with a lovely greeting
        PrintOperations.greet();

        // Run the program
        runProgram();

        // Exit the program
        exit();

    }

}