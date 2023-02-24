package duke.command;

import duke.data.TaskList;
import duke.filemanager.Storage;
import duke.ui.Ui;

/**
 * This class handles the listing of tasks command
 */
public class ListTasks extends Command {

    /**
     * Executes the command to list task
     *
     * @param tasks   TaskList of tasks currently stored
     * @param storage Handler to read write to json
     * @param ui      Handler to print text to user
     */
    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) {
        ui.printTasks(tasks);
    }
}
