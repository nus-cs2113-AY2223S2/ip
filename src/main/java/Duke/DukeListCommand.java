package Duke;

public class DukeListCommand extends DukeCommand {

    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        ui.showList(tasks);
    }

}
