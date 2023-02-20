package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListTasks extends Command {

    @Override
    public void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        ui.printTasks(tasks);
    }
}
