package ui.command;

import storage.TaskStorageManager;
import task.TaskList;
import ui.UserInterface;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList tasks, TaskStorageManager storage, UserInterface ui) {
        ui.printTasks(tasks);
    }
}
