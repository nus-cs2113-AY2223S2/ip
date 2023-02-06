package wilsonoh.sagyo.commands;

import wilsonoh.sagyo.tasklist.TaskList;
import wilsonoh.sagyo.tasks.Task;

public class AddTaskCommand extends Command {

    private final TaskList taskList;
    private final Task toAdd;

    public AddTaskCommand(TaskList taskList, Task toAdd) {
        this.taskList = taskList;
        this.toAdd = toAdd;
    }

    @Override
    public String[] getCommandMessage() {
        return new String[] {String.format("added %s: %s", toAdd.getTaskType(), toAdd)};
    }

    @Override
    public void executeCommand() {
        taskList.addTask(toAdd);
    }
}
