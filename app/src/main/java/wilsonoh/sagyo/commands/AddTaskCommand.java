package wilsonoh.sagyo.commands;

import java.util.ArrayList;

import wilsonoh.sagyo.tasks.Task;

public class AddTaskCommand extends Command {

    private final ArrayList<Task> taskList;
    private final Task toAdd;

    public AddTaskCommand(ArrayList<Task> taskList, Task toAdd) {
        this.taskList = taskList;
        this.toAdd = toAdd;
    }

    @Override
    public String[] getCommandMessage() {
        return new String[] {String.format("added %s: %s", toAdd.getTaskType(), toAdd)};
    }

    @Override
    public void executeCommand() {
        taskList.add(toAdd);
    }
}
