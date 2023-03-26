package Duke.command;

import Duke.DukeStorage;
import Duke.DukeUi;
import Duke.task.DukeTaskList;

public class DukeExitCommand extends DukeCommand {

    public DukeExitCommand() {
        setIsExit();
    }

    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        ui.showExit();
        storage.save(tasks);
    }

}
