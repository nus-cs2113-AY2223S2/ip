package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;

import error.DukeAlreadyMarkedException;
import error.DukeIllegalCommandException;
import error.DukeIllegalSyntaxException;
import error.DukeTaskDoesNotExistException;
import error.DukeIllegalCharacterException;

public class Duke {

    private static ArrayList<Task> tasks = FileOperations.loadArrayListFromFile();
    private static Scanner scanner = new Scanner(System.in);

    private static void runProgram() {

        // Variables needed
        String userInput = scanner.nextLine();

        // Loop for next tasks to add
        while (!userInput.equals("bye")) {

            try {
                CommandParser commandParser = new CommandParser();
                tasks = commandParser.manageInput(userInput, userInput.split(" ")[0], tasks);
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