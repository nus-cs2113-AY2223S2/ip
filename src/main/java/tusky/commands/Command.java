package tusky.commands;

import tusky.storage.Storage;
import tusky.tasks.TaskList;
import tusky.ui.Ui;

public abstract class Command {

    private final boolean isExit;

    Command(boolean isExit) {
        this.isExit = isExit;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }

}
