package duke.error;

import duke.output.Printer;

public class ThrowError {
    public static void throwError(int errorNumber) throws DukeException {
        if (errorNumber == ErrorTypes.INVALID_MARK_COMMAND.ERROR_TYPE) {
            Printer.invalidMarkCommand();
        } else if (errorNumber == ErrorTypes.NOT_WITHIN_TASK_COUNT.ERROR_TYPE) {
            Printer.exceedTaskCount();
        } else if (errorNumber == ErrorTypes.INVALID_UNMARK_COMMAND.ERROR_TYPE) {
            Printer.invalidUnmarkCommand();
        } else if (errorNumber == ErrorTypes.INVALID_INPUT.ERROR_TYPE) {
            Printer.invalidInput();
        } else if (errorNumber == ErrorTypes.INVALID_TODO.ERROR_TYPE) {
            Printer.invalidTodo();
        } else if (errorNumber == ErrorTypes.INVALID_DEADLINE_COMMAND.ERROR_TYPE) {
            Printer.invalidDeadline();
        } else if (errorNumber == ErrorTypes.INSUFFICIENT_DEADLINE_ARGUMENT.ERROR_TYPE) {
            Printer.insufficientDeadline();
        } else if (errorNumber == ErrorTypes.INVALID_EVENT_COMMAND.ERROR_TYPE) {
            Printer.invalidEvent();
        } else if (errorNumber == ErrorTypes.INSUFFICIENT_EVENT_ARGUMENT.ERROR_TYPE) {
            Printer.insufficientEvent();
        }
        throw new DukeException();
    }
}
