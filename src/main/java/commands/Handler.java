package commands;

import TaskManager.TaskManager;
import storage.Storage;
import UI.TextUI;

public interface Handler {
    public void execute(TaskManager taskManager, TextUI ui, Storage storage);
    public boolean isExit();
}