package commands;

import storage.Storage;
import Task.Task;
import Task.Todo;
import TaskManager.TaskManager;
import UI.TextUI;

public class AddTodoCommand implements Handler {
    private String arguments;
    public static final String COMMAND_WORD = "todo";

    public AddTodoCommand(String arguments) {
        this.arguments = arguments;
    }


    public void execute(TaskManager tasks, TextUI ui, Storage storage) {
        if (!arguments.isBlank()) {
            Task task = new Todo(arguments);
            tasks.addTask(task);
            ui.showNewTaskMessage(tasks, task);
        } else {
            ui.showPromptEmptyErrorMessage(COMMAND_WORD);
        }
    }

    public boolean isExit() {
        return false;
    }
}