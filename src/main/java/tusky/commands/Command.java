package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.ui.Ui;

/**
 * Abstract class that represents a command to be executed
 */
public abstract class Command {

    private final boolean isExit;

    Command(boolean isExit) {
        this.isExit = isExit;
    }


    /**
     * Executes the command implemented by the subclass
     * @param tasks The TaskList to be used for commands
     * @param ui The Ui class for interacting with the user
     * @param storage The Storage class for interacting with the file
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }
}
