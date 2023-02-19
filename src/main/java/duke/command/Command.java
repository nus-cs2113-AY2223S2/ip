package duke.command;

import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.error.Error;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

public abstract class Command {
    public static void evaluateCommand(String input, TaskList taskList) {
        try {
            String[] arrayOfInput = Parser.parse(input);
            switch (arrayOfInput[0]) {
            case (ListCommand.COMMAND_WORD):
                Ui.showTaskList(taskList);
                break;
            case (HelpCommand.COMMAND_WORD):
                Ui.help();
                break;
            case (MarkCommand.COMMAND_WORD): // if command is "mark <int>"
                MarkCommand.markTask(taskList, arrayOfInput);
                break;
            case (UnmarkCommand.COMMAND_WORD): // if command is "unmark <int>"
                UnmarkCommand.unmarkTask(taskList, arrayOfInput);
                break;
            case (DeleteCommand.COMMAND_WORD): // if command is "delete <int>"
                DeleteCommand.deleteTask(taskList, arrayOfInput);
                break;
            case (AddCommand.COMMAND_TODO):
                AddCommand.manageTodoInput(input, taskList);
                break;
            case (AddCommand.COMMAND_DEADLINE):
                AddCommand.manageDeadlineInput(input, taskList);
                break;
            case (AddCommand.COMMAND_EVENT):
                AddCommand.manageEventInput(input, taskList);
                break;
            default:
                // invalid command
                Error.throwError(ErrorTypes.INVALID_INPUT);
                break;
            }
        } catch (DukeException e) {
            Ui.endLine();
        }
    }
}