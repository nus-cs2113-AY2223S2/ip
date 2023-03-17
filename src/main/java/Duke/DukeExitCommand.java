package Duke;

public class DukeExitCommand extends DukeCommand {

    public DukeExitCommand() {
        setIsExit();
    }

    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        ui.showExit();
        // TODO: save tasks to file
        // storage.save(tasks);
    }

}
