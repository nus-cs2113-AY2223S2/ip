package duke.parser;

import duke.commands.Command;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class IncorrectCommand implements Command {

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.showCommandNotFoundError();

    }

    @Override
    public boolean isExit() {
        return false;
    }

}
