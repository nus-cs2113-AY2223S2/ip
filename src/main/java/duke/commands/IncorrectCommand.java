package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class IncorrectCommand implements Command {
    /**
     * Executes the InocrretCommand if the argument is not found
     * *
     *
     * @param tasks   The TaskList object containing the list of tasks.
     * @param ui      The TextUi object to handle user input and output.
     * @param storage The Storage object to handle reading and writing of task data to a file.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.showCommandNotFoundError();

    }

    /**
     * Returns a boolean value indicating if the command is an exit command.
     *
     * @return false since this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
