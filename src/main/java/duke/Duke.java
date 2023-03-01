package duke;

import duke.command.*;
import duke.exception.BlankDescException;
import duke.exception.CommandParseException;
import duke.exception.DukeException;
import duke.exception.TimeParseException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Duke program implements a todo list application that
 * takes in "todo", "deadline", and "event" tasks.
 *
 * @author  Rayden Teo
 * @version 1.0
 * @since   2023-01-13
 */
public class Duke {

    public static int userTextCount = 0;

    /**
     * This is the main method which lets you:
     * 1) List tasks
     * 2) Mark or Unmark tasks
     * 3) Delete tasks
     * 4) Add todo, deadline, or event
     *
     * @param args String class that stores java command line arguments.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Display.printWelcome();

        String userCommand = scanner.nextLine();

        ArrayList<Task> storedUserTasks = new ArrayList<>();

        try {
            IOFile.readData(storedUserTasks);
        } catch (FileNotFoundException e) {
            File f = new File("data/tasklist.txt");
        }

        boolean isExit = false;

        while(!isExit){
            switch (userCommand.split(" ")[0]) {
            case "list":
                Display.listTasks(userTextCount, storedUserTasks);
                break;
            case "find":
                try {
                    FindTask.searchForKeyword(userCommand, storedUserTasks);
                } catch (ParseException e) {
                    CommandParseException.errorMessage();
                }
                break;
            case "bye":
                isExit = true;
                break;
            case "mark":
                ModifyTask.markTask(userCommand, storedUserTasks);
                break;
            case "unmark":
                ModifyTask.unmarkTask(userCommand, storedUserTasks);
                break;
            case "delete":
                ModifyTask.deleteTask(userCommand, storedUserTasks);
                break;
            case "todo":
                try {
                    CreateTask.createTodo(userCommand, storedUserTasks);
                } catch (BlankDescException e) {
                    BlankDescException.errorMessage("todo");
                } catch (ParseException e) {
                    TimeParseException.errorMessage();
                } catch (DukeException e) {
                    DukeException.errorMessage();
                }
                break;
            case "deadline":
                try {
                    CreateTask.createDeadline(userCommand, storedUserTasks);
                } catch (BlankDescException e) {
                    BlankDescException.errorMessage("deadline");
                } catch (ParseException e) {
                    TimeParseException.errorMessage();
                } catch (DukeException e) {
                    DukeException.errorMessage();
                }
                break;
            case "event":
                try {
                    CreateTask.createEvent(userCommand, storedUserTasks);
                } catch (BlankDescException e) {
                    BlankDescException.errorMessage("event");
                } catch (ParseException e) {
                    TimeParseException.errorMessage();
                } catch (DukeException e) {
                    DukeException.errorMessage();
                }
                break;
            default:
                Display.printInvalidInput();
                break;
            }
            if(!isExit) {
                userCommand = scanner.nextLine();
            }
        }

        try {
            IOFile.writeData(storedUserTasks);
        } catch (IOException e) {
            System.out.println("Failed to save data!");
        }

        Display.printGoodbye();

    }
}
