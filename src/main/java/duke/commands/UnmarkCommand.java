package duke.commands;

import duke.tasks.TaskList;

public class UnmarkCommand extends Command {
    private final int index;
    private final TaskList taskList;

    public UnmarkCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    @Override
    public void handleCommand() {
        taskList.unmarkTask(index);
    }
}
