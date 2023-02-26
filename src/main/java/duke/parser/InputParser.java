package duke.parser;

import duke.command.*;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidInputIDException;
import duke.exceptions.InvalidTaskFormatException;
import duke.tasks.Task;
import duke.tasks.TaskEnum;

import java.util.Scanner;

/**
 * Processes user input
 */
public class InputParser {
    private static final String CHAR_SPACE = " ";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
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
    private static Task getTaskFromInput(Scanner input, TaskEnum type) throws InvalidTaskFormatException, InvalidDateTimeException {
        // validate input
        if (!input.hasNextLine()) {
            throw new InvalidTaskFormatException(type);
        }
        String taskDetails = input.nextLine().trim();

        Task task;
        switch (type) {
        case DEADLINE:
            DeadlineParser deadlineParser = new DeadlineParser();
            task = deadlineParser.parseInput(taskDetails);
            break;
        case EVENT:
            EventParser eventParser = new EventParser();
            task = eventParser.parseInput(taskDetails);
            break;
        case TODO:
            ToDoParser todoParser = new ToDoParser();
            task = todoParser.parseInput(taskDetails);
            break;
        default:
            if (taskDetails.isEmpty()) {
                throw new InvalidTaskFormatException(TaskEnum.UNDEFINED);
            }
            task = new Task(taskDetails, TaskEnum.UNDEFINED);
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
    public Command parse(String input) throws Exception {
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
        case COMMAND_DEADLINE:
            result = new AddTaskCommand(getTaskFromInput(scanner, TaskEnum.DEADLINE));
            break;
        case COMMAND_DELETE:
            result = new DeleteCommand(getID(scanner));
            break;
        case COMMAND_EVENT:
            result = new AddTaskCommand(getTaskFromInput(scanner, TaskEnum.EVENT));
            break;
        case COMMAND_FIND:
            result = new FindCommand(scanner.nextLine());
            break;
        case COMMAND_LIST:
            result = new ListCommand();
            break;
        case COMMAND_MARK:
            result = new MarkCommand(getID(scanner));
            break;
        case COMMAND_TODO:
            result = new AddTaskCommand(getTaskFromInput(scanner, TaskEnum.TODO));
            break;
        case COMMAND_UNMARK:
            result = new UnmarkCommand(getID(scanner));
            break;
        default:
            throw new InvalidCommandException();
        }

        return result;
    }
}
