package duke.command;

import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.error.ThrowError;

public class CheckTodo {
    private static final int MINIMUM_TODO_LENGTH = 2;
    public static void checkValidTodo(String[] input) throws DukeException {
        if (input.length < MINIMUM_TODO_LENGTH) {
            ThrowError.throwError(ErrorTypes.INVALID_TODO.ERROR_TYPE);
        }
    }
}
