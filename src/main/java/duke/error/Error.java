package duke.error;

import duke.ui.Ui;

public class Error {
    public static void throwError(ErrorTypes e) throws DukeException {
        switch (e) {
        case INVALID_MARK_COMMAND:
            Ui.invalidMarkCommand();
            break;
        case INVALID_UNMARK_COMMAND:
            Ui.invalidUnmarkCommand();
            break;
        case NOT_WITHIN_TASK_COUNT:
            Ui.exceedTaskCount();
            break;
        case INVALID_TODO:
            Ui.invalidTodo();
            break;
        case INVALID_DEADLINE_COMMAND:
            Ui.invalidDeadline();
            break;
        case INSUFFICIENT_DEADLINE_ARGUMENT:
            Ui.insufficientDeadline();
            break;
        case INVALID_EVENT_COMMAND:
            Ui.invalidEvent();
            break;
        case INSUFFICIENT_EVENT_ARGUMENT:
            Ui.insufficientEvent();
            break;
        case INVALID_DELETE_COMMAND:
            Ui.invalidDeleteCommand();
            break;
        default:
            Ui.invalidInput();
            break;
        }
        throw new DukeException();
    }
}
