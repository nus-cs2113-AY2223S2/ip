package duke.error;

import duke.command.DeleteCommand;
import duke.ui.ErrorMessages;
import duke.ui.Ui;

public class Error {
    public static void throwError(ErrorTypes e) throws DukeException {
        switch (e) {
        case INVALID_MARK_COMMAND:
            Ui.showError(ErrorMessages.INVALID_MARK_MESSAGE.MESSAGE);
            break;
        case INVALID_UNMARK_COMMAND:
            Ui.showError(ErrorMessages.INVALID_UNMARK_MESSAGE.MESSAGE);
            break;
        case NOT_WITHIN_TASK_COUNT:
            // Ui.showError(ErrorMessages.OVER_TASK_COUNT_MESSAGE.STANDARD_OUTPUT); todo
            Ui.showExceedTask();
            break;
        case INVALID_TODO:
            Ui.showError(ErrorMessages.INVALID_TODO_MESSAGE.MESSAGE);
            break;
        case INVALID_DEADLINE_COMMAND:
            Ui.showError(ErrorMessages.MISSING_DEADLINE_KEYWORD_MESSAGE.MESSAGE);
            break;
        case INSUFFICIENT_DEADLINE_ARGUMENT:
            Ui.showError(ErrorMessages.INSUFFICIENT_DEADLINE_FIELD_MESSAGE.MESSAGE);
            break;
        case INVALID_EVENT_COMMAND:
            Ui.showError(ErrorMessages.INVALID_EVENT_FORMAT_MESSAGE.MESSAGE);
            break;
        case INSUFFICIENT_EVENT_ARGUMENT:
            Ui.showError(ErrorMessages.INSUFFICIENT_EVENT_FIELD_MESSAGE.MESSAGE);
            break;
        case INVALID_DELETE_COMMAND:
            Ui.showError(DeleteCommand.INVALID_COMMAND_MESSAGE);
            break;
        case ERROR_WITH_DIRECTORY:
            Ui.showError(ErrorMessages.ERROR_IN_SETTING_UP.MESSAGE);
            break;
        default:
            Ui.showError(ErrorMessages.INVALID_INPUT_MESSAGE.MESSAGE);
            break;
        }
        throw new DukeException();
    }
}
