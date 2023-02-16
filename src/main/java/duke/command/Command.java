package duke.command;

import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.error.Error;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.task.Task;

import java.util.ArrayList;

public class Command {
    public static void evaluate(String input, ArrayList<Task> tasks) {
        try {
            String[] arrayOfInput = Parser.parse(input);
            switch (arrayOfInput[0]) {
            case (ListCommand.COMMAND_WORD):
                Ui.listTasks(tasks);
                break;
            case (HelpCommand.COMMAND_WORD):
                Ui.help();
                break;
            case (MarkCommand.COMMAND_WORD): // if command is "mark <int>"
                MarkCommand.markTask(tasks, arrayOfInput);
                break;
            case (UnmarkCommand.COMMAND_WORD): // if command is "unmark <int>"
                UnmarkCommand.unmarkTask(tasks, arrayOfInput);
                break;
            case (DeleteCommand.COMMAND_WORD): // if command is "delete <int>"
                DeleteCommand.deleteTask(tasks, arrayOfInput);
                break;
            case (AddCommand.COMMAND_TODO):
                AddCommand.manageTodoInput(input, tasks);
                break;
            case (AddCommand.COMMAND_DEADLINE):
                AddCommand.manageDeadlineInput(input, tasks);
                break;
            case (AddCommand.COMMAND_EVENT):
                AddCommand.manageEventInput(input, tasks);
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