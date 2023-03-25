package commands;

import storage.Storage;
import TaskManager.TaskManager;
import UI.TextUI;

public class GoodbyeCommand implements Handler {
    public static final String COMMAND_WORD = "bye";

    public void execute(TaskManager tasks, TextUI ui, Storage storage) {
        ui.showGoodbye();
    }

    public boolean isExit() {
        return true;
    }
}