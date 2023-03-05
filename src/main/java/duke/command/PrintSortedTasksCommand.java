package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.ui.Ui;

/**
 * Prints the list of sorted task according to due date.
 */
public class PrintSortedTasksCommand extends Command {

    /**
     * Executes the command to print the sorted tasks.
     *
     * @param tasks   TaskList of tasks currently stored.
     * @param storage Handler to read write to json.
     * @param ui      Handler to print text to user.
     * @throws DukeException
     */
    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ui.printSorted(storage);
    }
}
