package duke.parser;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidInputIDException;
import duke.exceptions.InvalidTaskFormatException;
import duke.parser.tasks.DeadlineParser;
import duke.parser.tasks.EventParser;
import duke.parser.tasks.ToDoParser;
import duke.tasks.Task;
import duke.tasks.TaskEnum;

import java.util.Scanner;

/**
 * Processes user input
 */
public class InputParser {
    private static final String CHAR_SPACE = " ";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_HELP = "help";
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
     * @param taskType  TaskEnum, for deserializing from JSON
     * @return The Task object corresponding to user input
     * @throws InvalidTaskFormatException If user input does not match the required format.
     *                                    Exception message will describe the required format.
     */
    private static Task getTaskFromInput(Scanner input, TaskEnum taskType) throws InvalidTaskFormatException, InvalidDateTimeException {
        // validate input
        if (!input.hasNextLine()) {
            throw new InvalidTaskFormatException(taskType);
        }
        String taskDetails = input.nextLine().trim();

        Task savedTask;
        switch (taskType) {
        case DEADLINE:
            DeadlineParser deadlineParser = new DeadlineParser();
            savedTask = deadlineParser.parseInput(taskDetails);
            break;
        case EVENT:
            EventParser eventParser = new EventParser();
            savedTask = eventParser.parseInput(taskDetails);
            break;
        case TODO:
            ToDoParser todoParser = new ToDoParser();
            savedTask = todoParser.parseInput(taskDetails);
            break;
        default:
            if (taskDetails.isEmpty()) {
                throw new InvalidTaskFormatException(TaskEnum.UNDEFINED);
            }
            savedTask = new Task(taskDetails, TaskEnum.UNDEFINED);
        }

        return savedTask;
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
    public Command parseInput(String input) throws Exception {
        final String inputFirstWord = input.split(CHAR_SPACE)[0];
        boolean isExit = inputFirstWord.equals(COMMAND_BYE) || inputFirstWord.equals(COMMAND_EXIT);
        if (isExit) {
            return new ExitCommand();
        }

        if (input.isEmpty()) { // do nothing
            return new Command();
        }

        Scanner scanner = new Scanner(input);
        String inputCommand = scanner.next().toLowerCase();

        Command commandObj;
        switch (inputCommand) {
        case COMMAND_DEADLINE:
            commandObj = new AddTaskCommand(getTaskFromInput(scanner, TaskEnum.DEADLINE));
            break;
        case COMMAND_DELETE:
            commandObj = new DeleteCommand(getID(scanner));
            break;
        case COMMAND_EVENT:
            commandObj = new AddTaskCommand(getTaskFromInput(scanner, TaskEnum.EVENT));
            break;
        case COMMAND_FIND:
            commandObj = new FindCommand(scanner.nextLine());
            break;
        case COMMAND_HELP:
            commandObj = new HelpCommand();
            break;
        case COMMAND_LIST:
            commandObj = new ListCommand();
            break;
        case COMMAND_MARK:
            commandObj = new MarkCommand(getID(scanner));
            break;
        case COMMAND_TODO:
            commandObj = new AddTaskCommand(getTaskFromInput(scanner, TaskEnum.TODO));
            break;
        case COMMAND_UNMARK:
            commandObj = new UnmarkCommand(getID(scanner));
            break;
        default:
            throw new InvalidCommandException();
        }

        return commandObj;
    }
}
