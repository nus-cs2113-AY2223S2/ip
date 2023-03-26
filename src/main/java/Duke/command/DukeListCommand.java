package Duke.command;

import Duke.DukeStorage;
import Duke.DukeUi;
import Duke.task.DukeTaskList;

public class DukeListCommand extends DukeCommand {

    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        ui.showList(tasks);
    }

}
