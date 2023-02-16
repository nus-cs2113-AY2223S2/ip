package ui.command;

import storage.TaskStorageManager;
import task.Task;
import task.TaskList;
import ui.UserInterface;

public class AddTaskCommand extends SaveTaskCommand {

    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }
    
    @Override
    public void execute(TaskList tasks, TaskStorageManager storage, UserInterface ui) {
        tasks.addTask(task);

        ui.printAddedTask(task);
        ui.printTaskCount(tasks);

        super.execute(tasks, storage, ui);
    }
}
