package duke.commands;

import duke.tasks.TaskList;

public class MarkCommand extends Command {
    private final int index;
    private final TaskList taskList;

    public MarkCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    @Override
    public void handleCommand() {
        taskList.markTask(index);
    }
}
