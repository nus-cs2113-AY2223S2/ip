package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public Command() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.writeFile(tasks.getTasks());
    }

    public boolean isExit() {
        return false;
    }
}
