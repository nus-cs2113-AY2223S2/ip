package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

/**
 * The ListCommand class represents a command to display all task.
 */
public class ListCommand implements Command {
    public static final String COMMAND_WORD = "list";

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }

    /**
     * Returns a boolean value indicating if the command is an exit command.
     *
     * @return false since this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
