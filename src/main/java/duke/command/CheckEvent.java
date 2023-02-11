package duke.command;

import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.error.ThrowError;

public class CheckEvent {
    private static final int MINIMUM_EVENT_LENGTH = 6;
    public static void checkValidEvent(String input, String[] arrayOfInput) throws DukeException {
        if ((!input.contains("/from") || !input.contains("/to")) || (input.indexOf("/from") > input.indexOf("/to"))) {
            ThrowError.throwError(ErrorTypes.INVALID_EVENT_COMMAND.ERROR_TYPE);
        }
        if (arrayOfInput.length < MINIMUM_EVENT_LENGTH) {
            ThrowError.throwError(ErrorTypes.INSUFFICIENT_EVENT_ARGUMENT.ERROR_TYPE);
        }
    }
}
