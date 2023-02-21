package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.ui.Ui;

public class PrintSortedTasks extends Command {

    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ui.printSorted(storage);
    }
}
