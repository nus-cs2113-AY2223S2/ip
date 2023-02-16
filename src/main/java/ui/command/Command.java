package ui.command;

import storage.TaskStorageManager;
import task.TaskList;
import ui.UserInterface;

public interface Command {

    void execute(TaskList tasks, TaskStorageManager storage, UserInterface ui);

}
