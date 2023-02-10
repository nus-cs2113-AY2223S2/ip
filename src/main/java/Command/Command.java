package Command;

import Entities.TaskList;
import Exceptions.DukeException;
import FileUtils.Storage;
import Output.UI;

public abstract class Command {
    private boolean isExit;

    public Command() {
        setIsExit(false);
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;
}
