package inu.parser;

import inu.commands.Command;
import inu.commands.TodoCommand;
import inu.commands.DeadlineCommand;
import inu.commands.EventCommand;
import inu.commands.DeleteCommand;
import inu.commands.MarkCommand;
import inu.commands.UnMarkCommand;
import inu.commands.ListWithDateCommand;
import inu.commands.ListDefaultCommand;
import inu.commands.FindCommand;
import inu.commands.ExitCommand;
import inu.commands.InvalidCommand;
import inu.commons.Messages;
import inu.exceptionhandling.*;
import inu.task.DeadLine;
import inu.task.Event;
import inu.task.Task;
import inu.task.Todo;
import inu.task.TaskList;
import inu.commons.Util;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Parser {

    private static final Scanner input = new Scanner(System.in);
    private static String[] userString;
    private static final int INDEX_COMMAND = 0;
    private static final int INDEX_ENTRY = 1;
    private static final int USER_STRING_SPLIT_LIMIT = 2;
    private static final int DECODED_TASK_TYPE = 0;
    private static final int DECODED_MARK = 1;
    private static final int DECODED_TASK = 2;
    private static final int DECODED_DUE_DATE = 3;
    private static final int DECODED_FROM_DATE = 3;
    private static final int DECODED_TO_DATE = 4;
    private static final String DECODE_TODO = "T";
    private static final String DECODE_DEADLINE = "D";
    private static final String DECODE_EVENT = "E";
    private static final String DECODE_MARKED = "[X]";

    /**
     * Reads user input as a string and converts it into an array.
     * This allows inputs to be processed as potential command instructions by
     * splitting inputs into commands and task descriptions.
     *
     * @return String array of user input
     */
    public static String[] readCommand() {
        String userInput = input.nextLine();
        userString = userInput.split(" ", USER_STRING_SPLIT_LIMIT);
        return userString;
    }

    /**
     * Parses user input into command for execution.
     * This method also handles empty inputs.
     *
     * @param taskList the task list where the task will be added to.
     * @return the command based on the user input.
     */
    public static Command parseCommand(TaskList taskList) {
        userString = readCommand();
        String command = userString[INDEX_COMMAND];

        switch (command) {
        case TodoCommand.COMMAND_WORD:
            return runTodo();
        case DeadlineCommand.COMMAND_WORD:
            return runDeadline();
        case EventCommand.COMMAND_WORD:
            return runEvent();
        case DeleteCommand.COMMAND_WORD:
            return runDelete(taskList);
        case ListWithDateCommand.COMMAND_WORD:
            return runListWithDate();
        case FindCommand.COMMAND_WORD:
            return runFindCommand();
        case MarkCommand.COMMAND_WORD:
            return runMark(taskList);
        case UnMarkCommand.COMMAND_WORD:
            return runUnmark(taskList);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand(Messages.MESSAGE_INVALID);
        }
    }

    /**
     * Collects user input and prepares {@code TodoCommand} for execution.
     * This method handles empty user inputs.
     *
     * @return the {@code TodoCommand} with the user input as its description.
     * Else an {@code InvalidCommand} with a corresponding prompt..
     */
    public static Command runTodo() {
        try {
            String description = userString[INDEX_ENTRY].trim();
            ExceptionManager.checkEmptyString(description);
            return new TodoCommand(description);
        } catch (ArrayIndexOutOfBoundsException | EmptyStringException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_TODO);
        }
    }

    /**
     * Collects user input and prepares {@code DeadlineCommand} for execution.
     * This method handles empty user inputs.
     *
     * @return the {@code DeadlineCommand} with the user input as its description.
     * Else an {@code InvalidCommand} with a corresponding prompt..
     */
    public static Command runDeadline() {
        try {
            String description = userString[INDEX_ENTRY].trim();
            String task = Util.fetchTask(description, Util.DELIMITER_DEADLINE_BY);
            String by = Util.fetchBy(description);
            return new DeadlineCommand(task, Util.parseDateTime(by));
        } catch (IndexOutOfBoundsException | EmptyStringException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_DEADLINE);
        } catch (InvalidDateTimeFormat  | InvalidDate i) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_DEADLINE_DATE);
        }
    }

    /**
     * Collects user input and prepares {@code EventCommand} for execution.
     * This method handles empty user inputs.
     *
     * @return the {@code EventCommand} with the user input as its description.
     * Else an {@code InvalidCommand} with a corresponding prompt.
     */
    public static Command runEvent() {
        try {
            String description = userString[INDEX_ENTRY].trim();
            String task = Util.fetchTask(description, Util.DELIMITER_EVENT_FROM);
            String from = Util.fetchFrom(description);
            String to = Util.fetchTo(description);
            return new EventCommand(task, Util.parseDateTime(from), Util.parseDateTime(to));
        } catch (IndexOutOfBoundsException | EmptyStringException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_EVENT);
        } catch (InvalidDateTimeFormat | InvalidDate | InvalidEventFromAndToDate i) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_EVENT_DATE);
        }
    }

    /**
     * Collects user input and prepares {@code DeleteCommand} for execution.
     * This method handles empty user inputs.
     *
     * @param taskList the task list the target task is in.
     * @return the {@code DeleteCommand} with the user input as its description.
     * Else an {@code InvalidCommand} with a corresponding prompt.
     */
    public static Command runDelete(TaskList taskList) {
        try {
            String description = userString[INDEX_ENTRY].trim();
            int targetIndex = Util.fetchIndexFromString(taskList, description);
            return new DeleteCommand(targetIndex);
        } catch (IndexOutOfBoundsException | EmptyStringException | NumberFormatException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_DELETE);
        }
    }

    /**
     * Collects user input and prepares {@code MarkCommand} for execution.
     * This method handles empty user inputs.
     *
     * @param taskList the task list the target task is in.
     * @return the {@code MarkCommand} with the user input as its description.
     * Else an {@code InvalidCommand} with a corresponding prompt.
     */
    public static Command runMark(TaskList taskList) {
        try {
            String description = userString[INDEX_ENTRY].trim();
            int targetIndex = Util.fetchIndexFromString(taskList, description);
            return new MarkCommand(targetIndex);
        } catch (IndexOutOfBoundsException | EmptyStringException | NumberFormatException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_MARK);
        }
    }

    /**
     * Collects user input and prepares {@code UnMarkCommand} for execution.
     * This method handles empty user inputs.
     *
     * @param taskList the task list the target task is in.
     * @return the {@code UnMarkCommand} with the user input as its description.
     * Else an {@code InvalidCommand} with a corresponding prompt.
     */
    public static Command runUnmark(TaskList taskList) {
        try {
            String description = userString[INDEX_ENTRY].trim();
            int targetIndex = Util.fetchIndexFromString(taskList, description);
            return new UnMarkCommand(targetIndex);
        } catch (IndexOutOfBoundsException | EmptyStringException | NumberFormatException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_UNMARK);
        }
    }

    /**
     * Collects user input as a date and prepares {@code ListWithDateCommand} for execution.
     * If a date is not provided, the method prepares {@code ListDefaultCommand} for execution.
     * This method handles empty user inputs.
     *
     * @return the {@code ListWithDateCommand} with the user input as the date to query.
     * If there is no date provided, {@code ListDefaultCommand} is returned instead.
     */
    public static Command runListWithDate() {
        try {
            String dateString = userString[INDEX_ENTRY].trim();
            LocalDate date = Util.parseDate(dateString);
            return new ListWithDateCommand(date);
        } catch (ArrayIndexOutOfBoundsException | EmptyStringException e) {
            return new ListDefaultCommand();
        } catch (InvalidDateFormat e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_DATE);
        }
    }

    /**
     * Collects user input as a key word and prepares {@code FindCommand} for execution.
     * This method handles empty user inputs.
     *
     * @return the {@code FindCommand} with the user input as the key word to query.
     */
    public static Command runFindCommand() {
        try {
            String keyword = userString[INDEX_ENTRY].trim();
            ExceptionManager.checkEmptyString(keyword);
            return new FindCommand(keyword);
        } catch (ArrayIndexOutOfBoundsException | EmptyStringException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_FIND);
        }
    }

    /**
     * Parses each saved task from the {@code TaskList} data from the storage file and return it.
     *
     * @return the task read from the storage file
     */
    public static Task decodeString(String fileString) {

        String[] fileStrings = fileString.split("//");
        String taskType = fileStrings[DECODED_TASK_TYPE];
        String task = fileStrings[DECODED_TASK];
        String markStatus = fileStrings[DECODED_MARK];

        switch (taskType) {

        case DECODE_TODO:
            Todo todo = new Todo(task);
            if (markStatus.equals(DECODE_MARKED)) {
                todo.setDone();
            }
            return todo;
        case DECODE_DEADLINE:
            String byDate = fileStrings[DECODED_DUE_DATE];
            LocalDateTime by = LocalDateTime.parse(byDate);
            DeadLine deadLine = new DeadLine(task, by);
            if (markStatus.equals(DECODE_MARKED)) {
                deadLine.setDone();
            }
            return deadLine;
        case DECODE_EVENT:
            String fromDate = fileStrings[DECODED_FROM_DATE];
            String toDate = fileStrings[DECODED_TO_DATE];
            LocalDateTime from = LocalDateTime.parse(fromDate);
            LocalDateTime to = LocalDateTime.parse(toDate);
            Event event = new Event(task, from, to);
            if (markStatus.equals(DECODE_MARKED)) {
                event.setDone();
            }
            return event;
        default:
            return null;
        }

    }

}
