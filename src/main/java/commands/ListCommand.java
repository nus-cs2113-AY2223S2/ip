package commands;

import storage.Storage;
import TaskManager.TaskManager;
import UI.TextUI;

public class ListCommand implements Handler {
    public static final String COMMAND_WORD = "list";

    public void execute(TaskManager tasks, TextUI ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }

    public boolean isExit() {
        return false;
    }
}
