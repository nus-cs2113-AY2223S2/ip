package app.commands;

import app.exceptions.DukeException;
import app.save.Storage;
import app.ui.Ui;
import app.tasks.TaskList;
public abstract class Command {
    private boolean isExit;

    public Command(){
        setIsExit(false);
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void setIsExit(boolean exit) {
        this.isExit = exit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
