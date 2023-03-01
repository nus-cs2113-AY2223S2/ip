package king.commands;

import king.tasks.Task;
import king.tasks.TaskList;

public class AddCommand extends Command {
    private final Task toAdd;
    private final TaskList taskList;

    public AddCommand(Task task, TaskList taskList) {
        this.toAdd = task;
        this.taskList = taskList;
    }

    @Override
    public void handleCommand() {
        taskList.addTask(toAdd);
    }
}
