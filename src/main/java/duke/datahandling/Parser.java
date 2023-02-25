package duke.datahandling;

import duke.command.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser extends TaskList {

    /**
     * Obtains user inputs and push them into another function to process what the user has typed.
     * Stores or deletes tasks that are stores in the ArrayList "list", according to what the user
     * has typed.
     *
     * @param input The user input typed in through the command prompt.
     */
    public void obtainUserInputs(ArrayList<Task> list, int listSize, Scanner input) {
        String line = "start";
        while (!line.equals("bye")) {
            line = input.nextLine();
            try {
                listSize = handleUserInputs(line, list, listSize);
            } catch (DukeException de) {
                handleException(line);
            }
        }
    }

    /**
     * Returns the number of tasks found in the list, after handling the commands given by the
     * user in the form of a line of string.
     *
     * @param line     The single line of string inputted by the user.
     * @return Returns the current size of the list populated by tasks created by the user.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    public int handleUserInputs(String line, ArrayList<Task> list, int listSize) throws DukeException {
        String[] command = line.split(" ");
        //Check the first word in the line of strings (list, mark, unmark, event, etc.)
        switch (command[0]) {
        case "list":
            dukeUserInterface.printList(list, listSize, command);
            break;
        case "find":
            dukeUserInterface.printListOfTasksFound(list, listSize, command);
            break;
        case "mark":
            TaskList.markAsDone(list, listSize, command);
            break;
        case "unmark":
            TaskList.markAsUndone(list, listSize, command);
            break;
        case "delete":
            listSize = dukeUserInterface.printNewlyRemovedTask(list, listSize, command);
            break;
        case "todo":
            TaskList.createNewTodo(line, list, command);
            listSize = dukeUserInterface.printNewlyAddedTask(list, listSize);
            break;
        case "deadline":
            TaskList.createNewDeadline(line, list);
            listSize = dukeUserInterface.printNewlyAddedTask(list, listSize);
            break;
        case "event":
            TaskList.createNewEvent(line, list);
            listSize = dukeUserInterface.printNewlyAddedTask(list, listSize);
            break;
        case "bye":
            dukeUserInterface.printGoodbyeMessage(command);
            break;
        default:
            throw new DukeException();
        }
        return listSize;
    }

    /**
     * Prints a series of error message strings depending on the wrong or partially wrong input by the user.
     *
     * @param line The single line of string inputted by the user.
     */
    private void handleException(String line) {
        String[] command = line.split(" ");
        switch (command[0]) {
        case "list":
            dukeUserInterface.printListCommandError();
            break;
        case "find":
            dukeUserInterface.printFindCommandError();
            break;
        case "todo":
            dukeUserInterface.printTodoAdditionError();
            break;
        case "event":
            dukeUserInterface.printEventAdditionError();
            break;
        case "deadline":
            dukeUserInterface.printDeadlineAdditionError();
            break;
        case "mark":
            //fallthrough
        case "unmark":
            //fallthrough
        case "delete":
            dukeUserInterface.printInvalidInputError();
            break;
        default:
            dukeUserInterface.printInvalidCommandError();
            break;
        }
    }

}
