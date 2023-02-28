package parser;

import commands.Handler;
import storage.Storage;
import TaskManager.TaskManager;
import UI.TextUI;

public class IncorrectCommand implements Handler {

    @Override
    public void execute(TaskManager tasks, TextUI ui, Storage storage) {
        ui.showCommandNotFoundError();

    }

    @Override
    public boolean isExit() {
        return false;
    }

}