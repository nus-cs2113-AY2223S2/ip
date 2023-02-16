package ui.command;

import storage.TaskStorageManager;
import task.TaskList;
import ui.UserInterface;

public class FindCommand implements Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, TaskStorageManager storage, UserInterface ui) {
        TaskList filteredTasks = tasks.findDescription(keyword);
        ui.printTasks(filteredTasks);
    }
}
