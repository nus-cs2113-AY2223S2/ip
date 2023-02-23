package duke.parser;

import duke.command.*;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidInputIDException;
import duke.exceptions.InvalidTaskFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskEnum;
import duke.tasks.ToDo;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final String KEYWORD_BY = "/by";
    private static final String KEYWORD_FROM = "/from";
    private static final String KEYWORD_TO = "/to";
    private static final Pattern patternToDo = Pattern.compile(
            "^(\\S+[\\S\\s]*)$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern patternDeadline = Pattern.compile(
            "^(\\S+[\\S\\s]*)(\\s+/by\\s+)(\\S+[\\S\\s]*)$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern patternEvent = Pattern.compile(
            "^(\\S+[\\S\\s]*)(\\s+/from\\s+)(\\S+[\\S\\s]*)(\\s+/to\\s+)(\\S+[\\S\\s]*)$",
            Pattern.CASE_INSENSITIVE);

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

    public static void checkValidInput(String input, Pattern pattern, TaskEnum taskType) throws InvalidTaskFormatException {
        Matcher matcher = pattern.matcher(input.trim());
        if (input.trim().isEmpty() || !matcher.find()) {
            throw new InvalidTaskFormatException(taskType);
        }
    }

    private static ToDo parseToDoInput(String input) throws InvalidTaskFormatException {
        checkValidInput(input, patternToDo, TaskEnum.TODO);
        return new ToDo(input);
    }

    private static Event parseEventInput(String input) throws InvalidTaskFormatException, InvalidDateTimeException {
        checkValidInput(input, patternEvent, TaskEnum.EVENT);
        int fromStartIndex = input.indexOf(KEYWORD_FROM);
        int toStartIndex = input.indexOf(KEYWORD_TO);
        String description = input.substring(0, fromStartIndex).trim();
        String fromString = input.substring(fromStartIndex + KEYWORD_FROM.length(), toStartIndex).trim();
        String toString = input.substring(toStartIndex + KEYWORD_TO.length()).trim();
        LocalDateTime fromDateTime = DateTimeParser.parse(fromString);
        LocalDateTime toDateTime = DateTimeParser.parse(toString);
        return new Event(description, fromDateTime, toDateTime);
    }

    private static Deadline parseDeadlineInput(String input) throws InvalidTaskFormatException, InvalidDateTimeException {
        checkValidInput(input, patternDeadline, TaskEnum.DEADLINE);
        int byStartIndex = input.indexOf(KEYWORD_BY);
        String description = input.substring(0, byStartIndex).trim();
        String byString = input.substring(byStartIndex + KEYWORD_BY.length()).trim();
        LocalDateTime byDateTime = DateTimeParser.parse(byString);
        return new Deadline(description, byDateTime);
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
        case TODO:
            task = parseToDoInput(taskDetails);
            break;
        case EVENT:
            task = parseEventInput(taskDetails);
            break;
        case DEADLINE:
            task = parseDeadlineInput(taskDetails);
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
        case COMMAND_FIND:
            result = new FindCommand(scanner.nextLine());
            break;
        default:
            throw new InvalidCommandException();
        }

        return result;
    }
}
