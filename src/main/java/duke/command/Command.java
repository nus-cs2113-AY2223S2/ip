package duke.command;

import duke.data.TaskData;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;

public abstract class Command {

    public abstract void executeCommand(TaskData taskdata, Storage storage) throws DukeException;

}
