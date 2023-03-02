package command;

import exception.DukeException;
import components.TaskList;
import components.UI;
import components.Storage;

public abstract class Command {
    String[] commandFields;

    public Command(String[] commandFields) {
        this.commandFields = commandFields;
    }

    public Command() {
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
    }

    public boolean isExit() {
        return false;
    }
}
