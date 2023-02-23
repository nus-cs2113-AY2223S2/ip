package duke.command;

import duke.tasks.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Adds a new task to the list.
 */
public class AddTaskCommand extends Command {
    private Task taskObj;

    public AddTaskCommand(Task taskObj) {
        this.taskObj = taskObj;
    }

    @Override
    public void executor(TaskList tasks, UI ui) {
        tasks.add(taskObj);
        ui.printTaskAdded(taskObj.describe(), tasks.size());
    }
}
