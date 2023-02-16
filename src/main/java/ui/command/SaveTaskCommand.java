package ui.command;

import java.io.IOException;
import storage.TaskStorageManager;
import task.TaskList;
import ui.UserInterface;

public class SaveTaskCommand implements Command {

    @Override
    public void execute(TaskList tasks, TaskStorageManager storage, UserInterface ui) {
        try {
            storage.saveTasks(tasks);
        } catch (IOException ex) {
            ui.printSaveFailure(ex);
        }
    }
}
