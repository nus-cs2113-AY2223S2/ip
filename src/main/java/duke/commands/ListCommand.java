package duke.commands;

import duke.tasks.TaskList;

public class ListCommand extends Command {
    private final TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void handleCommand() {
        taskList.printTasks();
    }
}
