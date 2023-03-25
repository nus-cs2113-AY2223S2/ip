package commands;

import parser.Parser;
import storage.Storage;
import Task.Task;
import TaskManager.TaskManager;
import UI.TextUI;

public class DeleteCommand implements Handler {
    public static final String COMMAND_WORD = "delete";
    private String arguments;

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    public void execute(TaskManager tasks, TextUI ui, Storage storage) {
        Parser parser = new Parser();
        int indexTask = parser.parseIndex(arguments);
        try {
            if (indexTask != -1) {
                Task deletedTask = tasks.deleteTask(indexTask);
                ;
                ui.showDeleteTaskMessage(tasks, deletedTask);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showTaskIndexOutOfBoundsError();
        }
    }

    public boolean isExit() {
        return false;
    }
}