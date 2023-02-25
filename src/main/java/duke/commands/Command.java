package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public interface Command {
    public void execute(TaskList tasks, TextUi ui, Storage storage);

    public boolean isExit();
}
