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

        String userInput = scanner.nextLine();

        // Loop for next tasks to add
        while (!userInput.equals("bye")) {

            try {
                CommandParser commandParser = new CommandParser();
                commandParser.manageInput(userInput, userInput.split(" ")[0], tasks);
            } catch (DukeIllegalCommandException exception) {
                PrintOperations.illegalCommand();
            } catch (DukeIllegalSyntaxException exception) {
                PrintOperations.illegalSyntax();
            } catch (DukeAlreadyMarkedException exception) {
                PrintOperations.alreadyMarked();
            } catch (DukeTaskDoesNotExistException exception) {
                PrintOperations.doesNotExist();
            } catch (DukeIllegalCharacterException exception) {
                PrintOperations.illegalCharacter();
            } catch (IOException exception) {
                PrintOperations.io();
            } finally {
                PrintOperations.horizontalLine();
                userInput = scanner.nextLine();
            }
        }

    }

    private static void exit() {
        PrintOperations.bye();
        System.exit(0);
    }

    /**
     * Main method of the program.
     */
    public static void main(String[] args) {
        PrintOperations.greet();
        runProgram();
        exit();
    }

}