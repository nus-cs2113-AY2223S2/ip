package duke.command;

import duke.ui.Ui;
import duke.task.TaskList;
import duke.task.Tasks;

/**
 * <code>UnmarkCommand</code> object represents a command that executes
 * the un-marking of tasks in the TaskList
 */
public class UnmarkCommand extends Command {
    String item;

    public UnmarkCommand(String item) {
        this.item = item;
    }

    @Override
    public void execute() {
        Tasks markTask = TaskList.getTaskList().get(Integer.parseInt(item) - 1);
        markTask.setMarked(false);
        Ui.displayUnmark(markTask);
    }
}
