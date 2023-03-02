package duke.command;

import duke.ui.Ui;
import duke.task.TaskList;
import duke.task.Tasks;

/**
 * <code>DeleteCommand</code> object represents a command that executes
 * the deletion of a Task object from the TaskList based on its index
 */
public class DeleteCommand extends Command {
    protected String item;

    public DeleteCommand(String item) {
        this.item = item;
    }

    public void execute() {
        Tasks toDelete = TaskList.getTaskList().get(Integer.parseInt(item) - 1);
        Ui.displayDelete(toDelete);
        TaskList.deleteFromList(Integer.parseInt(item) - 1);
    }
}
