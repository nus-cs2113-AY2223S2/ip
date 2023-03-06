package duke.error;

import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.DeleteCommand;
import duke.command.DateCommand;
import duke.command.InvalidCommand;
import duke.ui.ErrorMessages;
import duke.ui.Ui;

/**
 * Feedback to user the relevant error message and throws error
 */
public class Error {

    /**
     * Shows error message to user and throws DukeException
     *
     * @param e type of error to be shown to user
     * @throws DukeException thrown after displaying error message
     */
    public static void throwError(ErrorTypes e) throws DukeException {
        switch (e) {
        case INVALID_MARK_COMMAND:
            Ui.showError(MarkCommand.INVALID_COMMAND_MESSAGE);
            break;
        case INVALID_UNMARK_COMMAND:
            Ui.showError(UnmarkCommand.INVALID_COMMAND_MESSAGE);
            break;
        case INVALID_TODO:
            Ui.showError(TodoCommand.INVALID_COMMAND_MESSAGE);
            break;
        case INVALID_DEADLINE_COMMAND:
            Ui.showError(DeadlineCommand.MISSING_KEYWORD_MESSAGE);
            break;
        case INSUFFICIENT_DEADLINE_ARGUMENT:
            Ui.showError(DeadlineCommand.INSUFFICIENT_FIELD_MESSAGE);
            break;
        case INVALID_EVENT_COMMAND:
            Ui.showError(EventCommand.INVALID_FORMAT_MESSAGE);
            break;
        case INSUFFICIENT_EVENT_ARGUMENT:
            Ui.showError(EventCommand.INSUFFICIENT_FIELD_MESSAGE);
            break;
        case INVALID_DELETE_COMMAND:
            Ui.showError(DeleteCommand.INVALID_COMMAND_MESSAGE);
            break;
        case ERROR_WITH_DIRECTORY:
            Ui.showError(ErrorMessages.ERROR_IN_SETTING_UP.MESSAGE);
            break;
        case INVALID_FIND_COMMAND:
            Ui.showError(ErrorMessages.INVALID_FIND_MESSAGE.MESSAGE);
            break;
        case INVALID_DATE:
            Ui.showError(DateCommand.INVALID_COMMAND_MESSAGE);
            break;
        default:
            Ui.showError(InvalidCommand.MESSAGE);
            break;
        }
        throw new DukeException();
    }
}
