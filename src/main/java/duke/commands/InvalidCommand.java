package duke.commands;

import duke.exception.InvalidCommandException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command generated when there is an error in parsing the user inputs.
 */
public class InvalidCommand extends Command{

    /**
     * Constructs a command that displays an error message
     * when there is an error in parsing user inputs.
     *
     * @param taskList The task list that the command is executed on.
     */
    @Override
    public void execute(TaskList taskList) {
        Ui.printInvalidMessage();
    }
}
