package duke.command;

import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.error.ThrowError;

public class CheckDelete {
    private static final int VALID_DELETE_LENGTH = 2;
    public static boolean isDelete(String[] input) throws DukeException {
        boolean isTwoWordInput = (input.length == VALID_DELETE_LENGTH);
        boolean isFirstWordDelete = input[0].equals(CommandWords.DELETE.COMMAND);
        if (!isFirstWordDelete) {
            return false;
        }
        if (!isTwoWordInput) {
            // user only provided "delete"
            ThrowError.throwError(ErrorTypes.INVALID_DELETE_COMMAND.ERROR_TYPE);
        }
        boolean isSecondWordNumber = VerifyInput.isStringOfInteger(input[1]);
        if (!isSecondWordNumber) {
            // user provided "delete <non digit chara>"
            ThrowError.throwError(ErrorTypes.INVALID_DELETE_COMMAND.ERROR_TYPE);
        }
        VerifyInput.checkWithinCount(Integer.parseInt(input[1]));
        return true;
    }
}
