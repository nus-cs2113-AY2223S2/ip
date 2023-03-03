package duke.command;

import duke.tasks.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;


/**
 * Deletes the task ID from the list.
 */
public class DeleteCommand extends Command {
    private final int deleteId;

    public DeleteCommand(int deleteId) {
        this.deleteId = deleteId;
    }

    @Override
    public void executor(TaskList tasks, UI ui) throws Exception {
        Task toDelete = tasks.delete(deleteId);
        ui.printTaskDeleted(toDelete.describeTask(), tasks.getSize());
    }
}
