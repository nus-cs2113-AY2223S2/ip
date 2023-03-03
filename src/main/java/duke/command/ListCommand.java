package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    /**
     * Initialises an instance of ListCommand.
     */
    public ListCommand () {

    }

    /**
     * Executes list task command.
     * Displays task list array on the CLI.
     *
     * @param tasks Instance of task list containing task array list.
     * @param ui Instance of ui.
     * @param storage Instance of storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTask(tasks.getTasks());
    }
}
