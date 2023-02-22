package duke.command;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;

public class AddTaskCommand extends Command {
    private final Task taskObj;

    public AddTaskCommand(Task taskObj) {
        this.taskObj = taskObj;
    }

    @Override
    public void executor(TaskList tasks, UI ui) {
        tasks.add(taskObj);
        ui.printTaskAdded(taskObj.describe(), tasks.size());
    }
}
