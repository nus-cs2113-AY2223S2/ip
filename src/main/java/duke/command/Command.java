package duke.command;

import duke.DukeException;
import duke.ErrorTypes;
import duke.output.Printer;
import duke.output.StandardOutput;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Command {
    private static final int VALID_MARK_LENGTH = 2;
    private static final int VALID_UNMARK_LENGTH = 2;
    private static final int MINIMUM_TODO_LENGTH = 2;
    private static final int MINIMUM_DEADLINE_LENGTH = 4;
    private static final int MINIMUM_EVENT_LENGTH = 6;
    public static void evaluate(String input, Task[] tasks) {
        try {
            String[] arrayOfInput = input.split(" ");
            if (input.equals(CommandWords.LIST.COMMAND)) {
                Printer.listTasks(tasks);
            } else if (isMark(arrayOfInput)) {
                // if command is "mark <int>"
                markTask(tasks, arrayOfInput);
            } else if (isUnmark(arrayOfInput)) {
                // if command is "unmark <int>"
                unmarkTask(tasks, arrayOfInput);
            } else {
                // command is to add task
                decideTaskGroup(input, tasks, arrayOfInput);
            }
        } catch (DukeException e) {
            Printer.endLine();
        }
    }

    public static boolean isMark(String[] input) throws DukeException {
        boolean isTwoWordInput = (input.length == VALID_MARK_LENGTH);
        boolean isFirstWordMark = input[0].equals(CommandWords.MARK.COMMAND);
        if (!isFirstWordMark) {
            return false;
        }
        if (!isTwoWordInput) {
            // user only provided "mark"
            throwError(ErrorTypes.INVALID_MARK_COMMAND.ERROR_TYPE);
        }
        boolean isSecondWordNumber = isStringOfInteger(input[1]);
        if (!isSecondWordNumber) {
            // user provided "mark <non digit chara>"
            throwError(ErrorTypes.INVALID_MARK_COMMAND.ERROR_TYPE);
        }
        checkWithinCount(Integer.parseInt(input[1]));
        return true;
    }

    public static void throwError(int errorNumber) throws DukeException {
        if (errorNumber == ErrorTypes.INVALID_MARK_COMMAND.ERROR_TYPE) {
            Printer.invalidMarkCommand();
        } else if (errorNumber == ErrorTypes.NOT_WITHIN_TASK_COUNT.ERROR_TYPE) {
            Printer.exceedTaskCount();
        } else if (errorNumber == ErrorTypes.INVALID_UNMARK_COMMAND.ERROR_TYPE) {
            Printer.invalidUnmarkCommand();
        } else if (errorNumber == ErrorTypes.INVALID_INPUT.ERROR_TYPE) {
            Printer.invalidInput();
        } else if (errorNumber == ErrorTypes.INVALID_TODO.ERROR_TYPE) {
            Printer.invalidTodo();
        } else if (errorNumber == ErrorTypes.INVALID_DEADLINE_COMMAND.ERROR_TYPE) {
            Printer.invalidDeadline();
        } else if (errorNumber == ErrorTypes.INSUFFICIENT_DEADLINE_ARGUMENT.ERROR_TYPE) {
            Printer.insufficientDeadline();
        } else if (errorNumber == ErrorTypes.INVALID_EVENT_COMMAND.ERROR_TYPE) {
            Printer.invalidEvent();
        } else if (errorNumber == ErrorTypes.INSUFFICIENT_EVENT_ARGUMENT.ERROR_TYPE) {
            Printer.insufficientEvent();
        }
        throw new DukeException();
    }

    public static boolean isStringOfInteger(String input) {
        // takes in a string and checks whether the string only contains digits characters
        char[] inputInArray = input.toCharArray();
        for (char c : inputInArray) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static void checkWithinCount(int inputValue) throws DukeException {
        boolean isOverMaxTasks = (inputValue > Task.getTotalTasks());
        if (isOverMaxTasks) {
            // user provided taskNumber that is > noOfTasks
            throwError(ErrorTypes.NOT_WITHIN_TASK_COUNT.ERROR_TYPE);
        }
    }

    public static boolean isUnmark(String[] input) throws DukeException {
        // if input length (separated by " ") == 2 && first word == "unmark" && second word only contains numbers
        boolean isTwoWordInput = (input.length == VALID_UNMARK_LENGTH);
        boolean isFirstWordUnmark = input[0].equals(CommandWords.UNMARK.COMMAND);
        if (!isFirstWordUnmark) {
            return false;
        }
        if (!isTwoWordInput) {
            // user only provided "unmark"
            throwError(ErrorTypes.INVALID_UNMARK_COMMAND.ERROR_TYPE);
        }
        boolean isSecondWordNumber = isStringOfInteger(input[1]);
        if (!isSecondWordNumber) {
            // user provided "unmark <non digit chara>"
            throwError(ErrorTypes.INVALID_UNMARK_COMMAND.ERROR_TYPE);
        }
        checkWithinCount(Integer.parseInt(input[1]));
        return true;
    }

    public static void markTask(Task[] tasks, String[] arrayOfInput) {
        tasks[Integer.parseInt(arrayOfInput[1]) - 1].markAsDone();
        Printer.markOrUnmark(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
    }

    public static void unmarkTask(Task[] tasks, String[] arrayOfInput) {
        tasks[Integer.parseInt(arrayOfInput[1]) - 1].markAsNotDone();
        Printer.markOrUnmark(tasks, Integer.parseInt(arrayOfInput[1]) - 1);
    }

    public static void decideTaskGroup(String input, Task[] tasks, String[] arrayOfInput) throws DukeException {
        boolean isInputTodo = arrayOfInput[0].equals(CommandWords.TODO.COMMAND);
        boolean isInputDeadline = arrayOfInput[0].equals(CommandWords.DEADLINE.COMMAND);
        boolean isInputEvent = arrayOfInput[0].equals(CommandWords.EVENT.COMMAND);
        if (isInputTodo) {
            String[] todoTaskNameArray = input.split(" ", 2);
            checkValidTodo(todoTaskNameArray);
            addTodoTask(tasks, todoTaskNameArray);
        } else if (isInputDeadline) {
            checkValidDeadline(input, arrayOfInput);
            addDeadlineTask(tasks, input);
        } else if (isInputEvent) {
            checkValidEvent(input, arrayOfInput);
            addEventTask(tasks, input);
        } else {
            // if input doesn't contain any keywords
            throwError(ErrorTypes.INVALID_INPUT.ERROR_TYPE);
        }
        Printer.echoAddTasks(tasks);
        Task.incrementTotalTasks();
    }

    public static void checkValidTodo(String[] input) throws DukeException {
        if (input.length < MINIMUM_TODO_LENGTH) {
            throwError(ErrorTypes.INVALID_TODO.ERROR_TYPE);
        }
    }

    public static void addTodoTask(Task[] tasks, String[] input) {
        tasks[Task.totalTasks] = new Todo(input[1]);
    }

    public static void checkValidDeadline(String input, String[] arrayOfInput) throws DukeException {
        if (!input.contains("/by")) {
            throwError(ErrorTypes.INVALID_DEADLINE_COMMAND.ERROR_TYPE);
        }
        if (arrayOfInput.length < MINIMUM_DEADLINE_LENGTH) {
            throwError(ErrorTypes.INSUFFICIENT_DEADLINE_ARGUMENT.ERROR_TYPE);
        }
    }

    public static void addDeadlineTask(Task[] tasks, String input) {
        String[] commandInformation = input.split(" ", 2);
        String[] taskNameAndDate = commandInformation[1].split("/by", 2);
        tasks[Task.totalTasks] = new Deadline(taskNameAndDate[0].trim(), taskNameAndDate[1].trim());
    }

    public static void checkValidEvent(String input, String[] arrayOfInput) throws DukeException {
        if ((!input.contains("/from") || !input.contains("/to")) || (input.indexOf("/from") > input.indexOf("/to"))) {
            throwError(ErrorTypes.INVALID_EVENT_COMMAND.ERROR_TYPE);
        }
        if (arrayOfInput.length < MINIMUM_EVENT_LENGTH) {
            throwError(ErrorTypes.INSUFFICIENT_EVENT_ARGUMENT.ERROR_TYPE);
        }
    }

    public static void addEventTask(Task[] tasks, String input) {
        String taskNameInformation = input.split(" ", 2)[1];
        String[] taskNameAndDate = taskNameInformation.split("/from", 2); // name fromTo
        String[] fromAndTo = taskNameAndDate[1].split("/to", 2); // from to
        tasks[Task.totalTasks] = new Event(taskNameAndDate[0].trim(), fromAndTo[0].trim(), fromAndTo[1].trim());
    }
}