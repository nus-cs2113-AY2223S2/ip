package duke.command;

import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.error.ThrowError;

public class CheckDeadline {
    private static final int MINIMUM_DEADLINE_LENGTH = 4;
    public static void checkValidDeadline(String input, String[] arrayOfInput) throws DukeException {
        if (!input.contains("/by")) {
            ThrowError.throwError(ErrorTypes.INVALID_DEADLINE_COMMAND.ERROR_TYPE);
        }
        if (arrayOfInput.length < MINIMUM_DEADLINE_LENGTH) {
            ThrowError.throwError(ErrorTypes.INSUFFICIENT_DEADLINE_ARGUMENT.ERROR_TYPE);
        }
    }
}
