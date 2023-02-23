package duke.parser;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidInputIDException;
import duke.exceptions.InvalidTaskFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskEnum;
import duke.tasks.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Processes user input
 */
public class Parser {
    private static final String CHAR_SPACE = " ";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_SAVE = "save";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_UNMARK = "unmark";

    /**
     * Reads the task ID input by the user.
     *
     * @param scanner Scanner with the ID to be read
     * @return Task ID
     * @throws InvalidInputIDException If the given ID is invalid
     */
    private static int getID(Scanner scanner) throws InvalidInputIDException {
        if (!scanner.hasNextInt()) {
            throw new InvalidInputIDException();
        }
        return scanner.nextInt();
    }

    /**
     * Reads the user input and creates the corresponding task.
     *
     * @param input User input
     * @param type  TaskEnum, for deserializing from JSON
     * @return The Task object corresponding to user input
     * @throws InvalidTaskFormatException If user input does not match the required format.
     *                                    Exception message will describe the required format.
     */
    private static Task getTaskFromInput(Scanner input, TaskEnum type) throws InvalidTaskFormatException {
        // validate input
        if (!input.hasNextLine()) {
            throw new InvalidTaskFormatException(type);
        }
        String taskDetails = input.nextLine().trim();
        if (!ToDo.isValidInput(taskDetails) || taskDetails.isEmpty()) {
            throw new InvalidTaskFormatException(type);
        }

        Task task;
        ArrayList<String> details;
        switch (type) {
        case TODO:
            details = ToDo.convertInputIntoDetails(taskDetails);
            task = new ToDo(details);
            break;
        case EVENT:
            details = Event.convertInputIntoDetails(taskDetails);
            task = new Event(details);
            break;
        case DEADLINE:
            details = Deadline.convertInputIntoDetails(taskDetails);
            task = new Deadline(details);
            break;
        default:
            details = Task.convertInputIntoDetails(taskDetails);
            task = new Task(details);
        }

        return task;
    }

    /**
     * Reads user input and creates the corresponding Command object to be executed
     *
     * @param input String containing the command and parameters
     * @throws InvalidTaskFormatException If user input does not match the required format.
     *                                    Exception message will describe the required format.
     * @throws InvalidInputIDException    If the given ID is invalid
     * @throws InvalidCommandException    If the command does not match any supported commands
     */
    public Command parse(String input)
            throws InvalidTaskFormatException, InvalidInputIDException, InvalidCommandException {
        boolean isExit = input.split(CHAR_SPACE)[0].equals(COMMAND_EXIT);
        if (isExit) {
            return new ExitCommand();
        }

        if (input.isEmpty()) { // do nothing
            return new Command();
        }

        Scanner scanner = new Scanner(input);
        String command = scanner.next().toLowerCase();

        Command result;
        switch (command) {
        case COMMAND_LIST:
            result = new ListCommand();
            break;
        case COMMAND_MARK:
            result = new MarkCommand(getID(scanner));
            break;
        case COMMAND_UNMARK:
            result = new UnmarkCommand(getID(scanner));
            break;
        case COMMAND_TODO:
            result = new AddTaskCommand(getTaskFromInput(scanner, TaskEnum.TODO));
            break;
        case COMMAND_DEADLINE:
            result = new AddTaskCommand(getTaskFromInput(scanner, TaskEnum.DEADLINE));
            break;
        case COMMAND_EVENT:
            result = new AddTaskCommand(getTaskFromInput(scanner, TaskEnum.EVENT));
            break;
        case COMMAND_DELETE:
            result = new DeleteCommand(getID(scanner));
            break;
        default:
            throw new InvalidCommandException();
        }

        return result;
    }
}
