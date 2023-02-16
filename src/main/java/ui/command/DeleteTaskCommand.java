package ui.command;

import storage.TaskStorageManager;
import task.Task;
import task.TaskList;
import ui.UserInterface;

public class DeleteTaskCommand extends SaveTaskCommand {

    private final int taskId;

    public DeleteTaskCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, TaskStorageManager storage, UserInterface ui) {
        Task removedTask = tasks.removeTask(taskId);

        ui.printRemovedTask(removedTask);
        ui.printTaskCount(tasks);

        super.execute(tasks, storage, ui);
    }
}
