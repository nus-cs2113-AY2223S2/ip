package duke.command;

import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.error.ThrowError;
import duke.task.Task;

public class CheckMarkUnmark {
    private static final int VALID_MARK_LENGTH = 2;
    private static final int VALID_UNMARK_LENGTH = 2;
    public static boolean isMark(String[] input) throws DukeException {
        boolean isTwoWordInput = (input.length == VALID_MARK_LENGTH);
        boolean isFirstWordMark = input[0].equals(CommandWords.MARK.COMMAND);
        if (!isFirstWordMark) {
            return false;
        }
        if (!isTwoWordInput) {
            // user only provided "mark"
            ThrowError.throwError(ErrorTypes.INVALID_MARK_COMMAND.ERROR_TYPE);
        }
        boolean isSecondWordNumber = isStringOfInteger(input[1]);
        if (!isSecondWordNumber) {
            // user provided "mark <non digit chara>"
            ThrowError.throwError(ErrorTypes.INVALID_MARK_COMMAND.ERROR_TYPE);
        }
        checkWithinCount(Integer.parseInt(input[1]));
        return true;
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
            ThrowError.throwError(ErrorTypes.NOT_WITHIN_TASK_COUNT.ERROR_TYPE);
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
            ThrowError.throwError(ErrorTypes.INVALID_UNMARK_COMMAND.ERROR_TYPE);
        }
        boolean isSecondWordNumber = isStringOfInteger(input[1]);
        if (!isSecondWordNumber) {
            // user provided "unmark <non digit chara>"
            ThrowError.throwError(ErrorTypes.INVALID_UNMARK_COMMAND.ERROR_TYPE);
        }
        checkWithinCount(Integer.parseInt(input[1]));
        return true;
    }
}
