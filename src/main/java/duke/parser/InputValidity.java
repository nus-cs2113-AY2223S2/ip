package duke.parser;

import duke.command.DeleteCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.error.Error;
import duke.task.Task;

public class InputValidity {
    private static final int MINIMUM_TODO_LENGTH = 2;
    private static final int MINIMUM_DEADLINE_LENGTH = 4;
    private static final int MINIMUM_EVENT_LENGTH = 6;
    private static final int VALID_LENGTH_TWO = 2;
    public static void checkTodo(String input) throws DukeException {
        String[] arrayInput = input.split(" ");
        if (arrayInput.length < MINIMUM_TODO_LENGTH) {
            Error.throwError(ErrorTypes.INVALID_TODO);
        }
    }

    public static void checkDeadline(String input) throws DukeException {
        if (!input.contains(" /by ")) {
            Error.throwError(ErrorTypes.INVALID_DEADLINE_COMMAND);
        }
        String[] arrayInput = input.split(" ");
        if (arrayInput.length < MINIMUM_DEADLINE_LENGTH) {
            Error.throwError(ErrorTypes.INSUFFICIENT_DEADLINE_ARGUMENT);
        }
    }

    public static void checkValidEvent(String input) throws DukeException {
        if ((!input.contains(" /from ") || !input.contains(" /to "))
                || (input.indexOf(" /from ") > input.indexOf(" /to "))) {
            Error.throwError(ErrorTypes.INVALID_EVENT_COMMAND);
        }
        String[] arrayInput = input.split(" ");
        if (arrayInput.length < MINIMUM_EVENT_LENGTH) {
            Error.throwError(ErrorTypes.INSUFFICIENT_EVENT_ARGUMENT);
        }
    }
    public static boolean isStringOfInteger(String input) {
        // takes in a string and checks whether the string only contains digits characters
        input = input.trim();
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
            Error.throwError(ErrorTypes.NOT_WITHIN_TASK_COUNT);
        }
    }

    public static void isValid(String[] input, String command) throws DukeException {
        boolean isTwoWordInput = (input.length == VALID_LENGTH_TWO);
        if (!isTwoWordInput || !isStringOfInteger(input[1])) {
            switch (command) {
            case MarkCommand.COMMAND_WORD:
                // user only provided "mark" || user provided "mark <not digit chars>"
                Error.throwError(ErrorTypes.INVALID_MARK_COMMAND);
                break;
            case UnmarkCommand.COMMAND_WORD:
                // user only provided "unmark" || user provided "unmark <not digit chars>"
                Error.throwError(ErrorTypes.INVALID_UNMARK_COMMAND);
                break;
            case DeleteCommand.COMMAND_WORD:
                // user only provided "delete" || user provided "delete <not digit chars>"
                Error.throwError(ErrorTypes.INVALID_DELETE_COMMAND);
                break;
            }
        }
        checkWithinCount(Integer.parseInt(input[1].trim()));
    }
}
