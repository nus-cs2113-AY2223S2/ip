package commands;

import parser.Parser;
import storage.Storage;
import Task.Task;
import TaskManager.TaskManager;
import UI.TextUI;

public class MarkCommand implements Handler {
    public static final String COMMAND_WORD = "mark";
    private String arguments;

    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    public void execute(TaskManager tasks, TextUI ui, Storage storage) {
        Parser parser = new Parser();
        int indexTask = parser.parseIndex(arguments);
        try {
            if (indexTask != -1) {
                Task selectedTask = tasks.getTaskByIndex(indexTask);
                selectedTask.setMark(true);
                ui.showTaskStatusMessage(selectedTask);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showTaskIndexOutOfBoundsError();
        }
    }

    public boolean isExit() {
        return false;
    }
}