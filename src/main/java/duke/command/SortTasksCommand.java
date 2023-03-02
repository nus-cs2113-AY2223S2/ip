package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.ui.Ui;

/**
 * Sorts the tasks according to due date and writes to memory.
 */
public class SortTasksCommand extends Command {

    /**
     * Executes the command to sort and save the tasks.
     *
     * @param tasks   TaskList of tasks currently stored.
     * @param storage Handler to read write to json.
     * @param ui      Handler to print text to user.
     * @throws DukeException
     */
    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ui.sortTasks(tasks, storage);
    }
}
