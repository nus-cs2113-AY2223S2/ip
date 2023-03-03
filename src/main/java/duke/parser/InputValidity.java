package duke.parser;

import duke.command.DeleteCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.AddCommand;
import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.error.Error;

/** Checks input validity */
public class InputValidity {

    private static final int MINIMUM_TODO_LENGTH = 2;

    private static final int MINIMUM_DEADLINE_LENGTH = 4;

    private static final int MINIMUM_EVENT_LENGTH = 6;

    private static final int VALID_LENGTH_TWO = 2;

    protected static final String DEADLINE_DELIMITER = " /by ";

    protected static final String EVENT_FROM_DELIMITER = " /from ";

    protected static final String EVENT_TO_DELIMITER = " /to ";

    /**
     * Checks whether the input given by user is a valid todo command
     *
     * @param input input given by the user
     * @throws DukeException when the input provided by user does not have sufficient parameters
     */
    protected static void checkTodo(String input) throws DukeException {
        String[] arrayOfInput = input.split(" ");
        if (arrayOfInput.length < MINIMUM_TODO_LENGTH) {
            Error.throwError(ErrorTypes.INVALID_TODO);
        }
    }

    /**
     * Checks whether the input given by user is a valid deadline command
     *
     * @param input input given by the user
     * @throws DukeException when the input provided by user is of incorrect format or does not have
     *         sufficient parameters
     */
    protected static void checkDeadline(String input) throws DukeException {
        if (!input.contains(DEADLINE_DELIMITER)) {
            Error.throwError(ErrorTypes.INVALID_DEADLINE_COMMAND);
        }
        String[] arrayInput = input.split(" ");
        if (arrayInput.length < MINIMUM_DEADLINE_LENGTH) {
            Error.throwError(ErrorTypes.INSUFFICIENT_DEADLINE_ARGUMENT);
        }
    }

    /**
     * Checks whether the input given by user is a valid event command
     *
     * @param input input given by the user
     * @throws DukeException when the input provided by user is of incorrect format or does not have
     *         sufficient parameters
     */
    protected static void checkValidEvent(String input) throws DukeException {
        if ((!input.contains(EVENT_FROM_DELIMITER) || !input.contains(EVENT_TO_DELIMITER))
                || (input.indexOf(EVENT_FROM_DELIMITER) > input.indexOf(EVENT_TO_DELIMITER))) {
            Error.throwError(ErrorTypes.INVALID_EVENT_COMMAND);
        }
        String[] arrayInput = input.split(" ");
        if (arrayInput.length < MINIMUM_EVENT_LENGTH) {
            Error.throwError(ErrorTypes.INSUFFICIENT_EVENT_ARGUMENT);
        }
    }

    /**
     * Checks whether a given string only contains digit characters
     *
     * @param input input given by the user
     * @return true if input only contains digit characters, false otherwise
     */
    private static boolean isStringOfInteger(String input) {
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

    /**
     * Checks whether the input provided by user for mark/unmark/delete is valid
     *
     * @param input an array of the user input, separated by " "
     * @param command command word provided by user: mark/unmark/delete
     * @throws DukeException when insufficient parameters are provided or when wrong parameter format is given
     */
     protected static void checkValid(String[] input, String command) throws DukeException {
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
    }

    /**
     * Checks whether the input provided by user for find command is valid
     *
     * @param input input given by the user
     * @throws DukeException when the find command is invalid
     */
    protected static void checkValidFind(String input) throws DukeException {
        String[] arrayOfInput = input.split(" ");
        boolean isAtLeastTwoWord = (arrayOfInput.length >= VALID_LENGTH_TWO);
        if (!isAtLeastTwoWord) {
            Error.throwError(ErrorTypes.INVALID_FIND_COMMAND);
        }
    }

    /**
     * Only called during parsing of deadline and event command, to check whether a task name is present
     *
     * @param input input provided by user, without the deadline or event word
     * @param command command of the input, "deadline" or "event"
     * @throws DukeException when task name is absent
     */
    protected static void checkTaskName(String input, String command) throws DukeException {
        String[] inputArray = input.split(" ");
        ErrorTypes error;
        if (command.equals(AddCommand.COMMAND_DEADLINE) && inputArray[0].equals(DEADLINE_DELIMITER.trim())) {
            error = ErrorTypes.INVALID_DEADLINE_COMMAND;
        } else if (command.equals(AddCommand.COMMAND_EVENT) && inputArray[0].equals(EVENT_FROM_DELIMITER.trim())) {
            error = ErrorTypes.INVALID_EVENT_COMMAND;
        } else {
            return;
        }
        Error.throwError(error);
    }
}
