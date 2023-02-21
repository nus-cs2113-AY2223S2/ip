package inu.parser;

import inu.commands.*;
import inu.commons.Messages;
import inu.exceptionhandling.EmptyTaskListException;
import inu.exceptionhandling.EmptyUserInputException;
import inu.exceptionhandling.ExceptionManager;
import inu.task.DeadLine;
import inu.task.Event;
import inu.task.Task;
import inu.task.Todo;
import inu.task.TaskList;
import inu.commons.Util;

import java.util.Scanner;


public class Parser {

    public static Scanner input = new Scanner(System.in);

    public static String[] userString;

    public static String command;

    public static String entry;

    public static final int INDEX_COMMAND = 0;

    public static final int INDEX_ENTRY = 1;

    public static final int USER_STRING_SPLIT_LIMIT = 2;

    protected static final int DECODED_TASK_TYPE = 0;

    protected static final int DECODED_MARK = 1;

    protected static final int DECODED_TASK = 2;

    protected static final int DECODED_DUE_DATE = 3;

    protected static final int DECODED_FROM_DATE = 3;

    protected static final int DECODED_TO_DATE = 4;

    protected static final String DECODE_TODO = "T";

    protected static final String DECODE_DEADLINE = "D";

    protected static final String DECODE_EVENT = "E";

    protected static final String DECODE_MARKED = "X";

    public static String[] readCommand() {
        String userInput;
        String[] userString;
        userInput = input.nextLine();
        userString = userInput.split(" ", USER_STRING_SPLIT_LIMIT);
        return userString;
    }

    public static Command parseCommand(TaskList taskList) {

            userString = readCommand();
            command = userString[INDEX_COMMAND];

            switch (command) {

            case TodoCommand.COMMAND_WORD:

                try {
                    entry = userString[INDEX_ENTRY];
                    return runTodo(entry);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_STRING_INPUT);
                }

            case DeadlineCommand.COMMAND_WORD:

                try {
                    entry = userString[INDEX_ENTRY];
                    return runDeadLine(entry);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_STRING_INPUT);
                }

            case EventCommand.COMMAND_WORD:

                try {
                    entry = userString[INDEX_ENTRY];
                    return runEvent(entry);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_STRING_INPUT);
                }

            case DeleteCommand.COMMAND_WORD:

                try {
                    entry = userString[INDEX_ENTRY];
                    return runDelete(taskList, entry);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_INTEGER_INPUT);
                }

            case ListCommand.COMMAND_WORD:

                return runList(taskList);

            case MarkCommand.COMMAND_WORD:

                try {
                    entry = userString[INDEX_ENTRY];
                    return runMark(taskList, entry);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_INTEGER_INPUT);
                }

            case UnMarkCommand.COMMAND_WORD:

                try {
                    entry = userString[INDEX_ENTRY];
                    return runUnMark(taskList, entry);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_INTEGER_INPUT);
                }

            case ExitCommand.COMMAND_WORD:

                return new ExitCommand();

            default:

                return new InvalidCommand(Messages.MESSAGE_INVALID);

            }

    }

    public static Command runTodo(String entry) {
        try {
            ExceptionManager.checkEmptyUserInput(entry);
            return new TodoCommand(entry);
        } catch (EmptyUserInputException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_TASK_ENTRY);
        }
    }

    public static Command runDeadLine(String entry) {
        try {
            String task = Util.fetchTask(entry);
            String by = Util.fetchBy(entry);
            ExceptionManager.checkEmptyUserInput(task, by);
            return new DeadlineCommand(task, by);
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_DEADLINE);
        } catch (EmptyUserInputException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_BY_DATE_ENTRY_AFTER_SLASH);
        }
    }

    public static Command runEvent(String entry) {
        try {
            String task = Util.fetchTask(entry);
            String from = Util.fetchFrom(entry);
            String to = Util.fetchTo(entry);
            ExceptionManager.checkEmptyUserInput(task, from, to);
            return new EventCommand(task, from, to);
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_EVENT);
        } catch (EmptyUserInputException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_FROM_AND_TO_ENTRY_AFTER_SLASH);
        }
    }

    public static Command runDelete(TaskList taskList, String entry) {
        try {
            ExceptionManager.checkEmptyUserInput(entry);
            int targetIndex = Util.fetchIndexFromString(taskList, entry);
            return new DeleteCommand(targetIndex);
        } catch (EmptyUserInputException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_DELETE_ENTRY);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_TASK_INDEX);
        }
    }

    public static Command runMark(TaskList taskList, String entry) {
        try {
            ExceptionManager.checkEmptyUserInput(entry);
            int targetIndex = Util.fetchIndexFromString(taskList, entry);
            return new MarkCommand(targetIndex);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_TASK_INDEX);
        } catch (EmptyUserInputException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_MARK_ENTRY);
        }
    }

    public static Command runUnMark(TaskList taskList, String entry) {
        try {
            ExceptionManager.checkEmptyUserInput(entry);
            int targetIndex = Util.fetchIndexFromString(taskList, entry);
            return new UnMarkCommand(targetIndex);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_TASK_INDEX);
        } catch (EmptyUserInputException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_VALID_UN_MARK_ENTRY);
        }
    }

    public static Command runList(TaskList taskList) {
        try {
            ExceptionManager.checkEmptyTaskList(taskList);
            return new ListCommand();
        } catch (EmptyTaskListException e) {
            return new InvalidCommand(Messages.MESSAGE_PROMPT_EMPTY_TASK_LIST);
        }
    }

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

            String dueDate = fileStrings[DECODED_DUE_DATE];
            DeadLine deadLine = new DeadLine(task, dueDate);
            if (markStatus.equals(DECODE_MARKED)) {
                deadLine.setDone();
            }
            return deadLine;

        case DECODE_EVENT:

            String fromDate = fileStrings[DECODED_FROM_DATE];
            String toDate = fileStrings[DECODED_TO_DATE];
            Event event = new Event(task, fromDate, toDate);
            if (markStatus.equals(DECODE_MARKED)) {
                event.setDone();
            }
            return event;

        default:
            return null;
        }

    }

}
