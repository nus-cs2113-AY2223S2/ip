package duke.command;

import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.error.ThrowError;
import duke.task.Task;

public class VerifyInput {
    private static final int MINIMUM_TODO_LENGTH = 2;
    private static final int MINIMUM_DEADLINE_LENGTH = 4;
    private static final int MINIMUM_EVENT_LENGTH = 6;
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
            ThrowError.throwError(ErrorTypes.NOT_WITHIN_TASK_COUNT.ERROR_TYPE);
        }
    }

    public static void checkValidTodo(String[] input) throws DukeException {
        if (input.length < MINIMUM_TODO_LENGTH) {
            ThrowError.throwError(ErrorTypes.INVALID_TODO.ERROR_TYPE);
        }
    }

    public static void checkValidDeadline(String input, String[] arrayOfInput) throws DukeException {
        if (!input.contains("/by")) {
            ThrowError.throwError(ErrorTypes.INVALID_DEADLINE_COMMAND.ERROR_TYPE);
        }
        if (arrayOfInput.length < MINIMUM_DEADLINE_LENGTH) {
            ThrowError.throwError(ErrorTypes.INSUFFICIENT_DEADLINE_ARGUMENT.ERROR_TYPE);
        }
    }

    public static void checkValidEvent(String input, String[] arrayOfInput) throws DukeException {
        if ((!input.contains("/from") || !input.contains("/to")) || (input.indexOf("/from") > input.indexOf("/to"))) {
            ThrowError.throwError(ErrorTypes.INVALID_EVENT_COMMAND.ERROR_TYPE);
        }
        if (arrayOfInput.length < MINIMUM_EVENT_LENGTH) {
            ThrowError.throwError(ErrorTypes.INSUFFICIENT_EVENT_ARGUMENT.ERROR_TYPE);
        }
    }
}
