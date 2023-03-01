package king.commands;

import king.tasks.TaskList;

public class DeleteCommand extends Command {
    private final int index;
    private final TaskList taskList;

    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public void handleCommand() {
        taskList.deleteTask(index);
    }
}
