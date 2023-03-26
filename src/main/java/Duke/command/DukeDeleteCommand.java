package Duke.command;

import Duke.DukeException;
import Duke.DukeStorage;
import Duke.DukeUi;
import Duke.task.DukeTaskList;

public class DukeDeleteCommand extends DukeCommand {

    private int index;

    public DukeDeleteCommand(String index) throws DukeException {
        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new DukeException("The task number is invalid.");
        }
    }

    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        try {
            tasks.deleteTask(index);
            ui.showDeleteTask();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
