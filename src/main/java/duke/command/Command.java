package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.ui.Ui;

public abstract class Command {

    public abstract void executeCommand(TaskList tasks, Storage storage, Ui ui) throws DukeException;

}
