package command;

import storage.TaskStorage;
import task.Task;
import task.TaskList;
import ui.UI;

import java.util.ArrayList;

public class AddTaskCommand extends SaveCommand {
    private final Task task;
    public AddTaskCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, TaskStorage storage, UI ui) {
        tasks.addTask(task);
        ui.printAddTaskSuccess(task, tasks.getTasksCount());
        super.execute(tasks, storage,ui); //save task
    }
}
