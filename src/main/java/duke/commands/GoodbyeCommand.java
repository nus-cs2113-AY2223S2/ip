package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class GoodbyeCommand implements Command {
    public static final String COMMAND_WORD = "bye";

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns a boolean value indicating if the command is an exit command.
     *
     * @return true since this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
