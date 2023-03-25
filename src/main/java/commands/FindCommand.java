package commands;

import storage.Storage;
import TaskManager.TaskManager;
import UI.TextUI;

public class FindCommand implements Handler{
    public static final String COMMAND_WORD = "find";
    private String arguments;

    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

    public void execute(TaskManager tasks, TextUI ui, Storage storage) {
        ui.showTaskListByKeyword(tasks.getTasks(), arguments);
    }

    public boolean isExit() {
        return false;
    }
}