package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.ui.Ui;

/**
 * Handles the various user commands.
 */
public abstract class Command {
    /**
     * Abstract method to execute the commands.
     *
     * @param tasks   TaskList of tasks currently stored.
     * @param storage Handler to read write to json.
     * @param ui      Handler to print text to user.
     * @throws DukeException
     */

    public abstract void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException;

}
