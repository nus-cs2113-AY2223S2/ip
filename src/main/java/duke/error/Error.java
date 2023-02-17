package duke.error;

import duke.ui.Ui;

public class Error {
    public static void throwError(ErrorTypes e) throws DukeException {
        switch (e) {
        case INVALID_MARK_COMMAND:
            Ui.showInvalidMark();
            break;
        case INVALID_UNMARK_COMMAND:
            Ui.showInvalidUnmark();
            break;
        case NOT_WITHIN_TASK_COUNT:
            Ui.showExceedTask();
            break;
        case INVALID_TODO:
            Ui.showInvalidTodo();
            break;
        case INVALID_DEADLINE_COMMAND:
            Ui.showInvalidDeadline();
            break;
        case INSUFFICIENT_DEADLINE_ARGUMENT:
            Ui.showInsufficientDeadline();
            break;
        case INVALID_EVENT_COMMAND:
            Ui.showInvalidEvent();
            break;
        case INSUFFICIENT_EVENT_ARGUMENT:
            Ui.showInsufficientEvent();
            break;
        case INVALID_DELETE_COMMAND:
            Ui.showInvalidDelete();
            break;
        default:
            Ui.showInvalidInput();
            break;
        }
        throw new DukeException();
    }
}
