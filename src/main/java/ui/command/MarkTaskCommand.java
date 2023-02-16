package ui.command;

import storage.TaskStorageManager;
import task.Task;
import task.TaskList;
import ui.UserInterface;

public class MarkTaskCommand extends SaveTaskCommand {

    private final int taskId;
    private final boolean isDone;

    public MarkTaskCommand(int taskId, boolean isDone) {
        this.taskId = taskId;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, TaskStorageManager storage, UserInterface ui) {
        Task task = tasks.getTask(taskId);
        task.setIsDone(isDone);

        if (isDone) {
            ui.printMarkedTask(task);
        } else {
            ui.printUnmarkedTask(task);
        }

        super.execute(tasks, storage, ui);
    }
}
