package commands;

import data.TasksList;
import data.exceptions.SherlockException;
import storage.Storage;
import ui.Ui;

import java.util.HashMap;

public abstract class Command {
    private Ui ui;

    public abstract void execute(TasksList tasksList, Ui ui, Storage storage) throws SherlockException;

    public boolean isExit() {
        return false;
    }

}
