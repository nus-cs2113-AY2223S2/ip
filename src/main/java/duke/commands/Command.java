package duke.commands;
/**
 * The Command interface represents a command object that can be executed.
 */

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public interface Command {
    /**
     * Executes the command.
     * @param tasks The TaskList object containing the list of tasks.
     * @param ui The TextUi object to handle user input and output.
     * @param storage The Storage object to handle reading and writing of task data to a file.
     */
    public void execute(TaskList tasks, TextUi ui, Storage storage);

    /**
     * Returns a boolean value indicating if the command is an exit command.
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit();
}
