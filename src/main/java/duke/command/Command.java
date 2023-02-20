package duke.command;

import duke.data.TaskData;
import duke.exceptions.ListTooLarge;
import duke.filemanager.TaskWriter;

public abstract class Command {
    public abstract void executeCommand(TaskData taskdata);

}
